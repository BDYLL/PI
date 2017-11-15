/*
 * Created by JFormDesigner on Wed Nov 15 14:10:33 CST 2017
 */

package proyectoDB;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.filechooser.FileFilter;

/**
 * @author Diego YaÃ±ez
 */
public class OptionsWindows2 extends JFrame {

    private Connection c;

    private static final class FolderFilter extends FileFilter{

        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }

        @Override
        public String getDescription() {
            return "Directories only";
        }
    }

    public OptionsWindows2(Connection c) {
        this.c=c;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void btnVerActionPerformed(ActionEvent e) {
        VisualizationWindow vw=new VisualizationWindow(this.c);
        vw.setVisible(true);
        this.dispose();
    }

    private void btnInsertarActionPerformed(ActionEvent e) {
        MainOptionsWindow mainOptionsWindow=new MainOptionsWindow(this.c,true);
        mainOptionsWindow.setVisible(true);
        this.dispose();
    }

    private void btnAdminActionPerformed(ActionEvent e) {
        AdministracionWindow aw=new AdministracionWindow(this.c);
        aw.setVisible(true);
        this.dispose();
    }

    private void btnExpActionPerformed(ActionEvent e) {

        JFileChooser chooser=new JFileChooser("");
        FolderFilter filter=new FolderFilter();

        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int retVal=chooser.showDialog(this,"OK");

        if(retVal==JFileChooser.APPROVE_OPTION){
            System.out.println(chooser.getSelectedFile().toString());

            CSVExporter exporter=new CSVExporter(this.c);

            boolean result=exporter.accept(chooser.getSelectedFile());

            JOptionPane.showMessageDialog(this,result);

        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Diego YaÃ±ez
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        btnVer = new JButton();
        btnInsertar = new JButton();
        btnAdmin = new JButton();
        btnExp = new JButton();

        //======== this ========
        setTitle("Opciones");
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

                //---- btnVer ----
                btnVer.setText("Ver");
                btnVer.addActionListener(e -> btnVerActionPerformed(e));

                //---- btnInsertar ----
                btnInsertar.setText("Insertar");
                btnInsertar.addActionListener(e -> btnInsertarActionPerformed(e));

                //---- btnAdmin ----
                btnAdmin.setText("Administraci\u00f3n");
                btnAdmin.addActionListener(e -> btnAdminActionPerformed(e));

                //---- btnExp ----
                btnExp.setText("Exportar");
                btnExp.addActionListener(e -> btnExpActionPerformed(e));

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                            .addContainerGap(49, Short.MAX_VALUE)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(btnInsertar, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnVer, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExp, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAdmin, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE))
                            .addGap(31, 31, 31))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnVer)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnInsertar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                            .addComponent(btnAdmin)
                            .addGap(18, 18, 18)
                            .addComponent(btnExp)
                            .addGap(24, 24, 24))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
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
    private JButton btnVer;
    private JButton btnInsertar;
    private JButton btnAdmin;
    private JButton btnExp;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
