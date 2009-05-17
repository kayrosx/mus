package mus.io;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import mus.obj.Usuario;
import mus.util.MapaJugadores;

public class LeerUsuarios
{
	public static boolean cargaUsuarios()
	{
		FileInputStream fis = null;
		ObjectInputStream ois = null; 
		
		try
		{		
			fis = new FileInputStream("usuarios.obj");  
			ois = new ObjectInputStream(fis);
			while(true)
				MapaJugadores.add((Usuario)ois.readObject());
		}
		catch (EOFException e) 
		{
			try
			{
				ois.close();
				fis.close();
				return true;
			}
			catch (IOException ex) 
			{
				e.printStackTrace();
				return false;
			} 			
		}
		catch (FileNotFoundException e) 
		{
			return false;
		}		
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}
