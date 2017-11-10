package proyectoDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VisualizationWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Connection c;
	private JButton btnVerProveedores;
	private JButton btnVerFacturas;
	private JButton btnRegresar,btnVerClientes;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizationWindow frame = new VisualizationWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VisualizationWindow(Connection c) {
		super("Visualizar Informacion");
		this.c=c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/*try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			
			this.c = DriverManager.getConnection("jdbc:oracle:thin:@info.gda.itesm.mx:1521:alum", "a01228648", "tec8648");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		btnVerProveedores = new JButton("Ver proveedores");
		this.btnVerProveedores.addActionListener(this);
		
		btnVerFacturas = new JButton("Ver Facturas");
		this.btnVerFacturas.addActionListener(this);
		
		btnRegresar = new JButton("Regresar");
		this.btnRegresar.addActionListener(this);
		
		btnVerClientes = new JButton("Ver clientes");
		this.btnVerClientes.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRegresar)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnVerClientes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnVerProveedores, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(56)
							.addComponent(btnVerFacturas, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVerProveedores)
						.addComponent(btnVerFacturas))
					.addGap(30)
					.addComponent(btnVerClientes)
					.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
					.addComponent(btnRegresar)
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		JButton button=(JButton)arg.getSource();
		if(button.equals(this.btnVerProveedores)){
			VerProveedoresWindow vpw=new VerProveedoresWindow(this.c);
			vpw.setVisible(true);
		}
		else if(button.equals(this.btnVerFacturas)){
			VerFacturasWindow vfw=new VerFacturasWindow(this.c);
			vfw.setVisible(true);
		}
		else if(button.equals(this.btnRegresar)){
			OptionsWindows ow=new OptionsWindows(this.c);
			ow.setVisible(true);
			this.dispose();
		}
		else if(button.equals(this.btnVerClientes)){
			VerClientesWindow vcw=new VerClientesWindow(this.c);
			vcw.setVisible(true);
		}
	}
}
