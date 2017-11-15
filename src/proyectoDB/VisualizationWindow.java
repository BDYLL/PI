package proyectoDB;

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

public class VisualizationWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Connection c;
	private JButton btnVerLotes;
	private JButton btnVerProdEnvasado;
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

		
		btnVerLotes = new JButton("Ver lotes");
		this.btnVerLotes.addActionListener(this);
		
		btnVerProdEnvasado = new JButton("Ver producto envasado");
		this.btnVerProdEnvasado.addActionListener(this);
		
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
								.addComponent(btnVerLotes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(56)
							.addComponent(btnVerProdEnvasado, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVerLotes)
						.addComponent(btnVerProdEnvasado))
					.addGap(30)
					.addComponent(btnVerClientes)
					.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
					.addComponent(btnRegresar)
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);

		this.setLocationRelativeTo(null);

		this.btnVerClientes.setEnabled(false);
		this.btnVerClientes.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		JButton button=(JButton)arg.getSource();
		if(button.equals(this.btnVerLotes)){
			VerLotes vpw=new VerLotes(this.c);
			vpw.setVisible(true);
		}
		else if(button.equals(this.btnVerProdEnvasado)){
			VerProductoEnvasado productoEnvasado=new VerProductoEnvasado(this.c);
			productoEnvasado.setVisible(true);
		}
		else if(button.equals(this.btnRegresar)){
			OptionsWindows2 ow=new OptionsWindows2(this.c);
			ow.setVisible(true);
			this.dispose();
		}
		else if(button.equals(this.btnVerClientes)){
			VerClientesWindow vcw=new VerClientesWindow(this.c);
			vcw.setVisible(true);
		}
	}
}
