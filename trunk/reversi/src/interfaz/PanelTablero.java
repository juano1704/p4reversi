package interfaz;

import javax.swing.*;

import clasesBasicas.*;

import java.awt.*;
import java.awt.event.*;

import jugadores.*;

/**
 * Representa en pantalla el tablero donde se juega la partida. Es la clase
 * encargada de dibujarlo en pantalla y de recoger los eventos del ratón que
 * representan las jugadas del usuario.
 */
public class PanelTablero extends JPanel implements MouseListener {
	public final static Color COLOR_TABLERO = new Color(17, 149, 7);
	public final static int TAM_CASILLA = 40;
	public final static int TAM_FICHA = 30;
	public final static int TAM_FICHA_POS = 15;
	public final static int MARGEN_CASILLA = (TAM_CASILLA - TAM_FICHA) / 2;

	private Juego j;

	public PanelTablero() {
		super();
		setBackground(COLOR_TABLERO);
		setPreferredSize(new Dimension(TAM_CASILLA * 8 + 1, TAM_CASILLA * 8 + 1));
		addMouseListener(this);
	}

	public void setJuego(Juego j) {
		this.j = j;
	}

	private void dibujaFicha(Graphics g, byte color, int fila, int col) {
		Color colorAct;
		colorAct = g.getColor();
		if (color == Juego.BLANCAS)
			g.setColor(Color.white);
		else
			g.setColor(Color.black);
		g.fillOval(col * TAM_CASILLA + MARGEN_CASILLA, fila * TAM_CASILLA
				+ MARGEN_CASILLA, TAM_FICHA, TAM_FICHA);
		g.setColor(colorAct);
	}

	private void dibujaFichaPosible(Graphics g, byte color, int fila, int col) {
		Color colorAct;
		colorAct = new Color(170, 14, 57);
		g.fillRect(col * TAM_CASILLA + MARGEN_CASILLA, fila * TAM_CASILLA
				+ MARGEN_CASILLA, TAM_FICHA_POS, TAM_FICHA);
		g.setColor(colorAct);
	}

	public void paint(Graphics g) {
		int lin;
		int f, c;
		byte color;

		super.paint(g);
		// dibujar líneas
		for (lin = 0; lin <= 8; lin++) {
			// horizontales
			g.drawLine(0, TAM_CASILLA * lin, TAM_CASILLA * 8, TAM_CASILLA * lin);
			// verticales
			g.drawLine(TAM_CASILLA * lin, 0, TAM_CASILLA * lin, TAM_CASILLA * 8);
		}

		// dibujar fichas
		if (j == null)
			return;
		try {
			for (f = 0; f < 8; f++)
				for (c = 0; c < 8; c++) {
					color = j.getCasilla(f, c);
					if (color != Juego.VACIA)
						dibujaFicha(g, j.getCasilla(f, c), f, c);
				}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// MANEJO DE EVENTOS
	public void mouseClicked(MouseEvent e) {
		Jugador jugador;
		Casilla cas;

		if (j == null)
			return;
		jugador = j.getJugadorActual();
		if (jugador.getTipo() == Jugador.HUMANO) {
			cas = obtCasillaXY((int) e.getPoint().getX(), (int) e.getPoint()
					.getY());
			// if (j.movLegal(j.obtTurno(), cas)) {
			jugador.setJugada(cas);
			// }
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	private Casilla obtCasillaXY(int x, int y) {
		return new Casilla((int) (y / PanelTablero.TAM_CASILLA),
				(int) (x / PanelTablero.TAM_CASILLA));
	}

}