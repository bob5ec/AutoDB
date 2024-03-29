/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModelView.java
 *
 * Created on 19.03.2009, 08:50:08
 */

package bz.asd.autodb.gui;

import bz.asd.mvc.Controller;
import bz.asd.autodb.data.DefaultModel;
import bz.asd.autodb.data.Model;
import bz.asd.autodb.logic.ModelViewController;
import bz.asd.mvc.View;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.jdesktop.beansbinding.Binding;

/**
 *
 * @author lars
 */
public class ModelView extends javax.swing.JPanel implements View {

    private ModelViewController controller;

    /** Creates new empty ModelView which can be used independently
     * for Model GUI IO.
     */
    public ModelView() {
        initComponents();
        /*ImageView img = new ImageView();
        img.setMaxHeight(300);
        img.setMaxWidth(300);
        lblImage.setIcon(img);
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, model, org.jdesktop.beansbinding.ELProperty.create("${bilddatei}"), img, org.jdesktop.beansbinding.BeanProperty.create("imageFilename"));
        bindingGroup.addBinding(binding);
        bindingGroup.bind();*/
    }

    public void setModel(bz.asd.mvc.Model model) {
        System.out.println("ModelView.setModel()");
        
        this.model = (DefaultModel) model;
        
        for(Binding binding : bindingGroup.getBindings()) {
            binding.unbind();
            binding.setSourceObject(model);
            binding.bind();
        }
        repaint();
    }

    public void refreshModel() {
        repaint();
        /*for(Binding binding : bindingGroup.getBindings()) {
            binding.refreshAndNotify();
        }*/
    }

    public void focus() {
        tfdHersteller.requestFocusInWindow();
    }

    public Model getModel() {
        return model;
    }

