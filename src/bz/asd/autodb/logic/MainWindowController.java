package bz.asd.autodb.logic;

import bz.asd.autodb.data.Database;
import bz.asd.autodb.data.Settings;
import bz.asd.autodb.data.db4o.Db4oDatabase;
import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.MainWindow;
import bz.asd.autodb.gui.SubWindow;
import bz.asd.autodb.gui.ViewPlaceholder;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainWindowController extends Controller {

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
        //todo open file chooser
        String dbName = "test.db4o";
        // switch use database depanding on file extension
        // Server db has an file which contains server,user,pwd,tablename

        try {
            Database db = new Db4oDatabase(dbName);
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
        } catch (Exception ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            handleException("Datenbank "+dbName+ " konnte nicht geöffnet werden.", ex);
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
            res = true;
        } else res = dbvc == null;

        return res;
    }

    /**
     * User want's to create a new db.
     * 1. schow filechooser to select the location and name of the ne file
     * 2. create the db
     */
    public void newDb() {
        
    }

    /**
     * User want's to save all changes to db.
     * just save all changed items
     */
    public void save() {
        
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
