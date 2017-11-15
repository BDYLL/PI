package proyectoDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AdministracionWindow extends JFrame implements ActionListener {

	private JPanel contentPane;

	private JButton btnIngresarUsuarios, btnBajaDeUsuarios;
	
	private Connection c;
	private JButton btnRegresar;
	/**
	 * Create the frame.
	 */
	public AdministracionWindow(Connection c) {
		super("Administracion");
		this.c=c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnIngresarUsuarios = new JButton("Ingresar Usuarios");
		this.btnIngresarUsuarios.addActionListener(this);
		
		btnBajaDeUsuarios = new JButton("Baja de Usuarios");
		this.btnBajaDeUsuarios.addActionListener(this);
		
		btnRegresar = new JButton("Regresar");
		this.btnRegresar.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRegresar)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnIngresarUsuarios)
							.addGap(32)
							.addComponent(btnBajaDeUsuarios, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIngresarUsuarios)
						.addComponent(btnBajaDeUsuarios))
					.addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
					.addComponent(btnRegresar)
					.addGap(34))
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnIngresarUsuarios)){
			IngresarUsuariosWindow iuw=new IngresarUsuariosWindow(this.c);
			iuw.setVisible(true);
		}
		else if(arg0.getSource().equals(this.btnBajaDeUsuarios)){
			BajaUsuariosWindow buw=new BajaUsuariosWindow(this.c);
			buw.setVisible(true);
		}
		else if(arg0.getSource().equals(this.btnRegresar)){
			OptionsWindows2 ow=new OptionsWindows2(this.c);
			ow.setVisible(true);
			this.dispose();
		}
	}

}
