/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bz.asd.autodb.gui;

import bz.asd.autodb.logic.TreeViewController;
import javax.swing.JTree;

/**
 *
 * @author lars
 */
public class GroupTreeView extends JTree {
    private TreeViewController controller;

    public GroupTreeView(TreeViewController controller) {
        this.controller = controller;
    }

    public String convertValueToText(Object value,
                                 boolean selected,
                                 boolean expanded,
                                 boolean leaf,
                                 int row,
                                 boolean hasFocus) {
        //TODO nullpoiter
        if(controller == null) return "";
        return controller.getModelString(value);
    }

    public void setController(TreeViewController controller) {
        this.controller = controller;
    }

}
