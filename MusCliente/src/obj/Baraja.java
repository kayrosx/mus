package obj;
/* 
*	Esta es la clase para el mus
*	Define a la baraja y las operaciones con ella
*/

/*
*	DEFINICION PARA LOS VALORES DE LAS POSICIONES DE BARAJA "haSalido"
*	========================================================================
*	Cuando la carta aun no se haya cogido, el valor en esa posicion sera 0
*	Cuando la carta este en la mano de algun jugador, el valor en esa posicion sera 1
*	Cuando la carta se haya descartado, el valor en esa posicion sera -1
*/

/*      DISTRIBUCION DE CARTAS DENTRO DE LA BARAJA
 *      ===========================================
 *      Pitos: 0->7
 *      Cuatros: 8->11
 *      Cincos: 12->15
 *      Seis: 16->19
 *      Sietes: 20->23
 *      Sotas: 24->27
 *      Caballos: 28->31
 *      Reyes: 32->39
 *      ===========================================
 */

import java.util.*;

public class Baraja
{
	// Atributos
	private LinkedList<Carta> baraja = new LinkedList<Carta>();
	private LinkedList<Carta> descarte = new LinkedList<Carta>();
	
	// Constructores
	public Baraja()
	{
		inicializar();	// Inicializa la lista de cartas que han salido ya
	}
	
	/*
	 * Inicializa la baraja. En realidad, inicializa todas las cartas a "descartadas"
	 * por la forma de funcionar de simularBaraje();
	*/
	private void inicializar()
	{
		int n;
		
		for(n = 0; n < 40; n++)
		{
			descarte.add(new Carta(n));
		}
		
		simularBaraje();		// Inicializa la bajara al principio de la partida
	}
	
	/*
	 * Devuelve la primera carta de la baraja y la quita de la baraja
	*/
	/** Devuelve una instancia de carta. Solo se ha de llamar al principio de la partida
	 * para darle las 4 cartas a cada jugador. Para descartar, llamar a descarta(Carta)*/
	public Carta darCarta()
	{
		// Si la baraja no tiene cartas, simula un baraje y despues devuelve una carta
		if(baraja.isEmpty())
		{
			simularBaraje();
		}
		
		Carta c = baraja.getFirst();
		baraja.removeFirst();
		
		return c;
	}
	
	/*
	 * Pasa todas las cartas de la baraja "descarte" a la baraja principal.
	 * Las va pasando en un orden aleatorio, para que sea como si se hubiesen barajado
	*/
	private void simularBaraje()
	{
		Random rand = new Random();
		int n = 0;
		
		while(!descarte.isEmpty())
		{
			// Genera una carta aleatoriamente
			n = (int) Math.sqrt((Math.pow(rand.nextInt(), 2)))%(descarte.size());
			
			// Recupera la carta de la bajara de descartes
			// y la añade a la baraja
			Carta carta = descarte.get(n);
			descarte.remove(n);
			baraja.add(carta);
		}
	}
	
	/*
	 * Descarta una carta. La añade a la baraja de descartes y da una nueva carta
	*/
	/** Devuelve una instancia de Carta. Ademas, descarta la carta que se le ha pasado */
	private Carta descarta(Carta carta)
	{
		// Añade esa carta a la baraja de descarte
		descarte.add(carta);
		
		// Devuelve una nueva carta
		return darCarta();
		
	}
	
	/** Recibe un jugador, y mira cuales de sus cartas tiene que descartar	*/
	public void descarta(Jugador j)
	{
		Carta[] c = j.getCartas();
		
		for(int i = 0; i < 4; i++)
		{
			if (c[i].getParaDescartar())
			{
				descarta(c[i]);
			}
		}
	}
}