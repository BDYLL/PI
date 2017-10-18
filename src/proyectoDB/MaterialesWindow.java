package proyectoDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class MaterialesWindow extends JFrame implements ActionListener{

	private JPanel contentPane;

	private JButton btnAceptar,btnCancelar;
	
	private JComboBox<String> comboBox;
	
	private Connection c;
	private JLabel lblTamano;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblMarca;
	private JTextField textField_2;
	private JLabel lblPrecio;
	private JTextField textField_3;
	private JLabel lblProveedor;
	private JComboBox<String> comboBox_1;
	
	private Hashtable<Integer,String> idRFC;

	/**
	 * Create the frame.
	 */
	public MaterialesWindow(Connection c) {
		super("Materiales");
		this.c=c;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener(this);
		
		JLabel lblTipo = new JLabel("Tipo");
		
		String tipos[]={"clavo","grapa","engrapadora","emplayer"};
		
		comboBox = new JComboBox<String>(tipos);
		
		lblTamano = new JLabel("Tamano");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		lblMarca = new JLabel("Marca");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		lblPrecio = new JLabel("Precio");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		lblProveedor = new JLabel("Proveedor");
		
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
		
		comboBox_1 = new JComboBox<String>(proveedores.toArray(tmp));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAceptar)
							.addGap(5)
							.addComponent(btnCancelar)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblProveedor)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
											.addComponent(lblCantidad)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTamano)
												.addComponent(lblTipo))
											.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPrecio, Alignment.TRAILING)
										.addComponent(lblMarca, Alignment.TRAILING))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(340, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(61)
							.addComponent(lblTipo)
							.addGap(18))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(30)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTamano)
						.addComponent(lblMarca)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrecio)
						.addComponent(lblCantidad)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProveedor)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addGap(26))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e)  {
		if(e.getSource().equals(this.btnAceptar)){
		
			int index=this.comboBox.getSelectedIndex();
			String tipo=this.comboBox.getItemAt(index);
			
			Statement query;
			String qs="INSERT INTO Material VALUES('"+tipo+"',"+this.textField.getText()+","+this.textField_1.getText()
			+",'"+this.textField_2.getText()+"',"+this.textField_3.getText()+")";
			/*try{
				
				query=c.createStatement();
				
				qs="INSERT INTO Material VALUES('"+tipo+"',"+this.textField.getText()+","+this.textField_1.getText()
				+",'"+this.textField_2.getText()+"',"+this.textField_3.getText()+")";
				
				query.executeQuery(qs);
				
			}
			catch(SQLIntegrityConstraintViolationException ex1){
				String qs="UPDATE MATERIAL SET MATCantidad="+this.textField_1+" WHERE MATipo="+tipo+" AND MATamano="+this.textField;
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}*/
			
			boolean executed=false;
			
			while(!executed){
				try{
					query=c.createStatement();
							
					query.executeQuery(qs);
					executed=true;
					query.close();
				}
				catch(SQLIntegrityConstraintViolationException ex1){
					
					qs="UPDATE MATERIAL SET MATCantidad="+this.textField_1.getText()+" WHERE MATipo='"+tipo+"' AND MATamano="+this.textField.getText();
					executed=false;
				}
				catch(SQLException ex){
					//System.out.println(qs);
					executed=true;
					ex.printStackTrace();
				}
				
			}
			
			executed=false;
			
			int index2=this.comboBox_1.getSelectedIndex();
			
			String rfc=this.idRFC.get(index2);
			
			Statement query2;
			
			String qs2="INSERT INTO PROVEEMATERIAL VALUES('"+rfc+"','"+tipo+"',"+this.textField.getText()+",SYSDATE)";
			
			while(!executed){
				try{
					query2=this.c.createStatement();
					
					query2.executeQuery(qs2);
					
					executed=true;
				}
				catch(SQLIntegrityConstraintViolationException ex2){
					qs2="UPDATE PROVEEMATERIAL SET PMATUltimaFecha=SYSDATE WHERE RFCPROVEEDOR="+rfc+" AND MATIPO='"+tipo+"' AND MATAMANO="+this.textField.getText();
					executed=false;
				}
				catch(SQLException ex3){
					ex3.printStackTrace();
					System.out.println(qs2);
					executed=true;
				}
			}
			
			this.dispose();
		}
		else if(e.getSource().equals(this.btnCancelar)){
			this.dispose();
		}
		
	}
	
	public void setConnection(Connection c){
		this.c=c;
	}
}
