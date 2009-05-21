package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import obj.Usuario;

public class ConectarServidor extends Thread
{
	private Socket sCliente;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private final int LOGIN = 1;
	private final int CREA_USUARIO = 2;
	private final int MODIFICA_USUARIO = 3;
	private final int DEVUELVE_USUARIO = 4;
	
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
			System.out.println("No se encuentra el servidor");
		} 
		catch (IOException e)
		{
			System.out.println("No se puede conectar al servidor");
		}
	}
	
	public int login(Usuario u)
	{
		int login = -1;
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
			e.printStackTrace();
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
			System.out.println(u);
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
}
