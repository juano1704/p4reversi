package clases;

import java.util.LinkedList;

public class NombrePartida {

	public static void main(String[] args)
	{
		LinkedList<Partida>lPartidas=new LinkedList<Partida>();
		Partida p= new Partida();
		guardarPartida(lPartidas);
		
		

	}
	
	public static boolean guardarPartida(LinkedList<Partida> lPartidas)
	{
		String nombre;
		System.out.println("Introduce el nombre de la partida");
		nombre=Utilidades.leerCadena();
		boolean enc=false;
		int i=0;
		while(enc&&i<lPartidas.size())
		{
			Partida p = lPartidas.get(i);
			if(nombre.equals(p.getNombre()))
				
			{
			do
			{
		System.out.println("El nombre ya existe, desea sobreescribir?(S/N)");
			char opc=Utilidades.leerCaracter();
			
			
			if (opc=='S'||opc=='s')
			{
					
					lPartidas.remove(p);
					lPartidas.add(i, p);
			}
			else
				
				Partida p1=new Partida();
			p1=lPartidas.add
				
				
			}
			while(opc!='S'||opc!='s'||opc!='n'||opc!='N');
			
		
			
		
			return true;
			}
	
			else
				return false;
			i++;
		
		}	
					
			
		
		
	}
	

}
		}
