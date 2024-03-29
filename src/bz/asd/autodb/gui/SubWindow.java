/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SubWindow.java
 *
 * Created on 06.02.2009, 07:32:51
 */

package bz.asd.autodb.gui;

import bz.asd.autodb.logic.CloseListener;
import bz.asd.autodb.logic.CloseObservable;
import java.util.LinkedList;
import javax.swing.JComponent;

/**
 *
 * @author lars
 */
public class SubWindow extends javax.swing.JFrame {

    CloseObservable closeObservable;
    
    public SubWindow() {
        closeObservable = new CloseObservable();
        initComponents();
    }

    public void addCloseListener(CloseListener close) {
        closeObservable.addCloseListener(close);
    }

    public void addComponent(JComponent component) {
        getContentPane().add(component, java.awt.BorderLayout.CENTER);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (closeObservable.closeEvent()) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
