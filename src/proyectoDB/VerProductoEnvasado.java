package proyectoDB;

import entities.Lote;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerProductoEnvasado extends JFrame implements ActionListener{
    private JPanel contentPane;
    private JComboBox<String> comboBox;
    private Connection c;
    private JTable table;
    private JScrollPane scrollPane;

    private String[] lotesColumns={"ID","Descripción","Mililitros","Fecha de entrada","Fecha de salida","Cantidad de producto","Edad"};

    private Map<String,entities.ProductoEnvasado> productoEnvasadoMap=new HashMap<>();

    /**
     * Create the frame.
     */
    public VerProductoEnvasado(Connection c) {
        super("Ver productos envasados");
        this.c=c;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1300, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblProveedoresDeMateriales = new JLabel("");

        JLabel lblLote = new JLabel("ID del lote");

        List<String> productos=new ArrayList<>();
        try{

            String query="SELECT idCodigo,descripcion,mililitros from ProductoEnvasado";

            PreparedStatement statement=this.c.prepareStatement(query);

            ResultSet rs=statement.executeQuery();

            String codigo,descripcion;
            int milis;

            while(rs.next()){
                codigo=rs.getString("idCodigo");
                descripcion=rs.getString("descripcion");
                milis=rs.getInt("mililitros");
                String comboText=codigo+" "+descripcion+" "+milis;
                productos.add(comboText);
                this.productoEnvasadoMap.put(comboText,new entities.ProductoEnvasado(codigo,descripcion,milis));
            }


        }catch(SQLException e){
            e.printStackTrace();
        }

        String tmp[]=new String[productos.size()];


        comboBox = new JComboBox<String>(productos.toArray(tmp));
        this.comboBox.addActionListener(this);
        scrollPane = new JScrollPane();

		/*
		this.bg=new ButtonGroup();

		rdbtnNewRadioButton = new JRadioButton("Madera");
		this.rdbtnNewRadioButton.setSelected(true);
		this.bg.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton_1 = new JRadioButton("Material");
		this.bg.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_2 = new JRadioButton("Tarimas recicladas");
		this.bg.add(rdbtnNewRadioButton_2);*/

		/*
		lblNombreDeProveedor = new JLabel("");

		lblTelefonos = new JLabel("");*/


        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                        //.addComponent(rdbtnNewRadioButton)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        //.addComponent(rdbtnNewRadioButton_1)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									/*.addComponent(rdbtnNewRadioButton_2)*/)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(lblLote)
                                                                .addGap(35)
                                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(83)
                                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(52)
                                                .addComponent(lblProveedoresDeMateriales))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(260)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        //.addComponent(lblTelefonos)
								/*.addComponent(lblNombreDeProveedor)*/)))
                                .addContainerGap(261, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblProveedoresDeMateriales)
                                .addGap(33)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(18)
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(80)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblLote))))
                                .addGap(49)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        //.addComponent(rdbtnNewRadioButton)
                                        //.addComponent(rdbtnNewRadioButton_1)
						/*.addComponent(rdbtnNewRadioButton_2)*/)
                                .addGap(61)
                                //.addComponent(lblNombreDeProveedor)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                //.addComponent(lblTelefonos)
                                .addContainerGap(228, Short.MAX_VALUE))
        );

        table = new JTable();
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);

        this.setLocationRelativeTo(null);
    }


    private void update(String id){

        entities.ProductoEnvasado prod=this.productoEnvasadoMap.get(id);
        String idCodigo=prod.getIdCodigo();
        String descripcion=prod.getDescription();
        int milis=prod.getMilis();

        String query="SELECT fechaEntrada,fechaSalida,edad,cantidadProducto FROM ProductoEnvasado WHERE idCodigo=? AND descripcion=? AND mililitros=?";

        List<entities.ProductoEnvasado> productos=new ArrayList<>();

        try {
            PreparedStatement statement=this.c.prepareStatement(query);


            statement.setString(1,idCodigo);
            statement.setString(2,descripcion);
            statement.setInt(3,milis);

            ResultSet rs = statement.executeQuery();

            String fechaEntrada,fechaSalida;
            int edad,cantidad;

            while(rs.next()){

                fechaEntrada=rs.getString("fechaEntrada");
                fechaSalida=rs.getString("fechaSalida");
                edad=rs.getInt("edad");
                cantidad=rs.getInt("cantidadProducto");

                productos.add(new entities.ProductoEnvasado(idCodigo,descripcion,fechaEntrada,fechaSalida,milis,cantidad,edad));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[][] tmp=new String[productos.size()][];

        for(int i=0;i<productos.size();i++){
            entities.ProductoEnvasado p=productos.get(i);
            tmp[i]=new String[]{p.getIdCodigo(),p.getDescription(),Integer.toString(p.getMilis()),p.getFechaEntrada(),p.getFechaSalida(),Integer.toString(p.getCantidad()),Integer.toString(p.getEdad())};
        }

        this.scrollPane.remove(this.table);

        this.table=new JTable(tmp,lotesColumns);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        scrollPane.setViewportView(table);
        scrollPane.revalidate();
        scrollPane.repaint();


    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource().equals(comboBox)){
			/*int index=this.comboBox.getSelectedIndex();
			String prov=this.idRFC.get(index);
			this.updateTable(prov);
			*/

            String id = this.comboBox.getSelectedItem().toString();

            this.update(id);

        }
    }
}
