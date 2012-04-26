package otelo;

import java.io.Serializable;

/**
 * 
 * Representa la posici�n de una casilla en el tablero. Pr�cticamente act�a como
 * si fuera un <em>struct</em> que agrupa fila y columna.
 *
 */
public class Casilla implements Serializable {
   /**
    * Fila en la que est� la casilla. Las filas van de 0 a 7 de arriba a abajo.
    */	
   public int fila;
   /**
    * Columna en la que est� la casilla. Las columnas van de 0 a 7 de izquierda
    * a derecha.
    */
   public int col;   
   /**
    * Crea una casilla a partir de su n�mero de fila y columna
    */
   public Casilla(int fila, int col) {
      this.fila = fila;
      this.col = col;	
   }	
}