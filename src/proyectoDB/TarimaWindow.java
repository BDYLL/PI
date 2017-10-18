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
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TarimaWindow extends JFrame implements ActionListener{

	private JPanel contentPane;

	private Connection c;
	private JTextField textField;
	private JLabel lblAlto;
	private JTextField textField_1;
	private JLabel lblLargo;
	private JTextField textField_2;
	private JLabel lblAncho;
	private JTextField textField_3;
	private JLabel lblTipo;
	private JComboBox<String> comboBox;
	private JLabel lblCantidad;
	private JTextField textField_4;
	private JLabel lblPrecio;
	private JTextField textField_5;
	private JButton btnAceptar;
	private JButton btnCancerlar;
	private JLabel lblMaderaUsada;
	private JTextField textField_6;
	private JLabel lblProveedorDeUsadas;
	private JComboBox<String> comboBox_1;
	private Hashtable<Integer,String> idRFC;

	/**
	 * Create the frame.
	 */
	public TarimaWindow(Connection c) {
		
		super("Tarimas");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.c=c;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLote = new JLabel("Lote");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		lblAlto = new JLabel("Alto");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		lblLargo = new JLabel("Largo");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		lblAncho = new JLabel("Ancho");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		lblTipo = new JLabel("Tipo");
		
		String[] options = {"nueva","reciclada"};
		
		comboBox = new JComboBox<String>(options);
		
		lblCantidad = new JLabel("Cantidad");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		lblPrecio = new JLabel("Precio");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		
		btnCancerlar = new JButton("Cancelar");
		btnCancerlar.addActionListener(this);
		
		lblMaderaUsada = new JLabel("Madera Usada");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		lblProveedorDeUsadas = new JLabel("Proveedor de usadas");
		
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
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAlto)
						.addComponent(lblLote)
						.addComponent(lblLargo)
						.addComponent(lblAncho)
						.addComponent(lblTipo)
						.addComponent(lblCantidad)
						.addComponent(lblPrecio)
						.addComponent(lblMaderaUsada)
						.addComponent(lblProveedorDeUsadas))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(btnAceptar)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancerlar))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(207, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLote))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAlto)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLargo)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAncho)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidad)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblPrecio))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaderaUsada))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProveedorDeUsadas)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(161, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(446, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancerlar)
						.addComponent(btnAceptar))
					.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void setConnection(Connection c){
		this.c=c;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnAceptar)){
			
			boolean executed=false;
			
			int index=this.comboBox.getSelectedIndex();
			String choice=this.comboBox.getItemAt(index);
			
			String qs="INSERT INTO Tarima VALUES('"+this.textField.getText()+"',"+this.textField_1.getText()
			+","+this.textField_2.getText()+","+this.textField_3.getText()+",'"+choice+"',"+this.textField_4.getText()
			+",'"+this.textField_5.getText()+"',"+this.textField_6.getText()+")";
			while(!executed){
				try {
					Statement query=c.createStatement();


					/*String qs="INSERT INTO Tarima VALUES('"+this.textField.getText()+"',"+this.textField_1.getText()
					+","+this.textField_2.getText()+","+this.textField_3.getText()+",'"+choice+"',"+this.textField_4.getText()
					+",'"+this.textField_5.getText()+"',"+this.textField_6.getText()+")";
					*/


					query.executeQuery(qs);
					executed=true;
				}
				catch(SQLIntegrityConstraintViolationException e1){
					
					qs="UPDATE TARIMA SET TCANTIDAD='"+this.textField_5.getText()+"' WHERE TLOTE='"+this.textField.getText()+"'";
					
					executed=false;
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					executed=true;
				}
			}
			
			if(this.comboBox.getSelectedIndex()==1){
				
				//String rfc=this.comboBox_1.getItemAt(this.comboBox_1.getSelectedIndex());
				
				String rfc=this.idRFC.get(this.comboBox_1.getSelectedIndex());
				
				String qs2="INSERT INTO PROVEEDANADAS VALUES('"+this.textField.getText()+"','"+rfc+"',SYSDATE)";
				
				executed=false;
				
				while(!executed){
					try{
						Statement query2=this.c.createStatement();

						query2.executeQuery(qs2);
						executed=true;
					}
					catch(SQLIntegrityConstraintViolationException e1){
						qs2="UPDATE PROVEEDANADAS SET PDUltimaFecha=SYSDATE WHERE TLOTE='"+this.textField.getText()+"' AND RFCPROVEEDOR='"+rfc+"'";
						executed=false;
					}
					catch(SQLException e2){
						e2.printStackTrace();
						//System.out.println(qs2);
						executed=true;
					}
				}
			}
			
			this.dispose();
		}
		else if(arg0.getSource().equals(this.btnCancerlar)){
			this.dispose();
		}
		
	}

}
