package otelo.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import otelo.*;
import otelo.jugadores.*;

/**
 * Interfaz gráfico del Otelo. Es la clase principal.
 */
public class OteloGUI extends JFrame implements Runnable {

   public static final long T_CHEQUEO = 100;
   private static final String HUMANO = "Humano";
   private static final String MAQUINA = "Máquina";
   private static String tiposJugador[] = {HUMANO, MAQUINA};

   private Juego j;
   private boolean pararPartida;
   Thread t;


   //componentes gráficos
   private PanelTablero p;
   private JLabel nomJugBlancas, nomJugNegras;
   private JLabel numBlancas, numNegras;
   private Container pFichas;
   private JLabel mensajes;
   private JMenuItem miNuevo, miCargar, miGuardar, miPausa;

   public OteloGUI() {
      
      //inicializar datos miembro
      pararPartida = false;
      //cuestiones gráficas
      crearComponentes();
      //inicializar ventana
      setTitle("mierdo del Otelo muahahahaah(hay qe cambiar esto juoojo)");
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
	
      //crear tablero
      p = new PanelTablero();

      
      //jugadores
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
      //numero de fichas
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
	  pDatos.setLayout(new GridLayout(0,1));
	  pDatos.add(pJugadores);
	  pDatos.add(pFichas);
	  pDatos.add(mensajes);
      
      
      //panel de control
      JPanel pControl = new JPanel();
	  pControl.add(pDatos, BorderLayout.NORTH);
     
      //añadir componentes a la ventana principal
      getContentPane().add(p, BorderLayout.WEST);
      getContentPane().add(pControl, BorderLayout.CENTER);
      
      //menús
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
      miCargar = new JMenuItem("Cargar");
	  miCargar.addActionListener(new GestorMenu(this));
	  menuArchivo.add(miCargar);
      miGuardar = new JMenuItem("Guardar");
      miGuardar.addActionListener(new GestorMenu(this));
      menuArchivo.add(miGuardar);
      
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

	  //actualizar número de fichas en el GUI
	  numBlancas.setText(Integer.toString(j.getNumFichas(Juego.BLANCAS)));
	  numNegras.setText(Integer.toString(j.getNumFichas(Juego.NEGRAS)));
      
      //bucle de partida
	  while(!j.finPartida()) {

	  	 //si un jugador no puede mover, el turno debe pasar al siguiente
	  	 if (!j.puedePoner(j.getTurno())) {
			j.cambiarTurno();

	  	 }
		//actualiza turno en el GUI
		if (j.getTurno()==Juego.BLANCAS)
		   mensajes.setText("Mueven las blancas");
		else
		   mensajes.setText("Mueven las negras");
		//pedirle al jugador actual su movimiento
		 tTotal = 0;
		 jAct = j.getJugadorActual();
		 jAct.hacerJugada(j);   
		 while ((tTotal <= Juego.T_MAX_MOV)&&(jAct.getJugada()==null)&&(!pararPartida)) {
			try {      	  	   
				Thread.sleep(T_CHEQUEO);
			}
			catch(InterruptedException ie) {}
			tTotal += T_CHEQUEO;
		 }
		 System.out.println(tTotal);
		 //si se ha parado la partida, termina el hilo
		 if (pararPartida) {
		 	j.parar();
		 	System.out.println("pausa");
		 	return;
		 }
		    
		 
		 //comprobar si el movimiento es válido
		 if (j.ponerFicha(j.getTurno(), jAct.getJugada())) {
			//actualizar número de fichas en el GUI
			numBlancas.setText(Integer.toString(j.getNumFichas(Juego.BLANCAS)));
			numNegras.setText(Integer.toString(j.getNumFichas(Juego.NEGRAS)));		 
			//actualizar tablero en el GUI
			p.repaint();
			pFichas.repaint();
			j.cambiarTurno();
		 }
	  }
	  //pone en el GUI quién ha ganado
	  if (j.getNumFichas(Juego.BLANCAS)>j.getNumFichas(Juego.NEGRAS))
	     mensajes.setText("¡¡Las blancas ganan!!");
	  else if (j.getNumFichas(Juego.BLANCAS)<j.getNumFichas(Juego.NEGRAS))
	          mensajes.setText("¡¡Las negras ganan!!");
	       else
	          mensajes.setText("¡¡Empate!!"); 
	     	
   }

