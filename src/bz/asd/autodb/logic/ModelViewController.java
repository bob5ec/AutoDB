/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bz.asd.autodb.logic;

import bz.asd.mvc.Controller;
import bz.asd.autodb.data.Model;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.ModelView;
import bz.asd.autodb.gui.TestPanel;
import bz.asd.autodb.gui.ViewContainer;
import bz.asd.autodb.gui.ViewPlaceholder;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import bz.asd.autodb.data.CollectionChangeListener;
import bz.asd.autodb.data.Settings;
import bz.asd.autodb.data.UserSession;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author lars
 */
public class ModelViewController extends Controller implements CollectionChangeListener<Model> {

    private ModelView modelView;
    private boolean displayPlaceholder;

    public ModelViewController() {
        try {
            displayPlaceholder = true;
            init();
        } catch (Exception ex) {
            handleException("Unerwarteter Fehler", ex);
            Logger.getLogger(ModelViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected View createView() throws Exception {
        //TODO @see createModel
        return new ViewContainer(400, 300);
    }

    @Override
    protected bz.asd.mvc.Model createModel() throws Exception {
        // TODO load last state of database and jump to the viewed model
        // default is no data
        return null;
    }

    @Override
    public ViewContainer getView() {
        return (ViewContainer)view;
    }
    
    @Override
    public Model getModel() {
        return (Model) model;
    }

    /**
     * Set the Model to display.
     * @param model the model to display. If <i>null</i> a placeholder is used
     */
    public void setModel(Model model) {
        if(model == this.model
                || (model != null && model.equals(this.model))) return;

        this.model = model;

        if(model == null) {
            displayPlaceholder = true;
            getView().removeView();
        } else if(displayPlaceholder == true) {
            displayPlaceholder = false;
            
            if(modelView == null) {
                // init real View
                modelView = new ModelView();
                modelView.setModel(model);
                modelView.setController(this);
            } else {
                // or just reuse it
                modelView.setModel(model);
            }

            // and make it visible
            getView().setView(modelView);
        } else {
            modelView.setModel(model);
        }
        
        // observer for the TreeViewController
    }

    public void chooseImageFile() {
        UserSession session = Settings.getInstance().getUserSession();
        JFileChooser fileChooser = new javax.swing.JFileChooser(session.getLastImagePath());
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        /*FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);*/
        int returnVal = fileChooser.showOpenDialog(getView());
        if(returnVal != JFileChooser.APPROVE_OPTION) return;
        // only continue on ok-Button

        File selectedFile = fileChooser.getSelectedFile();
        session.setLastImagePath(selectedFile.getParent());

        modelView.getModel().setBilddatei(selectedFile.getAbsolutePath());
        modelView.repaint();
    }

    /*CollectionChangeListener implementation */

    public void added(Model element) {
        modelView.focus();
    }

    public void deleted(Model element) {
        setModel(null);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void changed(Model element) {
    }

}
