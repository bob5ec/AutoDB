/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bz.asd.mvc;

import javax.swing.JComponent;

/**
 * @link http://en.wikipedia.org/wiki/Model-view-controller
 * @author lars
 */
public interface View {
    public void setModel(Model model);

    //TODO If more then one Controller per View should be used extend this one.
    // (Give mor inteligence to the view, let it route the msgs to the controller)
    /**
     * Register the Controller as an observer for events.
     * @param controller
     */
    public void setController(Controller controller);
}
