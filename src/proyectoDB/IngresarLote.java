/*
 * Created by JFormDesigner on Thu Nov 09 23:16:33 CST 2017
 */

package proyectoDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author Diego YaÃ±ez
 */
public class IngresarLote extends JFrame  {

    private Connection c;

    public IngresarLote(Connection c) {
        this.c=c;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void okButtonActionPerformed(ActionEvent e) {
        String id=this.idLote.getText();

        int tarimas=-1;
        int cajas=-1;


        try{

            tarimas=Integer.parseInt(this.tarimas.getText());
            cajas=Integer.parseInt(this.cajas.getText());

        }
        catch(NumberFormatException ex){
            if(tarimas<0){
                this.tarimas.setForeground(Color.RED);
                return;
            }
            if(cajas<0){
                this.cajas.setForeground(Color.RED);
                return;
            }
        }

        int cajasXTarimas=cajas*tarimas;

        if(id.trim().isEmpty()){
            this.idLote.setForeground(Color.RED);
            return;
        }

        if(tarimas<0){
            this.tarimas.setForeground(Color.RED);
            return;
        }
        if(cajas<0){
            this.cajas.setForeground(Color.RED);
            return;
        }


        String query="INSERT INTO Lote (idLote,tarimas,cajas,cajasTarimas) VALUES(?,?,?,?)";

        try {
            PreparedStatement statement = this.c.prepareStatement(query);

            statement.setString(1,id);
            statement.setInt(2,tarimas);
            statement.setInt(3,cajas);
            statement.setInt(4,cajasXTarimas);

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
        lblLotes = new JLabel();
        idLote = new JTextField();
        lblTarimas = new JLabel();
        tarimas = new JTextField();
        lblCajas = new JLabel();
        cajas = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Lotes");
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

                //---- lblLotes ----
                lblLotes.setText("ID Lotes (5 max)");

                //---- lblTarimas ----
                lblTarimas.setText("Tarimas");

                //---- lblCajas ----
                lblCajas.setText("Cajas");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTarimas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblLotes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(lblCajas, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                            .addGap(78, 78, 78)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(cajas, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addComponent(idLote, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addComponent(tarimas, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(64, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblLotes)
                                .addComponent(idLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(26, 26, 26)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTarimas)
                                .addComponent(tarimas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblCajas)
                                .addComponent(cajas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(57, Short.MAX_VALUE))
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
    private JLabel lblLotes;
    private JTextField idLote;
    private JLabel lblTarimas;
    private JTextField tarimas;
    private JLabel lblCajas;
    private JTextField cajas;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
