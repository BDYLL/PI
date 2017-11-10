/*
 * Created by JFormDesigner on Fri Nov 10 14:42:14 CST 2017
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
public class IngresarEnvase extends JFrame {

    private Connection c;

    public IngresarEnvase(Connection c) {
        this.c=c;
        initComponents();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        String description=this.descripcion.getText().trim();
        String miliS=this.mili.getText();

        int milis=-1;

        try{
            milis=Integer.parseInt(miliS);
        }catch (NumberFormatException ex1){
            this.mili.setForeground(Color.RED);
            return;
        }

        if(description.trim().isEmpty()){
            this.descripcion.setForeground(Color.RED);
            return;
        }

        String query="INSERT INTO Envase (descripcion,mililitros) VALUES(?,?)";

        try {
            PreparedStatement statement=this.c.prepareStatement(query);

            statement.setString(1,description);
            statement.setInt(2,milis);

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
        lblDescripcion = new JLabel();
        descripcion = new JTextField();
        lblMIli = new JLabel();
        mili = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Envase");
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

                //---- lblDescripcion ----
                lblDescripcion.setText("Descripci\u00f3n");

                //---- lblMIli ----
                lblMIli.setText("Mililitros");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(lblDescripcion)
                                .addComponent(lblMIli))
                            .addGap(62, 62, 62)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(mili, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addComponent(descripcion, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(43, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(lblDescripcion)
                                .addComponent(descripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(lblMIli))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(mili, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(86, Short.MAX_VALUE))
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
    private JLabel lblDescripcion;
    private JTextField descripcion;
    private JLabel lblMIli;
    private JTextField mili;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
