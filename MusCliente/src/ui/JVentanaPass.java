package ui;

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

import net.ConectarServidor;

import obj.Usuario;


public class JVentanaPass extends JFrame
{
	private Usuario u;
	private JLabel lblPassA = new JLabel("Contraseña antigua:");
	private JLabel lblPassN = new JLabel("Contraseña nueva:");
	private JLabel lblPassNC = new JLabel("Confirma la contraseña:");
	private JPasswordField txtPassA = new JPasswordField(10);
	private JPasswordField txtPassN = new JPasswordField(10);
	private JPasswordField txtPassNC = new JPasswordField(10);
	private JButton btnAceptar = new JButton("Aceptar");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private ConectarServidor hilo;
	
	public JVentanaPass(Usuario u, ConectarServidor h)
	{
		this.hilo = h;
		this.u = u;
		init();
		events();
	}
	
	private void init()
	{
		this.setLayout(new GridLayout(4,1));
		
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.add(lblPassA);
		panel1.add(txtPassA);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		panel2.add(lblPassN);
		panel2.add(txtPassN);
		
		JPanel panel3 = new JPanel(new FlowLayout());
		panel3.add(lblPassNC);
		panel3.add(txtPassNC);
		
		JPanel panel4 = new JPanel(new FlowLayout());
		panel4.add(btnAceptar);
		panel4.add(btnCancelar);
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		
		this.setTitle("Cambiar contraseña");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void events()
	{
		btnAceptar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String passA = new String(txtPassA.getPassword());
				String passN = new String(txtPassN.getPassword());
				String passNC = new String(txtPassNC.getPassword());
				
				if(!passA.isEmpty() && !passN.isEmpty() && !passNC.isEmpty())
				{
					if(passA.equals(u.getPass()))
					{
						if(passN.equals(passNC))
						{
							// MANDAR EL USUARIO MODIFICADO
							hilo.getUsuario(u);
							u.setPass(passNC);
							hilo.modificaUsuario(u);
							JOptionPane.showMessageDialog(JVentanaPass.this, "La contraseña se ha cambiado satisfactoriamente.", "Contraseña Cambiada", JOptionPane.INFORMATION_MESSAGE);
							new JVentanaUsuario(u, hilo);
							JVentanaPass.this.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(JVentanaPass.this, "La contraseña nueva no coincide.\nPor favor, comprueba que esta bien introducida.", "Error", JOptionPane.ERROR_MESSAGE);
							txtPassN.setText("");
							txtPassNC.setText("");
							txtPassN.requestFocus();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(JVentanaPass.this, "La contraseña introducida no se corresponde con la de este usuario.", "Error", JOptionPane.ERROR_MESSAGE);
						txtPassA.selectAll();
						txtPassA.requestFocus();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(JVentanaPass.this, "Rellena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
					if(passA.isEmpty())
						txtPassA.requestFocus();
					else if(passN.isEmpty())
						txtPassN.requestFocus();
					else
						txtPassNC.requestFocus();
				}
				
			}
		});
		
		btnCancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new JVentanaUsuario(u, hilo);
				JVentanaPass.this.dispose();
			}
		});
		
		txtPassA.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					txtPassN.requestFocus();
			}
		});
		
		txtPassN.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					txtPassNC.requestFocus();
			}
		});
		
		txtPassNC.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnAceptar.doClick();
			}
		});
	}
}