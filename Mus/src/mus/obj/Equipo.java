package mus.obj;

public class Equipo 
{
    // Atributos
    private int puntos;
    private Jugador jugador1;
    private Jugador jugador2;
    
    public Equipo(Jugador _jugador1, Jugador _jugador2)
    {
        jugador1 = _jugador1;
        jugador2 = _jugador2;
        this.setPuntos(0);
    }
    
    /** Suma la cantidad de puntos indicada a los puntos ya conseguidos */
    public void setPuntos(int _puntos)
    {
    	if(_puntos > 0)
    	{
    		puntos += _puntos;
    	}
    	else if(_puntos == 0)
    	{
    		puntos = 0;
    	}
    }
    
    /** Devuelve los puntos de este equipo */
    public int getPuntos()
    {
    	return puntos;
    }
    
    /** Devuelve el jugador especificado del equipo */
    public Jugador getJugador(int i)
    {
    	if (i == 1)
    		return jugador1;
    	else
    		return jugador2;
    }
}