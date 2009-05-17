package ui;

import io.LeerUsuarios;

import java.awt.Container;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import net.HiloEscuchador;

public class JAppServidor extends JFrame implements Runnable
{
	private JLabel lblEstado ;
	private JButton btnConectar;
	private JTextArea txtConversacion;
	private HiloEscuchador servidorListener; 
	
	private Thread hiloReloj;
	
	private boolean fin = false;
	
	public static void main(String[] args) 
	{
		new JAppServidor();
	}
	
	public JAppServidor()
	{
		carga();
		hiloReloj = new Thread(this);
		
		Container c = this.getContentPane();
		
		c.setLayout(new BorderLayout());
		
		lblEstado = new JLabel("No escuchando...");
		btnConectar = new JButton("Escuchar Clientes");
		txtConversacion = new JTextArea(80, 30);

		btnConectar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
			    servidorListener = new HiloEscuchador(JAppServidor.this);
			    servidorListener.start();
				hiloReloj.start();
				btnConectar.setEnabled(false);
			}
		});
		
		this.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					fin = true;
					JAppServidor.this.dispose();
					System.exit(0);
				}
			});
		
		c.add(lblEstado, BorderLayout.NORTH);
		c.add(new JScrollPane(txtConversacion), BorderLayout.CENTER);
		c.add(btnConectar, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Servidor");
		this.setSize(200, 185);
		this.setVisible(true);
	}
	
	private void carga()
	{
		LeerUsuarios.cargaUsuarios();
	}
	
	public void setEstado(String s)
	{
		lblEstado.setText(s);
	}
	
	public void addMensaje(String s)
	{
		txtConversacion.append(s);
	}
	
	public void setError(String s)
	{
		JOptionPane.showMessageDialog(this, s, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void run() 
	{
		while(!fin)
		{
			try 
			{
				this.setTitle(new java.util.Date().toString());
				
				Thread.sleep(1000);				
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}	
}
