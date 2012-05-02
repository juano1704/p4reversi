package ventanas;

import otelo.interfaz.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;


public class registro2 extends javax.swing.JFrame implements ActionListener {

	public registro2() {
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
		if (pulsado == botonRegistrarse)// Registrarse
		{
			new registroNuevo2().setVisible(true);
			this.dispose();
		}
		if (pulsado == botonOk)// OK
		{
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
}
