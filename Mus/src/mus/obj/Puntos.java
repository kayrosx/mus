package mus.obj;
public class Puntos
{
	public static final int GRANDE = 0;
	public static final int CHICA = 1;
	public static final int PARES = 2;
	public static final int JUEGO = 3;
	
	/** Suma los puntos al equipo especificado. El primer parametro utilizara los
	 *  parametros de clase GRANDE, CHICA, PARES, JUEGO */
	public static void darPuntos(int _etapa, Equipo e, int _puntos)
	{
		e.setPuntos(_puntos);
		
		if(_etapa == 2)
			sumaPuntosXPar(e);
		else if(_etapa == 3)
			sumaPuntosXJuego(e);
	}
	
	/**	Devuelve un equipo especificando cual de los 2 gana la grande */
	public static Equipo calculaGrande(Equipo equipo1, Equipo equipo2, int _puntos)
	{
		int suma1;
		int suma2;
		int suma3;
		int suma4;
		int mano1;
		int mano2;

		// Calcula los puntos de cada jugador
		suma1 = calculaGrande(equipo1.getJugador(1).getCartas(), 4);
		suma2 = calculaGrande(equipo1.getJugador(2).getCartas(), 4);
		suma3 = calculaGrande(equipo2.getJugador(1).getCartas(), 4);
		suma4 = calculaGrande(equipo2.getJugador(2).getCartas(), 4);
		
		// El jugador2 del equipo1 tiene mejor grande que su compañero
		if(suma2 > suma1)
		{
			suma1 = suma2;
			mano1 = equipo1.getJugador(2).getPosicionMano();
		}
		// El jugador1 del equipo1 tiene mejor grande que su compañero
		else
			mano1 = equipo1.getJugador(1).getPosicionMano();
		
		// El jugador2 del equipo2 tiene mejor grande que su compañero
		if(suma4 > suma3)
		{
			suma3 = suma4;
			mano2 = equipo2.getJugador(2).getPosicionMano();
		}
		// El jugador1 del equipo2 tiene mejor grande que su compañero
		else
			mano2 = equipo2.getJugador(1).getPosicionMano();
		
		// Da los puntos al equipo ganador
		// El equpo1 tiene mejor grande que el equipo2
		if(suma1 > suma3)
		{
			equipo1.setPuntos(_puntos);
			return equipo1;
		}
		// El equpo2 tiene mejor grande que el equipo1
		else if (suma3 > suma1)
		{
			equipo2.setPuntos(_puntos);
			return equipo2;
		}
		// Tienen la misma grande
		else
		{
			// El equipo1 va antes que el equipo2
			if(mano1 < mano2)
			{
				return equipo1;
			}
			// El equipo2 va antes que el equipo1
			else
			{
				return equipo2;
			}
		}
			
	}
	
	/* Es necesario recibir un entero porque sumaPares lo usa, y puede enviar un
	 * vector de tamaño 2, 3 o 4, y asi solo hay que usar un metodo
	 */
	/** Devuelve un valor numerico que representa lo que lleva a grande */
	public static int calculaGrande(Carta[] cartas, int n)
	{
		int suma = 0;
		
		for (int i = 0; i < n; i++)
		{
			suma += cartas[i].getValorGrande();
		}
		
		return suma;
	}
	
