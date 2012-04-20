package clases;

public class Usuario {
	private String nombre;
	private char contraseña;
	private int partidasJugadas;
	private int partidasGanadas;

	public Usuario() {
		nombre = "";
		contraseña = '0';
		partidasJugadas = 0;
		partidasGanadas = 0;
	}

	public Usuario(String n, char c, int pJ, int pG) {
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

	public char getContraseña() {
		return contraseña;
	}

	public void setContraseña(char contraseña) {
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

	public static void main(String[] args) {
		String nombre;
		String contraseña;

		System.out.println("Introduce el nombre de usuario:");
		nombre = Utilidades.leerCadena();
		while (nombre.length() > 15)
		{
			System.out.println("Vuelva a introducirlo, debe tener como maximo 15 caracteres:");
			nombre = Utilidades.leerCadena();}

		System.out.println("Introduce la contraseña:");
		contraseña = Utilidades.leerCadena();
		while (contraseña.length()!=5)
		{	
			System.out.println("Error, vuelva a introducirla:");
			contraseña = Utilidades.leerCadena();}

		Usuario u = new Usuario();

		u.mostrar();
	}

}
