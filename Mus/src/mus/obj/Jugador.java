package mus.obj;
/* 
	Esta es la clase para el mus
	Define a los jugadores de mus
*/

public class Jugador extends Usuario
{
	//Atributos
	private Carta[] cartas = new Carta[4];
	private int posicionMano;	// 1, 2, 3, 4

	/** Crea una instancia de jugador */
	public Jugador(String _nombre, String _pass, int _posicionMano)
	{
		super(_nombre, _pass);
		posicionMano = _posicionMano;
	}

	/** Devuelve un vector con las cartas que tiene el usuario */
	public Carta[] getCartas()
	{
		return cartas;
	}
	
	/** Devuelve la posicion de la mano de ese jugador en la ronda actual */
	public int getPosicionMano()
	{
		return posicionMano;
	}
	
	/** Cambia la posicion de la mano de este jugador para la siguiente ronda */
	public void setPosicionMano(int posicionMano)
	{
		if(posicionMano > 0 && posicionMano < 5)
		{
			this.posicionMano = posicionMano;
		}
	}
	
	/** Se llama al iniciar la partida para darle las cartas */
	public void setCartas(Baraja b)
	{
		for(int i = 0; i < 4; i++)
		{
			cartas[i] = b.darCarta();
		}
		
		ordenaCartas();
	}
	
	/** Ordena las cartas del jugador de mayor a menor */
	private void ordenaCartas()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4-i; j++)
			{
				if(cartas[i].getValorGrande() < cartas[j].getValorGrande())
				{
					Carta c = cartas[j];
					cartas[j] = cartas[i];
					cartas[i] = c;
				}
			}
		}
	}
	
}