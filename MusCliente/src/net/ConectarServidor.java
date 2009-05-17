package net;

//import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import obj.Usuario;
import ui.JVentanaInicio;

public class ConectarServidor extends Thread
{
	// Socket
	private Socket sCliente;
	
	// Lectura
	private InputStream is;
	private DataInputStream dis;
	//private ObjectInputStream ois;
	//private BufferedReader br;
	
	// Escritura
	private OutputStream os;
	//private DataOutputStream dos;
	private ObjectOutputStream oos;
	//private PrintWriter pw;
	
	// Atributos
	private Usuario user;
	private JVentanaInicio ventana;
	
	public ConectarServidor(Usuario u, JVentanaInicio v)
	{
		user = u;
		ventana = v;
		try
		{
			sCliente = new Socket("127.0.0.1", 6060);
			os = sCliente.getOutputStream();
			is = sCliente.getInputStream();
			//ois = new ObjectInputStream(is);
			oos = new ObjectOutputStream(os);
			dis = new DataInputStream(is);
			//pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
			//br = new BufferedReader(new InputStreamReader(is));
		} 
		catch (UnknownHostException e)
		{
			System.out.println("Error");
		} 
		catch (IOException e)
		{
			System.out.println("Error");
		}
	}
	
	public void run()
	{
		try
		{
			oos.writeObject(user);
			int i = dis.readInt();
			System.out.println(i);
			ventana.setLogin(i);
		}
		catch (IOException e)
		{
			System.out.println("Error");
		}
	}
}
