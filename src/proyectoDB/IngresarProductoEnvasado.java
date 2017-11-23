/*
 * Created by JFormDesigner on Fri Nov 10 15:05:15 CST 2017
 */

package proyectoDB;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import org.jdatepicker.*;

/**
 * @author Diego YaÃ±ez
 */
public class IngresarProductoEnvasado extends JFrame {

    private Connection c;

    private Map<String,Integer> milisMap;
    private Map<String,String> descMap;

    public IngresarProductoEnvasado(Connection c) {
        this.c=c;
        this.milisMap =new HashMap<>();
        this.descMap=new HashMap<>();
        initComponents();
        updateList();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void updateList(){
        String queryEnvase="SELECT descripcion,mililitros FROM Envase";
        String queryProd="SELECT idCodigo FROM Producto";

        List<String> descriptions=new ArrayList<>();

        List<Integer> milis=new ArrayList<>();

        List<String> codes=new ArrayList<>();

        try{
            PreparedStatement statement=this.c.prepareStatement(queryEnvase);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                descriptions.add(rs.getString("descripcion"));
                milis.add(rs.getInt("mililitros"));
            }

            for(int i=0;i<descriptions.size();i++){
                String tmp=descriptions.get(i)+" "+milis.get(i).toString();
                boxDescMili.addItem(tmp);
                this.milisMap.put(tmp,milis.get(i));
                this.descMap.put(tmp,descriptions.get(i));
            }

            statement=this.c.prepareStatement(queryProd);

            rs=statement.executeQuery();

            while(rs.next()){
                codes.add(rs.getString("idCodigo"));
            }

            for(String code : codes){
                boxCodigo.addItem(code);
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void okButtonActionPerformed(ActionEvent e) {
        String code=this.boxCodigo.getSelectedItem().toString();
        String row = this.boxDescMili.getSelectedItem().toString();

        String desc=this.descMap.get(row);
        int milis=this.milisMap.get(row).intValue();

        int cantProd=-1;

        try{
            cantProd=Integer.parseInt(this.cantProd.getText());
        }
        catch(NumberFormatException ex){
            this.cantProd.setForeground(Color.RED);
            return;
        }

        if(cantProd<0){
            return;
        }


        String dateEntS=this.fechaEnt.getFormattedTextField().getText();

        String dateSalS=this.fechaSal.getFormattedTextField().getText();


        java.sql.Date dateEnt,dateSal;

        if(dateEntS.length()==9){
            dateEntS="0"+dateEntS;
        }
        if(dateSalS.length()==9){
            dateSalS="0"+dateSalS;
        }

        LocalDate first=LocalDate.parse(dateEntS, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        LocalDate second=LocalDate.parse(dateSalS,DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        int edad=Long.valueOf(ChronoUnit.DAYS.between(first,second)).intValue();

        if(edad<0){

            JOptionPane.showMessageDialog(this,"Fecha de salida es antes que la fecha de entrega");

            return;
        }


        dateEnt = new java.sql.Date(first.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        dateSal= new java.sql.Date(second.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());


        String query="INSERT INTO ProductoEnvasado (idCodigo,descripcion,mililitros,fechaEntrada,fechaSalida,edad,cantidadProducto) VALUES(?,?,?,?,?,?,?)";


        try {
            PreparedStatement statement=this.c.prepareStatement(query);

            statement.setString(1,code);
            statement.setString(2,desc);
            statement.setInt(3,milis);
            statement.setDate(4,dateEnt);
            statement.setDate(5,dateSal);
            statement.setInt(6,edad);
            statement.setInt(7,cantProd);


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
        lblCodigo = new JLabel();
        boxCodigo = new JComboBox();
        lblDescMili = new JLabel();
        boxDescMili = new JComboBox();
        fechaEnt = new JDatePicker();
        lblFechaEnt = new JLabel();
        lblFechaSal = new JLabel();
        fechaSal = new JDatePicker();
        lblCantProd = new JLabel();
        cantProd = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Producto Envasado");
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

                //---- lblCodigo ----
                lblCodigo.setText("Codigo");

                //---- lblDescMili ----
                lblDescMili.setText("Descripci\u00f3n y mililitros");

                //---- lblFechaEnt ----
                lblFechaEnt.setText("Fecha de entrada");

                //---- lblFechaSal ----
                lblFechaSal.setText("Fecha de salida");

                //---- lblCantProd ----
                lblCantProd.setText("Cantidad de producto");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(lblCodigo)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(boxCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                    .addComponent(lblDescMili)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
                                    .addComponent(boxDescMili, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(lblFechaEnt)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                                    .addComponent(fechaEnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(lblFechaSal)
                                        .addComponent(lblCantProd))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(fechaSal, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cantProd, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))))
                            .addGap(80, 80, 80))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(lblCodigo)
                                .addComponent(boxCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(28, 28, 28)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(lblDescMili)
                                .addComponent(boxDescMili, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(fechaEnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(lblFechaEnt)))
                            .addGap(33, 33, 33)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(lblFechaSal)
                                .addComponent(fechaSal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblCantProd)
                                .addComponent(cantProd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(19, Short.MAX_VALUE))
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
    private JLabel lblCodigo;
    private JComboBox boxCodigo;
    private JLabel lblDescMili;
    private JComboBox boxDescMili;
    private JDatePicker fechaEnt;
    private JLabel lblFechaEnt;
    private JLabel lblFechaSal;
    private JDatePicker fechaSal;
    private JLabel lblCantProd;
    private JTextField cantProd;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
