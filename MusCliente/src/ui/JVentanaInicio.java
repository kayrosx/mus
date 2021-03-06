package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import obj.Usuario;

import net.ConectarServidor;


public final class JVentanaInicio extends JFrame
{
	private JLabel lblNombre = new JLabel("Nombre:     ");
	private JLabel lblPass = new JLabel("Contrase�a:     ");
	private JLabel lblTitulo = new JLabel("Crea un nuevo usuario o entra con el tuyo");
	private JTextField txtNombre = new JTextField(10);
	private JPasswordField txtPass = new JPasswordField(10);
	private JButton btnLogin = new JButton("Acceder");
	private ConectarServidor hilo;
	private Usuario u;
	
	public static final int NO_CONECTABLE = 1;
	public static final int UNKNOWN_HOST = 2;
	
	public static void main(String[] args)
	{
		new JVentanaInicio();
	}
	
	public JVentanaInicio()
	{
		init();
		eventos();
		conectar();
	}
	
	private void init()
	{
		this.setLayout(new BorderLayout());
		
		// Panel norte
		JPanel pnlNorte = new JPanel(new FlowLayout());
		pnlNorte.add(lblTitulo);
		this.add(pnlNorte, BorderLayout.NORTH);
		
		// Panel del centro		
		JPanel pnlCentro = new JPanel(new GridLayout(2,2));
		
		JPanel pnl1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnl1.add(lblNombre);
		pnlCentro.add(pnl1);
		
		JPanel pnlCentro1 = new JPanel(new FlowLayout());
		pnlCentro1.add(txtNombre);
		pnlCentro.add(pnlCentro1);
		
		JPanel pnl2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnl2.add(lblPass);
		pnlCentro.add(pnl2);
		
		JPanel pnlCentro2 = new JPanel(new FlowLayout());
		pnlCentro2.add(txtPass);
		pnlCentro.add(pnlCentro2);
		
		this.add(pnlCentro, BorderLayout.CENTER);
		
		// Panel sur
		JPanel pnlCentro3 = new JPanel(new FlowLayout());
		pnlCentro3.add(btnLogin);
		this.add(pnlCentro3, BorderLayout.SOUTH);
		
		this.setTitle("Mus 2009");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void eventos()
	{
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String pass = new String(txtPass.getPassword());
				
				if(!txtNombre.getText().isEmpty() && !pass.isEmpty() )
				{
					u = new Usuario(txtNombre.getText(), pass);
					
					int login = hilo.login(u);
					if(login == 0)
					{
						u = hilo.getUsuario(u);
						new JVentanaUsuario(u, hilo);
						JVentanaInicio.this.dispose();
					}
					else if(login == 1)
					{
						JOptionPane.showMessageDialog(JVentanaInicio.this, "La contrase�a no se corresponde con la del usuario.\nAsegurate de haber introducido los datos correctamente.", "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
						txtPass.setText("");
						txtPass.requestFocus();
					}
					else if(login == 2)
					{
						int opcion = JOptionPane.showConfirmDialog(JVentanaInicio.this, "El usuario " + txtNombre.getText() + " no existe. Quieres crearlo?", "Nuevo Usuario", JOptionPane.YES_NO_OPTION);
						
						if(opcion == JOptionPane.YES_OPTION)
						{
							if(hilo.creaJugador(u))
							{
								JOptionPane.showMessageDialog(JVentanaInicio.this, "Usuario creado: " + u);
								new JVentanaImgs(u, hilo);
								JVentanaInicio.this.dispose();
							}
						}
						else
						{
							txtPass.setText("");
							txtNombre.selectAll();
							txtNombre.requestFocus();
						}
					}
					else if(login == -1)
						mensajeError(1, true);
				}
				else
				{
					JOptionPane.showMessageDialog(JVentanaInicio.this, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		txtNombre.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					txtPass.requestFocus();
			}
		});
		
		txtPass.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnLogin.doClick();
			}
		});
	}
	
	public void conectar()
	{
		hilo = new ConectarServidor(this);
		hilo.start();
	}
	
	public void mensajeError(int mensaje, boolean cerrarAplicacion)
	{
		String s = "";
		
		if(mensaje == NO_CONECTABLE)
			s = "No se ha podido conectar con el servidor.\nInt�ntalo m�s tarde.";
		else if(mensaje == UNKNOWN_HOST)
			s = "No se encuentra el servidor.\nInt�ntalo de nuevo m�s tarde.";
		
		JOptionPane.showMessageDialog(JVentanaInicio.this, s, "Error de conexi�n", JOptionPane.ERROR_MESSAGE);
		if(cerrarAplicacion)
			this.dispose();
	}
}