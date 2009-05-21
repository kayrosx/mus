package obj;

public class Partida
{
	private Usuario u1;
	private Usuario u2;
	private Usuario u3;
	private Usuario u4;
	private Baraja b;
	private Jugador j1;
	private Jugador j2;
	private Jugador j3;
	private Jugador j4;
	private Equipo equipo1;
	private Equipo equipo2;
	
	public Partida(Usuario u1, Usuario u2, Usuario u3, Usuario u4)
	{
		this.u1 = u1;
		this.u2 = u2;
		this.u3 = u3;
		this.u4 = u4;
		
		b = new Baraja();
		j1 = new Jugador(u1.getNombre(), u1.getPass(), 1);
		j2 = new Jugador(u2.getNombre(), u2.getPass(), 2);
		j3 = new Jugador(u3.getNombre(), u3.getPass(), 3);
		j4 = new Jugador(u4.getNombre(), u4.getPass(), 4);
		equipo1 = new Equipo(j1, j3);
		equipo2 = new Equipo(j2, j4);
	}
	
	
}
