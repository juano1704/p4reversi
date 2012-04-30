package ventanas;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;

public class menuPrincipal extends JFrame implements ActionListener {

	JButton botonJugar = new JButton();
	JButton botonInstrucciones = new JButton();
	JButton botonTop5 = new JButton();
	JButton botonSalir = new JButton();

	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();

	ImageIcon imagenes = new ImageIcon();

	public menuPrincipal() {

		botonJugar.setText("JUGAR");
		botonInstrucciones.setText("Instrucciones");
		botonTop5.setText("TOP 5");
		botonSalir.setText("SALIR");

		imagenes = new ImageIcon("src\\reversiMenuPrincipal.jpg");

		jLabel1.setIcon(imagenes);
		jLabel2.setFont(new java.awt.Font("Tahoma", 5, 30)); // NOI18N
		jLabel2.setText("REVERSI");

		this.setTitle("Menú Principal");
		this.setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonInstrucciones)
                            .addComponent(botonTop5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonSalir)
                        .addGap(64, 64, 64))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(206, 206, 206))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(botonJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonInstrucciones)
                        .addGap(18, 18, 18)
                        .addComponent(botonTop5)
                        .addGap(49, 49, 49)
                        .addComponent(botonSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE)));

        botonJugar.addActionListener(this);
        botonInstrucciones.addActionListener(this);
        botonTop5.addActionListener(this);
        botonSalir.addActionListener(this);

		pack();
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		if (pulsado == botonInstrucciones) {
			new instrucciones().setVisible(true);
			this.dispose();
		}
		if (pulsado == botonTop5) {
			new puntuaciones().setVisible(true);
			this.dispose();
		}

		else if (pulsado == botonJugar) {
			new preguntarRegistro().setVisible(true);
			this.dispose();
		}

		if (pulsado == botonSalir) {
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
			Logger.getLogger(menuPrincipal.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(menuPrincipal.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(menuPrincipal.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(menuPrincipal.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				new menuPrincipal().setVisible(true);
			}
		});
	}

}
