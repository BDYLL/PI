package proyectoDB;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;

public class VerProveedoresWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private Connection c;
	private Hashtable<Integer,String> idRFC;
	private JTable table;
	private JScrollPane scrollPane;
	private String columns1[]={"Largo","Alto","Grosor","UltimaFecha"/*,"Nombre","Apellido Pat","Apellido Mat","Telefono"*/};
	private String columns2[]={"Tipo","Tamano","Ultima Fecha"/*,"Nombre","Apellido Pat","Apellido Mat","Telefono"*/};
	private String columns3[]={"Lote","Ultima Fecha"/*,"Nombre","Apellido Pat","Apellido Mat","Telefono"*/};
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private ButtonGroup bg;
	private JLabel lblNombreDeProveedor,lblTelefonos;
	
	/**
	 * Create the frame.
	 */
	public VerProveedoresWindow(Connection c) {
		super("Ver Proveedores");
		this.c=c;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblProveedoresDeMateriales = new JLabel("Proveedores");
		
		JLabel lblProveedor = new JLabel("Proveedor");
		
		ArrayList<String> proveedores=new ArrayList<String>();
		this.idRFC=new Hashtable<Integer,String>();
		try{
			Statement query=this.c.createStatement();
			
			String qs="SELECT RFCProveedor, PNombre FROM Proveedor";
			
			ResultSet rs=query.executeQuery(qs);
			
			int i=0;
			while(rs.next()){
				String rfc,nombre;
				rfc=rs.getString("RFCProveedor");
				nombre=rs.getString("PNombre");
				String res=rfc+" "+nombre;
				proveedores.add(res);
				this.idRFC.put(i, rfc);
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}	
		
		String tmp[]=new String[proveedores.size()];
		
		
		comboBox = new JComboBox<String>(proveedores.toArray(tmp));
		this.comboBox.addActionListener(this);
		scrollPane = new JScrollPane();
		
		this.bg=new ButtonGroup();
		
		rdbtnNewRadioButton = new JRadioButton("Madera");
		this.rdbtnNewRadioButton.setSelected(true);
		this.bg.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton_1 = new JRadioButton("Material");
		this.bg.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_2 = new JRadioButton("Tarimas recicladas");
		this.bg.add(rdbtnNewRadioButton_2);
		
		lblNombreDeProveedor = new JLabel("Nombre de proveedor:");
		
		lblTelefonos = new JLabel("Telefonos:");
		
			
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnNewRadioButton)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnNewRadioButton_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnNewRadioButton_2))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblProveedor)
									.addGap(35)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(83)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 694, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(52)
							.addComponent(lblProveedoresDeMateriales))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(260)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTelefonos)
								.addComponent(lblNombreDeProveedor))))
					.addContainerGap(261, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblProveedoresDeMateriales)
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(80)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProveedor))))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(rdbtnNewRadioButton_1)
						.addComponent(rdbtnNewRadioButton_2))
					.addGap(61)
					.addComponent(lblNombreDeProveedor)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTelefonos)
					.addContainerGap(228, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	private void updateTable(String prov){
		String qs="";
		
		ArrayList<String> nombres=new ArrayList<String>();
		ArrayList<String> telefonos=new ArrayList<String>();
		try{
			Statement query=this.c.createStatement();
			
			ArrayList<String[]> rows=new ArrayList<String[]>();
			
			if(this.rdbtnNewRadioButton.isSelected()){
				qs="SELECT DISTINCT madalto,madlargo,madgrosor,cpnombre,cpapellidop,cpapellidom,cptel,PMADULTIMAFECHA "
						+ "FROM Proveedor NATURAL JOIN ContactoProveedor NATURAL JOIN ProveeMadera "
						+ "NATURAL JOIN CPTelefono WHERE RFCProveedor='"+prov+"'";
				
				ResultSet rs=query.executeQuery(qs);
				
				
				while(rs.next()){
					String alto,largo,grosor,cpnombre,appP,appM,tel,fecha;
					alto=rs.getString("madalto");
					largo=rs.getString("madlargo");
					grosor=rs.getString("madgrosor");
					cpnombre=rs.getString("cpnombre");
					appP=rs.getString("cpapellidop");
					appM=rs.getString("cpapellidom");
					tel=rs.getString("cptel");
					Date fechad=rs.getDate("PMADULTIMAFECHA");
					fecha=fechad.toString();
					String nombre=cpnombre+" "+appP+" "+appM;
					if(!nombres.contains(nombre)){
						nombres.add(nombre);
					}
					if(!telefonos.contains(tel)){
						telefonos.add(tel);
					}
					String row[]={alto,largo,grosor,fecha/*,cpnombre,appP,appM,tel*/};
					rows.add(row);
				}
			}
			else if(this.rdbtnNewRadioButton_1.isSelected()){
				qs="SELECT DISTINCT matipo,matamano,cpnombre,cpapellidop,cpapellidom,cptel,PMATULTIMAFECHA "
						+ "FROM Proveedor NATURAL JOIN ContactoProveedor NATURAL JOIN ProveeMaterial "
						+ "NATURAL JOIN CPTelefono WHERE RFCProveedor='"+prov+"'";				
				
				ResultSet rs=query.executeQuery(qs);
				
				
				while(rs.next()){
					String tipo,tamano,cpnombre,appP,appM,tel,fecha;
					tipo=rs.getString("matipo");
					tamano=rs.getString("matamano");
					cpnombre=rs.getString("cpnombre");
					appP=rs.getString("cpapellidop");
					appM=rs.getString("cpapellidom");
					tel=rs.getString("cptel");
					Date fechad=rs.getDate("PMATULTIMAFECHA");
					fecha=fechad.toString();
					String nombre=cpnombre+" "+appP+" "+appM;
					if(!nombres.contains(nombre)){
						nombres.add(nombre);
					}
					if(!telefonos.contains(tel)){
						telefonos.add(tel);
					}
					String row[]={tipo,tamano,fecha/*,cpnombre,appP,appM,tel*/};
					rows.add(row);
				}
			}
			else if(this.rdbtnNewRadioButton_2.isSelected()){
				qs="SELECT DISTINCT tlote,cpnombre,cpapellidop,cpapellidom,cptel,PDULTIMAFECHA "
						+ "FROM Proveedor NATURAL JOIN ContactoProveedor NATURAL JOIN ProveeDanadas "
						+ "NATURAL JOIN CPTelefono WHERE RFCProveedor='"+prov+"'";
				ResultSet rs=query.executeQuery(qs);
				
				
				while(rs.next()){
					String tlote,cpnombre,appP,appM,tel,fecha;
					tlote=rs.getString("tlote");
					cpnombre=rs.getString("cpnombre");
					appP=rs.getString("cpapellidop");
					appM=rs.getString("cpapellidom");
					tel=rs.getString("cptel");
					Date fechad=rs.getDate("PDULTIMAFECHA");
					fecha=fechad.toString();
					String nombre=cpnombre+" "+appP+" "+appM;
					if(!nombres.contains(nombre)){
						nombres.add(nombre);
					}
					if(!telefonos.contains(tel)){
						telefonos.add(tel);
					}
					String row[]={tlote,fecha/*,cpnombre,appP,appM,tel*/};
					rows.add(row);
				}
			}
			
			String tmp[][]=new String[rows.size()][];
			
			for(int i=0;i<rows.size();i++){
				tmp[i]=rows.get(i);
			}
			
			this.scrollPane.remove(this.table);
			
			
			if(this.rdbtnNewRadioButton.isSelected()){
				this.table=new JTable(tmp,columns1);
			}
			else if(this.rdbtnNewRadioButton_1.isSelected()){
				this.table=new JTable(tmp,columns2);
			}
			else if(this.rdbtnNewRadioButton_2.isSelected()){
				this.table=new JTable(tmp,columns3);
			}
			
			table.setPreferredScrollableViewportSize(table.getPreferredSize());
			table.setFillsViewportHeight(true);
			table.setEnabled(false);
			scrollPane.setViewportView(table);
			scrollPane.revalidate();
			scrollPane.repaint();
			
			String nombresLabel="Nombre de proveedor: ";
			for(int i=0;i<nombres.size();i++){
				nombresLabel+=nombres.get(i)+" ";
			}
			this.lblNombreDeProveedor.setText(nombresLabel);
			
			
			String telLabel="Telefonos: ";
			
			for(int i=0;i<telefonos.size();i++){
				telLabel+=telefonos.get(i)+" ";
			}
			this.lblTelefonos.setText(telLabel);
			
		}catch(SQLException e){
			System.out.println(qs);
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(comboBox)){
			int index=this.comboBox.getSelectedIndex();
			String prov=this.idRFC.get(index);
			this.updateTable(prov);
		}
	}
}
