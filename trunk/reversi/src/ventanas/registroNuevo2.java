package ventanas;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;

public class registroNuevo2 extends javax.swing.JFrame implements
		ActionListener {

	public registroNuevo2() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();

		continuar = new javax.swing.JButton();
		cancelar = new javax.swing.JButton();
		
		jTextField1 = new javax.swing.JTextField();

		jPasswordField1 = new javax.swing.JPasswordField();

		jTextPane1 = new javax.swing.JTextPane();
		jTextPane2 = new javax.swing.JTextPane();

		jLabel1.setFont(new java.awt.Font("Tahoma", 5, 17));
		jLabel1.setText("NUEVO REGISTRO 2");
		jLabel2.setText("Nombre jugador:");
		jLabel3.setText("Password:");

		continuar.setText("Continuar");
		cancelar.setText("Cancelar");

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
																		continuar,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		158,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(148,
																		148,
																		148)
																.addComponent(
																		cancelar,
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
								.addComponent(continuar,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										37,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(cancelar).addGap(19, 19, 19)));

		continuar.addActionListener(this);
		cancelar.addActionListener(this);

		pack();
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		if (pulsado == continuar)// Continuar
		{
			new juego().setVisible(true);
			this.dispose();
		}
		if (pulsado == cancelar)// Cancelar
		{
			new registro2().setVisible(true);
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
			java.util.logging.Logger.getLogger(registroNuevo2.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(registroNuevo2.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(registroNuevo2.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(registroNuevo2.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new registroNuevo2().setVisible(true);
			}
		});
	}

	// Declaracion de variables
	private javax.swing.JButton continuar;
	private javax.swing.JButton cancelar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JTextPane jTextPane1;
	private javax.swing.JTextPane jTextPane2;
}
