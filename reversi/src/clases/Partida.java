package clases;

public class Partida {
	private String nombre;

	
	public Partida()
	{
		nombre="";
		
	}
	public Partida(String n)
	{
		nombre=n;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void mostrar()
	{
		System.out.println("Nombre de la partida:" + nombre);
	}
	public static void main(String []args)
	{
		Partida p=new Partida();
		p.mostrar();
	}
}
