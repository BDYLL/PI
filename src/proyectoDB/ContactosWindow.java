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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ContactosWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private Connection c;
	
	private JButton btnAceptar,btnCancelar;
	
	private JComboBox<String> comboBox;

	private Hashtable<Integer,String> idCP;
	
	public ContactosWindow(Connection c) {
		
		super("Ingresar contactos");
	
		this.c=c;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 489, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblPuesto = new JLabel("Puesto");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		
		
		ArrayList<String> contactos=new ArrayList<String>();
		this.idCP=new Hashtable<Integer,String>();
		
		try{
			
			Statement query=this.c.createStatement();
			
			String qs="SELECT rfcproveedor,pnombre from proveedor";
			
			ResultSet rs=query.executeQuery(qs);
			int i=0;
			while(rs.next()){
				String rfc,nombre;
				rfc=rs.getString("rfcproveedor");
				nombre=rs.getString("pnombre");
				String res=rfc+" "+nombre;
				contactos.add(res);
				this.idCP.put(i, rfc);
				i++;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		String[] tmp=new String[contactos.size()];
		
		
		comboBox = new JComboBox<String>(contactos.toArray(tmp));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblApellidoMaterno)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNombre)
									.addComponent(lblApellidoPaterno))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProveedor)
								.addComponent(lblPuesto))
							.addGap(36)))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(191, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(260, Short.MAX_VALUE)
					.addComponent(btnAceptar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancelar)
					.addGap(22))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellidoPaterno)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblApellidoMaterno)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPuesto))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProveedor)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addGap(23))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setConnection(Connection c){
		this.c=c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.btnAceptar)){
			int index=this.comboBox.getSelectedIndex();
			String prov=this.idCP.get(index);
			try{
				Statement query=c.createStatement();
				String qs="INSERT INTO CONTACTOPROVEEDOR VALUES(SYS_GUID(),'"+this.textField.getText()+"','"
						+this.textField_1.getText()+"','"+this.textField_2.getText()+"','"+this.textField_3.getText()+"','"+prov+"')";
				query.executeQuery(qs);
				this.dispose();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		else if(e.getSource().equals(this.btnCancelar)){
			this.dispose();
		}
		
	}
}