   public static void main(String[] args) {
      new OteloGUI();
   }
   
   

   private class GestorMenu implements ActionListener, ItemListener {
   	   private JFrame ventana;
   	   
	   public GestorMenu() {
	   }
	   
   	   public GestorMenu(JFrame vent) {
   	   	  ventana = vent;
   	   }
   	   
	   public void actionPerformed(ActionEvent e) {
	   	  //nueva partida
   	      if (e.getSource()==miNuevo) {
   	      	 nuevaPartida();
   	      }
   	      //grabar partida
   	      if (e.getSource()==miGuardar)
   	         guardarPartida();
   	        
   	      //cargar partida
		  if (e.getSource()==miCargar)
		     cargarPartida();
   	           	        
	   }
	   
	   private void nuevaPartida() {
	   	  Jugador jBlancas, jNegras;
	   	  
	   	  //elegir tipos de jugador
	   	  jBlancas = elegirJugador(ventana, Juego.BLANCAS);
	   	  jNegras = elegirJugador(ventana, Juego.NEGRAS);
	   	  //comenzar partida
		  j = new Juego(jBlancas, jNegras);
		  p.setJuego(j);
		  p.repaint();
		  nomJugBlancas.setText(jBlancas.getNombre());
		  nomJugNegras.setText(jNegras.getNombre());		  
		  miPausa.setEnabled(true);
		  jugar();
	   }
	   
	   private void guardarPartida() {
			try{
				FileDialog fDialogo = new FileDialog(ventana);
				fDialogo.setMode(FileDialog.SAVE);
				fDialogo.show();
				FileOutputStream fileStream = new FileOutputStream(fDialogo.getDirectory() 
				                                                   + fDialogo.getFile());
				ObjectOutputStream stream = new ObjectOutputStream(fileStream);					
				stream.writeObject(j); 		
				stream.close();		
				fileStream.close();		
			}
			catch(Exception e){
				System.out.println("Error de escritura en fichero" + e);
			}

	   }

		private void cargarPartida() {
			 try{
				 FileDialog fDialogo = new FileDialog(ventana);
				 fDialogo.setMode(FileDialog.LOAD);
				 fDialogo.show();
				 FileInputStream fileStream = new FileInputStream(fDialogo.getDirectory() 
																	+ fDialogo.getFile());
				 ObjectInputStream stream = new ObjectInputStream(fileStream);
				 
				 //para la partida, si es que hay una en marcha
				 if (j!=null) {
					 pararPartida = true;
					 try {
					    t.join();
					 }
					 catch(InterruptedException ie){}
			     }	
				 //lee la partida del fichero	
				 j = (Juego) stream.readObject();
				 p.setJuego(j);
				 stream.close();		
				 fileStream.close();
				 //vuelve a poner la partida en marcha
				 nomJugBlancas.setText(j.getJugadorBlancas().getNombre());
				 nomJugNegras.setText(j.getJugadorNegras().getNombre());		  
		
				 jugar();
			 }
			 catch(Exception e){
				 System.out.println("Error de lectura" + e);
			 }
	
		}

	   
	   public void itemStateChanged(ItemEvent e) {
	   	  //Parar/poner en marcha la partida
	   	  if (e.getStateChange()==ItemEvent.SELECTED) {
			 mensajes.setText("Partida en pausa");
			 pararPartida = true; 
			 //espera a que la partida se pare
			 try {
			 	t.join();
			 }
			 catch(InterruptedException ie){}
	   	  }
	   	  else {
			 pararPartida = false;
            if (j!=null) {
				jugar();
            }
                
	   	  }
	   	  
	   }
	   
	   private Jugador elegirJugador(JFrame ventana, byte color) {
	   	  String nomJugador;
	   	  
	   	  nomJugador = (String) JOptionPane.showInputDialog(ventana, Juego.nomColores[color], 
                                      "Elegir jugador", JOptionPane.PLAIN_MESSAGE,
                                      null, tiposJugador, "uno" );
          if (nomJugador.equals(MAQUINA))
             return new JugadorMaquina(color);
          else
             return new JugadorHumano(color);
	   }
   }

}