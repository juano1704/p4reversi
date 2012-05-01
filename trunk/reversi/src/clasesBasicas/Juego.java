package clasesBasicas;

import java.util.*;
import java.io.Serializable;

import jugadores.*;


/**
 * Contiene el estado actual del juego: a quién le toca mover, la disposición 
 * de las fichas en el tablero y referencias a los jugadores.
 *
 */
public class Juego implements Serializable {

   /**
    * Para definir las fichas negras.
    */
   public static final byte	NEGRAS = 0;
   /**
	* Para definir las fichas blancas.
	*/
   public static final byte BLANCAS = 1;
   /**
	* Define una casilla del tablero como vacía.
	*/
   public static final byte VACIA = 2;
   /**
	* Asigna nombres de cadena a las constantes que definen los colores.
	*/
   public static final String nomColores[] = {"negras", "blancas"};
   /**
	* Especifica el tiempo máximo (en ms.) que se puede tardar en hacer un movimiento.
	*/
   public static final long T_MAX_MOV = Long.MAX_VALUE;


   private byte[][] tablero;
   private byte turno;
   private Jugador jugadorBlancas, jugadorNegras;

   /**
	* Inicializa un nuevo juego. Dispone dos fichas blancas y dos negras en el centro del 
	* tablero, siguiendo la configuración estándar del Otelo. Las negras mueven a continuación.
	* @param jugadorBlancas El jugador que llevará las fichas blancas.
	* @param jugadorNegras El jugador que llevará las fichas negras.
	*/
   public Juego(Jugador jugadorBlancas, Jugador jugadorNegras) {
      int i,j;

      tablero = new byte[8][8];

      //vacía el tablero
      for (i=0; i<8 ; i++)
         for (j=0; j<8; j++)
            tablero[i][j] = VACIA;

      //pone las 4 fichas en el centro para comenzar
      tablero[3][3] = BLANCAS;
      tablero[3][4] = NEGRAS;
      tablero[4][3] = NEGRAS;
      tablero[4][4] = BLANCAS;

      //siempre empiezan las negras
      turno = NEGRAS;
      
      //asigna jugadores
      this.jugadorBlancas = jugadorBlancas;
      this.jugadorNegras = jugadorNegras;
   }
   
   /**
    * Devuelve una referencia al jugador que lleva las blancas.
    * @return el jugador con las blancas.
    */
   public Jugador getJugadorBlancas() {
   	  return jugadorBlancas;
   }
   
   /**
	* Devuelve una referencia al jugador que lleva las negras.
	* @return el jugador con las negras.
	*/
   public Jugador getJugadorNegras() {
	  return jugadorNegras;
   }

   /**
    * Devuelve una referencia al jugador al que le toca mover.
	* @return el jugador que mueve ahora.
	*/
   public Jugador getJugadorActual() {
   	  if (turno==BLANCAS)
   	     return jugadorBlancas;
   	  else
   	     return jugadorNegras;   
   }

   /**
	* Devuelve el número de fichas de un determinado color que hay en el tablero.
	* @param color de las fichas.
	* @see Juego#BLANCAS
	* @see Juego#NEGRAS
	* @return número de fichas de ese color.
	*/
   public int getNumFichas(byte color) {
   	  int cantidad;
   	  
   	  cantidad = 0;
   	  for(int i=0; i<8; i++)
	     for(int j=0; j<8; j++)
	        if (tablero[i][j]==color)
	           cantidad++;
   	  return cantidad;
   }

   /**
	* Devuelve la disposición de las piezas sobre el tablero. Las piezas están contenidas 
	* en un array 8x8 de bytes, codificadas mediante las constantes de color definidas en esta
	* clase.
	* @return el tablero, como un array de bytes 8x8.
	*/
   public byte[][] getTablero() {
   	  byte[][] tab;
   	  
   	  tab = new byte[8][8];
   	  for(int i=0; i<8 ; i++)
   	     for(int j=0; j<8; j++)
   	        tab[i][j] = tablero[i][j];
   	  
   	  return tab;      
   }


