package obj;

public class AI
{
	/** Devuelve verdadero si la maquina se tiene que dar mus, o falso si tiene que cortar */
	public static boolean queHacerMus(Jugador j)
	{
		// Mas de RRC
		if(Puntos.calculaGrande(j.getCartas(), 4) > 49151)
		{
			return false;
		}
		else if(Puntos.calculaChica(j.getCartas()) > 65535)
		{
			return false;
		}
		else if(Puntos.queParesTiene(j.getCartas()) > 2)
		{
			return false;
		}
		else if(Puntos.calculaJuego(j.getCartas()) == 42 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/** Devuelve 0 si no quiere el envite a grande o no quiere envidar, 1 si quiere ver, o lo que quiera subir o envidar*/
	public static int queHacerGrande(Jugador j, int puntos)
	{
		return 1;
	}
	
	/** Devuelve 0 si no quiere el envite a chica o no quiere envidar, 1 si quiere ver, o lo que quiera subir */
	public static int queHacerChica(Jugador j, int puntos)
	{
		return 1;
	}
	
	/** Devuelve 0 si no quiere el envite a pares o no quiere envidar, 1 si quiere ver, o lo que quiera subir */
	public static int queHacerPares(Jugador j, int puntos)
	{
		return 1;
	}
	
	/** Devuelve 0 si no quiere el envite a juego o no quiere envidar, 1 si quiere ver, o lo que quiera subir */
	public static int queHacerJuego(Jugador j, int puntos)
	{
		return 1;
	}
	
	/** Escoge que cartas descartar */
	public static void queHacerDescarte(Jugador j)
	{
		
	}
}
