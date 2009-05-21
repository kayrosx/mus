package util;

import java.util.HashMap;
import java.util.LinkedList;

import obj.Usuario;


public class MapaJugadores
{
	// Atributo
	private static HashMap<String, Usuario> mapa = new HashMap<String, Usuario>();
	
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
	
	public static Usuario getUsuario(Usuario u)
	{
		return mapa.get(u.getNombre());
	}
	
	public static boolean modificaUsuario(Usuario u)
	{
		boolean mod = false;
		if(exists(u))
		{
			mapa.remove(u.getNombre());
			if(add(u))
				mod = true;
		}
		return mod;
	}
	
	public static LinkedList<Usuario> devolverLista()
	{
		return new LinkedList<Usuario>(mapa.values());
	}
	
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
}
