/*
 * Created by JFormDesigner on Fri Nov 10 17:11:19 CST 2017
 */

package proyectoDB;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author Diego YaÃ±ez
 */
public class MainOptionsWindow extends JFrame {

    private Connection c;

    public MainOptionsWindow(Connection c,boolean admin) {
        this.c=c;
        initComponents();
        this.okButton.setEnabled(admin);
    }

    private void okButtonActionPerformed(ActionEvent e) {
        OptionsWindows ow=new OptionsWindows(this.c);
        ow.setVisible(true);
        this.dispose();
    }

    private void btnLotesActionPerformed(ActionEvent e) {

        IngresarLote ingresarLote=new IngresarLote(this.c);
        ingresarLote.setVisible(true);
    }

    private void btnProdActionPerformed(ActionEvent e) {
        IngresarProducto ingresarProducto=new IngresarProducto(this.c);
        ingresarProducto.setVisible(true);
    }

    private void btnEnvActionPerformed(ActionEvent e) {
        IngresarEnvase ingresarEnvase=new IngresarEnvase(this.c);
        ingresarEnvase.setVisible(true);
    }

    private void btnProdEnvActionPerformed(ActionEvent e) {
        ProductoEnvasado productoEnvasado=new ProductoEnvasado(this.c);
        productoEnvasado.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Diego YaÃ±ez
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        btnLotes = new JButton();
        btnEnv = new JButton();
        btnProd = new JButton();
        btnProdEnv = new JButton();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- btnLotes ----
                btnLotes.setText("Ingresar Lotes");
                btnLotes.addActionListener(e -> btnLotesActionPerformed(e));

                //---- btnEnv ----
                btnEnv.setText("Ingresar Envases");
                btnEnv.addActionListener(e -> btnEnvActionPerformed(e));

                //---- btnProd ----
                btnProd.setText("Ingresar Producto");
                btnProd.addActionListener(e -> btnProdActionPerformed(e));

                //---- btnProdEnv ----
                btnProdEnv.setText("Ingresar Producto Envasado");
                btnProdEnv.addActionListener(e -> btnProdEnvActionPerformed(e));

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnProdEnv, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEnv, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnProd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLotes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(158, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(btnLotes)
                            .addGap(18, 18, 18)
                            .addComponent(btnProd)
                            .addGap(18, 18, 18)
                            .addComponent(btnEnv)
                            .addGap(18, 18, 18)
                            .addComponent(btnProdEnv)
                            .addContainerGap(39, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- okButton ----
                okButton.setText("Regresar");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Diego YaÃ±ez
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JButton btnLotes;
    private JButton btnEnv;
    private JButton btnProd;
    private JButton btnProdEnv;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
