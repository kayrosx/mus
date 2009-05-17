package net;

import java.net.*;
import java.io.*;

import obj.Usuario;

import ui.JAppServidor;
import util.MapaJugadores;
import util.PassIncorrecto;
import util.UsuarioNoExiste;

public class ConexionClientes extends Thread
{
	private Socket socketServidor;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	private JAppServidor ventanaServidor;
	
	public ConexionClientes(Socket s, JAppServidor v)
	{
	    socketServidor = s;
        ventanaServidor = v;
        try
		{
			ois = new ObjectInputStream(socketServidor.getInputStream());
			oos = new ObjectOutputStream(socketServidor.getOutputStream());
			dos = new DataOutputStream(socketServidor.getOutputStream());
		} 
        catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		Usuario u = null;
		String s = "El usuario ";
		try
		{
			u = (Usuario)ois.readObject();
			
			s = s + u.getNombre() + " y se ha identificado "; 
			int i;
			try
			{
				MapaJugadores.validate(u);
				i = 0;
				s = s + "correctamente\n";
			}
			catch (PassIncorrecto e)
			{
				s = s + "incorrectamente\n";
				i = 1;
			}
			catch (UsuarioNoExiste e)
			{
				s = s + "incorrectamente\n";
				i = 2;
			}
			dos.writeInt(i);
			ventanaServidor.addMensaje(s);
		}
		catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
