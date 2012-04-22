package ventanas;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.*;

import javax.swing.*;

public class instrucciones extends JFrame implements ActionListener {
	JScrollPane jScrollPane1 = new JScrollPane();
	JTextArea jTextArea1 = new JTextArea();
	JLabel jLabel1 = new JLabel();
	JButton jButton1 = new JButton();

	public instrucciones() {
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		jTextArea1.setColumns(20);
		jTextArea1.setFont(new Font("Monospaced", 0, 12)); // NOI18N
		jTextArea1.setRows(1);
		jTextArea1.setEditable(false);
		jTextArea1
				.setText("   El Reversi es un juego de tablero en el\n "
						+ "que compiten dos jugadores con fichas de\n"
						+ " diferente color (blancas/negras) en un\n "
						+ "tablero de 64 casillas (8 x 8). \n\n   "
						+ "En su turno cada jugador coloca una pieza\n "
						+ "de su color en una casilla vacía del\n "
						+ "tablero que debe ser adyacente a una ficha\n "
						+ "del contrario y además en línea con otra\n "
						+ "ficha propia (horizontal, vertical o\n "
						+ "diagonal). \n\n   Tras colocar una pieza nueva, todas las\n "
						+ "fichas del contrario que se encuentre\n "
						+ "entre dos piezas del jugador (en línea\n "
						+ "horizontal, vertical o diagonal) son\n "
						+ "convertidas al color del jugador.");

		jScrollPane1.setViewportView(jTextArea1);

		jLabel1.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		jLabel1.setText("INSTRUCCIONES");

		jButton1.setText("VOLVER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 54, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(210, 210, 210))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

		jButton1.addActionListener(this);

		pack();
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		if (pulsado == jButton1) {
			new menuPrincipal().setVisible(true);
			this.dispose();
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
			Logger.getLogger(instrucciones.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(instrucciones.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(instrucciones.class.getName())
					.log(Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			Logger.getLogger(instrucciones.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				new instrucciones().setVisible(true);
			}
		});
	}

}
