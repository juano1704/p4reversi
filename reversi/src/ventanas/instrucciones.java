package ventanas;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.*;

import javax.swing.*;

public class instrucciones extends JFrame implements ActionListener {
	JScrollPane jScrollPane1 = new JScrollPane();
	JLabel jLabel1 = new JLabel();
	JLabel texto = new JLabel();
	ImageIcon imagenes;
	JButton botonVolver = new JButton();

	public instrucciones() {
		
		this.setTitle("Instrucciones");
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		imagenes = new ImageIcon("src\\imagenes\\instrucciones.jpg");

		texto.setIcon(imagenes);

		jScrollPane1.setViewportView(texto);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17));
		jLabel1.setText("INSTRUCCIONES");

		botonVolver.setText("VOLVER");

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
                .addComponent(botonVolver)
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
                .addComponent(botonVolver)
                .addContainerGap())
        );

		botonVolver.addActionListener(this);

		pack();
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		if (pulsado == botonVolver) {
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
