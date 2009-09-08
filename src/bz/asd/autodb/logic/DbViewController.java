package bz.asd.autodb.logic;

import bz.asd.autodb.data.Database;
import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.DbView;
import bz.asd.autodb.gui.YesNoDialog;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author larsModel
 */
public class DbViewController extends Controller implements CloseListener {

    private TreeViewController tvc;
    private ModelViewController mvc;

    public DbViewController(Database db) throws Exception {
        model = db;
        init();
    }

    /**
     * User wants to add a new Model to the displayed Db
     */
    public void addModel() {
        try {
            getModel().createModel(); //Displayes are notified via Database
        } catch (Exception ex) {
            handleException("Fehler beim Anlegen des Modells.", ex);
            Logger.getLogger(DbViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * User wants to delete the current displayed Model
     */
    public void deleteModel() {
        try {
            getModel().deleteModel(mvc.getModel()); //Displayes are notified via Database
        } catch (Exception ex) {
            handleException("Fehler beim Anlegen des Modells.", ex);
            Logger.getLogger(DbViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected View createView() throws Exception {
        mvc = new ModelViewController();
        mvc.setParentFrame(parentFrame);
        getModel().addDbContentListener(mvc);

        tvc = new TreeViewController(getModel(), mvc);
        tvc.setParentFrame(parentFrame);

        DbView dbView = new DbView(this, tvc.getView(), mvc.getView());
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
        boolean ok = false;
        if(getModel().hasChanged()) {
            //todo prompt user for saving or loosing unsaved changes
            //ok = okCancle.getResult()
            int answer = askUserYesNoCancle("Sollen die Änderungen gespeichert werden?");
            ok = answer != YesNoDialog.RET_CANCLE;

            if(answer == YesNoDialog.RET_YES) {
                try {
                    getModel().save();
                } catch (Exception ex) {
                    ok = false;
                    handleException("Fehler beim speichern der Daten.", ex);
                    Logger.getLogger(DbViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

    public void save() {
        try {
            getModel().save();
        } catch (Exception ex) {
            handleException("Fehler beim speichern der Datenbank.", ex);
            Logger.getLogger(DbViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
