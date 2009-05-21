package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.ConectarServidor;

import obj.Usuario;


public class JVentanaTPartida extends JFrame
{
	Usuario u;
	JRadioButton rb1 = new JRadioButton("1 jugador");
	JRadioButton rb4 = new JRadioButton("4 jugadores");
	JLabel lblMensaje = new JLabel("Selecciona el numero de jugadores:");
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");
	
	private ConectarServidor hilo;
	
	public JVentanaTPartida(Usuario u)
	{
		this.u = u;
		init();
		events();
	}
	
	private void init()
	{
		this.setLayout(new BorderLayout());
		
		// Panel superior
		JPanel pnlSuperior = new JPanel(new FlowLayout());
		pnlSuperior.add(lblMensaje);
		this.add(pnlSuperior, BorderLayout.NORTH);
		
		// Panel central
		JPanel pnlCentral = new JPanel(new GridLayout(2, 1));
		pnlCentral.add(rb1);
		pnlCentral.add(rb4);
		this.add(pnlCentral, BorderLayout.CENTER);
		
		// Panel inferior
		JPanel pnlInferior = new JPanel(new FlowLayout());
		pnlInferior.add(btnAceptar);
		pnlInferior.add(btnCancelar);
		this.add(pnlInferior, BorderLayout.SOUTH);
		
		rb1.setSelected(true);
		rb4.setEnabled(false);
		
		this.setTitle("Tipo de partida");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void events()
	{
		btnCancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				new JVentanaUsuario(u, hilo);
				JVentanaTPartida.this.dispose();
			}
		});
		
		btnAceptar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new JVentanaPartida(u);
				JVentanaTPartida.this.dispose();
			}
		});
		
		rb1.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				if(!rb1.isSelected())
					rb1.setSelected(true);
				
				rb4.setSelected(false);
			}
		});
		/*
		rb4.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent arg0) 
			{
				if(!rb4.isSelected())
					rb4.setSelected(true);
				
				rb1.setSelected(false);
			}
		});*/
	}
}
