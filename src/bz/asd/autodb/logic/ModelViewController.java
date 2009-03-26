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

/**
 *
 * @author lars
 */
public class ModelViewController extends Controller {

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
        //DEBUGreturn new ViewContainer(400, 300);
        return new TestPanel();
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

public void setModel(Model model) {
        if(model.equals(this.model)) return;

        this.model = model;
        if(displayPlaceholder == true) {
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
            //    .setModel(model);
        // observer for the TreeViewController
    }

}
