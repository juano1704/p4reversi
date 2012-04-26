package otelo.jugadores;

import java.io.Serializable;

import otelo.Casilla;
import otelo.Juego;

/**
 *  Representa un jugador de Otelo. Al ser una clase abstracta, en la práctica los jugadores
 *  deben ser descendientes de esta clase. Cada jugador de Otelo se ejecuta
 *  en su propio {@link java.lang.Thread Thread}.
 * 
 */
abstract public class Jugador implements Runnable, Serializable {
   /**
    * Significa que el jugador es humano (es decir, el entorno gráfico debe chequear
    * movimientos de ratón o entradas de teclado o similar para obtener el movimiento).
    */	
   public static final int HUMANO = 1;
   /**
    * Significa que el jugador está controlado por la máquina.
    */
   public static final int MAQUINA = 2;
   /**
    * color con el que juega.
    */
   private byte color;
   /**
    * tipo de jugador que es: {@link #HUMANO HUMANO} o {@link #MAQUINA MAQUINA}
    */
   private int tipo;
   /**
    * Referencia al {@link otelo.Juego juego} en el que participa el jugador.
    */
   private Juego j;
   /**
    * Casilla que representa la mejor jugada que se puede hacer.
    */
   private Casilla jugada;
   /**
    * Hilo que ejecuta el jugador. No se almacena si éste se serializa.
    */
   private transient Thread t;
   /**
    * Variable booleana que indica si el jugador debe parar de "pensar" la jugada actual.
    */
   private boolean parar;

   /**
    * Crea un jugador sabiendo el color con el que juega y si es humano o máquina.
    * @param color Color de las fichas con las que juega.
    * @see Juego#BLANCAS BLANCAS
    * @see Juego#NEGRAS NEGRAS
    * @see #HUMANO HUMANO
    * @see #MAQUINA MAQUINA
    * @param tipo Si es humano o máquina.
    */
   public Jugador(byte color, int tipo) {
   	  this.color = color;
   	  this.tipo = tipo;
   	  parar = false;
   }
   
   /**
    * Obtiene el color de las fichas con las que juega este jugador.
    * @return Color de las fichas.
    * @see Juego#BLANCAS BLANCAS
    * @see Juego#NEGRAS NEGRAS
    */
   public byte getColor() {
   	  return color; 
   }

   /**
    * Obtiene el tipo de jugador que es: controlado por el usuario o por la
    * máquina.
    * @return Tipo de jugador
    * @see #HUMANO HUMANO
    * @see #MAQUINA MAQUINA
    */
   public int getTipo() {
   	  return tipo;
   }
   
   /**
    * Obtiene una referencia al juego en el que está participando.
    * @return El juego.
    */
   public Juego getJuego() {
   	  return j;
   }
   
   /**
    * Obtiene la mejor jugada calculada hasta el momento para la
    * situación actual del tablero.
    * @return La casilla a la que mover para hacer la mejor jugada.
    */
   public Casilla getJugada() {
   	  return jugada;
   }
   
   /**
    * Permite cambiar el lado del que juega el jugador.
    * @param color El color de las fichas que va a llevar.
    * @see Juego#BLANCAS BLANCAS
    * @see Juego#NEGRAS NEGRAS
    */
   public void setColor(byte color) {
   	  this.color = color;
   }
   
   /**
    * Actualiza la mejor jugada calculada hasta el momento. 
    * El jugador debería llamar a este método cuando encuentre
    * la mejor jugada. Puede llamar varias veces al metodo
    * si va refinando la solucion de manera iterativa.
    * @param jugada Casilla a la que mover para hacer la mejor jugada.
    */
   public void setJugada(Casilla jugada) {
   	  this.jugada = jugada;
   }
   
   /**
    * Calcula la mejor jugada. Inicializa un thread que comienza
    * a "pensar" dicha jugada.
    * @param j Juego en el que participa el jugador.
    */   
   public void hacerJugada(Juego j) {   	  
   	  jugada = null;
   	  t = new Thread(this);
   	  t.setName("Jugador " + Juego.nomColores[color]);
   	  
   	  this.j = j;
   	  t.start();
   }
   
   /**
    * El thread que calcula la mejor jugada. No se debería llamar a este método
    * directamente, ya que lo llama {@link #hacerJugada(Juego) hacerJugada(Juego)}
    */
   public void run() {
   	  setJugada(calcularMejorJugada(j));
   }
   
   /**
    * Indica al jugador que debería parar de "pensar" su jugada.
    * Esto solo tendrá efecto si el jugador contempla esta posibilidad
    * en el thread, chequeando {@link #getParar() getParar()} de modo
    * regular durante la ejecución.
    * @param valor
    */
   public void setParar(boolean valor) {
   	  parar = valor;
   }
   
   /**
    * Comprueba si deseamos que el jugador pare de "pensar"
    * @return <i>true</i> si debe parar, <i>false</i> en caso contrario.
    */
   public boolean getParar() {
   	  return parar;
   }

   /**
    * Obliga al jugador a parar de "pensar" su jugada actual.
    *
    */   
   public void parar() {
   	  //si sabemos que el jugador va a colaborar, pararlo
   	  //"por las buenas"
   	  if (esParable()) {
   	  	 setParar(true);
		 try {
		    t.join();
		 }
		 catch(InterruptedException ie){}   	  	 
   	  }
   	  //si no, pararlo "por las malas"
   	  else  
   	     t.stop();
   }
   
   /**
    * Indica que el jugador puede parar por sí mismo de "pensar"
    * su jugada sin necesidad de "matar" el hilo que lo está ejecutando.
    * En ese caso, el jugador debería chequear de manera regular el
    * método {@link #getParar() getParar()} durante su ejecuación
    * para comprobar si deseamos que pare. Por defecto un jugador
    * no es "parable". Para indicar lo contrario hay que sobreescribir
    * el método y hacer que devuelva <i>true</i>.
    * @return <i>true</i> si el jugador colaborará en la "parada", 
    * <i>false</i> en caso contrario.
    */
   public boolean esParable() {
   	  return false;
   }
   
   /**
    * Calcula la mejor jugada. <b>Aquí (en las clases descendientes)
    * se debería meter el código del alfa-beta</b>. Este método
    * solo debería ser llamado desde el propio jugador.
    * @param j El juego en el que está el jugador
    * @return La posición mejor para poner ficha
    */
   abstract protected Casilla calcularMejorJugada(Juego j);
   /**
    * Devuelve una cadena que identifica al jugador.
    * @return "Nombre" del jugador.
    */
   abstract public String getNombre();   	
} 