   /**
	* Devuelve el color de la ficha que está en una casilla, o VACIA si no contiene ficha
	* @param fila: fila de la casilla especificada
	* @param col: columna de la casilla especificada
	* @return el color de la ficha en dicha fila y columna.
	* @throws Exception si la posición especificada se sale del tablero (fila>7, fila<0, 
	* col>7, col<0).
	*/
   public byte getCasilla(int fila, int col) throws Exception {
   	  if ((fila>=0)&&(fila<8)&&(col>=0)&&(col<8))
   	     return tablero[fila][col];
   	  else
   	     throw new Exception("Fuera de los límites del tablero");
   }

   /**
	* Devuelve el color del jugador que mueve a continuación
	* @return el color de las fichas que deben mover.
	*/
   public byte getTurno() {
      return turno;
   }

   /**
	* Cambia el turno al otro jugador.
	* @return el color del jugador que mueve a continuación.
	*/
   public byte cambiarTurno() {
      if (turno==NEGRAS)
         turno = BLANCAS;
      else
         turno = NEGRAS;
      return turno;
   }

   /**
	* Para el juego, parando al jugador que mueve a continuación.
	*/
   public void parar() {
  	  getJugadorActual().parar();
   }

   /**
	* Comprueba si debe acabar la partida. La partida acaba cuando ninguno de los jugadores
	* puede mover.
	* @return <i>true</i> si la partida ha terminado, <i>false</i> en caso contrario.
	*/
   public boolean finPartida() {
      if (!puedePoner(BLANCAS)&&(!puedePoner(NEGRAS)))
         return true;
      else
         return false;
   }

   /**
	* Comprueba si hay alguna posición en la que el jugador que mueve
	* a continuación puede poner ficha.
	* @return <i>true</i> si existe dicha posición, <i>false</i> en caso contrario.
	*/
   public boolean puedePoner(byte color) {
   	  int f,c;

   	  for (f=0; f<8; f++)
   	     for (c=0; c<8; c++)
  	           if (movLegal(color, new Casilla(f,c)))
   	              return true;
   	  return false;
   }


   /**
	* Pone una ficha en una casilla. El método comprueba que el movimiento sea legal.
	* Si lo es, la situación del tablero cambiará para reflejar el nuevo movimiento.
	* @param color color de la ficha a poner
	* @param destino casilla en la que se intenta poner la ficha.
	* @return <i>true</i> si el movimiento es válido, <i>false</i> en caso contrario.
	*/
   public boolean ponerFicha(byte color, Casilla destino) {
   	  List pivotes;
   	  Iterator it;
   	  int incFila, incCol;

   	  //comprobar que el destino está vacío
   	  if (tablero[destino.fila][destino.col]!=VACIA)
   	     return false;

   	  //buscar la/s ficha/s que hace/n de pivote
   	  pivotes = crearListaPivotes(color, destino);

   	  //si no hay pivotes, el movimiento no es válido
   	  if (pivotes.size()==0)
   	     return false;

   	  //si hay pivotes, se deben cambiar de color las fichas entre la posición
   	  //actual y cada uno de los pivotes
   	  it = pivotes.iterator();
   	  while (it.hasNext()) {
   	     voltearFichas(destino, (Casilla)it.next());
   	  }

   	  //colocar la nueva ficha en el tablero
   	  tablero[destino.fila][destino.col] = color;

   	  return true;
   }


   /**
	* Voltea (cambia de color) todas las fichas del color contrario comprendidas entre
	* la nueva pieza y una ya puesta (el "pivote").
	* @param origen casilla donde se ha colocado la nueva pieza.
	* @param pivote casilla donde está la pieza que actúa de pivote.
	*/
   private void voltearFichas(Casilla origen, Casilla pivote) {
   	  int incFila, incCol;
   	  int numFilas, numCols;
   	  int numVolteos;
   	  int i, f, c;

   	  numFilas = pivote.fila - origen.fila;
   	  numCols = pivote.col - origen.col;

   	  //ver la dirección del pivote con respecto a la nueva ficha
   	  if (numFilas!=0)
         incFila = numFilas/Math.abs(numFilas);
      else
         incFila = 0;
      if (numCols!=0)
         incCol = numCols/Math.abs(numCols);
      else
         incCol = 0;

      numVolteos = Math.max(Math.abs(numFilas), Math.abs(numCols))-1;
      f = origen.fila;
      c = origen.col;

      //"dar la vuelta" a las fichas
      for(i=0; i<numVolteos; i++) {
      	 f += incFila;
      	 c += incCol;
      	 if (tablero[f][c] == BLANCAS)
      	    tablero[f][c] = NEGRAS;
      	 else
      	    tablero[f][c] = BLANCAS;
      }

   }

