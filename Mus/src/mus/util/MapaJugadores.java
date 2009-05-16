package mus.util;

import java.util.HashMap;
import java.util.LinkedList;

import mus.obj.Usuario;

public class MapaJugadores
{
	// Atributo
	private static HashMap<String, Usuario> mapa = new HashMap<String, Usuario>();
	
	/** Devuelve si el usuario recibido existe ya o no */
	public static boolean exists(Usuario u)
	{
		return mapa.containsKey(u.getNombre());
	}
	
	/** Devuelve si el usuario recibido existe ya o no */
	public static void validate(Usuario u) throws PassIncorrecto, UsuarioNoExiste
	{
		if(mapa.containsKey(u.getNombre()))
		{
			Usuario user = mapa.get(u.getNombre());
			
			if(user.getNombre().equals(u.getNombre()) && !user.getPass().equals(u.getPass()))
			{
				throw new PassIncorrecto(u.getNombre());					
			}
		}
		else
			throw new UsuarioNoExiste(u.getNombre());
	}
	
	/** Añade el usuario al mapa hash */
	public static boolean add(Usuario u) //throws PassIncorrecto, UsuarioNoExiste
	{
		if(!mapa.containsKey(u.getNombre()))
		{
			mapa.put(u.getNombre(), u);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static LinkedList<Usuario> devolverLista()
	{
		return new LinkedList<Usuario>(mapa.values());
	}
	
	public static Usuario getUsuario(Usuario u)
	{
		return mapa.get(u.getNombre());
	}
	
	public static void modificaUsuario(Usuario u)
	{
		if(exists(u))
		{
			mapa.remove(u.getNombre());
			add(u);
		}
	}
}
