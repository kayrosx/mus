package ui;

import io.EscribirUsuarios;
import io.Informe;
import io.LeerUsuarios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import net.HiloEscuchador;

public class JAppServidor extends JFrame implements Runnable
{
	private JLabel lblEstado = new JLabel("No escuchando...");
	private JButton btnConectar = new JButton("Escuchar Clientes");
	private JButton btnInforme = new JButton("Generar Informe");
	private JTextArea txtConversacion = new JTextArea(80, 30);
	private HiloEscuchador servidorListener; 
	private JScrollPane scroll = new JScrollPane(txtConversacion);
	
	private Thread hiloReloj;
	
	private boolean fin = false;
	
	public static void main(String[] args) 
	{
		new JAppServidor();
	}
	
	public JAppServidor()
	{
		init();
		events();
		LeerUsuarios.cargaUsuarios();
	}
	
	private void init()
	{
		hiloReloj = new Thread(this);
		
		this.setLayout(new BorderLayout());
		this.add(lblEstado, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		txtConversacion.scrollRectToVisible(getBounds());
		
		// Panel sur
		JPanel pnlSur = new JPanel(new FlowLayout());
		pnlSur.add(btnConectar);
		pnlSur.add(btnInforme);
		
		this.add(pnlSur, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Servidor");
		this.setSize(300, 185);
		this.setVisible(true);
	}
	
	private void events()
	{
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
				int opc = JOptionPane.showConfirmDialog(JAppServidor.this, "Desea guardar los cambios?", "Guardar", JOptionPane.YES_NO_OPTION);
				if(opc == JOptionPane.YES_OPTION)
					EscribirUsuarios.guardaUsuarios();
				fin = true;
				JAppServidor.this.dispose();
				System.exit(0);
			}
		});
		
		btnInforme.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Informe.generaInforme();
				JOptionPane.showMessageDialog(JAppServidor.this, "Informe generado correctamente", "Informe generado", JOptionPane.INFORMATION_MESSAGE);
				Runtime rt = Runtime.getRuntime();
				try
				{
					rt.exec("cmd /c start informe.html");
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void setEstado(String s)
	{
		lblEstado.setText(s);
	}
	
	public void addMensaje(String s)
	{
		s = s + "\n";
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
