/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DbView.java
 *
 * Created on 26.01.2009, 19:40:24
 */

package bz.asd.autodb.gui;

import bz.asd.autodb.data.Database;
import bz.asd.autodb.logic.DbViewController;
import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lars
 */
public class DbView extends javax.swing.JPanel implements View {

    private DbViewController controller;
    private Database db;

    /** Creates new form DbView */
    public DbView(DbViewController controller, TreeView treeView, Container modelView) {
        this.controller = controller;
        this.treeView = treeView;
        this.viewContainer = (ViewContainer)modelView;
        initComponents();
    }

    public void setModel(Model model) {
        db = (Database) model;
    }

    public void setController(Controller controller) {
        this.controller = (DbViewController) controller;
    }

    public void setTreeView(TreeView treeView) {
        jSplitPane1.setLeftComponent(treeView);
        jSplitPane1.validate();
    }

    /*public static void main(String[] args) {
        new DbView();
    }*/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        treeView = treeView;
        viewContainer = viewContainer;

        setAutoscrolls(true);
        setMinimumSize(new java.awt.Dimension(500, 300));
        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(3);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(400, 300));
        jSplitPane1.setLeftComponent(treeView);

        viewContainer.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setRightComponent(viewContainer);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private bz.asd.autodb.gui.TreeView treeView;
    private bz.asd.autodb.gui.ViewContainer viewContainer;
    // End of variables declaration//GEN-END:variables


}
