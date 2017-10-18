package proyectoDB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JButton;

public class LoginWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnIngresar,btnSalir;
	private Connection c;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		super("Login");
		
		try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			
			this.c = DriverManager.getConnection("jdbc:oracle:thin:@info.gda.itesm.mx:1521:alum", "a01228648", "tec8648");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		
		passwordField = new JPasswordField();
		
		
		BufferedImage image=null;
		
		try {
			image=ImageIO.read(new File("img/Tarimas y Cajas.gif"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Image im2=image.getScaledInstance(100, 100, 0);
		
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(im2));
		
		
		
		
		btnIngresar = new JButton("Ingresar");
		this.btnIngresar.addActionListener(this);
		JLabel lblCopyright = new JLabel("<html>Este modulo le permitira administrar la base de datos de: <br>"
				+ "Proveedores, facturas, tarimas, contactos, clientes</br></html>");
		lblCopyright.setFont(new Font("Courier New",Font.ITALIC,12));
		
		btnSalir = new JButton("Salir");
		this.btnSalir.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblUsuario)
								.addComponent(lblContrasea)
								.addComponent(lblCopyright, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(passwordField)
								.addComponent(textField))
							.addContainerGap(144, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnIngresar)
							.addGap(18)
							.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(34))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuario))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContrasea))
					.addGap(96)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalir)
						.addComponent(btnIngresar))
					.addPreferredGap(ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
					.addComponent(lblCopyright)
					.addGap(29))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(314, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);	
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		if(arg.getSource().equals(this.btnIngresar)){
			try{
				Statement query=this.c.createStatement();
				
				String user,password;
				
				user=this.textField.getText();
				
				char[] tmp=this.passwordField.getPassword();
				
				password=new String(tmp);
				
				
				String qs="SELECT * FROM USUARIO WHERE NOMBREUSUARIO='"+user+"' AND CONTRASENA='"+password+"'";
		
				ResultSet rs=query.executeQuery(qs);
				
				String result[]=new String[3];
				
				while(rs.next()){
					result[0]=rs.getString("NOMBREUSUARIO");
					result[1]=rs.getString("CONTRASENA");
					result[2]=rs.getString("TIPO");
				}
				
				if(result[0]==null){
					this.textField.setBackground(Color.RED);
					this.passwordField.setText("");
				}
				if(result[1]==null){
					this.passwordField.setBackground(Color.RED);
					this.passwordField.setText("");
				}
				if("administrador".equals(result[2])){
					OptionsWindows ow=new OptionsWindows(this.c);
					ow.setVisible(true);
					this.dispose();
				}
				else if("empleado".equals(result[2])){
					MainWindow mw=new MainWindow(this.c,false);
					mw.setVisible(true);
					this.dispose();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		else if(arg.getSource().equals(this.btnSalir)){
			this.dispose();
		}
	}
}


