package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import obj.Usuario;
import util.MapaJugadores;


public class JVentanaImgs extends JFrame
{
	Usuario u;
	String imgSeleccionada = "";
	JLabel lblImg1 = new JLabel(new ImageIcon("1.jpg"));
	JLabel lblImg2 = new JLabel(new ImageIcon("2.jpg"));
	JLabel lblImg3 = new JLabel(new ImageIcon("3.jpg"));
	JLabel lblImg4 = new JLabel(new ImageIcon("4.jpg"));
	JLabel lblImg5 = new JLabel(new ImageIcon("5.jpg"));
	JLabel lblImg6 = new JLabel(new ImageIcon("6.jpg"));
	JLabel lblImg7 = new JLabel(new ImageIcon("7.jpg"));
	JLabel lblImg8 = new JLabel(new ImageIcon("8.jpg"));
	JLabel lblImg9 = new JLabel(new ImageIcon("9.jpg"));
	JLabel lblSeleccionar = new JLabel("Imagen seleccionada: ");
	JLabel lblSeleccion = new JLabel();
	JButton btnSeleccionar = new JButton("Seleccionar");
	JButton btnCancelar = new JButton("Cancelar");
	
	JPanel pnlSuperior = new JPanel(new GridLayout(2,1));
	JPanel pnlSuperior1 = new JPanel(new FlowLayout());
	JPanel pnlSuperior2 = new JPanel(new FlowLayout());
	JPanel pnlInferior = new JPanel(new FlowLayout());
	
	public JVentanaImgs(Usuario u)
	{
		this.u = u;
		init();
		events();
	}
	
	private void init()
	{
		this.setLayout(new BorderLayout());
		
		// Inicializa los paneles
		pnlSuperior.removeAll();
		pnlSuperior1.removeAll();
		pnlSuperior2.removeAll();
		pnlInferior.removeAll();
		
		// Panel superior
		pnlSuperior1.add(lblImg1);
		pnlSuperior1.add(lblImg2);
		pnlSuperior1.add(lblImg3);
		pnlSuperior1.add(lblImg4);
		pnlSuperior1.add(lblImg5);
		pnlSuperior1.add(lblImg6);
		pnlSuperior1.add(lblImg7);
		pnlSuperior1.add(lblImg8);
		pnlSuperior1.add(lblImg9);
		pnlSuperior.add(pnlSuperior1);
		
		pnlSuperior2.add(lblSeleccionar);
		if(!imgSeleccionada.isEmpty())
		{
			lblSeleccion = new JLabel(new ImageIcon(imgSeleccionada));
		}
		else if (!u.getImg().isEmpty())
		{
			lblSeleccion = new JLabel(new ImageIcon(u.getImg()));
		}
		else
		{
			imgSeleccionada = "1.jpg";
			lblSeleccion = new JLabel(new ImageIcon("1.jpg"));	
			u.setImg(imgSeleccionada);
		}
		pnlSuperior2.add(lblSeleccion);
		pnlSuperior.add(pnlSuperior2);
		
		this.add(pnlSuperior, BorderLayout.CENTER);
		
		// Panel inferior
		pnlInferior.add(btnSeleccionar);
		pnlInferior.add(btnCancelar);
		this.add(pnlInferior, BorderLayout.SOUTH);
		
		this.setTitle("Escoge imagen");
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void events()
	{
		btnSeleccionar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				u.setImg(imgSeleccionada);
				new JVentanaUsuario(u);
				JVentanaImgs.this.dispose();

			}
		});
		
		btnCancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!imgSeleccionada.isEmpty() || !u.getImg().isEmpty())
				{
					new JVentanaUsuario(u);
					JVentanaImgs.this.dispose();	
				}
			}
		});
		
		lblImg1.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "1.jpg";
				lblSeleccion.setIcon(new ImageIcon("1.jpg"));
			}
		});
		
		lblImg2.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "2.jpg";
				lblSeleccion.setIcon(new ImageIcon("2.jpg"));
			}
		});
		
		lblImg3.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "3.jpg";
				lblSeleccion.setIcon(new ImageIcon("3.jpg"));
			}
		});
		
		lblImg4.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "4.jpg";
				lblSeleccion.setIcon(new ImageIcon("4.jpg"));
			}
		});
		
		lblImg5.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "5.jpg";
				lblSeleccion.setIcon(new ImageIcon("5.jpg"));
			}
		});
		
		lblImg6.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "6.jpg";
				lblSeleccion.setIcon(new ImageIcon("6.jpg"));
			}
		});
		
		lblImg7.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "7.jpg";
				lblSeleccion.setIcon(new ImageIcon("7.jpg"));
			}
		});
		
		lblImg8.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "8.jpg";
				lblSeleccion.setIcon(new ImageIcon("8.jpg"));
			}
		});
		
		lblImg9.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "9.jpg";
				lblSeleccion.setIcon(new ImageIcon("9.jpg"));
			}
		});
		
		JVentanaImgs.this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if(!imgSeleccionada.isEmpty())
				{
					MapaJugadores.getUsuario(u);
					u.setImg(imgSeleccionada);
					MapaJugadores.modificaUsuario(u);
				}
			}
		});
	}
}