package bz.asd.mvc;

import java.awt.Frame;

/** This class defines a generic Controller of the MVC pattern.
 * Events rised by the view should be handled in seperate methods, which have
 * to be implemented.
 *
 * @link http://en.wikipedia.org/wiki/Model-view-controller
 * @author lars
 */
public abstract class Controller {
    protected View view;
    protected Model model;
    protected Frame parentFrame;

    public Controller() {
        view = createView();
        model = createModel();

        view.setModel(model);
        view.setController(this);
    }

    protected abstract View createView();
    protected abstract Model createModel();

    public void handleException(String userError, Throwable e) {
        e.printStackTrace();
    }

    //TODO View needs to initializes AFTER Model and Controller has been set?

    /* Getter and Setter */
    protected Model getModel() {
        return model;
    }

    protected void setModel(Model model) {
        this.model = model;
    }

    protected View getView() {
        return view;
    }

    protected void setView(View view) {
        this.view = view;
    }

    /*public Frame getParentFrame() {
        return parentFrame;
    }*/

    public void setParentFrame(Frame parentFrame) {
        this.parentFrame = parentFrame;
    }


}
