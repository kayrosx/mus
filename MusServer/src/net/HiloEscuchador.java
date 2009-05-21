package net;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import ui.JAppServidor;

public class HiloEscuchador extends Thread 
{
	JAppServidor ventanaServidor ;
	boolean fin = false;
	ArrayList<ConexionClientes> listaClientes = new ArrayList<ConexionClientes>();
	
	public HiloEscuchador(JAppServidor v)
	{
		ventanaServidor = v;
	}
	
	public void run()
	{
		ventanaServidor.setEstado("Escuchando...");
		try 
		{
			ServerSocket ss = new ServerSocket(6060);
			ventanaServidor.setEstado("Escuchando clientes...");

			Socket sServidor  = null;
			while(true)
			{
				sServidor = ss.accept();
				ConexionClientes gc = new ConexionClientes(sServidor, ventanaServidor);
				gc.start();
				listaClientes.add(gc);
			}
		}
		catch (IOException e1)
		{
			ventanaServidor.setError(e1.toString());
		}
	}
}
