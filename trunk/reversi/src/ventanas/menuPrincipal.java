package ventanas;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;

public class menuPrincipal extends JFrame implements ActionListener {

	JButton jugar = new JButton();
	JButton instrucciones = new JButton();
	JButton top5 = new JButton();
	JButton salir = new JButton();

	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();

	ImageIcon imagenes = new ImageIcon();

	public menuPrincipal() {

		jugar.setText("JUGAR");
		instrucciones.setText("Instrucciones");
		top5.setText("TOP 5");
		salir.setText("SALIR");

		imagenes = new ImageIcon("src\\imagenes\\reversiMenuPrincipal.jpg");

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
                            .addComponent(jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(instrucciones)
                            .addComponent(top5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(salir)
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
                        .addComponent(jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(instrucciones)
                        .addGap(18, 18, 18)
                        .addComponent(top5)
                        .addGap(49, 49, 49)
                        .addComponent(salir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE)));

		jugar.addActionListener(this);
		instrucciones.addActionListener(this);
		top5.addActionListener(this);
		salir.addActionListener(this);

		pack();
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		if (pulsado == instrucciones) {
			new instrucciones().setVisible(true);
			this.dispose();
		}
		if (pulsado == top5) {
			new puntuaciones().setVisible(true);
			this.dispose();
		}

		else if (pulsado == jugar) {
			new preguntarRegistro().setVisible(true);
			this.dispose();
		}

		if (pulsado == salir) {
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
