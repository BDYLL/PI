package proyectoDB;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainWindow extends JFrame implements ActionListener{

	private JPanel contentPane;

	private Connection c;
	
	private JButton btnIngresarCliente,btnIngresarProveedor,btnContactos,btnFacturar;
	private JButton btnTarimas;
	private JButton btnIngresarMateriales;
	private JButton btnIngresarMaderas;
	private JButton btnFacturarMaterial;
	private JButton btnIngresarTelefono;
	private JButton btnRegresar;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow(Connection c, boolean administrador) {
		super("Ingresar Datos");
		this.c=c;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.btnIngresarCliente = new JButton("Ingresar Cliente");
		this.btnIngresarCliente.addActionListener(this);
		
		btnIngresarProveedor = new JButton("Ingresar Proveedor");
		this.btnIngresarProveedor.addActionListener(this);
		
		btnContactos = new JButton("Ingresar Contacto");
		btnContactos.addActionListener(this);
		
		btnTarimas = new JButton("Ingresar Tarimas");
		btnTarimas.addActionListener(this);
		
		btnFacturar = new JButton("Facturar Tarima");
		btnFacturar.addActionListener(this);
		
		
		/*BufferedImage image=null;
		
		try {
			image=ImageIO.read(new File("/home/diego/workspace/proyectoDB/src/proyectoDB/Tarimas y Cajas.gif"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Image im2=image.getScaledInstance(100, 100, 0);
		*/
		JLabel imgLabel=new JLabel(/*new ImageIcon(im2)*/);
		
		btnIngresarMateriales = new JButton("Ingresar Materiales");
		this.btnIngresarMateriales.addActionListener(this);
		
		btnIngresarMaderas = new JButton("Ingresar Maderas");
		this.btnIngresarMaderas.addActionListener(this);
		
		btnFacturarMaterial = new JButton("Facturar Material");
		this.btnFacturarMaterial.addActionListener(this);
		
		btnIngresarTelefono = new JButton("Ingresar telefono");
		this.btnIngresarTelefono.addActionListener(this);
		
		btnRegresar = new JButton("Regresar");
		this.btnRegresar.addActionListener(this);
		
		this.btnRegresar.setEnabled(administrador);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnIngresarProveedor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnFacturar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnIngresarCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnFacturarMaterial, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnIngresarTelefono, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnIngresarMaderas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnIngresarMateriales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnTarimas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnContactos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(151)
							.addComponent(imgLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(50)
							.addComponent(btnRegresar)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIngresarCliente)
						.addComponent(btnContactos))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTarimas)
						.addComponent(btnIngresarProveedor))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFacturar)
						.addComponent(btnIngresarMateriales))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFacturarMaterial)
						.addComponent(btnIngresarMaderas))
					.addGap(28)
					.addComponent(btnIngresarTelefono)
					.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
					.addComponent(btnRegresar)
					.addGap(32)
					.addComponent(imgLabel)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		/*try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			
			this.c = DriverManager.getConnection("jdbc:oracle:thin:@info.gda.itesm.mx:1521:alum", "a01228648", "tec8648");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button=(JButton)e.getSource();
		if(button.equals(this.btnIngresarCliente)){
			IngresarClienteWindow icw=new IngresarClienteWindow();
			icw.setConnection(this.c);
			icw.setVisible(true);
		}
		else if(button.equals(this.btnContactos)){
			ContactosWindow cw=new ContactosWindow(this.c);
			cw.setVisible(true);
		}
		else if(button.equals(this.btnIngresarProveedor)){
			ProveedorWindow pw=new ProveedorWindow(this.c);
			pw.setVisible(true);
		}
		else if(button.equals(this.btnTarimas)){
			TarimaWindow tw=new TarimaWindow(this.c);
			//tw.setConnection(this.c);
			tw.setVisible(true);
		}
		else if(button.equals(this.btnFacturar)){
			FacturaWindow fw=new FacturaWindow(this.c);
			fw.setVisible(true);
		}
		else if(button.equals(this.btnIngresarMateriales)){
			MaterialesWindow mw=new MaterialesWindow(this.c);
			mw.setVisible(true);
		}
		else if(button.equals(this.btnIngresarMaderas)){
			MaderaWindow mw=new MaderaWindow(this.c);
			mw.setVisible(true);
		}
		else if(button.equals(this.btnFacturarMaterial)){
			FacturarMaterialWindow fmw=new FacturarMaterialWindow(this.c);
			fmw.setVisible(true);
		}
		else if(button.equals(this.btnIngresarTelefono)){
			TelefonosWindows tw=new TelefonosWindows(this.c);
			tw.setVisible(true);
		}
		else if(button.equals(this.btnRegresar)){
			OptionsWindows ow=new OptionsWindows(this.c);
			ow.setVisible(true);
			this.dispose();
		}
	}
}