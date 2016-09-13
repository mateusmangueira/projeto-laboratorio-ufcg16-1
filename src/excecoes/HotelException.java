package excecoes;

public class HotelException extends Exception {
	
	public static final long SerialVersionUID = 1L;
	
	public HotelException(String mensagem){
		super(mensagem);
	}

}
