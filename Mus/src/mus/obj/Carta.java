package mus.obj;

import javax.swing.ImageIcon;

/* 
*	Esta es la clase para una carta
*	Define una carta, sus puntuaciones en las distintas rondas, etcetera.
*/

public class Carta
{
	// Atributos
	private int carta;
	private int valorGrande;
	private int valorChica;
	private int valorJuego;
	private String img;
	private boolean paraDescartar;
	
	/**     DISTRIBUCION DE CARTAS DENTRO DE LA BARAJA</br>
	 *      ===========================================</br>
	 *      Pitos: 0->7</br>
	 *      Cuatros: 8->11</br>
	 *      Cincos: 12->15</br>
	 *      Seis: 16->19</br>
	 *      Sietes: 20->23</br>
	 *      Sotas: 24->27</br>
	 *      Caballos: 28->31</br>
	 *      Reyes: 32->39</br>
	 *      ===========================================</br>
	 */
	
	/**		Constructor al que le mandas el numero de la carta a crear, sabiendo que 
	 *		las cartas siguen la numeracion establecida mas arriba
	 */
	public Carta(int _carta)
	{
		setCarta(_carta);
		paraDescartar =  false;
	}
	
	/* Inicializa la carta */
	private void setCarta(int _carta)
	{
		if (_carta>-1 && _carta<40)
		{
			carta = _carta;
			setImg();
			switch(carta)
			{
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
					valorGrande = 1;
					valorChica = 21845;
					//valorPares = 15;
					valorJuego = 1;
					break;
				case 8:
				case 9:
				case 10:
				case 11:
					valorGrande = 5;
					valorChica = 5461;
					//valorPares = 16;
					valorJuego = 4;
					break;
				case 12:
				case 13:
				case 14:
				case 15:
					valorGrande = 21;
					valorChica = 1365;
					//valorPares = 17;
					valorJuego = 5;
					break;
				case 16:
				case 17:
				case 18:
				case 19:
					valorGrande = 85;
					valorChica = 341;
					//valorPares = 18;
					valorJuego = 6;
					break;
				case 20:
				case 21:
				case 22:
				case 23:
					valorGrande = 341;
					valorChica = 85;
					//valorPares = 19;
					valorJuego = 7;
					break;
				case 24:
				case 25:
				case 26:
				case 27:
					valorGrande = 1365;
					valorChica = 21;
					//valorPares = 20;
					valorJuego = 10;
					break;
				case 28:
				case 29:
				case 30:
				case 31:
					valorGrande = 5461;
					valorChica = 5;
					//valorPares = 21;
					valorJuego = 10;
					break;
				case 32:
				case 33:
				case 34:
				case 35:
				case 36:
				case 37:
				case 38:
				case 39:
					valorGrande = 21845;
					valorChica = 1;
					//valorPares = 22;
					valorJuego = 10;
					break;
			}
		}
	}
	
	/**		Devuelve el numero de la carta	 */
	
	public int getCarta()
	{
		return carta;
	}
	
	/**	Devuelve el valor usado para calcular quien gana la grande, o los pares en caso de duplex	*/
	public int getValorGrande()
	{
		return valorGrande;
	}
	
	/** Devuelve el valor usado para ver quien gana la chica */
	public int getValorChica()
	{
		return valorChica;
	}
	
	/** Devuelve si una carta esta para descartarse o no */
	public boolean getParaDescartar()
	{
		return paraDescartar;
	}
	
	/** Devuelve el valor usado para ver quien gana el juego */
	public int getValorJuego()
	{
		return valorJuego;
	}
	
	/** Devuelve una imagen que representa esta carta*/
	public ImageIcon getImg()
	{
		return new ImageIcon(this.img);
	}
	
	/* Al inicializar la carta, le asigna una imagen */
	private void setImg()
	{
		String s = "";
		String p = "";
		int palo = carta % 4;
		
		switch(carta)
		{
			case 0:
			case 1:
			case 2:
			case 3:
				s = "1";
				break;
			case 4:
			case 5:
			case 6:
			case 7:
				s = "2";
				break;
			case 8:
			case 9:
			case 10:
			case 11:
				s = "4";
				break;
			case 12:
			case 13:
			case 14:
			case 15:
				s = "5";
				break;
			case 16:
			case 17:
			case 18:
			case 19:
				s = "6";
				break;
			case 20:
			case 21:
			case 22:
			case 23:
				s = "7";
				break;
			case 24:
			case 25:
			case 26:
			case 27:
				s = "s";
				break;
			case 28:
			case 29:
			case 30:
			case 31:
				s = "c";
				break;
			case 32:
			case 33:
			case 34:
			case 35:
				s = "3";
				break;
			case 36:
			case 37:
			case 38:
			case 39:
				s = "r";
				break;
		}
		
		switch(palo)
		{
			case 0:
				p = "o";	// oros
				break;
			case 1:
				p = "c";	// copas
				break;
			case 2:
				p = "e";	// espadas
				break;
			case 3:
				p = "b";	// bastos
				break;
		}
		
		img = s + "-" + p + ".jpg";
	}
	
	/** Compara dos cartas y devuelve si son iguales o no */
	public boolean equals(Object o)
	{
		if (o instanceof Carta)
		{
			Carta c = (Carta) o;
			
			if (valorGrande == c.getValorGrande())
				return true;
			else
				return false;
		}
		else
			return false;
	}
}