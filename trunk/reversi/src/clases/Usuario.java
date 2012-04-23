package clases;

import java.util.LinkedList;

public class Usuario {
	private String nombre;
	private String contraseña;
	private int partidasJugadas;
	private int partidasGanadas;

	public Usuario() {
		nombre = "";
		contraseña = "";
		partidasJugadas = 0;
		partidasGanadas = 0;
	}

	public Usuario(String n, String c, int pJ, int pG) {
		nombre = n;
		contraseña = c;
		partidasJugadas = pJ;
		partidasGanadas = pG;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public int getPartidasGanadas() {
		return partidasGanadas;
	}

	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}

	public void mostrar() {
		System.out.println("Nombre de usuario:" + nombre);
		System.out.println("Contraseña:" + contraseña);
		System.out.println("Número de partidas jugadas:" + partidasJugadas);
		System.out.println("Número de partidas ganadas:" + partidasGanadas);
	}
	
	public static String introducirNombre(){
		System.out.println("Introduce el nombre de usuario:");
		String nombre = Utilidades.leerCadena();
		while (nombre.length() > 15) {
			System.out.println("Vuelva a introducirlo, debe tener como maximo 15 caracteres:");
			nombre = Utilidades.leerCadena();
		}
		return nombre;
	}
	
	public static boolean buscarUsuario (String n1, LinkedList<Usuario> lU){
		boolean enc=false;
		int i=1;
		while ((lU.size()<=i)||(enc!=true)){
			Usuario u1 = new Usuario();
			u1=lU.get(i);
			if (u1.getNombre().equals(n1)){
				enc = true;
			}
			else
				i++;
		}
		return enc;	
	}
	
	public static void main(String[] args) {
		String nombre;
		String contraseña;
		boolean enc=false;
		Usuario u = new Usuario();
		LinkedList<Usuario> lU = new LinkedList<Usuario>();

		nombre = introducirNombre();
		boolean enc2 = buscarUsuario(nombre, lU);
		while (enc==false){
			nombre=introducirNombre();
			enc2 = buscarUsuario(nombre, lU);
			if (enc2==true){
				enc = true;
			}
		}
		u.setNombre(nombre);
		System.out.println("Introduce la contraseña:");
		contraseña = Utilidades.leerCadena();
		while (contraseña.length() != 8) {
			System.out.println("Error, vuelva a introducirla:");
			contraseña = Utilidades.leerCadena();
		}
		u.setContraseña(contraseña);
		lU.add(u);
		u.mostrar();
	}

}
