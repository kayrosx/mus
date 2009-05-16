package util;

import java.io.Serializable;

public class UsuarioNoExiste extends Exception implements Serializable
{
	private String nombre;
	private String error;
	
	public UsuarioNoExiste(String nombre)
	{
		this.nombre = nombre;
		this.error = "No existe el usuario ";
	}
	
	public String toString()
	{
		return error + nombre;
	}
}