	/**	Devuelve un equipo especificando cual de los 2 gana la chica */
	public static Equipo calculaChica(Equipo equipo1, Equipo equipo2, int _puntos)
	{
		int suma1;
		int suma2;
		int suma3;
		int suma4;
		int mano1;
		int mano2;
		
		// Calcula los puntos de cada jugador
		suma1 = calculaChica(equipo1.getJugador(1).getCartas());
		suma2 = calculaChica(equipo1.getJugador(2).getCartas());
		suma3 = calculaChica(equipo2.getJugador(1).getCartas());
		suma4 = calculaChica(equipo2.getJugador(2).getCartas());
		
		// El jugador2 del equipo1 tiene mejor chica que su compañero
		if(suma2 > suma1)
		{
			suma1 = suma2;
			mano1 = equipo1.getJugador(2).getPosicionMano();
		}
		// El jugador1 del equipo1 tiene mejor chica que su compañero
		else
			mano1 = equipo1.getJugador(1).getPosicionMano();
		
		// El jugador2 del equipo2 tiene mejor chica que su compañero
		if(suma4 > suma3)
		{
			suma3 = suma4;
			mano2 = equipo2.getJugador(2).getPosicionMano();
		}
		// El jugador1 del equipo2 tiene mejor chica que su compañero
		else
			mano2 = equipo2.getJugador(1).getPosicionMano();
		
		
		// Da los puntos al equipo ganador
		// El equpo1 tiene mejor chica que el equipo2
		if(suma1 > suma3)
		{
			return equipo1;
		}
		// El equpo2 tiene mejor chica que el equipo1
		else if (suma3 > suma1)
		{
			return equipo2;
		}
		// Tienen la misma chica
		else
		{
			// El equipo1 va antes que el equipo2
			if(mano1 < mano2)
			{
				return equipo1;
			}
			// El equipo2 va antes que el equipo1
			else
			{
				return equipo2;
			}
		}
	}
	
	/** Devuelve un valor numerico que representa lo que lleva a chica */
	public static int calculaChica(Carta[] cartas)
	{
		int suma = 0;
		
		for (int i = 0; i < 4; i++)
		{
			suma += cartas[i].getValorChica();
		}
		
		return suma;
	}
	
