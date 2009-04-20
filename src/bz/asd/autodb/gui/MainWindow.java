/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainView.java
 *
 * Created on 26.01.2009, 07:12:12
 */

package bz.asd.autodb.gui;

import bz.asd.autodb.logic.MainWindowController;
import bz.asd.autodb.data.UserSession;
import bz.asd.autodb.logic.DbViewController;
import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import java.awt.MenuComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author lars
 */
public class MainWindow extends javax.swing.JFrame implements View {

    MainWindowController controller;
    DbViewController dbvc;
    UserSession userSession;

    public MainWindow(MainWindowController controller, JPanel dbView) {
        this.controller = controller;
        this.dbView = dbView;
        initComponents();
        initAccelerators();
    }

    public void setModel(Model model) {
        userSession = (UserSession)model;
    }

    public void setController(Controller controller) {
        this.controller = (MainWindowController) controller;
    }

    public void setDbViewController(DbViewController controller) {
        dbvc = controller;
    }

    public void setDbView(JPanel dbView) {
        getContentPane().remove(this.dbView);
        this.dbView = dbView;
        getContentPane().add(dbView, java.awt.BorderLayout.CENTER);
        getContentPane().validate();
        //repaint();
        //getContentPane().validate();
        //pack();
    }

    public void setDbMenuEnabled(boolean enabled) {
        modellMenu.setEnabled(enabled);
    }

    private void initAccelerators() {
        dateiOeffnenMenuItem.getAccelerator();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dbView = this.dbView;
        jMenuBar1 = new javax.swing.JMenuBar();
        dateiMenu = new javax.swing.JMenu();
        dateiNeuMenuItem = new javax.swing.JMenuItem();
        dateiOeffnenMenuItem = new javax.swing.JMenuItem();
        schliessenMenuItem = new javax.swing.JMenuItem();
        speichernMenuItem = new javax.swing.JMenuItem();
        modellMenu = new javax.swing.JMenu();
        modellNeuMenuItem = new javax.swing.JMenuItem();
        loeschenMenuItem = new javax.swing.JMenuItem();
        kopierenMenuItem = new javax.swing.JMenuItem();
        einfuegenMenuItem = new javax.swing.JMenuItem();
        optionenMenu = new javax.swing.JMenu();
        sortierungMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        getContentPane().add(dbView, java.awt.BorderLayout.CENTER);

        dateiMenu.setText("Datei");

        dateiNeuMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        dateiNeuMenuItem.setText("Neu");
        dateiNeuMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateiNeuMenuItemActionPerformed(evt);
            }
        });
        dateiMenu.add(dateiNeuMenuItem);

        dateiOeffnenMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        dateiOeffnenMenuItem.setText("Öffnen");
        dateiOeffnenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateiOeffnenMenuItemActionPerformed(evt);
            }
        });
        dateiMenu.add(dateiOeffnenMenuItem);

        schliessenMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK));
        schliessenMenuItem.setText("Schließen");
        schliessenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schliessenMenuItemActionPerformed(evt);
            }
        });
        dateiMenu.add(schliessenMenuItem);

        speichernMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        speichernMenuItem.setText("Speichern");
        speichernMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speichernMenuItemActionPerformed(evt);
            }
        });
        dateiMenu.add(speichernMenuItem);

        jMenuBar1.add(dateiMenu);

        modellMenu.setText("Modell");
        modellMenu.setEnabled(false);

        modellNeuMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        modellNeuMenuItem.setText("Neu");
        modellNeuMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modellNeuMenuItemActionPerformed(evt);
            }
        });
        modellMenu.add(modellNeuMenuItem);

        loeschenMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK));
        loeschenMenuItem.setText("Löschen");
        loeschenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loeschenMenuItemActionPerformed(evt);
            }
        });
        modellMenu.add(loeschenMenuItem);

        kopierenMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        kopierenMenuItem.setText("Kopieren");
        modellMenu.add(kopierenMenuItem);

        einfuegenMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        einfuegenMenuItem.setText("Einfügen");
        modellMenu.add(einfuegenMenuItem);

        jMenuBar1.add(modellMenu);

        optionenMenu.setText("Optionen");

        sortierungMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        sortierungMenuItem.setText("Sortierung");
        optionenMenu.add(sortierungMenuItem);

        jMenuBar1.add(optionenMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void speichernMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speichernMenuItemActionPerformed
        controller.save();
    }//GEN-LAST:event_speichernMenuItemActionPerformed

    private void dateiNeuMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateiNeuMenuItemActionPerformed
        controller.newDb();
    }//GEN-LAST:event_dateiNeuMenuItemActionPerformed

    private void dateiOeffnenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateiOeffnenMenuItemActionPerformed
        controller.open();
    }//GEN-LAST:event_dateiOeffnenMenuItemActionPerformed

    private void schliessenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schliessenMenuItemActionPerformed
        controller.close();
    }//GEN-LAST:event_schliessenMenuItemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        controller.exit();
    }//GEN-LAST:event_formWindowClosing

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
/*        KeyStroke k = dateiOeffnenMenuItem.getAccelerator();
        for(MenuComponent comp : dateiMenu.getMenuComponents()) {

        }*/
    }//GEN-LAST:event_formKeyTyped

    private void modellNeuMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modellNeuMenuItemActionPerformed
        dbvc.addModel();
    }//GEN-LAST:event_modellNeuMenuItemActionPerformed

    private void loeschenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loeschenMenuItemActionPerformed
        dbvc.deleteModel();
    }//GEN-LAST:event_loeschenMenuItemActionPerformed

    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow(null).setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu dateiMenu;
    private javax.swing.JMenuItem dateiNeuMenuItem;
    private javax.swing.JMenuItem dateiOeffnenMenuItem;
    private javax.swing.JPanel dbView;
    private javax.swing.JMenuItem einfuegenMenuItem;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem kopierenMenuItem;
    private javax.swing.JMenuItem loeschenMenuItem;
    private javax.swing.JMenu modellMenu;
    private javax.swing.JMenuItem modellNeuMenuItem;
    private javax.swing.JMenu optionenMenu;
    private javax.swing.JMenuItem schliessenMenuItem;
    private javax.swing.JMenuItem sortierungMenuItem;
    private javax.swing.JMenuItem speichernMenuItem;
    // End of variables declaration//GEN-END:variables


}
