package bz.asd.mvc;

import bz.asd.autodb.gui.ExceptionDialog;
import bz.asd.autodb.gui.YesNoDialog;
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

    protected void init() throws Exception {
        model = createModel();

        view = createView();
        view.setModel(model);
        view.setController(this);
    }

    protected abstract View createView() throws Exception;
    protected abstract Model createModel() throws Exception;

    public void handleException(String userError, Throwable e) {
        ExceptionDialog dialog = new ExceptionDialog(parentFrame);
        dialog.setException(e);
        dialog.setUserError(userError);
        dialog.setVisible(true);
        e.printStackTrace();
    }

    public void handleUserError(String userError) {
        ExceptionDialog dialog = new ExceptionDialog(parentFrame);
        dialog.setUserError(userError);
        dialog.setVisible(true);
    }

    public int askUserYesNo(String userQuestion) {
        YesNoDialog dialog = new YesNoDialog(parentFrame);
        dialog.setUserQuestion(userQuestion);
        dialog.showYesNoDialog();
        return dialog.getReturnStatus();
    }

    public int askUserYesNoCancle(String userQuestion) {
        YesNoDialog dialog = new YesNoDialog(parentFrame);
        dialog.setUserQuestion(userQuestion);
        dialog.setCancelVisible(true);
        dialog.showYesNoDialog();
        return dialog.getReturnStatus();
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
