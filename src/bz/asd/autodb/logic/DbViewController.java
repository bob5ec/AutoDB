package bz.asd.autodb.logic;

import bz.asd.autodb.data.Database;
import bz.asd.autodb.data.GroupTree;
import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.DbView;
import bz.asd.autodb.gui.TreeView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author larsModel
 */
public class DbViewController extends Controller implements CloseListener {

    private TreeViewController tvc;

    public DbViewController(Database db) throws Exception {
        model = db;

        tvc = new TreeViewController(getModel().getModels());
        tvc.setParentFrame(parentFrame);
        getView().setTreeView(tvc.getView());
    }

    /*public TreeViewController getTreeViewController() {
        if (tvc == null) {
            
        }
        return tvc;
    }*/

    /*public TreeView getTreeView() {
        return tvc.getView();
    }*/

    @Override
    protected View createView() {
        DbView dbView = new DbView(this);
        return dbView;
    }

    @Override
    protected Model createModel() {
        return model;
    }

    @Override
    protected Database getModel() {
        return (Database) model;
    }

    @Override
    protected DbView getView() {
        return (DbView) view;
    }

    /* CloseListener implementation - called from outside the DbView */
    /**
     * 1. check if all Items of the Db are saved
     * 2. prompt user for saving or loosing unsaved changes
     * @return
     */
    public boolean isCloseOk() {
        boolean ok;
        if(getModel().hasChanged()) {
            //todo prompt user for saving or loosing unsaved changes

            try {
                getModel().save();
            } catch (Exception ex) {
                handleException("Fehler beim speichern der Daten.", ex);
                Logger.getLogger(DbViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ok = true;
        } else {
            ok = true;
        }
        return ok;
    }

    public void close() {
        try {
            getModel().close();
        } catch (Exception ex) {
            handleException("Fehler beim schließen der Datenbank.", ex);
            Logger.getLogger(DbViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
