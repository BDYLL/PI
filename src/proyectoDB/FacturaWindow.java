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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class FacturaWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private JButton btnAceptar,btnCancelar;
	
	private JComboBox<String> comboBox,comboBox_1,comboBox_2;
	
	private Connection c;
	
	private Hashtable<Integer,String> rfcIndex,lotesIndex;

	/**
	 * Create the frame.
	 */
	public FacturaWindow(Connection c) {
		super("Facturar tarima");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.c=c;
		
		JLabel lblMonto = new JLabel("Monto");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha (MM-DD-YYYY)");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblFormaDePago = new JLabel("Forma de pago");
		
		String[] options={"efectivo","credito","desconocido"};
		
		comboBox = new JComboBox<String>(options);
		
		JLabel lblCliente = new JLabel("Cliente");
		
		ArrayList<String> clients=new ArrayList<String>();
		this.rfcIndex=new Hashtable<Integer,String>();
		
		try {
			Statement query=c.createStatement();
			
			String qs="SELECT RFCCliente, Cnombre, CApellidoP, CApellidoM FROM Cliente";
			
			ResultSet rs=query.executeQuery(qs);
			int i=0;
			while(rs.next()){
				String rfc,name,appP,appM;
				rfc=rs.getString("RFCCliente");
				name=rs.getString("Cnombre");
				appP=rs.getString("CApellidoP");
				appM=rs.getString("CApellidoM");
				String res=rfc+" "+name+" "+appP+" "+appM;
				clients.add(res);
				this.rfcIndex.put(i,rfc);
				i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String [] tmp=new String[clients.size()];
		
		comboBox_1 = new JComboBox<String>(clients.toArray(tmp));
		
		JLabel lblLote = new JLabel("Lote");
		
		ArrayList<String> lotes=new ArrayList<String>();
		this.lotesIndex=new Hashtable<Integer,String>();
		
		try{
			Statement query=c.createStatement();
			String qs="SELECT tlote FROM Tarima";
			
			ResultSet rs=query.executeQuery(qs);
			int i=0;
			while(rs.next()){
				String lote;
				lote=rs.getString("tlote");
				lotes.add(lote);
				this.lotesIndex.put(i, lote);
				i++;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		String[] tmp2=new String[lotes.size()];
		
		comboBox_2 = new JComboBox<String>(lotes.toArray(tmp2));
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMonto)
								.addComponent(lblFecha))
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(83)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCliente)
								.addComponent(lblLote))
							.addGap(67)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(lblFormaDePago)
							.addGap(33)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(430, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(679, Short.MAX_VALUE)
					.addComponent(btnAceptar)
					.addGap(26)
					.addComponent(btnCancelar)
					.addGap(100))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMonto)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCliente)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFecha)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLote)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFormaDePago)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(65)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnAceptar))
					.addContainerGap(272, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnAceptar)){
			
			try {
				Statement query=this.c.createStatement();
				
				String formaDePago,cliente,lote;
				
				formaDePago=this.comboBox.getItemAt(this.comboBox.getSelectedIndex());
				cliente=this.rfcIndex.get(this.comboBox_1.getSelectedIndex());
				lote=this.comboBox_2.getItemAt(this.comboBox_2.getSelectedIndex());
				
				String qs="INSERT INTO FacturaTarima VALUES(SYS_GUID(),'"+cliente+"','"+lote+"',"+
				this.textField.getText()+",to_date('"+
						this.textField_1.getText()+"','MM-DD-YYYY'),'"+formaDePago+"')";
		
				query.executeQuery(qs);
				this.dispose();

			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(arg0.getSource().equals(this.btnCancelar)){
			this.dispose();
		}
		
	}
}
