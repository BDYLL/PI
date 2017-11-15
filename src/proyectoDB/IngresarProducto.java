/*
 * Created by JFormDesigner on Fri Nov 10 14:20:54 CST 2017
 */

package proyectoDB;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author Diego YaÃ±ez
 */
public class IngresarProducto extends JFrame {

    private Connection c;

    public IngresarProducto(Connection c) {
        this.c=c;

        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void okButtonActionPerformed(ActionEvent e) {

        String id = this.idProd.getText();

        if(id.trim().isEmpty()){
            this.idProd.setForeground(Color.RED);
            return;
        }

        String query="INSERT INTO Producto (idCodigo) VALUES(?)";

        try {
            PreparedStatement statement=this.c.prepareStatement(query);

            statement.setString(1,id);

            statement.executeUpdate();

            this.dispose();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Diego YaÃ±ez
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        lblProd = new JLabel();
        idProd = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Producto");
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

                //---- lblProd ----
                lblProd.setText("ID de Producto (5 max)");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(lblProd)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                            .addComponent(idProd, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblProd)
                                .addComponent(idProd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(120, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
    private JLabel lblProd;
    private JTextField idProd;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
