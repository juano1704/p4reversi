package otelo.jugadores;

import otelo.Casilla;
import otelo.Juego;

/**
 * Representa un jugador manejado por el usuario a través de ratón, teclado o similar.
 */

public class JugadorHumano extends Jugador {

    /**
     * Crea un jugador manejado por el usuario con el color especificado.
     * @param color Color de las fichas que lleva el jugador.
     */
    public JugadorHumano(byte color) {
       super(color, HUMANO);	
    }
    
    /**
     * Calcula la mejor jugada. En este caso se limita a esperar
     * que el entorno gráfico actualice la jugada por él.
     */
	protected Casilla calcularMejorJugada(Juego j) {
		setJugada(null);
		while (getJugada()==null && !getParar()) {
		   try {
			  Thread.sleep(100);
		   }
		   catch(InterruptedException ie) {		   	
		   }		   	
		}
		return getJugada();
	}
	
	/**
	 * En esta clase devuelve <i>true</i> indicando que el jugador colaborará parando por si
	 * mismo si se lo solicitamos mediante el método {@link Jugador#setParar(boolean) setParar()}.	 * 
	 */
	public boolean esParable() {
	   return true;
	}
	
	/**
	 * Devuelve una cadena identificando al jugador como "Humano"
	 */
	public String getNombre() {
	   return "Humano";	
	}
	
}
