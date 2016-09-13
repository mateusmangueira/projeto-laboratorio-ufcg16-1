package hotel_gotemburgo;

import excecoes.HotelException;

public interface Administravel {

	public void cadastra(String string)throws HotelException;

	public void remove(String string)throws HotelException;
	
	public Object criador(String string, String string2, String string3) throws HotelException;

}
