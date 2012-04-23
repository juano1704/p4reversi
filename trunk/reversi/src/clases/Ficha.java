package clases;

public class Ficha {
	
	private String color;  //color de la ficha
	private boolean seleccionada;  // saber si esta seleccionada o no en el tablero
	private boolean origen;  // la situación de la ficha
	
	public Ficha()
	{
		color="";
		seleccionada=false;
		origen=false;
		
	}
	public Ficha(String c, boolean s, boolean o)

	{
		color=c;
		seleccionada=s;
		origen=o;
	}
	
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isSeleccionada() {
		return seleccionada;
	}
	public void setSeleccionada(boolean seleccionada) {
		this.seleccionada = seleccionada;
	}
	public boolean isOrigen() {
		return origen;
	}
	public void setOrigen(boolean origen) {
		this.origen = origen;
	}
	
	public void mostrar()
	{
		System.out.println("Color:" + color);
		System.out.println("Seleccionada:" + seleccionada);
		System.out.println("Origen:" + origen);
	}
	public static void main(String[] args) {
		Ficha f=new Ficha();
		f.mostrar();


	}

}
