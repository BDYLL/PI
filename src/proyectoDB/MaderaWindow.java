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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class MaderaWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private JButton btnAceptar,btnCancelar;
	
	private Connection c;
	private JLabel lblRfc;
	private JComboBox<String> comboBox;

	private Hashtable<Integer,String> idRFC;

	/**
	 * Create the frame.
	 */
	public MaderaWindow(Connection c) {
		super("Maderas");
		this.c=c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDimensiones = new JLabel("Dimensiones");
		
		JLabel lblAlto = new JLabel("Alto");
		
		JLabel lblLargo = new JLabel("Largo");
		
		JLabel lblGrosor = new JLabel("Grosor");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener(this);
		
		JLabel lblPrecio = new JLabel("Precio");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		lblRfc = new JLabel("RFC");
		
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblLargo)
							.addComponent(lblAlto)
							.addComponent(lblGrosor))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblPrecio)
							.addComponent(lblDimensiones)
							.addComponent(lblCantidad)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(247, Short.MAX_VALUE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnAceptar)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnCancelar)
								.addGap(32)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(142)
							.addComponent(lblRfc)
							.addGap(27)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(146))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDimensiones)
						.addComponent(lblRfc)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAlto)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLargo)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGrosor)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrecio))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidad)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addGap(22))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.btnAceptar)){
		
			Statement query;
			String qs="INSERT INTO MADERA VALUES("+this.textField.getText()+","+this.textField_1.getText()+","
					+this.textField_2.getText()+","+this.textField_3.getText()+","+this.textField_4.getText()+")";
			
			boolean executed=false;
			
			
			while(!executed){
				try{
					query=this.c.createStatement();
					
					query.executeQuery(qs);
					executed=true;
				}
				catch(SQLIntegrityConstraintViolationException ex1){
					qs="UPDATE MADERA SET MADPRECIO="+this.textField_3.getText()+", MADCANTIDAD="+this.textField_4.getText()
					+" WHERE MADAlto="+this.textField.getText()+"AND MADLargo="+this.textField_1.getText()+"AND MADGROSOR="+this.textField_2.getText();
					executed=false;
				}
				catch(SQLException ex2){
					ex2.printStackTrace();
					//System.out.println(qs);
					executed=true;
				}
			}
			
			executed=false;
			
			int index2=this.comboBox.getSelectedIndex();
			
			String rfc=this.idRFC.get(index2);
			
			Statement query2;
			
			String qs2="INSERT INTO PROVEEMADERA VALUES('"+rfc+"',"+this.textField.getText()+","+this.textField_1.getText()
			+","+this.textField_2.getText()+",SYSDATE)";
			
			while(!executed){
				try{
					query2=this.c.createStatement();
					
					query2.executeQuery(qs2);
					
					executed=true;
				}
				catch(SQLIntegrityConstraintViolationException ex2){
					
					qs2="UPDATE PROVEEMADERA SET PMADUltimaFecha=SYSDATE WHERE RFCPROVEEDOR='"+rfc+
							"' AND MADALTO="+this.textField.getText()+" AND MADLARGO="+this.textField_1.getText()+
							" AND MADGROSOR="+this.textField_2.getText();

					executed=false;
				}
				catch(SQLException ex3){
					ex3.printStackTrace();
				
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
