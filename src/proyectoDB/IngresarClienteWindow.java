/*package proyectoDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IngresarClienteWindow extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresarClienteWindow frame = new IngresarClienteWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public IngresarClienteWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
*/

package proyectoDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class IngresarClienteWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Connection c;
	private JTextField idLote;
	private JTextField tarimas;
	private JTextField cajas;
	private JTextField cajasXTarimas;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JButton btnAceptar;
	private JButton btnCancelar;
	

	/**
	 * Create the frame.
	 */
	public IngresarClienteWindow() {
		
		super("Ingresar Cliente");

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		idLote = new JTextField();
		idLote.setColumns(10);
		
		JLabel lblLote = new JLabel("ID de Lote");
		
		tarimas = new JTextField();
		tarimas.setColumns(10);
		
		JLabel lblTarimas = new JLabel("Tarimas");
		
		JLabel lblCajas = new JLabel("Cajas");
		
		cajas = new JTextField();
		cajas.setColumns(10);
		
		JLabel lblCajaXTarimas = new JLabel("Cajas x tarimas");
		
		cajasXTarimas = new JTextField();
		cajasXTarimas.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Calle");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Colonia");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("No. Externo");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JLabel lblNoInterno = new JLabel("No. Interno");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		JLabel lblMunicipio = new JLabel("Municipio");
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("email");
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		
		btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		GroupLayout layout = new GroupLayout(contentPane);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblLote).addGap(20).addComponent(idLote))

		);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLote).addComponent(idLote))
		);


		contentPane.setLayout(layout);
	}
	public void setConnection(Connection c){
		this.c=c;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnAceptar)){
			
			try {
				Statement query=c.createStatement();
				
				String qs="INSERT INTO Cliente VALUES('"+ idLote.getText()+"','"+ tarimas.getText()+
						"','"+ cajas.getText()+"','"+ cajasXTarimas.getText()+"','"+textField_4.getText()+"','"+
						textField_5.getText()+"',"+textField_6.getText()+","+textField_7.getText()+","+textField_8.getText()+",'"
						+textField_9.getText()+"','"
						+textField_10.getText()+"','"+textField_11.getText()+"')";
				
				//String qs="INSERT INTO Client VALUES('D000','Diego','Yanez','cd granja');";
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
