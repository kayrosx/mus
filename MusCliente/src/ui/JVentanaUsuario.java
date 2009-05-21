package ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.ConectarServidor;

import obj.Usuario;

public class JVentanaUsuario extends JFrame
{
	private Usuario u;
	private JLabel lblNombre = new JLabel();
	private JLabel lblImg;
	private JButton btnIniciarPartida = new JButton("Iniciar Partida");
	private JButton btnModificarImg = new JButton("Modificar Imagen");
	private JButton btnModificarPass = new JButton("Cambiar Contraseña");
	
	ConectarServidor hilo;
	
	JVentanaUsuario(Usuario user, ConectarServidor hilo)
	{
		this.hilo = hilo;
		this.u = hilo.getUsuario(user);
		init();
		events();
	}
	
	private void init()
	{
		this.setLayout(new GridLayout(2,1));
		
		// Panel Superior
		JPanel panelSuperior = new JPanel(new FlowLayout());
		lblNombre.setText(u.getNombre());
		lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
		panelSuperior.add(lblNombre);
		if(!u.getImg().isEmpty())
		{
			lblImg = new JLabel(new ImageIcon(u.getImg()));
			panelSuperior.add(lblImg);
		}
		
		// Panel Inferior
		JPanel panelInferior = new JPanel(new FlowLayout());
		panelInferior.add(btnModificarImg);
		panelInferior.add(btnModificarPass);
		panelInferior.add(btnIniciarPartida);
		
		this.add(panelSuperior);
		this.add(panelInferior);
		
		this.setTitle("Panel de control");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void events()
	{
		btnIniciarPartida.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new JVentanaTPartida(u, hilo);
				JVentanaUsuario.this.dispose();
			}
		});
		
		btnModificarImg.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new JVentanaImgs(u, hilo);
				JVentanaUsuario.this.dispose();
			}
		});
		
		btnModificarPass.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new JVentanaPass(u, hilo);
				JVentanaUsuario.this.dispose();
			}
		});
	}
}