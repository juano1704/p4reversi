package otelo;

import java.io.Serializable;

/**
 * 
 * Representa la posición de una casilla en el tablero. Prácticamente actúa como
 * si fuera un <em>struct</em> que agrupa fila y columna.
 *
 */
public class Casilla implements Serializable {
   /**
    * Fila en la que está la casilla. Las filas van de 0 a 7 de arriba a abajo.
    */	
   public int fila;
   /**
    * Columna en la que está la casilla. Las columnas van de 0 a 7 de izquierda
    * a derecha.
    */
   public int col;   
   /**
    * Crea una casilla a partir de su número de fila y columna
    */
   public Casilla(int fila, int col) {
      this.fila = fila;
      this.col = col;	
   }	
}