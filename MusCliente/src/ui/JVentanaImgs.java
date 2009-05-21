package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.ConectarServidor;

import obj.Usuario;


public class JVentanaImgs extends JFrame
{
	private Usuario u;
	private String imgSeleccionada = "";
	private JLabel lblImg1 = new JLabel(new ImageIcon("imgs/1.jpg"));
	private JLabel lblImg2 = new JLabel(new ImageIcon("imgs/2.jpg"));
	private JLabel lblImg3 = new JLabel(new ImageIcon("imgs/3.jpg"));
	private JLabel lblImg4 = new JLabel(new ImageIcon("imgs/4.jpg"));
	private JLabel lblImg5 = new JLabel(new ImageIcon("imgs/5.jpg"));
	private JLabel lblImg6 = new JLabel(new ImageIcon("imgs/6.jpg"));
	private JLabel lblImg7 = new JLabel(new ImageIcon("imgs/7.jpg"));
	private JLabel lblImg8 = new JLabel(new ImageIcon("imgs/8.jpg"));
	private JLabel lblImg9 = new JLabel(new ImageIcon("imgs/9.jpg"));
	private JLabel lblSeleccionar = new JLabel("Imagen seleccionada: ");
	private JLabel lblSeleccion = new JLabel();
	private JButton btnSeleccionar = new JButton("Seleccionar");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private JPanel pnlSuperior = new JPanel(new GridLayout(2,1));
	private JPanel pnlSuperior1 = new JPanel(new FlowLayout());
	private JPanel pnlSuperior2 = new JPanel(new FlowLayout());
	private JPanel pnlInferior = new JPanel(new FlowLayout());
	
	private ConectarServidor hilo;
	
	public JVentanaImgs(Usuario u, ConectarServidor h)
	{
		this.hilo = h;
		this.u = hilo.getUsuario(u);
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
		if (!u.getImg().isEmpty())
		{
			lblSeleccion = new JLabel(new ImageIcon(u.getImg()));
		}
		else
		{
			imgSeleccionada = "imgs/1.jpg";
			u.setImg(imgSeleccionada);
			lblSeleccion = new JLabel(new ImageIcon(imgSeleccionada));	
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
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void events()
	{
		btnSeleccionar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// MANDAR EL USUARIO MODIFICADO
				Usuario user = hilo.getUsuario(u);
				user.setImg(imgSeleccionada);
				if(hilo.modificaUsuario(user))
				{
					new JVentanaUsuario(user, hilo);
					JVentanaImgs.this.dispose();
				}
				else
				{
					System.out.println("No se ha podido modificar");
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!imgSeleccionada.isEmpty() || !u.getImg().isEmpty())
				{
					new JVentanaUsuario(u, hilo);
					JVentanaImgs.this.dispose();	
				}
			}
		});
		
		lblImg1.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "imgs/1.jpg";
				lblSeleccion.setIcon(new ImageIcon(imgSeleccionada));
			}
		});
		
		lblImg2.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "imgs/2.jpg";
				lblSeleccion.setIcon(new ImageIcon(imgSeleccionada));
			}
		});
		
		lblImg3.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "imgs/3.jpg";
				lblSeleccion.setIcon(new ImageIcon(imgSeleccionada));
			}
		});
		
		lblImg4.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "imgs/4.jpg";
				lblSeleccion.setIcon(new ImageIcon(imgSeleccionada));
			}
		});
		
		lblImg5.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "imgs/5.jpg";
				lblSeleccion.setIcon(new ImageIcon(imgSeleccionada));
			}
		});
		
		lblImg6.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "imgs/6.jpg";
				lblSeleccion.setIcon(new ImageIcon(imgSeleccionada));
			}
		});
		
		lblImg7.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "imgs/7.jpg";
				lblSeleccion.setIcon(new ImageIcon(imgSeleccionada));
			}
		});
		
		lblImg8.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "imgs/8.jpg";
				lblSeleccion.setIcon(new ImageIcon(imgSeleccionada));
			}
		});
		
		lblImg9.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				imgSeleccionada = "imgs/9.jpg";
				lblSeleccion.setIcon(new ImageIcon(imgSeleccionada));
			}
		});
	}
}