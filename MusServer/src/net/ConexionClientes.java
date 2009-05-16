package net;

import java.net.*;
import java.io.*;

import ui.JAppServidor;

public class ConexionClientes extends Thread
{
	private Socket socketServidor;
	PrintWriter pw;
	private JAppServidor ventanaServidor;
	
	public ConexionClientes(Socket s, JAppServidor v)
	{
	    socketServidor = s;
        ventanaServidor = v;
        
        try 
        {
            pw = new PrintWriter(
                    new OutputStreamWriter(socketServidor.getOutputStream()), true);
        }
        catch (IOException e) 
        {
            this.setError(e.toString());
        } 
	}
	
    public void send(String texto)
	{
			pw.println(texto);
	}
	
	public void run()
	{
		try
		{
			ventanaServidor.setEstado("Cliente aceptado");
			
			BufferedReader br = new BufferedReader(
				new InputStreamReader(socketServidor.getInputStream()));
	
			String s = "";
			String nombre = br.readLine();
			while(!s.equalsIgnoreCase("FIN"))
			{
				s = br.readLine();
				ventanaServidor.addMensaje(nombre.toUpperCase() + " dice: " + s + "\n");		        
			}
			ventanaServidor.addMensaje(nombre.toUpperCase() + " desconectado\n");
			
			br.close();
			socketServidor.close();
		}
		catch (IOException e) 
		{
		    this.setError(e.toString());
		}	
	}
	
    private void setError(String error)
    {
        ventanaServidor.setError(error);
        
    }	

}
