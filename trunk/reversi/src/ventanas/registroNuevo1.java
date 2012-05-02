package ventanas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.*;
import javax.swing.*;

public class registroNuevo1 extends javax.swing.JFrame implements
		ActionListener {

	public registroNuevo1() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		botonContinuar = new javax.swing.JButton();
		botonCancelar = new javax.swing.JButton();

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();

		jTextField1 = new javax.swing.JTextField();

		jPasswordField1 = new javax.swing.JPasswordField();
		
		jTextPane1 = new javax.swing.JTextPane();
		jTextPane2 = new javax.swing.JTextPane();

		jLabel1.setFont(new java.awt.Font("Tahoma", 5, 17));
		jLabel1.setText("NUEVO REGISTRO 1");
		jLabel2.setText("Nombre jugador:");
		jLabel3.setText("Password:");

		botonContinuar.setText("Continuar");
		botonCancelar.setText("Cancelar");

		this.setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(122,
																		122,
																		122)
																.addComponent(
																		botonContinuar,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		158,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(148,
																		148,
																		148)
																.addComponent(
																		botonCancelar,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		107,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(120, Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap(
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		jLabel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		155,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(31, 31,
																		31)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						jLabel3)
																				.addComponent(
																						jLabel2))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jTextField1)
																				.addComponent(
																						jPasswordField1))))
								.addGap(130, 130, 130)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(jLabel2)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel3)
												.addComponent(
														jPasswordField1,

														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										21, Short.MAX_VALUE)
								.addComponent(botonContinuar,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										37,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(botonCancelar).addGap(19, 19, 19)));

		botonContinuar.addActionListener(this);
		botonCancelar.addActionListener(this);

		pack();
		this.setLocationRelativeTo(null);
	}

	private Connection conn;
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db/reversiDB.sqlite");
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		String nombre, pass;
		if (pulsado == botonContinuar)// Continuar
		{
			nombre=jTextField1.getText();
			pass=jPasswordField1.getText();
			try {
				connect();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				insertarUsuario(nombre, pass);
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
			
			new registro2().setVisible(true);
			this.dispose();
		}
		if (pulsado == botonCancelar)// Cancelar
		{
			new registro1().setVisible(true);
			this.dispose();
		}

	}

	public void disconnect() throws SQLException {
		conn.close();
	}
	
	public void insertarUsuario(String nom, String pass)throws SQLException{
		PreparedStatement stat = conn.prepareStatement("insert into Usuario values (?, ?)");
		stat.setString(1, nom);
		stat.setString(2, pass);
		stat.executeUpdate();
		stat.close();
	}
	public static void main(String args[]) {

		
		new registroNuevo1().setVisible(true);
	}

	// Declaracion de variables
	private javax.swing.JButton botonContinuar;
	private javax.swing.JButton botonCancelar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;

	private javax.swing.JTextField JTextField;
	private javax.swing.JTextField JTextField2;

	private javax.swing.JTextField jTextField1;
	private javax.swing.JPasswordField jPasswordField1;

	private javax.swing.JTextPane jTextPane1;
	private javax.swing.JTextPane jTextPane2;
}
