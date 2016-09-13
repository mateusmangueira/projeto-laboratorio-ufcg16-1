package excecoes;

public class TipoInvalidoException extends ValorInvalidoException {
	
	public static final long SerialVersionUID = 1L;
	
	public TipoInvalidoException(String mensagem){
		super(mensagem);
	}

}
