package jugadores;

import clasesBasicas.Casilla;
import clasesBasicas.Juego;

/**
 * Jugador controlado por la m�quina. <b> Deb�is sustituir  el c�digo
 * del m�todo {@link #calcularMejorJugada(Juego) calcularMejorJugada(Juego)}
 * por vuestra implementaci�n del algoritmo alfa-beta.</b>
 */
public class JugadorMaquina extends Jugador {
   private static final int T_PAUSA = 1000;

   /**
	* Crea un jugador manejado por la m�quina con el color especificado.
	* @param color Color de las fichas que lleva el jugador.
	*/   
   public JugadorMaquina(byte color) {
   	  super(color, MAQUINA);	
   }

   /**
	* Calcula la mejor jugada: <b> deb�is sustituir el
	* c�digo de este m�todo por vuestra implementaci�n de alfa-beta </b>
	*/   
   protected Casilla calcularMejorJugada(Juego j) {
		int fila, col;
		Casilla cas;	
	   	
	   	j = getJuego();
	   	  
		//elegir aleatoriamente una casilla
		fila = (int) Math.floor(Math.random()*8);
		col = (int) Math.floor(Math.random()*8);
		//tomando esta casilla como punto de partida, elegir la primera
		//casilla a la que se pueda mover
		cas = new Casilla(0,0);
		for(int f=0; f<8 ; f++)
		   for(int c=0; c<8; c++) {
			  cas.fila = (fila + f) % 8;
			  cas.col = (col+c) % 8;
			  if (j.movLegal(getColor(), cas)) {
			  	 try { 
			  	 	Thread.sleep(T_PAUSA);
			  	 } 
			  	 catch (InterruptedException ie) {}			  	 
				 return cas;
			  }
		   }
		return null;
  }

  /**
   * Devuelve una cadena identificando al jugador como "M�quina"
   */  
  public String getNombre() {
  	 return "M�quina";      
  }
}