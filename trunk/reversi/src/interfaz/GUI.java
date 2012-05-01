package interfaz;

import javax.swing.*;

import clasesBasicas.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import jugadores.*;
import ventanas.juego;
import ventanas.menuPrincipal;

/**
 * Interfaz gráfico del reversi. Es la clase principal.
 */
public class GUI extends JFrame implements Runnable, ActionListener {

	public static final long T_CHEQUEO = 10;
	private static final String HUMANO = "Humano";
	private static final String MAQUINA = "Máquina";
	private static String tiposJugador[] = { HUMANO, MAQUINA };

	private Juego j;
	private boolean pararPartida;
	Thread t;

	// componentes gráficos
	private PanelTablero p;
	private JLabel nomJugBlancas, nomJugNegras;
	private JLabel numBlancas, numNegras;
	private Container pFichas;
	private JLabel mensajes;
	private JMenuItem miNuevo, miPausa;
	private JButton botonOpciones;

	public GUI() {

		// inicializar datos miembro
		pararPartida = false;
		// cuestiones gráficas
		crearComponentes();
		// inicializar ventana
		setTitle("Juego Reversi");
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		pack();

	}

	private void crearComponentes() {
		JMenuBar barraMenu;
		JMenu menuArchivo;
		Container pJugadores;

		// crear tablero
		p = new PanelTablero();

		// jugadores
		pJugadores = Box.createHorizontalBox();
		pJugadores.add(new JLabel("Blancas: "));
		nomJugBlancas = new JLabel("--------------");
		pJugadores.add(nomJugBlancas);
		pJugadores.add(Box.createHorizontalStrut(20));
		pJugadores.add(new JLabel("vs"));
		pJugadores.add(Box.createHorizontalStrut(20));
		pJugadores.add(new JLabel("Negras: "));
		nomJugNegras = new JLabel("---------------");
		pJugadores.add(nomJugNegras);
		
		// numero de fichas
		pFichas = Box.createHorizontalBox();
		pFichas.add(new JLabel("Blancas: "));
		numBlancas = new JLabel("-");
		pFichas.add(numBlancas);
		pFichas.add(Box.createHorizontalStrut(20));
		pFichas.add(new JLabel("Negras: "));
		numNegras = new JLabel("-");
		pFichas.add(numNegras);

		mensajes = new JLabel("");

		JPanel pDatos = new JPanel();
		pDatos.setLayout(new GridLayout(0, 1));
		pDatos.add(pJugadores);
		pDatos.add(pFichas);
		pDatos.add(mensajes);

		// panel de control
		JPanel pControl = new JPanel();
		pControl.add(pDatos, BorderLayout.NORTH);

		// añadir componentes a la ventana principal
		getContentPane().add(p, BorderLayout.WEST);
		getContentPane().add(pControl, BorderLayout.CENTER);

		// menús
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		menuArchivo = new JMenu("Partida");
		barraMenu.add(menuArchivo);
		miNuevo = new JMenuItem("Nueva");
		miNuevo.addActionListener(new GestorMenu());
		menuArchivo.add(miNuevo);
		miPausa = new JCheckBoxMenuItem("Pausa");
		miPausa.addItemListener(new GestorMenu());
		miPausa.setEnabled(false);
		menuArchivo.add(miPausa);
		
		botonOpciones= new JButton("Opciones");
		JLabel blanco = new JLabel("");
		pDatos.add(blanco);
		pDatos.add(botonOpciones);
		botonOpciones.addActionListener(this);
		botonOpciones.setEnabled(false);
		this.setResizable(false);
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
			new GUI();
		}
		if (code == 1) {
			// guardar
		}
		if (code == 3)// Cancelar
		{

		}
		if (code == 2)// Menu Principal
		{
			int confirmationType = JOptionPane.QUESTION_MESSAGE;
			String[] confirmationOptions = { "Si", "No"};
			int codigo = JOptionPane.showOptionDialog(null, " ¿Seguro que desea salir?", "",
					0, confirmationType, null, confirmationOptions, "Si");
		
			if (codigo == 0) {
				this.dispose();
				new menuPrincipal().setVisible(true);
			}
			if (codigo == 1) {
				opciones();
			}
		}
	}

	public void jugar() {
		t = new Thread(this);
		t.start();
	}

	public void run() {
		long tTotal;
		Casilla cas;
		Jugador jAct;

		pararPartida = false;

		// actualizar número de fichas en el GUI
		numBlancas.setText(Integer.toString(j.getNumFichas(Juego.BLANCAS)));
		numNegras.setText(Integer.toString(j.getNumFichas(Juego.NEGRAS)));

		// bucle de partida
		while (!j.finPartida()) {

			// si un jugador no puede mover, el turno debe pasar al siguiente
			if (!j.puedePoner(j.getTurno())) {
				j.cambiarTurno();

			}
			// actualiza turno en el GUI
			if (j.getTurno() == Juego.BLANCAS)
				mensajes.setText("Mueven las blancas");
			else
				mensajes.setText("Mueven las negras");
			// pedirle al jugador actual su movimiento
			tTotal = 0;
			jAct = j.getJugadorActual();
			jAct.hacerJugada(j);
			while ((tTotal <= Juego.T_MAX_MOV) && (jAct.getJugada() == null)
					&& (!pararPartida)) {
				try {
					Thread.sleep(T_CHEQUEO);
				} catch (InterruptedException ie) {
				}
				tTotal += T_CHEQUEO;
			}
			System.out.println(tTotal);
			// si se ha parado la partida, termina el hilo
			if (pararPartida) {
				j.parar();
				System.out.println("pausa");
				return;
			}

			// comprobar si el movimiento es válido
			if (j.ponerFicha(j.getTurno(), jAct.getJugada())) {
				// actualizar número de fichas en el GUI
				numBlancas.setText(Integer.toString(j
						.getNumFichas(Juego.BLANCAS)));
				numNegras
						.setText(Integer.toString(j.getNumFichas(Juego.NEGRAS)));
				// actualizar tablero en el GUI
				p.repaint();
				pFichas.repaint();
				j.cambiarTurno();
			}
		}
		// pone en el GUI quién ha ganado
		if (j.getNumFichas(Juego.BLANCAS) > j.getNumFichas(Juego.NEGRAS))
			mensajes.setText("¡¡Las blancas ganan!!");
		else if (j.getNumFichas(Juego.BLANCAS) < j.getNumFichas(Juego.NEGRAS))
			mensajes.setText("¡¡Las negras ganan!!");
		else
			mensajes.setText("¡¡Empate!!");

	}

	public static void main(String[] args) {
		new GUI();
	}

	private class GestorMenu implements ActionListener, ItemListener {
		

		public GestorMenu() {
		}

		public void actionPerformed(ActionEvent e) {
			// nueva partida
			if (e.getSource() == miNuevo) {
				nuevaPartida();
			}
		}

		private void nuevaPartida() {
			Jugador jBlancas, jNegras;

			// elegir tipos de jugador
			jBlancas = new JugadorHumano(Juego.BLANCAS);
			jNegras = new JugadorHumano(Juego.NEGRAS);
			// comenzar partida
			j = new Juego(jBlancas, jNegras);
			p.setJuego(j);
			p.repaint();
			nomJugBlancas.setText(jBlancas.getNombre());
			nomJugNegras.setText(jNegras.getNombre());
			miPausa.setEnabled(true);
			jugar();
			botonOpciones.setEnabled(true);
		}


		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

}