package mus.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

import mus.obj.Usuario;
import mus.util.MapaJugadores;

public class EscribirUsuarios
{
	public static boolean guardaUsuarios()
	{
		
		LinkedList<Usuario> lista = MapaJugadores.devolverLista();
		Iterator<Usuario> i = lista.iterator();
		
		try 
		{			
			FileOutputStream fos = new FileOutputStream("usuarios.obj");  
			ObjectOutputStream oos = new ObjectOutputStream (fos);
		
			while(i.hasNext())
			{
				oos.writeObject(i.next());
			}
		
			oos.close();
			fos.close();
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		}	
	}
}
