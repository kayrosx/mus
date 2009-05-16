package mus.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;

import mus.obj.Usuario;
import mus.util.MapaJugadores;

public class Informe 
{
	
	/** Genera un informe html llamado informe.html */
	public static void generaInforme()
	{
		LinkedList<Usuario> lista = MapaJugadores.devolverLista();
		
		Iterator<Usuario> i = lista.iterator();
		
		try 
		{			
			FileWriter fw = new FileWriter("informe.html");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter pw = new PrintWriter(bw, true);
		
			pw.println("<h1>Informe de jugadores</h1>");
			while(i.hasNext())
			{
				pw.println("<br />###################");
				pw.println(format2HTML(i.next().toString()));
				pw.println("<br />###################");
			}
		
			pw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public static String format2HTML(String s)
	{
		s = s.replace("\n", "<br />");
		s = s.replace("Nombre", "<b>Nombre</b>");
		s = s.replace("Puntos", "<b>Puntos</b>");
		return s;
	}
}
