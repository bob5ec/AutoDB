package bz.asd.autodb.logic;

import bz.asd.autodb.data.Database;
import bz.asd.autodb.data.Settings;
import bz.asd.autodb.data.db4o.Db4oDatabase;
import bz.asd.autodb.gui.ExceptionDialog;
import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.MainWindow;
import bz.asd.autodb.gui.SubWindow;
import bz.asd.autodb.gui.ViewPlaceholder;
import bz.asd.autodb.gui.YesNoDialog;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindowController extends Controller {

    private static final String ERROR_FILEEXTENSION = "Ungültieger Dateinamenerweiterung. "
            +"Datei wurde nicht geöffnet.\nGültige Dateinamenerweiterungen sind \".db4o\"";

    private DbViewController dbvc;
    private List<DbViewController> subwindowController; //TODO implement multi dbViews

    public MainWindowController() throws Exception {
        dbvc = null;
        subwindowController = new LinkedList<DbViewController>();
        init();
    }
    
    /*public DbViewController getDbViewController() {
        return subwindowController.get(0);
    }*/

    /*public JPanel getDbView() {
        JPanel res;
        if(dbvc == null) {
            res = dbViewPlaceholder;
        } else {
            res = dbvc.getView();
        }
        return res;
    }*/

    /**
     * User want's to open the Db.
     * 1. show Filechooser
     * 2. open the chosen Db
     */
	public void open() {
        JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        /*FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);*/
        int returnVal = fileChooser.showOpenDialog(getView());
        if(returnVal != JFileChooser.APPROVE_OPTION) return;
        // only continue on ok-Button

        File file = fileChooser.getSelectedFile();

        // switch use database depanding on file extension
        // Server db has an file which contains server,user,pwd,tablename

        try {
            open(file);
        } catch (Exception ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            handleException("Datenbank "+file.getName()+ " konnte nicht geöffnet werden.", ex);
        }
    }

    /**
     * Open a file with the korresponding db engine.
     * @param file
     * @throws java.lang.Exception
     */
    private void open(File file) throws Exception {
        //IMPROVE Multi Db support
        if(dbvc != null) {
            handleUserError("Momentan kann nur eine Datei gleichzeitig geöffnet werden.\n"
                +"In weiteren Versionen kann dieses Feature bei Bedarf ergänzt werden.");
            return;
        }
        
        String fileName = file.getAbsolutePath();
        int extensionSplit = fileName.lastIndexOf(".")+1;
        if(extensionSplit == -1) {
            // no valid filename
            handleUserError(ERROR_FILEEXTENSION);
            return;
        }
        String extension = fileName.substring(extensionSplit);

        // construct absolute fileName
        //fileName = file.getAbsolutePath() + fileName;

        Database db = null;
        if("db4o".equals(extension)) {
            db = new Db4oDatabase(fileName);
        } else {
            handleUserError(ERROR_FILEEXTENSION);
            return;
        }

        db.open();
        DbViewController newDbvc = new DbViewController(db);
        newDbvc.setParentFrame(getView());
        if (dbvc != null) {
            // open another Window
            SubWindow sub = new SubWindow();
            sub.addComponent(newDbvc.getView());
            sub.addCloseListener(newDbvc);
            subwindowController.add(newDbvc);
            sub.setVisible(true);
        } else {
            // open DbView in MainWindow
            dbvc = newDbvc;
            getView().setDbView(dbvc.getView());
            getView().setDbViewController(newDbvc);
            getView().setDbMenuEnabled(true);
        }
    }

    /**
     * User want's to close the (main) Db.
     * 1. check if all Items of the Db are saved
     * 2. prompt user for saving or loosing unsaved changes
     * 3. close db
     */
    public boolean close() {
        boolean res;
        /* IMPROVE multiple DbViews
        for(DbViewController subWindow : subwindowController) {
            if(! subWindow.isCloseOk()) return; // do not exit
        }

        for(DbViewController subWindow : subwindowController) {
            subWindow.close();
        }*/
        // todo checks have to be done in other controller
        if(dbvc != null && dbvc.isCloseOk()) {
            dbvc.close();
            dbvc = null;
            getView().setDbView(new ViewPlaceholder(400,300));
            getView().setDbViewController(null);
            getView().setDbMenuEnabled(false);
            res = true;
        } else res = dbvc == null;

        return res;
    }

    /**
     * User want's to create a new db.
     * 1. schow filechooser to select the location and name of the new file
     * 2. create the db
     */
    public void newDb() {
        JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        /*FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);*/
        int returnVal = fileChooser.showOpenDialog(getView());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // delete existing file or abord
            if(file.exists()) {
                int answer = askUserYesNo("Eine Datei mit gleichem Namen existiert bereits.\n"
                        +"Datei überschreiben?");
                if(answer == YesNoDialog.RET_YES) {
                    if(!file.delete()) handleUserError("Die bestehende Datei kann nicht gelöscht werden.");
                } else return;
            }

            try {
                open(file);
            } catch (Exception ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                handleException("Datei "+file.getName()+ " konnte nicht angelegt werden.", ex);
            }

            System.out.println("You chose to open this file: " +
            fileChooser.getSelectedFile().getName());
        }

    }

    /**
     * User want's to save all changes to db.
     * just save all changed items
     */
    public void save() {
        if(dbvc != null) dbvc.save();
    }

    /**
     * User want's to leave this program.
     * 1. show save|discard changes|cancle dialog
     * 2. react on result
     */
    public void exit() {
        if(close()) {
            try {
                //int[] order = new int[1];
                //order[0] = bz.asd.autodb.data.Model.DRUCK;
                //Settings.getInstance().getTreeViewSettings().setOrder(order);
                //Settings.getInstance().getTreeViewSettings().setGroupLevel(1);
                Settings.getInstance().save();
                System.exit(0);
            } catch (IOException ex) {
                handleException("Die Einstellungen konnten nicht gespeichert werden.", ex);
                // TODO "Wollen sie trozdem das Programm beenden?" einfügen
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
	}

    /* Controller implementation */
    
    @Override
    protected View createView() {
        MainWindow mw = new MainWindow(this, new ViewPlaceholder(400,300));
        mw.setVisible(true);
        return mw;
    }

    @Override
    protected Model createModel() {
        return Settings.getInstance().getUserSession();
    }

    @Override
    protected MainWindow getView() {
        return (MainWindow)view;
    }

    @Override
    protected Model getModel() {
        return model;
    }
}
