package hotel_gotemburgo;

import excecoes.StringInvalidaException;

public interface Administravel {

	public void cadastra(String string)throws StringInvalidaException;

	public void remove(String string)throws StringInvalidaException;
	
	public Object criador(String string, String string2, String string3) throws StringInvalidaException;

}