	/**	Devuelve un equipo especificando cual de los 2 gana los pares	*/
	public static Equipo calculaPares(Equipo equipo1, Equipo equipo2, int _puntos)
	{
		int suma1, num1;
		int suma2, num2;
		int suma3, num3;
		int suma4, num4;
		int mano1, mano2;
		int compara1, compara2;
		
		num1 = queParesTiene(equipo1.getJugador(1).getCartas());
		num2 = queParesTiene(equipo1.getJugador(2).getCartas());
		num3 = queParesTiene(equipo2.getJugador(1).getCartas());
		num4 = queParesTiene(equipo2.getJugador(2).getCartas());
		
		// Calcula los pares de cada jugador
		suma1 = sumaPares(equipo1.getJugador(1).getCartas());
		suma2 = sumaPares(equipo1.getJugador(2).getCartas());
		suma3 = sumaPares(equipo2.getJugador(1).getCartas());
		suma4 = sumaPares(equipo2.getJugador(2).getCartas());
		
		// El jugador1 del equipo1 tiene mejores pares que sus contrarios
		if(num1 > num3 && num1 > num4)
		{
			return equipo1;
		}
		// El jugador2 del equipo1 tiene mejores pares que sus contrincantes
		else if(num2 > num3 && num2 > num4)
		{
			return equipo1;
		}
		// El jugador1 del equipo2 tiene mejores pares que sus contrarios
		else if(num1 < num3 && num1 < num4)
		{
			return equipo2;
		}
		// El jugador2 del equipo2 tiene mejores pares que sus contrarios
		else if(num2 < num3 && num2 < num4)
		{
			return equipo2;
		}
		// Hay jugadores de ambos equipos que tienen los mismos pares
		else 
		{
			// El jugador1 del equipo1 tiene mejores pares que su compañero
			if(num1 > num2)
			{
				compara1 = suma1;
				mano1 = equipo1.getJugador(1).getPosicionMano();
			}
			// El jugador2 del equipo1 tiene mejores pares que su compañero
			else if(num1 < num2)
			{
				compara1 = suma2;
				mano1 = equipo1.getJugador(2).getPosicionMano();
			}
			// Tienen los mismos pares
			else
			{
				// El jugador1 tiene los mismos pares, pero mas altos
				if(suma1 > suma2)
				{
					compara1 = suma1;
					mano1 = equipo1.getJugador(1).getPosicionMano();
				}
				// El jugador2 tiene los mismos pares, pero mas altos
				else if(suma1 < suma2)
				{
					compara1 = suma2;
					mano1 = equipo1.getJugador(2).getPosicionMano();
				}
				// Tienen exactamente los mismos pares
				else
				{
					// El jugador1 va antes que el jugador2
					if(equipo1.getJugador(1).getPosicionMano() < equipo1.getJugador(2).getPosicionMano())
					{
						compara1 = suma1;
						mano1 = equipo1.getJugador(1).getPosicionMano();
					}
					// El jugador2 va antes que el jugador1
					else
					{
						compara1 = suma2;
						mano1 = equipo1.getJugador(2).getPosicionMano();
					}
				}
			}
			
			// El jugador1 del equipo2 tiene mejores pares que su compañero
			if(num3 > num4)
			{
				compara2 = suma3;
				mano2 = equipo2.getJugador(1).getPosicionMano();
			}
			// El jugador2 del equipo2 tiene mejores pares que su compañero
			else if(num3 < num4)
			{
				compara2 = suma4;
				mano2 = equipo2.getJugador(2).getPosicionMano();
			}
			// Tienen los mismos pares
			else
			{
				// El jugador1 tiene los mismos pares, pero mas altos
				if(suma3 > suma4)
				{
					compara2 = suma3;
					mano2 = equipo2.getJugador(1).getPosicionMano();
				}
				// El jugador2 tiene los mismos pares, pero mas altos
				else if(suma3 < suma4)
				{
					compara2 = suma4;
					mano2 = equipo2.getJugador(2).getPosicionMano();
				}
				// Tienen exactamente los mismos pares
				else
				{
					// El jugador1 va antes que el jugador2
					if(equipo2.getJugador(1).getPosicionMano() < equipo2.getJugador(2).getPosicionMano())
					{
						compara2 = suma3;
						mano2 = equipo2.getJugador(1).getPosicionMano();
					}
					// El jugador2 va antes que el jugador1
					else
					{
						compara2 = suma4;
						mano2 = equipo2.getJugador(2).getPosicionMano();
					}
				}	
			}
			
			// Da los puntos al equipo ganador
			// El equipo1 tiene mejores pares que el equipo2
			if(compara1 > compara2)
			{
				return equipo1;
			}
			// El equipo2 tiene mejores pares que el equipo1
			else if (compara1 < compara2)
			{
				return equipo2;
			}
			// Tienen los mismos pares
			else
			{
				// El equipo1 va antes que el equipo2
				if(mano1 < mano2)
				{
					return equipo1;
				}
				// El equipo2 va antes que el equipo1
				else
				{
					return equipo2;
				}
			}	
		}
	}
	
