package proyectoDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class VerClientesWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Connection c;
	private JComboBox<String> comboBox;
	private JButton btnAceptar;
	private Hashtable<Integer,String> idRFC;
	/*private String[] columns={"RFC","NOMBRE","APELLIDO PAT","APELLIDO MAT","CALLE","COLONIA","NO EXT","NO INT",
			"TELEFONO","ESTADO","MUNICIPIO","EMAIL","TOTAL DE TARIMAS","MONTO TOTAL DE TARIMAS",
			"TOTAL DE MATERIALES","MONTO TOTAL DE MATERIALES"};*/
	private JLabel lblRfc;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblCalle;
	private JLabel lblColonia;
	private JLabel lblNoExterior;
	private JLabel lblNoInterior;
	private JLabel lblTelefono;
	private JLabel lblEstado;
	private JLabel lblMunicipio;
	private JLabel lblEmail;
	private JLabel lblTarimasTotales;
	private JLabel lblMontoTotalDe;
	private JLabel lblMaterialesTotales;
	private JLabel lblMontoTotalDe_1;
	
	/**
	 * Create the frame.
	 */
	public VerClientesWindow(Connection c) {
		super("Ver Clientes");
		
		this.c=c;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblRfcDeCliente = new JLabel("RFC de Cliente");
	
		ArrayList<String> clientes=new ArrayList<String>();
		this.idRFC=new Hashtable<Integer,String>();
		try{
			Statement query=this.c.createStatement();
			
			String qs="SELECT RFCCLIENTE, CNOMBRE, CAPELLIDOP, CAPELLIDOM FROM CLIENTE";
			
			ResultSet rs=query.executeQuery(qs);
			int i=0;
			while(rs.next()){
				String rfc,nombre,appP,appM;
				rfc=rs.getString("RFCCLIENTE");
				nombre=rs.getString("CNOMBRE");
				appP=rs.getString("CAPELLIDOP");
				appM=rs.getString("CAPELLIDOM");
				String res=rfc+" "+nombre+" "+appP+" "+appM;
				clientes.add(res);
				this.idRFC.put(i, rfc);
				i++;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		String tmp[]=new String[clientes.size()];
		
		
		comboBox = new JComboBox<String>(clientes.toArray(tmp));
		comboBox.addActionListener(this);
		
		btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(this);
		
		lblRfc = new JLabel("RFC:");
		
		lblNombre = new JLabel("Nombre:");
		
		lblApellidos = new JLabel("Apellidos:");
		
		lblCalle = new JLabel("Calle:");
		
		lblColonia = new JLabel("Colonia:");
		
		lblNoExterior = new JLabel("No. Exterior:");
		
		lblNoInterior = new JLabel("No. Interior:");
		
		lblTelefono = new JLabel("Telefono:");
		
		lblEstado = new JLabel("Estado:");
		
		lblMunicipio = new JLabel("Municipio:");
		
		lblEmail = new JLabel("Email:");
		
		lblTarimasTotales = new JLabel("Tarimas totales:");
		
		lblMontoTotalDe = new JLabel("Monto total de tarimas:");
		
		lblMaterialesTotales = new JLabel("Materiales totales:");
		
		lblMontoTotalDe_1 = new JLabel("Monto total de materiales:");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAceptar)
						.addComponent(lblRfcDeCliente))
					.addGap(18)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(146)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMontoTotalDe_1)
						.addComponent(lblTarimasTotales)
						.addComponent(lblColonia)
						.addComponent(lblApellidos)
						.addComponent(lblEmail)
						.addComponent(lblEstado)
						.addComponent(lblMunicipio)
						.addComponent(lblTelefono)
						.addComponent(lblNoInterior)
						.addComponent(lblNoExterior)
						.addComponent(lblCalle)
						.addComponent(lblNombre)
						.addComponent(lblRfc)
						.addComponent(lblMontoTotalDe)
						.addComponent(lblMaterialesTotales))
					.addContainerGap(197, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRfcDeCliente)
						.addComponent(lblRfc))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNombre)
					.addGap(12)
					.addComponent(lblApellidos)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCalle)
					.addGap(18)
					.addComponent(lblColonia)
					.addGap(18)
					.addComponent(lblNoExterior)
					.addGap(18)
					.addComponent(lblNoInterior)
					.addGap(18)
					.addComponent(lblTelefono)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEstado)
					.addGap(12)
					.addComponent(lblMunicipio)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEmail)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTarimasTotales)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMontoTotalDe)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMaterialesTotales)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMontoTotalDe_1)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnAceptar)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		
	}
	
	/*private void updateTable(String rfc){
		String qs="SELECT DISTINCT RFCCLIENTE, CNOMBRE, CAPELLIDOP, CAPELLIDOM, CCALLE, COLONIA,"
				+ "NOEXT, NOINT, TELEFONO, ESTADO, MUNICIPIO, EMAIL,"
				+ "TOTALTARIMAS, MONTOTARIMATOTAL, TOTALMATERIALES, MONTOMATERIALTOTAL "
				+ "FROM Cliente LEFT NATURAL JOIN "
				+ "(SELECT RFCCLIENTE, count(*) AS TOTALTARIMAS,sum(FTMONTO) AS MONTOTARIMATOTAL "
				+ "FROM FACTURATARIMA "
				+ "WHERE RFCCLIENTE='"+rfc+"' "
				+ "GROUP BY RFCCLIENTE) "
				+ "NATURAL JOIN ( "
				+ "SELECT RFCCLIENTE, count(*) AS TOTALMATERIALES, SUM(FMMONTO) AS MONTOMATERIALTOTAL "
				+ "FROM FACTURAMATERIAL "
				+ "WHERE RFCCLIENTE='"+rfc+"' "
				+ "GROUP BY RFCCLIENTE) "
				+ "WHERE RFCCLIENTE='"+rfc+"'";
		
		ArrayList<String[]> rows=new ArrayList<String[]>();
		
		try{
			Statement query=this.c.createStatement();
			ResultSet rs=query.executeQuery(qs);
			
			while(rs.next()){
				String resrfc,nombre,appP,appM,calle,colonia,noExt,noInt,tel,est,mun,email,ttar,mttar,tmat,mmat;
				resrfc=rs.getString("RFCCLIENTE");
				nombre=rs.getString("CNOMBRE");
				appP=rs.getString("CAPELLIDOP");
				appM=rs.getString("CAPELLIDOM");
				calle=rs.getString("CCALLE");
				colonia=rs.getString("COLONIA");
				noExt=Integer.toString(rs.getInt("NOEXT"));
				noInt=Integer.toString(rs.getInt("NOINT"));
				est=rs.getString("ESTADO");
				mun=rs.getString("MUNICIPIO");
				email=rs.getString("EMAIL");
				ttar=Integer.toString(rs.getInt("TOTALTARIMAS"));
				mttar=Integer.toString(rs.getInt("MONTOTARIMATOTAL"));
				tmat=Integer.toString(rs.getInt("TOTALMATERIALES"));
				mmat=Integer.toString(rs.getInt("MONTOMATERIALTOTAL"));
				
				String row[]={resrfc,nombre,appP,appM,calle,colonia,noExt,noInt,est,mun,email,ttar,mttar,tmat,mmat};
				rows.add(row);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		String tmp[][]=new String[rows.size()][];
		
		for(int i=0;i<rows.size();i++){
			tmp[i]=rows.get(i);
		}
		
		this.scrollPane.remove(this.table);
		
		this.table=new JTable(tmp,columns);
		
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		scrollPane.revalidate();
		scrollPane.repaint();
	}*/

	private void updateClient(String rfc){
		String qs="SELECT * FROM CLIENTE WHERE RFCCLIENTE='"+rfc+"'";
		
		try{
			Statement query=this.c.createStatement();
			
			ResultSet rs=query.executeQuery(qs);
			
			while(rs.next()){
				String resrfc,nombre,appP,appM,calle,colonia,noExt,noInt,tel,est,mun,email;
				resrfc=rs.getString("RFCCLIENTE");
				nombre=rs.getString("CNOMBRE");
				appP=rs.getString("CAPELLIDOP");
				appM=rs.getString("CAPELLIDOM");
				calle=rs.getString("CCALLE");
				colonia=rs.getString("COLONIA");
				noExt=Integer.toString(rs.getInt("NOEXT"));
				noInt=Integer.toString(rs.getInt("NOINT"));
				tel=rs.getString("TELEFONO");
				est=rs.getString("ESTADO");
				mun=rs.getString("MUNICIPIO");
				email=rs.getString("EMAIL");
				
				this.lblRfc.setText("RFC: "+resrfc);
				this.lblNombre.setText("Nombre: "+nombre);
				this.lblApellidos.setText("Apellidos: "+appP+" "+appM);
				this.lblCalle.setText("Calle: "+calle);
				this.lblColonia.setText("Colonia: "+colonia);
				this.lblNoExterior.setText("No. Exterior: "+noExt);
				this.lblNoInterior.setText("No. Interior: "+noInt);
				this.lblTelefono.setText("Telefono: "+tel);
				this.lblEstado.setText("Estado: "+est);
				this.lblMunicipio.setText("Municipio: "+mun);
				this.lblEmail.setText("Email: "+email);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		String qs2="SELECT RFCCLIENTE,COUNT(*) AS TOTALFACTURAS ,SUM(FTMONTO) AS MONTOTOTAL FROM FACTURATARIMA WHERE RFCCLIENTE='"+rfc+"' GROUP BY RFCCLIENTE";
		
		try{
			Statement query=this.c.createStatement();
			
			ResultSet rs=query.executeQuery(qs2);
			
			String totalFactura="0";
			String totalMonto="0";
			while(rs.next()){
				totalFactura=rs.getString("TOTALFACTURAS");
				totalMonto=rs.getString("MONTOTOTAL");				
			}

			this.lblTarimasTotales.setText("Tarimas totales: "+totalFactura);
			this.lblMontoTotalDe.setText("Monto total de tarimas: "+totalMonto);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		String qs3="SELECT RFCCLIENTE,COUNT(*) AS TOTALFACTURAS, SUM(FMMONTO) AS MONTOTOTAL FROM FACTURAMATERIAL WHERE RFCCLIENTE='"+rfc+"' GROUP BY RFCCLIENTE";
		
		try{
			
			Statement query=this.c.createStatement();
			
			ResultSet rs=query.executeQuery(qs3);
			
			String totalFactura="0";
			String totalMonto="0";
			
			while(rs.next()){
				totalFactura=rs.getString("TOTALFACTURAS");
				totalMonto=rs.getString("MONTOTOTAL");
				
			}
			
			this.lblMaterialesTotales.setText("Materiales totales: "+totalFactura);
			this.lblMontoTotalDe_1.setText("Monto total de materiales: "+totalMonto);
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnAceptar)){
			this.dispose();
		}
		else if(arg0.getSource().equals(this.comboBox)){
			int index=this.comboBox.getSelectedIndex();
			String rfc=this.idRFC.get(index);
			this.updateClient(rfc);
			//this.updateTable(rfc);
		}
		
	}
}
