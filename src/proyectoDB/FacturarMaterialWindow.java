package proyectoDB;

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
import javax.swing.JTextField;
import javax.swing.JButton;

public class FacturarMaterialWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Connection c;
	private JTextField textField;
	private JTextField textField_1;

	private JButton btnAceptar,btnCancelar;
	
	private JComboBox<String> comboBox,comboBox_1,comboBox_2;
	
	private Hashtable<Integer,String> idRFC;
	private Hashtable<Integer,String[]> idMaterial;
	

	/**
	 * Create the frame.
	 */
	public FacturarMaterialWindow(Connection c) {
		super("Facturar material");
		
		this.c=c;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblRfc = new JLabel("RFC");
		
		ArrayList<String> clientes=new ArrayList<String>();
		this.idRFC=new Hashtable<Integer,String>();
		try{
			Statement query=this.c.createStatement();
			
			String qs="SELECT RFCCliente, Cnombre, CApellidoP, CApellidoM FROM Cliente";
			
			ResultSet rs=query.executeQuery(qs);
			
			int i=0;
			while(rs.next()){
				String rfc,name,appP,appM;
				rfc=rs.getString("RFCCliente");
				name=rs.getString("Cnombre");
				appP=rs.getString("CApellidoP");
				appM=rs.getString("CApellidoM");
				String res=rfc+" "+name+" "+appP+" "+appM;
				clientes.add(res);
				this.idRFC.put(i, rfc);
				i++;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		String tmp[]=new String[clientes.size()];
		
		comboBox = new JComboBox<String>(clientes.toArray(tmp));
		
		JLabel lblMaterial = new JLabel("Material");
		
		ArrayList<String> materiales=new ArrayList<String>();
		this.idMaterial=new Hashtable<Integer,String[]>();
		
		try{
			Statement query=this.c.createStatement();
			
			String qs="SELECT MATipo, MATamano FROM Material";
			
			ResultSet rs=query.executeQuery(qs);
			
			int i=0;
			while(rs.next()){
				String tipo,tamano;
				tipo=rs.getString("MATipo");
				//tamano=rs.getString("MATamano");
				tamano=Integer.toString(rs.getInt("MATamano"));
				String res=tipo+" "+tamano;
				String key[]=new String[2];
				key[0]=tipo;
				key[1]=tamano;
				materiales.add(res);
				this.idMaterial.put(i,key);
				i++;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		//System.out.println(this.idMaterial.toString());
		
		String tmp2[]=new String[materiales.size()];
		
		comboBox_1 = new JComboBox<String>(materiales.toArray(tmp2));
		
		JLabel lblMonto = new JLabel("Monto");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblFechammddyyyy = new JLabel("Fecha (MM-DD-YYYY)");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		String formasDePago[]={"efectivo","credito","desconocido"};
		
		JLabel lblFormaDePago = new JLabel("Forma de pago");
		
		comboBox_2 = new JComboBox<String>(formasDePago);
		
		btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addComponent(lblRfc)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(lblMaterial)
							.addGap(34)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFechammddyyyy)
								.addComponent(lblMonto))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnAceptar)
								.addComponent(lblFormaDePago))
							.addGap(27)
							.addComponent(btnCancelar)))
					.addContainerGap(504, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRfc)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaterial)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMonto))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechammddyyyy)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFormaDePago)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		if(arg.getSource().equals(this.btnAceptar)){
			try{
				Statement query=this.c.createStatement();
				
				String rfc=this.idRFC.get(/*this.comboBox.getSelectedItem()*/this.comboBox.getSelectedIndex());
				
				String keyMaterial[]=this.idMaterial.get(/*this.comboBox_1.getSelectedItem()*/this.comboBox_1.getSelectedIndex());
				
				String tipoDePago=this.comboBox_2.getItemAt(this.comboBox_2.getSelectedIndex());
				
				String qs="INSERT INTO FACTURAMATERIAL VALUES(SYS_GUID(),'"+rfc+"','"+keyMaterial[0]+"',"+keyMaterial[1]+","+
						this.textField.getText()+",to_date('"+this.textField_1.getText()+"','MM-DD-YYYY'),'"+tipoDePago+"')";
				//System.out.println(qs);
				query.executeQuery(qs);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			this.dispose();
		}
		else if(arg.getSource().equals(this.btnCancelar)){
			this.dispose();
		}
	}
}
