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
	private DataInputStream dis;
	private ObjectInputStream ois;
	private DataOutputStream dos;
	private ObjectOutputStream oos;
	private JAppServidor ventanaServidor;
	
	private HiloEscuchador hPadre;
	
	public ConexionClientes(Socket s, JAppServidor v, HiloEscuchador hPadre)
	{
		this.hPadre = hPadre;
	    socketServidor = s;
        ventanaServidor = v;
	}
	
	public void run()
	{
		try
		{
			dis = new DataInputStream(socketServidor.getInputStream());
			ois = new ObjectInputStream(socketServidor.getInputStream());
			dos = new DataOutputStream(socketServidor.getOutputStream());
			oos = new ObjectOutputStream(socketServidor.getOutputStream());
			
			ventanaServidor.addMensaje("Conectado" + socketServidor.getInetAddress().toString().replace("/", ""));
			
			while(true)
			{
				int opc = dis.readInt();
				switch(opc)
				{
				case 1:		// Login
					try 
					{
						int login = -1;
						// Recibo el usuario del que hacer el login
						Usuario u = (Usuario)ois.readObject();
						try 
						{
							MapaJugadores.validate(u);
							login = 0;
						} 
						catch (PassIncorrecto e)
						{
							login = 1;
						}
						catch (UsuarioNoExiste e)
						{
							login = 2;
						}
						dos.writeInt(login);
						u=null;
					} 
					catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
					}
					
					break;
				case 2:		// Crea usuario
					try 
					{
						// Recibo el usuario a crear
						Usuario newUser = (Usuario) ois.readObject();
						// Añade al mapa el nuevo usuario
						boolean creado = false;
						if(MapaJugadores.add(newUser))
						{
							creado = true;
							ventanaServidor.addMensaje("Creado el usuario " + newUser.getNombre());
						}
						
						// Envío si se ha creado
						dos.writeBoolean(creado);
						
						newUser = null;
					} 
					catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}
					break;
				case 3:		// Modifica usuario
					// Recibo el usuario a modificar
					Usuario uMod;
					try 
					{
						// Recibo el código de qué quiero cambiar
						int opc1 = dis.readInt();
						
						// Recibo el usuario sobre el que cambiarla
						uMod = (Usuario)ois.readObject();
						
						switch(opc1)
						{
						case 1: // Imagen
							// Recibo la imagen a cambiar
							String img = (String)ois.readObject();
							uMod.setImg(img);
							break;
						case 2: // Contraseña
							String pass = (String)ois.readObject();
							uMod.setPass(pass);
							break;
						case 3: // Puntos
							int puntos = dis.readInt();
							uMod.setPuntos(puntos);
							break;
						}
						
						boolean mod = false;
						mod = MapaJugadores.modificaUsuario(uMod);
						dos.writeBoolean(mod);
					} 
					catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
					}
					uMod = null;
					break;
				case 4:		// Devuelve usuario
					try 
					{
						// Recibo el usuario a devolver
						Usuario uDevolver = (Usuario)ois.readObject();
						// Devuelvo el usuario "completo"
						uDevolver = MapaJugadores.getUsuario(uDevolver);
						oos.writeObject(uDevolver);
						uDevolver = null;
					} 
					catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
					}
					break;
				case -1:	// El cliente se desconecta
					break;
				}
			}
		} 
        catch (IOException e)
		{
        	// Salta aquí cuando se desconecta el cliente
        	hPadre.clienteDesconectado(socketServidor);
			System.out.println("Cliente desconectado");
		}
	}
}
