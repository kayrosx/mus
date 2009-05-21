package obj;

public class Usuario implements java.io.Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = -1221813109873655076L;
    //Atributos
	private String nombre;
	private String pass;
	private int puntos;
	private String img;
	
	public Usuario(String _nombre, String _pass)
	{
		nombre = _nombre;
		pass = _pass;
		puntos = 1000;
		img = "";
	}
	
	/** Devuelve el nombre del usuario */
	public String getNombre()
	{
		return nombre;
	}
	
	/** Devuelve los puntos que tiene el usuario */
	public int getPuntos()
	{
		return puntos;
	}
	
	/** Devuelve un String con la imagen asociada a este usuario */
	public String getImg()
	{
		return img;
	}
	
	/** Devuelve la contraseña de este usuario */
	public String getPass()
	{
		return pass;
	}
	
	/** Recibe un String para cambiar la contraseña */
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	
	/** Cambia los puntos de este usuario */
	public void setPuntos(int puntos)
	{
		this.puntos = puntos;
	}
	
	/** Cambia el String que representa la imagen asociada a este usuario */
	public void setImg(String img)
	{
		this.img = img;
	}
	
	/** Compara a dos usuarios y determina si son iguales o no */
	public boolean equals(Object o)
	{
		if(o instanceof Jugador)
		{
			Jugador j = (Jugador) o;
			
			if (nombre.equals(j.getNombre()))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	/** Devuelve una representacion en forma de String del usuario */
	public String toString()
	{
		return "\nNombre: " + nombre + "\nPuntos: " + puntos + img;
	}
	
	/** Devuelve un entero que se corresponde con la representacion hash del usuario */
	public int hashCode()
	{
		return nombre.hashCode();
	}
}