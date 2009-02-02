package bz.asd.autodb.logic;

import bz.asd.autodb.data.Settings;
import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.MainWindow;

public class MainWindowController extends Controller {

    /**
     * User want's to open the Db.
     * 1. show Filechooser
     * 2. open the chosen Db
     */
	public void open() {
        
    }

    /**
     * User want's to close the Db.
     * 1. check if all Items of the Db are saved
     * 2. prompt user for saving or loosing unsaved changes
     * 3. close db
     */
    public void close() {
        
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
