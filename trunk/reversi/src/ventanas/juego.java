package ventanas;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import otelo.interfaz.OteloGUI;
import otelo.interfaz.PanelTablero;

public class juego extends JFrame implements ActionListener {

	public juego() {
		initComponents();
	}

	PanelTablero p = new PanelTablero();
	ImageIcon blanca = new ImageIcon();
	ImageIcon negra = new ImageIcon();
	ImageIcon tablero = new ImageIcon();

	@SuppressWarnings("unchecked")
	private void initComponents() {

		blanca = new ImageIcon("src\\imagenes\\blanca.jpg");
		negra = new ImageIcon("src\\imagenes\\negra.jpg");
		tablero = new ImageIcon("src\\imagenes\\tablero.jpg");

		botonOpciones = new JButton();

		jLabel1 = new JLabel(); // imagen del tablero en juego
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		jPanel1 = new JPanel();

		botonOpciones.setText("Opciones");

		p.setBounds(30, 80, 320, 320);
		this.add(p);
		
		jLabel2.setText("Jugador 1:");
		jLabel3.setText("Jugador 2:");
		jLabel4.setIcon(blanca);
		jLabel5.setIcon(negra);
		jLabel6.setText("NUM");
		jLabel7.setText("NUM");

		this.setTitle("Tabla de Juego");
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonOpciones)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addComponent(jLabel2))
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(55, 55, 55)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(62, 62, 62)
                .addComponent(botonOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

		botonOpciones.addActionListener(this);

		pack();
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		if (pulsado == botonOpciones)
			opciones();
	}

	public void opciones() {
		int messageType = JOptionPane.QUESTION_MESSAGE;
		String[] options = { "Reiniciar", "Guardar", "Menú Principal",
				"Cancelar" };
		int code = JOptionPane.showOptionDialog(null, " OPCIONES", "Opciones",
				0, messageType, null, options, "Cancelar");
		if (code == 0)// Reiniciar
		{
			this.dispose();
			new juego().setVisible(true);
		}
		if (code == 1) {
			// guardar
		}
		if (code == 3)// Cancelar
		{

		}
		if (code == 2)// Menu Principal
		{
			int codigo = JOptionPane.showConfirmDialog(null,
					"¿Seguro que quiere salir?");
			if (codigo == 0) {
				this.dispose();
				new menuPrincipal().setVisible(true);
			}
			if (codigo == 1 || codigo == 2) {
				opciones();
			}
		}
	}

	public static void main(String[] args) {
	      new juego();
	      
	  }
	
	

	// Declaracion de variables
	private JButton botonOpciones;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JPanel jPanel1;

}