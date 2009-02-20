package bz.asd.autodb.logic;

import bz.asd.autodb.data.Database;
import bz.asd.autodb.data.Settings;
import bz.asd.autodb.data.db4o.Db4oDatabase;
import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.MainWindow;
import bz.asd.autodb.gui.SubWindow;
import java.util.Collection;
import java.util.LinkedList;

public class MainWindowController extends Controller {

    private DbViewController dbvc;
    private Collection<DbViewController> subwindowController;

    public MainWindowController() {
        dbvc = null;
        subwindowController = new LinkedList<DbViewController>();
    }
    
    public DbViewController getDbViewController() {
        return dbvc;
    }

    /**
     * User want's to open the Db.
     * 1. show Filechooser
     * 2. open the chosen Db
     */
	public void open() {


        //todo open file chooser

        String dbName = "test";
        // switch use database depanding on file extension
        // Server db has an file which contains server,user,pwd,tablename

        Database db = new Db4oDatabase(dbName);
        DbViewController newDbvc = new DbViewController(db);
        newDbvc.setParentFrame(getView());

        if(dbvc != null) {
            SubWindow sub = new SubWindow();
            sub.addComponent(newDbvc.getView());
            sub.addCloseListener(newDbvc);
            subwindowController.add(newDbvc);
            sub.setVisible(true);
        } else {
            dbvc = newDbvc;
        }
        
    }

    /**
     * User want's to close the Db.
     * 1. check if all Items of the Db are saved
     * 2. prompt user for saving or loosing unsaved changes
     * 3. close db
     */
    public void close() {
        // todo checks have to be done in other controller
        if(dbvc != null && dbvc.isCloseOk()) {
            dbvc.close();
        }
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
		System.exit(0);
	}

    /* Controller implementation */
    
    @Override
    protected View createView() {
        MainWindow mw = new MainWindow();
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
