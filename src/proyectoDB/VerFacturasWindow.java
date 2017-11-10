package proyectoDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VerFacturasWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnVer;
	private JButton btnSalir;
	private JTable table;
	private String columns[]={"Lote","Nombre","Apellido Pat","Apellido Mat","Telefono","Monto"};
	private Connection c;
	private JScrollPane scrollPane;
	private JLabel lblMontoTotal;
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerFacturasWindow frame = new VerFacturasWindow();
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
	public VerFacturasWindow(Connection c) {
		super("Ver Facturas");
		this.c=c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblVerFacturasEntre = new JLabel("Ver facturas entre las fechas especificadas (MM-DD-YYYY)");
		
		JLabel lblNewLabel = new JLabel("Fecha de inicio");
		
		JLabel lblNewLabel_1 = new JLabel("Fecha final");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		btnVer = new JButton("Ver");
		
		this.btnVer.addActionListener(this);
		
		btnSalir = new JButton("Salir");
		this.btnSalir.addActionListener(this);
		
		scrollPane = new JScrollPane();
		
		lblMontoTotal = new JLabel("Monto total: ");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVerFacturasEntre)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_1))))
					.addContainerGap(34, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(221)
					.addComponent(lblMontoTotal)
					.addGap(174)
					.addComponent(btnVer, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
					.addGap(81)
					.addComponent(btnSalir)
					.addGap(104))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblVerFacturasEntre)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(72)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(49)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVer)
								.addComponent(btnSalir)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addComponent(lblMontoTotal)))
					.addContainerGap(311, Short.MAX_VALUE))
		);
		table = new JTable();
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	private void updateTable(String date1,String date2){

		try{
			Statement query=this.c.createStatement();
			ArrayList<String[]> rows=new ArrayList<String[]>();
			
			String qs="SELECT tlote AS Lote, cnombre AS nombre, CApellidop as ApellidoP, capellidom, telefono, ftmonto"
					+ " FROM facturatarima NATURAL JOIN cliente"
					+ " WHERE ftfechadecompra BETWEEN to_date('"+date1+"','MM-DD-YYYY') AND to_date('"+date2+"','MM-DD-YYYY')"
					+ " GROUP BY tlote, cnombre,CApellidop,CApellidom,telefono, ftmonto";
			ResultSet rs=query.executeQuery(qs);
			
			while(rs.next()){
				String lote,nombre,appP,appM,tel,monto;
				lote=rs.getString("Lote");
				nombre=rs.getString("nombre");
				appP=rs.getString("ApellidoP");
				appM=rs.getString("capellidom");
				tel=rs.getString("telefono");
				monto=rs.getString("ftmonto");
				String tmp[]={lote,nombre,appP,appM,tel,monto};
				rows.add(tmp);
			}
			
			String tmp[][]=new String[rows.size()][];
			
			for(int i=0;i<rows.size();i++){
				tmp[i]=rows.get(i);
			}
			this.scrollPane.remove(this.table);
			
			this.table=new JTable(tmp,columns);
			
			table.setPreferredScrollableViewportSize(table.getPreferredSize());
			table.setFillsViewportHeight(true);
			table.setEnabled(false);
			scrollPane.setViewportView(table);
			scrollPane.revalidate();
			scrollPane.repaint();
			
			String qs2="SELECT SUM(ftmonto) as Total"
					+ " FROM facturatarima NATURAL JOIN cliente"
					+ " WHERE ftfechadecompra BETWEEN to_date('"+date1+"','MM-DD-YYYY') AND to_date('"+date2+"','MM-DD-YYYY')";
			
			Statement query2=this.c.createStatement();
			
			ResultSet rs2=query2.executeQuery(qs2);
			
			String total="";
			
			while(rs2.next()){
				total=rs2.getString("TOTAL");
			}
			
			this.lblMontoTotal.setText("Monto total: "+total);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.btnVer)){
			String date1=this.textField.getText();
			String date2=this.textField_1.getText();
			this.updateTable(date1, date2);
		}
		else if(arg0.getSource().equals(this.btnSalir)){
			this.dispose();
		}
	}
}
