/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DbViewPlaceholder.java
 *
 * Created on 04.03.2009, 07:14:13
 */

package bz.asd.autodb.gui;

import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;

/**
 *
 * @author lars
 */
public class ViewPlaceholder extends javax.swing.JPanel implements View {

    /** Creates new form DbViewPlaceholder */
    public ViewPlaceholder(int sizeX, int sizeY) {
        initComponents();
        setMinimumSize(new java.awt.Dimension(sizeX, sizeY));
    }

    public void setModel(Model model) {
        System.out.println("placeholder has no model");
    }

    public void setController(Controller controller) {
        System.out.println("placeholder has no controller");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
