package proyectoDB;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BajaUsuariosWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private Connection c;
	private ArrayList<String> usuarios;
	private JButton btnAceptar,btnCancelar;
	
	/**
	 * Create the frame.
	 */
	public BajaUsuariosWindow(Connection c) {
		super("Baja de Usuarios");
		this.c=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		usuarios=new ArrayList<String>();
		
		try{
			Statement query=this.c.createStatement();
			
			String qs="SELECT nombreUsuario, tipo FROM Usuario";
			
			ResultSet rs=query.executeQuery(qs);
			
			while(rs.next()){
				String nombre=rs.getString("NOMBREUSUARIO");
				String tipo=rs.getString("TIPO");
				if(!"administrador".equals(tipo)){
					usuarios.add(nombre);
				}
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		String tmp[]=new String[this.usuarios.size()];
		
		comboBox = new JComboBox<String>(this.usuarios.toArray(tmp));
		
		btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblUsuario)
					.addGap(43)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(289, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(217, Short.MAX_VALUE)
					.addComponent(btnAceptar)
					.addGap(18)
					.addComponent(btnCancelar)
					.addGap(20))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(208, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(209, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnAceptar))
					.addGap(28))
		);
		contentPane.setLayout(gl_contentPane);

		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnAceptar)){
			
			String usuario=this.usuarios.get(this.comboBox.getSelectedIndex());
			
			try{
				
				String qs="DELETE FROM Usuario WHERE nombreUsuario= ? ";

				PreparedStatement query = this.c.prepareStatement(qs);

				query.setString(1,usuario);

				query.executeUpdate();

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
