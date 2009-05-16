package net;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import obj.Usuario;
import util.PassIncorrecto;
import util.UsuarioNoExiste;

public class ConectarServidor
{
	static Socket sCliente;
	static OutputStream os;
	static OutputStreamWriter osw;
	static BufferedWriter bw;
	static ObjectOutputStream oos;
	static InputStream is;
	static ObjectInputStream ois;
	static InputStreamReader isr;
	
	public static void conectar() throws IOException
	{
		sCliente = new Socket("127.0.0.1", 6060);
		os = sCliente.getOutputStream();
		is = sCliente.getInputStream();
		ois = new ObjectInputStream(is);
		oos = new ObjectOutputStream(os);
		osw = new OutputStreamWriter (os);
		isr = new InputStreamReader(is);
		bw = new BufferedWriter (osw);
	}
	
	public static void desconectar() throws IOException
	{
		sCliente.close();
	}
	
	public static void login(Usuario u) throws PassIncorrecto, UsuarioNoExiste
	{
		try
		{
			oos.writeObject(u);
			try
			{
				Object o = ois.readObject();
				
				if(o instanceof PassIncorrecto)
				{
					throw (PassIncorrecto) o;
				}
				else if(o instanceof UsuarioNoExiste)
				{
					throw (UsuarioNoExiste) o;
				}
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
