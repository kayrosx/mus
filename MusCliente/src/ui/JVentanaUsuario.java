package ui;

import io.EscribirUsuarios;
import io.Informe;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import obj.Usuario;

import obj.*;

public class JVentanaUsuario extends JFrame
{
	Usuario u;
	JLabel lblNombre = new JLabel();
	JLabel lblImg;
	JButton btnIniciarPartida = new JButton("Iniciar Partida");
	JButton btnModificarImg = new JButton("Modificar Imagen");
	JButton btnModificarPass = new JButton("Cambiar Contraseña");
	JButton btnInforme = new JButton("Generar Informe");
	
	JVentanaUsuario(Usuario u)
	{
		this.u = u;
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
		panelInferior.add(btnInforme);
		
		this.add(panelSuperior);
		this.add(panelInferior);
		
		this.setTitle("Panel de control");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void events()
	{
		btnIniciarPartida.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new JVentanaTPartida(u);
				JVentanaUsuario.this.dispose();
			}
		});
		
		btnModificarImg.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new JVentanaImgs(u);
				JVentanaUsuario.this.dispose();
			}
		});
		
		btnModificarPass.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new JVentanaPass(u);
				JVentanaUsuario.this.dispose();
			}
		});
		
		btnInforme.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Informe.generaInforme();
				JOptionPane.showMessageDialog(JVentanaUsuario.this, "Informe generado correctamente", "Informe generado", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JVentanaUsuario.this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int opc = JOptionPane.showConfirmDialog(JVentanaUsuario.this, "Desea guardar los cambios?", "Guardar", JOptionPane.YES_NO_OPTION);
				if(opc == JOptionPane.YES_OPTION)
					EscribirUsuarios.guardaUsuarios();
			}
		});
	}
}