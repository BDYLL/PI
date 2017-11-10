package proyectoDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class IngresarUsuariosWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblTipoDeUsuario;
	private JComboBox<String> comboBox;
	private Connection c;
	private JButton btnAceptar,btnCancelar;

	/**
	 * Create the frame.
	 */
	public IngresarUsuariosWindow(Connection c) {
		super("Ingresar Usuarios");
		this.c=c;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		
		passwordField = new JPasswordField();
		
		lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		
		String options[]={"admin","empleado"};
		
		comboBox = new JComboBox<String>(options);
		
		btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombreDeUsuario)
						.addComponent(lblContrasea)
						.addComponent(lblTipoDeUsuario))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, 114, 114, 114)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(134, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(208, Short.MAX_VALUE)
					.addComponent(btnAceptar)
					.addGap(18)
					.addComponent(btnCancelar)
					.addGap(29))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreDeUsuario)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContrasea))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipoDeUsuario)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addGap(32))
		);
		contentPane.setLayout(gl_contentPane);

		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnAceptar)){
			
			int index=this.comboBox.getSelectedIndex();
			
			String tipo=this.comboBox.getItemAt(index);
		
			char[] password=this.passwordField.getPassword();
			
			String passwordS=new String(password);

			String user= textField.getText();

			String qs="INSERT INTO Usuario (nombreUsuario,contrasena,tipo) VALUES(?,?,?)";
			
			try{

				PreparedStatement ps = this.c.prepareStatement(qs);

				ps.setString(1,user);
				ps.setString(2,passwordS);
				ps.setString(3,tipo);

				ps.executeUpdate();

				AdministracionWindow aw=new AdministracionWindow(this.c);
				aw.setVisible(true);
				this.dispose();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		else if(arg0.getSource().equals(this.btnCancelar)){
			this.dispose();
		}
	}
}
