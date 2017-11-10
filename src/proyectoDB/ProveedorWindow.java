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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ProveedorWindow extends JFrame implements ActionListener{

	private JPanel contentPane;

	private Connection c;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	private JButton btnAceptar,btnCancelar;
	
	private Hashtable<Integer,String> indexIDTable;


	/**
	 * Create the frame.
	 */
	public ProveedorWindow(Connection c) {
		super("Ingresar proveedor");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.c=c;
		
		/*ArrayList<String> names=new ArrayList<String>();
		indexIDTable=new Hashtable<Integer,String>();
		try{
			Statement retriveContacts=c.createStatement();
			String qs="SELECT IDCP, CPNombre, CPApellidoP, CPApellidoM FROM ContactoProveedor";
			ResultSet rs=retriveContacts.executeQuery(qs);
			int i=0;
		
			while(rs.next()){
				String id,name,apellidoP,apellidoM;
				id=rs.getString("IDCP");
				name=rs.getString("CPNombre");
				apellidoP=rs.getString("CPApellidoP");
				apellidoM=rs.getString("CPApellidoM");
				String res=name+" "+apellidoP+" "+apellidoM;
				names.add(res);
				indexIDTable.put(i, id);
				i++;
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}*/
		
		JLabel lblRfc = new JLabel("RFC");
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblCalle = new JLabel("Calle");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblColonia = new JLabel("Colonia");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("No Externo");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblNoInterno = new JLabel("No Interno");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNombre))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblColonia))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNoInterno)
									.addComponent(lblNewLabel))
								.addComponent(lblCalle)
								.addComponent(lblRfc))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(137)
									.addComponent(btnAceptar)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCancelar))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(34)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRfc))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCalle))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColonia)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNoInterno)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnAceptar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setConnection(Connection c){
		this.c=c;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnAceptar)){
			try {
				/*int index=this.comboBox.getSelectedIndex();
				String id=this.indexIDTable.get(index);*/
				
				Statement query=c.createStatement();
				String qs="INSERT INTO PROVEEDOR VALUES('"+this.textField.getText()+"','"+this.textField_1.getText()
				+"','"+this.textField_2.getText()+"','"+this.textField_3.getText()+"',"+this.textField_4.getText()+","+this.textField_5.getText()+")";

				
				query.executeQuery(qs);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			this.dispose();
		}
		else if(arg0.getSource().equals(this.btnCancelar)){
			this.dispose();
		}
	}
}
