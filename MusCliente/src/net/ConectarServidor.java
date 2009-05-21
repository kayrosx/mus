package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import obj.Usuario;
import ui.JVentanaInicio;

public class ConectarServidor extends Thread
{
	private JVentanaInicio v;
	
	private Socket sCliente;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private static final int LOGIN = 1;
	private static final int CREA_USUARIO = 2;
	private static final int MODIFICA_USUARIO = 3;
	private static final int DEVUELVE_USUARIO = 4;
	private static final int DESCONECTAR = -1;
	
	public ConectarServidor(JVentanaInicio v)
	{
		this.v = v;
	}
	
	public void run()
	{
		try
		{
			sCliente = new Socket("127.0.0.1", 6060);
			dos = new DataOutputStream(sCliente.getOutputStream());
			oos = new ObjectOutputStream(sCliente.getOutputStream());
			dis = new DataInputStream(sCliente.getInputStream());
			ois = new ObjectInputStream(sCliente.getInputStream());
		} 
		catch (UnknownHostException e)
		{
			v.mensajeError(v.UNKNOWN_HOST, true);
		} 
		catch (IOException e)
		{
			v.mensajeError(v.NO_CONECTABLE, true);
		}
	}
	
	public int login(Usuario u)
	{
		int login;
		try 
		{
			// Envío el código para el servidor
			dos.writeInt(LOGIN);
			// Envío el usuario para ver si el login es correcto
			oos.writeObject(u);
			// Recibo un entero verificando el login
			login = dis.readInt();
		} 
		catch (IOException e) 
		{
			login = -1;
		}
		return login;
	}
	
	public boolean creaJugador(Usuario u)
	{
		boolean creado = false;
		try 
		{
			// Envío el código para el servidor
			dos.writeInt(CREA_USUARIO);
			// Envío el usuario a crear
			oos.writeObject(u);
			// Recibo si se ha creado correctamente
			creado = dis.readBoolean();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return creado;
	}
	
	public boolean modificaUsuario(Usuario u)
	{
		Boolean b = false;
		try 
		{
			// Envío el código para el servidor
			dos.writeInt(MODIFICA_USUARIO);
			// Envío el usuario a modificar
			oos.writeObject(u);
			// Recibo si se ha modificado correctamente
			b = dis.readBoolean();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return b;
	}
	
	public Usuario getUsuario(Usuario u)
	{
		try 
		{
			// Envío el código para el servidor
			dos.writeInt(DEVUELVE_USUARIO);
			// Envío el usuario
			oos.writeObject(u);
			// Recibo el usuario "completo"
			Usuario uGet = (Usuario) ois.readObject();
			// Devuelvo el usuario
			return uGet;
		} 
		catch (ClassNotFoundException e)
		{
			return null;
		}
		catch (IOException e)
		{
			return null;
		}
	}

	public void desconectar(Usuario u)
	{
		
		try
		{
			// Envío el código para el servidor
			dos.writeInt(DESCONECTAR);
			// Envío el usuario para que lo desconecte
			oos.writeObject(u);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
