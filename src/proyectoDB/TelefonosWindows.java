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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class TelefonosWindows extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Connection c;
	private JTextField textField;
	private JButton btnAceptar,btnCancelar;
	private JComboBox<String> comboBox;
	private int numP,numCP;
	
	private Hashtable<Integer,String> idRFCProv, idRFCCP;

	/**
	 * Create the frame.
	 */
	public TelefonosWindows(Connection c) {
		super("Telefonos");
		this.c=c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(this);
		btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener(this);
		
		JLabel lblRfc = new JLabel("Proveedores y Contactos");
		
		ArrayList<String> rfcs=new ArrayList<String>();
		this.idRFCProv=new Hashtable<Integer,String>();
		this.idRFCCP=new Hashtable<Integer,String>();
		this.numCP=0;
		this.numP=0;
		
		try{
			Statement query=this.c.createStatement();
			
			String qs="SELECT RFCPROVEEDOR, PNOMBRE FROM PROVEEDOR";
			
			ResultSet rs=query.executeQuery(qs);
			
			int i=0;
			
			while(rs.next()){
				String rfc,nombre;
				rfc=rs.getString("RFCPROVEEDOR");
				nombre=rs.getString("PNOMBRE");
				String res=rfc+" "+nombre;
				rfcs.add(res);
				this.idRFCProv.put(i, rfc);
				i++;
				this.numP++;
			}
			
			Statement query2=this.c.createStatement();
			String qs2="SELECT IDCP, CPNombre FROM CONTACTOPROVEEDOR";
			
			ResultSet rs2=query2.executeQuery(qs2);
			
			while(rs2.next()){
				String nombre,id;
				id=rs2.getString("IDCP");
				nombre=rs2.getString("CPNombre");
				rfcs.add(nombre);
				this.idRFCCP.put(i,id);
				i++;
				this.numCP++;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		String tmp[]=new String[rfcs.size()];
		
		comboBox = new JComboBox<String>(rfcs.toArray(tmp));
		
		JLabel lblTelefono = new JLabel("Telefono");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(219, Short.MAX_VALUE)
					.addComponent(btnAceptar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancelar)
					.addGap(24))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addComponent(lblRfc)
					.addGap(50)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(293, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(lblTelefono)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(220, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRfc)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnAceptar)){
			
			int index=this.comboBox.getSelectedIndex();
			
			String person="";
			String qs="";
			
			if(index<this.numP){
				qs="INSERT INTO PTELEFONO VALUES";
				person=this.idRFCProv.get(index);
			}
			else{
				qs="INSERT INTO CPTELEFONO VALUES";
				person=this.idRFCCP.get(index);
			}
			
			try{
				Statement query=this.c.createStatement();
				
				qs=qs+"("+this.textField.getText()+",'"+person+"')";
				
				query.executeQuery(qs);
			}
			catch(SQLException e){
				System.out.println(qs);
				e.printStackTrace();
			}
			
			
			this.dispose();
		}
		else if(arg0.getSource().equals(this.btnCancelar)){
			this.dispose();
		}
	}
}
