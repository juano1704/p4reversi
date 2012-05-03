package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class registro1 extends javax.swing.JFrame implements ActionListener {

	public registro1() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		botonRegistrarse = new javax.swing.JButton();
		botonOk = new javax.swing.JButton();

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();

		jTextField1 = new javax.swing.JTextField();

		jPasswordField1 = new javax.swing.JPasswordField();

		botonRegistrarse.setText("Registrarse");
		botonOk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		botonOk.setText("OK");

		jLabel1.setFont(new java.awt.Font("Tahoma", 5, 17)); // NOI18N
		jLabel1.setText("JUGADOR 1:");
		jLabel2.setText("Nombre jugador:");
		jLabel3.setText("Password:");

		this.setTitle("Registro Jugador 1");
		this.setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(35, 35, 35)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						jLabel2)
																				.addComponent(
																						jLabel3))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						jTextField1)
																				.addComponent(
																						jPasswordField1,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						149,
																						Short.MAX_VALUE)))
												.addComponent(
														jLabel1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														104,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										25, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														botonOk,
														javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														70,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														botonRegistrarse,
														javax.swing.GroupLayout.Alignment.TRAILING))
								.addGap(33,33,33)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(35, 35, 35)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jLabel1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														23,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(botonRegistrarse))
								.addGap(33,33,33)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel2)
																				.addComponent(
																						jTextField1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel3)
																				.addComponent(
																						jPasswordField1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addComponent(
														botonOk,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														50,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(37, Short.MAX_VALUE)));

		botonRegistrarse.addActionListener(this);
		botonOk.addActionListener(this);

		pack();
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		String nom, pass;
		if (pulsado == botonRegistrarse)// Registrarse
		{
			new registroNuevo1().setVisible(true);
			this.dispose();
		}
		if (pulsado == botonOk)// OK
		{
			nom=jTextField1.getText();
			pass=jPasswordField1.getText();
			try {
				connect();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				comprobarUsuario(nom, pass);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				disconnect();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void comprobarUsuario(String nom, String pass)throws SQLException{

		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("Select nombre, password from Usuario");
		boolean encontrado=false;
		rs.next();
		do{			
			if(rs.getString("Nombre")==nom){
				if( rs.getString("Password")==pass){
					encontrado=true;
					seleccionarUsuario(nom);
					rs.close();					
					stat.close();
					new registro2().setVisible(true);
					this.dispose();
				}				
			}
			else{
				int messageType = JOptionPane.QUESTION_MESSAGE;
				String[] options = { "Volver a intentar" };
				int code = JOptionPane.showOptionDialog(null, "   Nombre de usuario o contraseņa incorrectos.", "ERROR",
						0, messageType, null, options, "Volver a intentar");
				if (code == 0)// Reiniciar
				{
					this.dispose();
					new registro1().setVisible(true);
					
				}
			}
		}while(encontrado==false && rs.next());
			
		rs.close();
		stat.close();
	}
	
	public void seleccionarUsuario(String nom)throws SQLException{
		PreparedStatement stat = conn.prepareStatement("update Jugadores set Jugador1='"+nom+"'");
		stat.executeUpdate();
		stat.close();
	}
	public void disconnect() throws SQLException {
		conn.close();
	}
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db/reversiDB.sqlite");
	}

	public static void main(String args[]) {

		new registro1().setVisible(true);
	}

	// Declaracion de variables
	private javax.swing.JButton botonRegistrarse;
	private javax.swing.JButton botonOk;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JTextField jTextField1;
	private Connection conn;
}
