/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class juego extends JFrame implements ActionListener {

	public juego() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jButton1 = new JButton();

		jLabel1 = new JLabel(); // imagen del tablero en juego
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();

		jButton1.setText("Opciones");

		jLabel2.setText("Jugador 1:");
		jLabel3.setText("Jugador 2:");
		jLabel4.setText("circulo B");
		jLabel5.setText("circulo N");
		jLabel6.setText("NUM");
		jLabel7.setText("NUM");

		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1,
										GroupLayout.PREFERRED_SIZE, 313,
										GroupLayout.PREFERRED_SIZE)
								.addGap(32, 32, 32)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(jButton1)
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
																.addComponent(
																		jLabel3)
																.addComponent(
																		jLabel2,
																		GroupLayout.Alignment.TRAILING))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel4)
																.addGap(18, 18,
																		18)
																.addComponent(
																		jLabel6))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel5)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jLabel7)))
								.addGap(0, 43, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(54, 54,
																		54)
																.addComponent(
																		jLabel2)
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel4)
																				.addComponent(
																						jLabel6))
																.addGap(24, 24,
																		24)
																.addComponent(
																		jLabel3)
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel5)
																				.addComponent(
																						jLabel7))
																.addGap(68, 68,
																		68)
																.addComponent(
																		jButton1))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(42, 42,
																		42)
																.addComponent(
																		jLabel1,
																		GroupLayout.PREFERRED_SIZE,
																		286,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(21, Short.MAX_VALUE))

		);

		jButton1.addActionListener(this);

		pack();
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		if (pulsado == jButton1) {
			new opciones().setVisible(true);
		}
	}

	public static void main(String args[]) {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(juego.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(juego.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(juego.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(juego.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new juego().setVisible(true);
			}
		});
	}

	// Declaracion de variables
	private JButton jButton1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;

}