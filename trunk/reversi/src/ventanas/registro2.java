package ventanas;


import interfaz.*;

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


public class registro2 extends JFrame implements ActionListener {

	public registro2() {
		initComponents();
	}

	
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
		jLabel1.setText("JUGADOR 2:");
		jLabel2.setText("Nombre jugador:");
		jLabel3.setText("Password:");

		this.setTitle("Registro Jugador 2");
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
																						jTextField1,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						149,
																						Short.MAX_VALUE)
																				.addComponent(
																						jPasswordField1)))
												.addComponent(
														jLabel1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														104,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										24, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														botonOk,
														javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														73,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														botonRegistrarse,
														javax.swing.GroupLayout.Alignment.TRAILING))
								.addGap(40, 40, 40)));
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
								.addGap(33, 33, 33)
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
														58,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(34, Short.MAX_VALUE)));

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
			new registroNuevo2().setVisible(true);
			this.dispose();
		}
		if (pulsado == botonOk)// OK
		{
			nom=jTextField1.getText();
			pass=jPasswordField1.getText();
			
			try {
				connect();
			} catch (ClassNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
			
			try {
				comprobarUsuario(nom, pass);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				disconnect();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			try {
				new OteloGUI();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		}
	}
	public void comprobarUsuario(String nom, String pass)throws SQLException, ClassNotFoundException{

		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT * FROM Usuario");
		boolean encontrado=false;
		String nom2, pass2;
		while(rs.next()){
			nom2=rs.getString("Nombre");
			pass2=rs.getString("Password");			
			if(nom2.equals(nom) && pass2.equals(pass)){
				encontrado=true;
				seleccionarUsuario(nom);
				rs.close();					
				stat.close();
				new OteloGUI();
				this.dispose();
			}
		}
		
		if(encontrado==false){
			rs.close();					
			stat.close();
			int messageType = JOptionPane.QUESTION_MESSAGE;
			String[] options = { "Volver a intentar" };
			int code = JOptionPane.showOptionDialog(null, "   Nombre de usuario o contraseña incorrectos.", "ERROR",
					0, messageType, null, options, "Volver a intentar");
			if (code == 0)
			{
				this.dispose();
				new OteloGUI();
				
			}
		}
	}
	
	
	public void seleccionarUsuario(String nom)throws SQLException{
		PreparedStatement stat = conn.prepareStatement("update Jugadores set Jugador2='"+nom+"'");
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

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(registro2.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(registro2.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(registro2.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(registro2.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new registro2().setVisible(true);
			}
		});
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
