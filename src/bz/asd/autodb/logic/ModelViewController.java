/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bz.asd.autodb.logic;

import bz.asd.mvc.Controller;
import bz.asd.autodb.data.Model;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.ModelView;

/**
 *
 * @author lars
 */
public class ModelViewController extends Controller {

    @Override
    protected View createView() throws Exception {
        return new ModelView();
    }

    @Override
    protected bz.asd.mvc.Model createModel() throws Exception {
        // TODO save last state of database and jump to the viewed model
        // default is no data
        return null;
    }

    @Override
    public ModelView getView() {
        return (ModelView) view;
    }
    
    @Override
    public Model getModel() {
        return (Model) model;
    }

    public void setModel(Model model) {
        this.model = model;
        getView().setModel(model);
        // observer for the TreeViewController
        //TODO notify the view
    }

}