   /**
	* Comprueba que un movimiento sea legal.
	* @param color color de la pieza que se va a colocar.
	* @param c casilla en la que se va a poner la pieza.
	* @return <i>true</i> si el movimiento es legal, <i>false</i> en caso contrario.
	*/
   public boolean movLegal(byte color, Casilla c) {
   	  int incFila, incCol;

      //comprobar que la casilla esté vacía
      if (tablero[c.fila][c.col]!=VACIA)
         return false;
         
   	  //recorrer las 8 direcciones posibles
   	  for (incFila=-1; incFila<=1; incFila++)
   	     for (incCol=-1; incCol<=1; incCol++)
   	        if ((incCol!=0)||(incFila!=0)) {
   	           //buscar un pivote en esa dirección
   	           if (buscarPivote(color, c, incFila, incCol)!=null)
   	              return true;
   	        }
      return false;
   }

   /**
	* Crea una lista con todos los <i>pivotes</i>: piezas que "encierran" a las piezas contrarias
	* si se traza una línea recta entre ellas y la posición de la pieza que vamos a poner.
	* @param color color de la pieza que se va a poner.
	* @param origen casilla donde se pretende colocar la pieza.
	* @return Una lista con los pivotes.
	*/
   private List crearListaPivotes(byte color, Casilla origen) {
   	  List pivotes;
   	  Casilla cas;
   	  int incFila, incCol;

   	  pivotes = new ArrayList();
   	  //recorrer las 8 direcciones posibles
   	  for (incFila=-1; incFila<=1; incFila++)
   	     for (incCol=-1; incCol<=1; incCol++)
   	        if ((incCol!=0)||(incFila!=0)) {
   	           //buscar un pivote en esa dirección
   	           cas = buscarPivote(color, origen, incFila, incCol);
   	           if (cas!=null)
   	              pivotes.add(cas);
   	        }

      return pivotes;
   }


   /**
	* Busca un <i>pivote</i> en la dirección especificada (un pivote es una pieza que
	* "encierra" a las piezas contrarias si se traza una línea recta entre ella 
	* y la posición de la pieza que vamos a poner.
	* @param color color de la pieza que vamos a poner.
	* @param incCol si es positiva indica que el pivote se buscará en las columnas de más
	* a la derecha, si es 0 se buscará en la misma columna y si es negativo en las columnas
	* de más a la izquierda de la ficha que vamos a poner.
	* @param incFila si es positiva indica que el pivote se buscará en las filas de más
	* abajo,  si es 0 se buscará en la misma fila y si es negativo en las columnas
	* de más arriba de la ficha que vamos a poner.
	* @return la casilla donde se encuentra el pivote en esa dirección, <i>null</i> en caso
	* de que no exista.
	*/
   private Casilla buscarPivote(byte color, Casilla origen, int incFila, int incCol) {
   	  int f,c;

      //a partir de dónde se empieza a buscar el pivote
   	  f = origen.fila + incFila;
   	  c = origen.col + incCol;

   	  //no buscar el pivote fuera del tablero
   	  if ((f<0)||(f>7)||(c<0)||(c>7))
   	     return null;

   	  //tiene que haber al menos una ficha del color contrario que "flanquear"
   	  //entre la nueva ficha y el pivote
   	  if ((tablero[f][c]==VACIA)||((tablero[f][c]==color)))
   	     return null;

   	  //buscar y devolver posición del pivote
   	  while ((f>=0)&&(f<8)&&(c>=0)&&(c<8)) {
         //hemos llegado a una casilla vacía, no hay pivote
   	     if (tablero[f][c] == VACIA)
   	        return null;
   	     //pivote encontrado (ficha del mismo color)
   	     if (tablero[f][c] == color)
   	        return new Casilla(f,c);
   	     //siguiente casilla
         f += incFila;
   	     c += incCol;
   	  }

   	  //no se ha encontrado pivote
   	  return null;
   }
   
}