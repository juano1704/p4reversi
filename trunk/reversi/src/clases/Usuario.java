package clases;

public class Usuario {
	private String nombre;
	private char contrase�a;
	private int partidasJugadas;
	private int partidasGanadas;

	public Usuario() {
		nombre = "";
		contrase�a = '0';
		partidasJugadas = 0;
		partidasGanadas = 0;
	}

	public Usuario(String n, char c, int pJ, int pG) {
		nombre = n;
		contrase�a = c;
		partidasJugadas = pJ;
		partidasGanadas = pG;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(char contrase�a) {
		this.contrase�a = contrase�a;
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
		System.out.println("Contrase�a:" + contrase�a);
		System.out.println("N�mero de partidas jugadas:" + partidasJugadas);
		System.out.println("N�mero de partidas ganadas:" + partidasGanadas);
	}

	public static void main(String[] args) {
		String nombre;
		String contrase�a;

		System.out.println("Introduce el nombre de usuario:");
		nombre = Utilidades.leerCadena();
		while (nombre.length() > 15)
		{
			System.out.println("Vuelva a introducirlo, debe tener como maximo 15 caracteres:");
			nombre = Utilidades.leerCadena();}

		System.out.println("Introduce la contrase�a:");
		contrase�a = Utilidades.leerCadena();
		while (contrase�a.length()!=5)
		{	
			System.out.println("Error, vuelva a introducirla:");
			contrase�a = Utilidades.leerCadena();}

		Usuario u = new Usuario();

		u.mostrar();
	}

}
