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

import net.ConectarServidor;

import obj.Usuario;


public final class JVentanaInicio extends JFrame
{
	private JLabel lblNombre = new JLabel("Nombre:     ");
	private JLabel lblPass = new JLabel("Contraseña:     ");
	private JLabel lblTitulo = new JLabel("Crea un nuevo usuario o entra con el tuyo");
	private JTextField txtNombre = new JTextField(10);
	private JPasswordField txtPass = new JPasswordField(10);
	private JButton btnLogin = new JButton("Acceder");
	private ConectarServidor comunicacion;
	private Usuario u;
	private int codop = -1;
	
	public static void main(String[] args)
	{
		new JVentanaInicio();
	}
	
	public JVentanaInicio()
	{
		init();
		eventos();
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					comunicacion = new ConectarServidor(u, JVentanaInicio.this);
					comunicacion.start();
					while(codop == -1)
					{}
					System.out.println(codop);
					if(codop == 0)
					{
						new JVentanaUsuario(u);
						JVentanaInicio.this.dispose();
					}
					else if(codop == 1)
					{
						JOptionPane.showMessageDialog(JVentanaInicio.this, "La contraseña no se corresponde con la del usuario.\nAsegurate de haber introducido los datos correctamente.", "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
						txtPass.setText("");
						txtPass.requestFocus();
					}
					else if(codop == 2)
					{
						int opcion = JOptionPane.showConfirmDialog(JVentanaInicio.this, "El usuario " + txtNombre.getText() + " no existe. Quieres crearlo?", "Nuevo Usuario", JOptionPane.YES_NO_OPTION);
						
						if(opcion == JOptionPane.YES_OPTION)
						{
							if(true/*MapaJugadores.add(u)*/)
							{
								JOptionPane.showMessageDialog(JVentanaInicio.this, "Usuario creado: " + u);
								new JVentanaImgs(u);
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
	
	public void setLogin(int i)
	{
		codop = i;
	}
}