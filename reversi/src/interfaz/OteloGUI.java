package interfaz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import otelo.*;
import otelo.jugadores.*;
import ventanas.menuPrincipal;


/**
 * Interfaz gr�fico del Otelo. Es la clase principal.
 */
public class OteloGUI extends JFrame implements Runnable, ActionListener {

	public static final long T_CHEQUEO = 10;
	private static final String HUMANO = "Humano";
	private static final String MAQUINA = "M�quina";
	private static String tiposJugador[] = { HUMANO, MAQUINA };

	private Juego j;
	private boolean pararPartida;
	Thread t;
	private Connection conn;

	// componentes gr�ficos
	private PanelTablero p;
	private JLabel nomJugBlancas, nomJugNegras;
	private JLabel numBlancas, numNegras;
	private Container pFichas;
	private JLabel mensajes;
	private JMenuItem miNuevo, miPausa;
	private JButton botonOpciones;
	private JButton botonEmpezar;

	public OteloGUI() throws ClassNotFoundException, SQLException {

		// inicializar datos miembro
		pararPartida = false;
		// cuestiones gr�ficas
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

	private void crearComponentes() throws ClassNotFoundException, SQLException {
		JMenuBar barraMenu;
		JMenu menuArchivo;
		Container pJugadores;

		// crear tablero
		p = new PanelTablero();

		// jugadores
		pJugadores = Box.createHorizontalBox();
		pJugadores.add(new JLabel("Blancas: "));
		nomJugBlancas = new JLabel("-----------");
		pJugadores.add(nomJugBlancas);
		pJugadores.add(Box.createHorizontalStrut(20));
		pJugadores.add(new JLabel("vs"));
		pJugadores.add(Box.createHorizontalStrut(20));
		pJugadores.add(new JLabel("Negras: "));
		nomJugNegras = new JLabel("------------");
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

		// a�adir componentes a la ventana principal
		getContentPane().add(p, BorderLayout.WEST);
		getContentPane().add(pControl, BorderLayout.CENTER);

		// men�s
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		menuArchivo = new JMenu("Partida");
		barraMenu.add(menuArchivo);
		miNuevo = new JMenuItem("Nueva");
		barraMenu.setVisible(false);
		
		menuArchivo.add(miNuevo);
		miPausa = new JCheckBoxMenuItem("Pausa");
		
		miPausa.setEnabled(false);
		menuArchivo.add(miPausa);
		
		botonOpciones= new JButton("Opciones");
		botonEmpezar= new JButton("Empezar a jugar");
		JLabel blanco = new JLabel("");
		pDatos.add(botonOpciones);
		pDatos.add(blanco);
		blanco.setVisible(false);
		botonOpciones.setVisible(false);
		pDatos.add(botonEmpezar);
		botonOpciones.addActionListener(this);
		botonEmpezar.addActionListener(this);
		this.setResizable(false);
		connect();
		try {
			nombres();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pack();
		this.setLocationRelativeTo(null);
	
	}
	public void actionPerformed(ActionEvent e) {
		JButton pulsado = (JButton) e.getSource();
		if (pulsado == botonOpciones)
			try {
				opciones();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (pulsado == botonEmpezar)
			{
			Jugador jBlancas, jNegras;

			// elegir tipos de jugador
			jBlancas = new JugadorHumano(Juego.BLANCAS);
			jNegras = new JugadorHumano(Juego.NEGRAS);
			// comenzar partida
			j = new Juego(jBlancas, jNegras);
			p.setJuego(j);
			p.repaint();
			miPausa.setEnabled(true);
			botonEmpezar.setVisible(false);
			botonOpciones.setVisible(true);
			jugar();
			
			}
	}
	public void opciones() throws ClassNotFoundException, SQLException {
		int messageType = JOptionPane.QUESTION_MESSAGE;
		String[] options = { "Reiniciar", "Men� Principal", "Cancelar" };
		int code = JOptionPane.showOptionDialog(null, "Opciones", "OPCIONES",
				0, messageType, null, options, "Cancelar");
		if (code == 0)// Reiniciar
		{
			this.dispose();
			new OteloGUI();
		}
		
		if (code == 1)// Menu Principal
		{
			int confirmationType = JOptionPane.QUESTION_MESSAGE;
			String[] confirmationOptions = { "Si", "No"};
			int codigo = JOptionPane.showOptionDialog(null, " �Seguro que desea salir?", "",
					0, confirmationType, null, confirmationOptions, "Si");
		
			if (codigo == 0) {
				this.dispose();
				new menuPrincipal().setVisible(true);
			}
			if (codigo == 1) {
				opciones();
			}
		}
		
		if (code == 2)// Cancelar
		{

		}
		
	}

	public void jugar() {
		
		t = new Thread(this);
		t.start();
		
	}
	
	public void disconnect() throws SQLException {
		conn.close();
	}
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db/reversiDB.sqlite");
	}
	public void nombres()throws SQLException{
		String j1, j2;
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from Jugadores");
		j1=rs.getString("Jugador1");
		j2=rs.getString("Jugador2");
		rs.close();
		stat.close();
		
		nomJugNegras.setText(j1);
		nomJugBlancas.setText(j2);
		
	}
	
	public void run() {
		long tTotal;
		Casilla cas;
		Jugador jAct;

		pararPartida = false;

		// actualizar n�mero de fichas en el GUI
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

			// comprobar si el movimiento es v�lido
			if (j.ponerFicha(j.getTurno(), jAct.getJugada())) {
				// actualizar n�mero de fichas en el GUI
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
		// pone en el GUI qui�n ha ganado
		if (j.getNumFichas(Juego.BLANCAS) > j.getNumFichas(Juego.NEGRAS)){
			mensajes.setText("��Las blancas ganan!!");
			if(nomJugBlancas.getText().equals("Jugador 2")==false && nomJugNegras.getText().equals("Jugador 1")==false){
				System.out.println("ey");
			
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
					actualizarPuntuacionBlancas();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					mostrarPuntuaciones();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		else if (j.getNumFichas(Juego.BLANCAS) < j.getNumFichas(Juego.NEGRAS)){
			mensajes.setText("��Las negras ganan!!");
			if(nomJugBlancas.getText().equals("Jugador 2")==false && nomJugNegras.getText().equals("Jugador 1")==false){
				System.out.println("ey");
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
					actualizarPuntuacionNegras();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mostrarPuntuaciones();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else{
			mensajes.setText("��Empate!!");
			if(nomJugBlancas.getText().equals("Jugador 2")==false && nomJugNegras.getText().equals("Jugador 1")==false){
				
				
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
					actualizarPuntuacionEmpate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					mostrarPuntuaciones();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	public void mostrarPuntuaciones()throws SQLException{
		float estad1, estad2;
		String nom1, nom2;
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT * FROM Usuario where Nombre='"+nomJugBlancas.getText()+"' or Nombre='"+nomJugNegras.getText()+"'");
		rs.next();
		estad1=((rs.getFloat("PartidasGanadas")/rs.getFloat("PartidasJugadas"))*100);
		nom1=rs.getString("Nombre");
		rs.next();
		estad2=((rs.getFloat("PartidasGanadas")/rs.getFloat("PartidasJugadas"))*100);
		nom2=rs.getString("Nombre");
		int messageType = JOptionPane.QUESTION_MESSAGE;
		String[] options = { "OK" };
		int code = JOptionPane.showOptionDialog(null, "   Nombre                 Porcentaje de victorias\n" +
				"   "+nom1+"                  "+estad1+"\n" +
						"   "+nom2+"                         "+estad2+"", "Final",
				0, messageType, null, options, "OK");
		if (code == 0)
		{
						
		}
		rs.close();
		stat.close();
	}

	public void actualizarPuntuacionBlancas() throws SQLException{
		System.out.println("ey");
		//Actualizar ganadas
		PreparedStatement stat = conn.prepareStatement("update Usuario set PartidasGanadas=PartidasGanadas+1 where Nombre='"+nomJugBlancas.getText()+"'");
		stat.executeUpdate();
		stat.close();
		
		//Actualizar jugadas
		PreparedStatement stat2 = conn.prepareStatement("update Usuario set PartidasJugadas=PartidasJugadas+1 where Nombre='"+nomJugBlancas.getText()+"'");
		stat2.executeUpdate();
		stat2.close();
		
		//Actualizar jugadar perdedor
		PreparedStatement stat3 = conn.prepareStatement("update Usuario set PartidasJugadas=PartidasJugadas+1 where Nombre='"+nomJugNegras.getText()+"'");
		stat3.executeUpdate();
		stat3.close();
		
	}
	
	public void actualizarPuntuacionNegras() throws SQLException{
		System.out.println("ey");
		//Actualizar ganadas
		PreparedStatement stat = conn.prepareStatement("update Usuario set PartidasGanadas=PartidasGanadas+1 where Nombre='"+nomJugNegras.getText()+"'");
		stat.executeUpdate();
		stat.close();
		
		//Actualizar jugadas
		PreparedStatement stat2 = conn.prepareStatement("update Usuario set PartidasJugadas=PartidasJugadas+1 where Nombre='"+nomJugNegras.getText()+"'");
		stat2.executeUpdate();
		stat2.close();
		
		//Actualizar jugadar perdedor
		PreparedStatement stat3 = conn.prepareStatement("update Usuario set PartidasJugadas=PartidasJugadas+1 where Nombre='"+nomJugBlancas.getText()+"'");
		stat3.executeUpdate();
		stat3.close();
	}
	
	public void actualizarPuntuacionEmpate() throws SQLException{
		System.out.println("ey");
		//Actualizar jugadas blancas
		PreparedStatement stat = conn.prepareStatement("update Usuario set PartidasJugadas=PartidasJugadas+1 where Nombre='"+nomJugBlancas.getText()+"'");
		stat.executeUpdate();
		stat.close();		
		
		
		
		//Actualizar jugadas negras
		PreparedStatement stat2 = conn.prepareStatement("update Usuario set PartidasJugadas=PartidasJugadas+1 where Nombre='"+nomJugNegras.getText()+"'");
		stat2.executeUpdate();
		stat2.close();
				
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new OteloGUI();
	}

	private class GestorMenu implements ActionListener, ItemListener {
		

		public GestorMenu() {
		}

		public void actionPerformed(ActionEvent e) {
			// nueva partida
			
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
			botonEmpezar.setVisible(false);
			botonOpciones.setVisible(true);			
		}


		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

}