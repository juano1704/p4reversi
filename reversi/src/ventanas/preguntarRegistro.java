package ventanas;


import interfaz.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;



public class preguntarRegistro extends javax.swing.JFrame implements
		ActionListener {

	public preguntarRegistro() {
		initComponents();
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();

		botonRegistrarse = new javax.swing.JButton();
		botonModoInvitado = new javax.swing.JButton();

		jLabel1.setFont(new java.awt.Font("Tahoma", 5, 16));
		jLabel1.setText("¿QUIERES REGISTRARTE?");

		botonRegistrarse.setText("Registrarse");
		botonModoInvitado.setText("Modo invitado");

		this.setTitle("Registro:");
		this.setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(52, 52, 52)
								.addComponent(botonRegistrarse,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										138,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(botonModoInvitado)
								.addContainerGap(57, Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										195,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(87, 87, 87)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										41,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														botonRegistrarse,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														botonModoInvitado,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														67,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(38, Short.MAX_VALUE)));

		botonRegistrarse.addActionListener(this);
		botonModoInvitado.addActionListener(this);

		pack();
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		if (pulsado == botonRegistrarse)// Registrarse
		{
			new registro1().setVisible(true);
			this.dispose();
		}
		
		if (pulsado == botonModoInvitado)// Modo invitado
		{
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
				seleccionarUsuario1();
				seleccionarUsuario2();
			} catch (SQLException e2) {
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

	public void seleccionarUsuario1()throws SQLException{
		PreparedStatement stat = conn.prepareStatement("update Jugadores set Jugador1='Jugador 1'");
		stat.executeUpdate();
		stat.close();
	}
	
	public void seleccionarUsuario2()throws SQLException{
		PreparedStatement stat = conn.prepareStatement("update Jugadores set Jugador2='Jugador 2'");
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
			java.util.logging.Logger.getLogger(
					preguntarRegistro.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					preguntarRegistro.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					preguntarRegistro.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					preguntarRegistro.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new preguntarRegistro().setVisible(true);
			}
		});
	}

	// Declaracion de variables
	private javax.swing.JButton botonRegistrarse;
	private javax.swing.JButton botonModoInvitado;
	private javax.swing.JLabel jLabel1;
	private Connection conn;
}
