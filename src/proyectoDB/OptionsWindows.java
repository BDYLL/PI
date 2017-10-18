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

public class OptionsWindows extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Connection c;
	private JButton btnVerInformacion,btnInsertarInformacion,btnAdministracion;
	/**
	 * Create the frame.
	 */
	public OptionsWindows(Connection c) {
		super("Opciones");
		this.c=c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnVerInformacion = new JButton("Ver informacion");
		btnVerInformacion.addActionListener(this);
		btnInsertarInformacion = new JButton("Insertar informacion");
		this.btnInsertarInformacion.addActionListener(this);
		
		btnAdministracion = new JButton("Administracion");
		this.btnAdministracion.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnAdministracion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnVerInformacion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnInsertarInformacion)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVerInformacion)
						.addComponent(btnInsertarInformacion))
					.addGap(73)
					.addComponent(btnAdministracion)
					.addContainerGap(93, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.btnVerInformacion)){
			VisualizationWindow vw=new VisualizationWindow(this.c);
			vw.setVisible(true);
			this.dispose();
		}
		else if(e.getSource().equals(this.btnInsertarInformacion)){
			MainWindow mw=new MainWindow(this.c,true);
			mw.setVisible(true);
			this.dispose();
		}
		else if(e.getSource().equals(this.btnAdministracion)){
			AdministracionWindow aw=new AdministracionWindow(this.c);
			aw.setVisible(true);
			this.dispose();
		}
	}
}
