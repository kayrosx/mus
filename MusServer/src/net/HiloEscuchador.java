package net;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ui.JAppServidor;

public class HiloEscuchador extends Thread 
{
	JAppServidor ventanaServidor ;
	boolean fin = false;
	Collection listaClientes = new ArrayList();
	
	public HiloEscuchador(JAppServidor j)
	{
		ventanaServidor = j;
	}
	
	public void sendAll(String texto)
	{
		Iterator it = listaClientes.iterator();
		while(it.hasNext())
			((ConexionClientes)it.next()).send(texto);
	}
	
	public void run()
	{

		ventanaServidor .setEstado("Escuchando...");
		try 
		{
			ServerSocket ss = new ServerSocket(6060);
			ventanaServidor.setEstado("Escuchando clientes...");

			Socket sServidor  = null;
			while(true)
			{
				sServidor  = ss.accept();
				ConexionClientes gc = new ConexionClientes(sServidor, ventanaServidor);
				gc.start();
				listaClientes.add(gc);
			}
			
			//ss.close();
		}
		catch (IOException e1) 
		{
			ventanaServidor.setError(e1.toString());
		}	
	}
}
