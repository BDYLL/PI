package proyectoDB;

import entities.Lote;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class VerLotes extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private Connection c;
	private JTable table;
	private JScrollPane scrollPane;

	private String[] lotesColumns={"ID","Tarimas","Cajas","Cajas X Tarimas"};

	/**
	 * Create the frame.
	 */
	public VerLotes(Connection c) {
		super("Ver lotes");
		this.c=c;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblProveedoresDeMateriales = new JLabel("");
		
		JLabel lblLote = new JLabel("ID del lote");

		List<String> lotes=new ArrayList<>();
		try{

			String query="SELECT idLote from Lote";

			PreparedStatement statement=this.c.prepareStatement(query);

			ResultSet rs=statement.executeQuery();


			while(rs.next()){
				lotes.add(rs.getString("idLote"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}	
		
		String tmp[]=new String[lotes.size()];
		
		
		comboBox = new JComboBox<String>(lotes.toArray(tmp));
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
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									//.addComponent(rdbtnNewRadioButton)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									//.addComponent(rdbtnNewRadioButton_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									/*.addComponent(rdbtnNewRadioButton_2)*/)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblLote)
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
								//.addComponent(lblTelefonos)
								/*.addComponent(lblNombreDeProveedor)*/)))
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
								.addComponent(lblLote))))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						//.addComponent(rdbtnNewRadioButton)
						//.addComponent(rdbtnNewRadioButton_1)
						/*.addComponent(rdbtnNewRadioButton_2)*/)
					.addGap(61)
					//.addComponent(lblNombreDeProveedor)
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
/*
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
					String row[]={alto,largo,grosor,fecha/*,cpnombre,appP,appM,tel};
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
					String row[]={tipo,tamano,fecha/*,cpnombre,appP,appM,tel};
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
					String row[]={tlote,fecha/*,cpnombre,appP,appM,tel};
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
	}*/

	private void update(String id){
		String query="SELECT tarimas,cajas,cajasTarimas FROM Lote WHERE idLote = ?";

		List<Lote> lotes=new ArrayList<>();

		try {
			PreparedStatement statement=this.c.prepareStatement(query);


			statement.setString(1,id);

			ResultSet rs = statement.executeQuery();

			int tarimas,cajas,cajasTarimas;

			while(rs.next()){

				tarimas=rs.getInt("tarimas");
				cajas=rs.getInt("cajas");
				cajasTarimas=rs.getInt("cajasTarimas");

				lotes.add(new Lote(id,tarimas,cajas,cajasTarimas));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[][] tmp=new String[lotes.size()][];

		for(int i=0;i<lotes.size();i++){
			Lote lote=lotes.get(i);
			tmp[i]=new String[]{lote.getId(),Integer.toString(lote.getTarimas()),Integer.toString(lote.getCajas()),Integer.toString(lote.getCajasTarimas())};
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