    public void setController(Controller controller) {
        this.controller = (ModelViewController)controller;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        model = new bz.asd.autodb.data.DefaultModel();
        bgModellart = new javax.swing.ButtonGroup();
        bgModellstandort = new javax.swing.ButtonGroup();
        image = new bz.asd.autodb.gui.ImageView();
        lblHersteller = new javax.swing.JLabel();
        tfdHersteller = new javax.swing.JTextField();
        imagePanel = new javax.swing.JPanel();
        btnDurchsuchen = new javax.swing.JButton();
        tfdImageFile = new javax.swing.JTextField();
        lblImage = new javax.swing.JLabel();
        lblHerstellerNummer = new javax.swing.JLabel();
        tfdHerstellerNummer = new javax.swing.JTextField();
        lblAuflage = new javax.swing.JLabel();
        tfdAuflage = new javax.swing.JTextField();
        modellartPanel = new javax.swing.JPanel();
        rbSerienmodell = new javax.swing.JRadioButton();
        rbWerbemodell = new javax.swing.JRadioButton();
        rbEigenbau = new javax.swing.JRadioButton();
        modellStandortPanel = new javax.swing.JPanel();
        rbWettmar = new javax.swing.JRadioButton();
        rbDinklage = new javax.swing.JRadioButton();
        rbGarbsen = new javax.swing.JRadioButton();
        lblMarke = new javax.swing.JLabel();
        tfdMarke = new javax.swing.JTextField();
        tfdAchsfolge = new javax.swing.JTextField();
        lblAchsfolge = new javax.swing.JLabel();
        lblTyp = new javax.swing.JLabel();
        tfdTyp = new javax.swing.JTextField();
        tfdAufbau = new javax.swing.JTextField();
        lblAufbau = new javax.swing.JLabel();
        lblArt = new javax.swing.JLabel();
        tfdArt = new javax.swing.JTextField();
        lblDruck = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDruck = new javax.swing.JTextArea();
        lblBemerkung = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        preisePanel = new javax.swing.JPanel();
        lblPreisEK = new javax.swing.JLabel();
        lblPreisVK = new javax.swing.JLabel();
        lblPreisSL = new javax.swing.JLabel();
        tfdPreisEK = new javax.swing.JFormattedTextField();
        tfdPreisVK = new javax.swing.JFormattedTextField();
        tfdPreisSL = new javax.swing.JFormattedTextField();
        lblProduktionsdatum = new javax.swing.JLabel();
        lblAenderungsdatum = new javax.swing.JLabel();
        tfdAenderungsdatum = new javax.swing.JFormattedTextField();
        tfdProduktionsdatum = new javax.swing.JTextField();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${bilddatei}"), image, org.jdesktop.beansbinding.BeanProperty.create("imageFilename"));
        bindingGroup.addBinding(binding);

        setMinimumSize(new java.awt.Dimension(300, 300));

        lblHersteller.setText("Hersteller");

        tfdHersteller.setMinimumSize(new java.awt.Dimension(60, 21));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${hersteller}"), tfdHersteller, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        btnDurchsuchen.setText("Durchsuchen");
        btnDurchsuchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDurchsuchenActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${bilddatei}"), tfdImageFile, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblImage.setIcon(image);
        lblImage.setPreferredSize(new java.awt.Dimension(300, 200));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addComponent(tfdImageFile, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDurchsuchen))
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdImageFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDurchsuchen)))
        );

        lblHerstellerNummer.setText("Hersteller Nummer");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${herstellerNr}"), tfdHerstellerNummer, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblAuflage.setText("Auflage");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${auflage}"), tfdAuflage, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        modellartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Modellart"));

        bgModellart.add(rbSerienmodell);
        rbSerienmodell.setText("Serienmodell");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${modellArtSerienmodell}"), rbSerienmodell, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        bgModellart.add(rbWerbemodell);
        rbWerbemodell.setText("Werbemodell");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${modellArtWerbemodell}"), rbWerbemodell, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        bgModellart.add(rbEigenbau);
        rbEigenbau.setText("Eigenbau");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${modellArtEigenbau}"), rbEigenbau, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout modellartPanelLayout = new javax.swing.GroupLayout(modellartPanel);
        modellartPanel.setLayout(modellartPanelLayout);
        modellartPanelLayout.setHorizontalGroup(
            modellartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modellartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modellartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbSerienmodell)
                    .addComponent(rbWerbemodell)
                    .addComponent(rbEigenbau))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        modellartPanelLayout.setVerticalGroup(
            modellartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modellartPanelLayout.createSequentialGroup()
                .addComponent(rbSerienmodell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbWerbemodell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbEigenbau))
        );

        modellStandortPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Modellstandort"));

        bgModellstandort.add(rbWettmar);
        rbWettmar.setText("Wettmar");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${modellStandortWettmar}"), rbWettmar, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        bgModellstandort.add(rbDinklage);
        rbDinklage.setText("Dinklage");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${modellStandortDinklage}"), rbDinklage, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        bgModellstandort.add(rbGarbsen);
        rbGarbsen.setText("Garbsen");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${modellStandortGarbsen}"), rbGarbsen, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout modellStandortPanelLayout = new javax.swing.GroupLayout(modellStandortPanel);
        modellStandortPanel.setLayout(modellStandortPanelLayout);
        modellStandortPanelLayout.setHorizontalGroup(
            modellStandortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modellStandortPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modellStandortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbWettmar)
                    .addComponent(rbDinklage)
                    .addComponent(rbGarbsen))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        modellStandortPanelLayout.setVerticalGroup(
            modellStandortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modellStandortPanelLayout.createSequentialGroup()
                .addComponent(rbWettmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbDinklage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbGarbsen))
        );

        lblMarke.setText("Marke");

        tfdMarke.setPreferredSize(new java.awt.Dimension(150, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${marke}"), tfdMarke, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        tfdAchsfolge.setPreferredSize(new java.awt.Dimension(150, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${achsfolge}"), tfdAchsfolge, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblAchsfolge.setText("Achsfolge");

        lblTyp.setText("Typ");

        tfdTyp.setPreferredSize(new java.awt.Dimension(150, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${typ}"), tfdTyp, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        tfdAufbau.setPreferredSize(new java.awt.Dimension(150, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${aufbau}"), tfdAufbau, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblAufbau.setText("Aufbau");

        lblArt.setText("Art");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${art}"), tfdArt, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblDruck.setText("Druck");

        taDruck.setColumns(20);
        taDruck.setRows(5);
        taDruck.setPreferredSize(new java.awt.Dimension(250, 85));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${druck}"), taDruck, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(taDruck);

        lblBemerkung.setText("Bemerkung");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setPreferredSize(new java.awt.Dimension(250, 85));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${bemerkung}"), jTextArea1, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(jTextArea1);

        preisePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Preise"));

        lblPreisEK.setText("EK");

        lblPreisVK.setText("VK");

        lblPreisSL.setText("SL");

        tfdPreisEK.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tfdPreisEK.setPreferredSize(new java.awt.Dimension(52, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${preisEK}"), tfdPreisEK, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        tfdPreisVK.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tfdPreisVK.setPreferredSize(new java.awt.Dimension(52, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${preisVK}"), tfdPreisVK, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        tfdPreisSL.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tfdPreisSL.setPreferredSize(new java.awt.Dimension(52, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${preisSL}"), tfdPreisSL, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout preisePanelLayout = new javax.swing.GroupLayout(preisePanel);
        preisePanel.setLayout(preisePanelLayout);
        preisePanelLayout.setHorizontalGroup(
            preisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preisePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPreisEK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdPreisEK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPreisVK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdPreisVK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPreisSL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdPreisSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        preisePanelLayout.setVerticalGroup(
            preisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preisePanelLayout.createSequentialGroup()
                .addGroup(preisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreisEK)
                    .addComponent(lblPreisVK)
                    .addComponent(lblPreisSL)
                    .addComponent(tfdPreisEK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdPreisVK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdPreisSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        lblProduktionsdatum.setText("Produktionsdatum");

        lblAenderungsdatum.setText("Änderungsdatum");

        tfdAenderungsdatum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d.M.yyyy"))));
        tfdAenderungsdatum.setPreferredSize(new java.awt.Dimension(80, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${aenderungsdatum}"), tfdAenderungsdatum, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        tfdProduktionsdatum.setPreferredSize(new java.awt.Dimension(80, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, model, org.jdesktop.beansbinding.ELProperty.create("${produktionsdatum}"), tfdProduktionsdatum, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(preisePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProduktionsdatum)
                            .addComponent(tfdProduktionsdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdAenderungsdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAenderungsdatum)))
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBemerkung)
                                .addGap(381, 381, 381))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDruck)
                                .addGap(418, 418, 418))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfdHersteller, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                        .addGap(12, 12, 12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblHersteller)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHerstellerNummer, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfdHerstellerNummer, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                        .addGap(4, 4, 4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAuflage)
                                    .addComponent(tfdAuflage, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblMarke)
                                            .addComponent(tfdMarke, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblAchsfolge)
                                            .addComponent(tfdAchsfolge, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTyp)
                                            .addComponent(tfdTyp, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblAufbau)
                                            .addComponent(tfdAufbau, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(12, 12, 12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdArt, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(lblArt)
                            .addComponent(modellartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modellStandortPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {modellStandortPanel, modellartPanel, tfdArt});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfdHersteller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHerstellerNummer)
                                    .addComponent(lblHersteller)
                                    .addComponent(lblAuflage))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfdAuflage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfdHerstellerNummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMarke)
                            .addComponent(lblAchsfolge))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfdMarke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdAchsfolge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTyp)
                            .addComponent(lblAufbau))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfdTyp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdAufbau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDruck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBemerkung))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblArt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modellartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modellStandortPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(preisePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProduktionsdatum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdProduktionsdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAenderungsdatum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdAenderungsdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDurchsuchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDurchsuchenActionPerformed
        controller.chooseImageFile();
    }//GEN-LAST:event_btnDurchsuchenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgModellart;
    private javax.swing.ButtonGroup bgModellstandort;
    private javax.swing.JButton btnDurchsuchen;
    private bz.asd.autodb.gui.ImageView image;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblAchsfolge;
    private javax.swing.JLabel lblAenderungsdatum;
    private javax.swing.JLabel lblArt;
    private javax.swing.JLabel lblAufbau;
    private javax.swing.JLabel lblAuflage;
    private javax.swing.JLabel lblBemerkung;
    private javax.swing.JLabel lblDruck;
    private javax.swing.JLabel lblHersteller;
    private javax.swing.JLabel lblHerstellerNummer;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMarke;
    private javax.swing.JLabel lblPreisEK;
    private javax.swing.JLabel lblPreisSL;
    private javax.swing.JLabel lblPreisVK;
    private javax.swing.JLabel lblProduktionsdatum;
    private javax.swing.JLabel lblTyp;
    private bz.asd.autodb.data.DefaultModel model;
    private javax.swing.JPanel modellStandortPanel;
    private javax.swing.JPanel modellartPanel;
    private javax.swing.JPanel preisePanel;
    private javax.swing.JRadioButton rbDinklage;
    private javax.swing.JRadioButton rbEigenbau;
    private javax.swing.JRadioButton rbGarbsen;
    private javax.swing.JRadioButton rbSerienmodell;
    private javax.swing.JRadioButton rbWerbemodell;
    private javax.swing.JRadioButton rbWettmar;
    private javax.swing.JTextArea taDruck;
    private javax.swing.JTextField tfdAchsfolge;
    private javax.swing.JFormattedTextField tfdAenderungsdatum;
    private javax.swing.JTextField tfdArt;
    private javax.swing.JTextField tfdAufbau;
    private javax.swing.JTextField tfdAuflage;
    private javax.swing.JTextField tfdHersteller;
    private javax.swing.JTextField tfdHerstellerNummer;
    private javax.swing.JTextField tfdImageFile;
    private javax.swing.JTextField tfdMarke;
    private javax.swing.JFormattedTextField tfdPreisEK;
    private javax.swing.JFormattedTextField tfdPreisSL;
    private javax.swing.JFormattedTextField tfdPreisVK;
    private javax.swing.JTextField tfdProduktionsdatum;
    private javax.swing.JTextField tfdTyp;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
