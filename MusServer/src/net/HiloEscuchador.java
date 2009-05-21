package net;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import ui.JAppServidor;

public class HiloEscuchador extends Thread 
{
	private JAppServidor ventanaServidor ;
	private ArrayList<ConexionClientes> listaClientes = new ArrayList<ConexionClientes>();
	private int conectados;
	
	public HiloEscuchador(JAppServidor v)
	{
		ventanaServidor = v;
	}
	
	public void run()
	{
		conectados = 0;
		ventanaServidor.setEstado("Escuchando...");
		try 
		{
			ServerSocket ss = new ServerSocket(6060);
			ventanaServidor.setEstado("Escuchando clientes...");

			Socket sServidor  = null;
			while(true)
			{
				sServidor = ss.accept();
				// Se establece que el número máximo de clientes conectados a la vez es de 4
				if(conectados < 4)
				{
					conectados++;
					ConexionClientes gc = new ConexionClientes(sServidor, ventanaServidor, this);
					gc.start();
					listaClientes.add(gc);
				}
				else
				{
					ventanaServidor.addMensaje("Se ha conectado un nuevo cliente " + 
							sServidor.getInetAddress().toString().replace("/", "") + " pero se ha llegado al máximo de conexiones permitidas");
					sServidor.close();
				}
			}
		}
		catch (IOException e1)
		{
			ventanaServidor.setError(e1.toString());
			ventanaServidor.dispose();
		}
	}
	
	public void clienteDesconectado(Socket s)
	{
		// Quitamos el cliente de la lista de clientes conectados
		listaClientes.remove(s);
		// Restamos 1 al número de clientes conectados
		conectados--;
	}
}
