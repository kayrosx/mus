package mus.util;

import java.io.Serializable;

public class PassIncorrecto extends Exception implements Serializable
{
	private String nombre;
	private String error;
	
	public PassIncorrecto(String nombre)
	{
		this.nombre = nombre;
		this.error = "La contraseña introducida no se corresponde con la del usuario ";
	}
	
	public String toString()
	{
		return error + nombre;
	}

}
