/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bz.asd.autodb.gui;

import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lars
 */
public class ViewContainer extends JPanel implements View {

    public ViewContainer(int width, int hight) {
        setMinimumSize(new Dimension(width, hight));
    }

    public void setView(Component view) {
        removeAll();
        add(view);
        validate();
    }

    public void setModel(Model model) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("container has no model");
    }

    public void setController(Controller controller) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("container has no controller");
    }

}