	/** Devuelve n > 0 si hay pares, n = 0 si no hay pares. n = 2 => par. n = 3 => medias. n = 4 => duples  */
	public static int queParesTiene(Carta[] cartas)
	{
		int n = 0;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3-i; j++)
			{
				if(cartas[j].equals(cartas[j+1]) == true)
					n++;
			}
		}
		
		if(n > 0)
			n++;
		
		return n;
	}
	
	/* Devuelve un numero entero con la puntuacion de los pares. Usa el metodo calculaGrande
	 *  para poder calcularlo 
	*/
	private static int sumaPares(Carta[] cartas)
	{
		// Como las cartas estan ordenadas, se puede asumir que las cartas iguales
		// estaran en posiciones consecutivas
		int pares = queParesTiene(cartas);
		Carta[] aux = new Carta[pares];
		
		if(pares == 2)
		{
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3-i; j++)
				{
					if(cartas[j].equals(cartas[j+1]) == true)
					{
						aux[0] = cartas[j];
						aux[1] = cartas[j+1];
						
						return calculaGrande(aux, pares);
					}
				}
			}
		}
		else if(pares == 3)
		{
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3-i; j++)
				{
					if(cartas[j].equals(cartas[j+1]) == true)
					{
						aux[0] = cartas[j];
						aux[1] = cartas[j+1];
						aux[2] = cartas[j+2];
						
						return calculaGrande(aux, pares);
					}
				}
			}
		}
		else if(pares == 4)
		{
			return calculaGrande(cartas, pares);
		}
		
		return 0;
	}
	
	/**	Devuelve un equipo especificando cual de los 2 gana el juego */
	public static Equipo calculaJuego(Equipo equipo1, Equipo equipo2, int _puntos)
	{
		int suma1;
		int suma2;
		int suma3;
		int suma4;
		int mano1;
		int mano2;
		
		// Calcula los puntos de cada jugador
		suma1 = calculaJuego(equipo1.getJugador(1).getCartas());
		suma2 = calculaJuego(equipo1.getJugador(2).getCartas());
		suma3 = calculaJuego(equipo2.getJugador(1).getCartas());
		suma4 = calculaJuego(equipo2.getJugador(2).getCartas());
		
		// El jugador2 del equipo1 tiene mejor juego que su compañero
		if(suma2 > suma1)
		{
			suma1 = suma2;
			mano1 = equipo1.getJugador(2).getPosicionMano();
		}
		// El jugador1 del equipo1 tiene mejor juego que su compañero
		else
			mano1 = equipo1.getJugador(1).getPosicionMano();
		
		// El jugador2 del equipo2 tiene mejor juego que su compañero
		if(suma4 > suma3)
		{
			suma3 = suma4;
			mano2 = equipo2.getJugador(2).getPosicionMano();
		}
		// El jugador1 del equipo2 tiene mejor juego que su compañero
		else
			mano2 = equipo2.getJugador(1).getPosicionMano();
		
		// Da los puntos al equipo ganador
		// El equipo1 tiene mejor juego que el equipo2
		if(suma1 > suma3)
		{
			return equipo1;
		}
		// El equipo2 tiene mejor juego que el equipo1
		else if (suma3 > suma1)
		{
			return equipo2;
		}
		// Tienen el mismo juego
		else
		{
			// El equipo1 va antes que el equipo2
			if(mano1 < mano2)
			{
				return equipo1;
			}
			// El equipo2 va antes que el equipo1
			else
			{
				return equipo2;
			}
		}
	}
	
	/** Devuelve un entero para especificar que juego tiene. X>30 => tiene juego.
	 *  X<=30 => No tiene juego	 */
	public static int calculaJuego(Carta[] cartas)
	{
		int suma = 0;
		
		for (int i = 0; i < 4; i++)
		{
			suma += cartas[i].getValorJuego();
		}
		
		if(suma == 31)
			suma = 42;
		else if(suma == 32)
			suma = 41;
		
		return suma;
	}
	
	private static void sumaPuntosXPar(Equipo e)
	{
		int suma1 = queParesTiene(e.getJugador(1).getCartas());
		int suma2 = queParesTiene(e.getJugador(2).getCartas());
		
		if(suma1 > 0)
			suma1--;
		if(suma2 > 0)
			suma2--;
		
		e.setPuntos(suma1 + suma2);
		
		return;
	}
	
	private static void sumaPuntosXJuego(Equipo e)
	{
		int suma1 = calculaJuego(e.getJugador(1).getCartas());
		int suma2 = calculaJuego(e.getJugador(2).getCartas());
		
		if(suma1 == 42)
			suma1 = 3;
		else if(suma1 > 30)
			suma1 = 2;
		else 
			suma1 = 0;
		
		if(suma2 == 42)
			suma2 = 3;
		else if(suma2 > 30)
			suma2 = 2;
		else
			suma2 = 0;
		
		e.setPuntos(suma1 + suma2);
	}
}