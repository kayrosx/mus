package ui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.ConectarServidor;

import obj.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class JVentanaPartida extends JFrame
{
	private Usuario u;
	private JTextArea txtAChat = new JTextArea(2, 10);
	private JTextField txtChat = new JTextField(15);
	
	private JLabel lblPuntos1 = new JLabel("Puntos: ");
	private JLabel lblPuntos2 = new JLabel("Puntos: ");
	
	private JButton btnChat = new JButton("Enviar");
	private JButton btnMus = new JButton("Mus");
	private JButton btnNoMus = new JButton("No Mus");
	private JButton btnPasar = new JButton("Pasar");
	private JButton btnEnvidar = new JButton("Envidar 2");
	private JButton btnOrdago = new JButton("Ordago");
	private JButton btnVer = new JButton("Veo");
	private JButton btnNoQuiero = new JButton("No Quiero");
	private JButton btnDescartar = new JButton("Descartar");
	private JButton btnSubir = new JButton("Subir 2");
	
	private JLabel carta1Jugador1 = new JLabel();
	private JLabel carta2Jugador1 = new JLabel();
	private JLabel carta3Jugador1 = new JLabel();
	private JLabel carta4Jugador1 = new JLabel();
	private JLabel carta1Jugador2 = new JLabel();
	private JLabel carta2Jugador2 = new JLabel();
	private JLabel carta3Jugador2 = new JLabel();
	private JLabel carta4Jugador2 = new JLabel();
	private JLabel carta1Jugador3 = new JLabel();
	private JLabel carta2Jugador3 = new JLabel();
	private JLabel carta3Jugador3 = new JLabel();
	private JLabel carta4Jugador3 = new JLabel();
	private JLabel carta1Jugador4 = new JLabel();
	private JLabel carta2Jugador4 = new JLabel();
	private JLabel carta3Jugador4 = new JLabel();
	private JLabel carta4Jugador4 = new JLabel();
	
	private JPanel pnlSuperior = new JPanel(new GridLayout(2,1));
	private JPanel pnlChat = new JPanel(new BorderLayout());
	private JPanel pnlCentral = new JPanel(new BorderLayout());
	private JPanel pnlInferior = new JPanel(new FlowLayout());
	private JPanel pnlMus = new JPanel(new FlowLayout());
	private JPanel pnlEnvidar = new JPanel(new FlowLayout());
	private JPanel pnlSubir = new JPanel(new FlowLayout());
	private JPanel pnlDescartar = new JPanel(new FlowLayout());
	private JPanel pnlUsuarioCartas = new JPanel(new FlowLayout());
	private Box pnlUsuarioDerCartas = new Box(BoxLayout.Y_AXIS);
	private Box pnlUsuarioIzqCartas = new Box(BoxLayout.Y_AXIS);
	private JPanel pnlUsuarioSupCartas = new JPanel(new FlowLayout());
	
	private ConectarServidor hilo;
	
	public JVentanaPartida(Usuario u, ConectarServidor h)
	{
		this.hilo = h;
		this.u = u;
		init();
		events();
	}
	
	public void init()
	{
		this.setLayout(new BorderLayout());
		
		// Panel superior: Puntos
		pnlSuperior.add(lblPuntos1);
		pnlSuperior.add(lblPuntos2);
		pnlSuperior.setBackground(Color.YELLOW);
		this.add(pnlSuperior, BorderLayout.NORTH);
		
		// Panel inferior: Mus / No Mus
		pnlMus.add(btnMus);
		pnlMus.add(btnNoMus);
		pnlMus.setBackground(Color.YELLOW);
		
		// Panel inferior: Envidar
		pnlEnvidar.add(btnPasar);
		pnlEnvidar.add(btnEnvidar);
		pnlEnvidar.add(btnOrdago);
		pnlEnvidar.setBackground(Color.YELLOW);
		
		// Panel inferior: Subir
		pnlSubir.add(btnNoQuiero);
		pnlSubir.add(btnVer);
		pnlSubir.add(btnSubir);
		//pnlSubir.add(btnOrdago);
		pnlSubir.setBackground(Color.YELLOW);
		
		// Panel inferior: Descartar
		pnlDescartar.add(btnDescartar);
		pnlDescartar.setBackground(Color.YELLOW);
		
		this.add(pnlMus, BorderLayout.SOUTH);
		
		// Panel derecho: Chat
		txtAChat.setBackground(Color.WHITE);
		txtAChat.setEditable(false);
		txtAChat.setAutoscrolls(true);
		
		pnlChat.add(new JLabel("Chat"), BorderLayout.NORTH);
		pnlChat.setBackground(Color.LIGHT_GRAY);
		pnlChat.add(txtAChat, BorderLayout.CENTER);
		JPanel pnlChatSur = new JPanel(new FlowLayout());
		pnlChatSur.setBackground(Color.GRAY);
		pnlChatSur.add(txtChat);
		pnlChatSur.add(btnChat);
		pnlChat.add(pnlChatSur, BorderLayout.SOUTH);
		this.add(pnlChat, BorderLayout.EAST);
		
		// Panel central: Cartas
		initCartas();
		// Usuario propio - Inferior
		pnlUsuarioCartas.add(carta1Jugador1);
		pnlUsuarioCartas.add(carta2Jugador1);
		pnlUsuarioCartas.add(carta3Jugador1);
		pnlUsuarioCartas.add(carta4Jugador1);
		pnlCentral.add(pnlUsuarioCartas, BorderLayout.SOUTH);
		
		// Compañero - Superior
		pnlUsuarioSupCartas.add(carta1Jugador3);
		pnlUsuarioSupCartas.add(carta2Jugador3);
		pnlUsuarioSupCartas.add(carta3Jugador3);
		pnlUsuarioSupCartas.add(carta4Jugador3);
		pnlCentral.add(pnlUsuarioSupCartas, BorderLayout.NORTH);
		
		// Contrincante 1 - Derecha
		pnlUsuarioDerCartas.add(carta1Jugador2);
		pnlUsuarioDerCartas.add(carta2Jugador2);
		pnlUsuarioDerCartas.add(carta3Jugador2);
		pnlUsuarioDerCartas.add(carta4Jugador2);
		pnlCentral.add(pnlUsuarioDerCartas, BorderLayout.EAST);
		
		// Contrincante 2 - Izqda
		pnlUsuarioIzqCartas.add(carta1Jugador4);
		pnlUsuarioIzqCartas.add(carta2Jugador4);
		pnlUsuarioIzqCartas.add(carta3Jugador4);
		pnlUsuarioIzqCartas.add(carta4Jugador4);
		pnlCentral.add(pnlUsuarioIzqCartas, BorderLayout.WEST);
		
		pnlCentral.setBackground(Color.GREEN);
		pnlUsuarioCartas.setBackground(Color.GREEN);
		pnlUsuarioSupCartas.setBackground(Color.GREEN);
		pnlUsuarioDerCartas.setBackground(Color.GREEN);
		pnlUsuarioIzqCartas.setBackground(Color.GREEN);
		
		this.add(pnlCentral, BorderLayout.CENTER);
		
		//this.setIconImage();
		this.setTitle("Mus");
		this.setSize(870, 670);
		//this.setLocation(180, 40);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void events()
	{
		btnChat.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				enviarMensajeChat();
			}
		});
		
		txtChat.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
					enviarMensajeChat();
			}
		});
		
		btnNoMus.addActionListener(new ActionListener()
		{
			
			// CARDLAYOUT pnlinferior.add(pnl, "id")
			// cl.show(pnlinferior, "id");
			public void actionPerformed(ActionEvent e)
			{
				JVentanaPartida.this.add(pnlEnvidar, BorderLayout.SOUTH);
				JVentanaPartida.this.remove(pnlMus);
				JVentanaPartida.this.validate();
			}
		});
		
		JVentanaPartida.this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				// Modifico los valores del usuario que hagan falta (los puntos)
				
				// Mandar un mensaje al servidor diciendo que se desconecta
				// y enviar el usuario
				hilo.desconectar(u);
			}
		});
	}
	
	private void enviarMensajeChat()
	{
		if(!txtChat.getText().isEmpty())
		{
			txtAChat.setText(txtAChat.getText() + "<" + u.getNombre()+ "> " + txtChat.getText() + "\n");
			txtChat.setText("");
		}
	}
	
	private void initCartas()
	{
		carta1Jugador1.setIcon(new ImageIcon("cartaAtras.png"));
		carta2Jugador1.setIcon(new ImageIcon("cartaAtras.png"));
		carta3Jugador1.setIcon(new ImageIcon("cartaAtras.png"));
		carta4Jugador1.setIcon(new ImageIcon("cartaAtras.png"));
		carta1Jugador2.setIcon(new ImageIcon("cartaDerAtras.png"));
		carta2Jugador2.setIcon(new ImageIcon("cartaDerAtras.png"));
		carta3Jugador2.setIcon(new ImageIcon("cartaDerAtras.png"));
		carta4Jugador2.setIcon(new ImageIcon("cartaDerAtras.png"));
		carta1Jugador3.setIcon(new ImageIcon("cartaSupAtras.png"));
		carta2Jugador3.setIcon(new ImageIcon("cartaSupAtras.png"));
		carta3Jugador3.setIcon(new ImageIcon("cartaSupAtras.png"));
		carta4Jugador3.setIcon(new ImageIcon("cartaSupAtras.png"));
		carta1Jugador4.setIcon(new ImageIcon("cartaIzqAtras.png"));
		carta2Jugador4.setIcon(new ImageIcon("cartaIzqAtras.png"));
		carta3Jugador4.setIcon(new ImageIcon("cartaIzqAtras.png"));
		carta4Jugador4.setIcon(new ImageIcon("cartaIzqAtras.png"));
	}
}