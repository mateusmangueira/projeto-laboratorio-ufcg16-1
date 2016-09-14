package excecoes;

public class TipoInvalidoException extends ValorInvalidoException {
	
	private static final long serialVersionUID = 1L;
	
	public TipoInvalidoException(String mensagem){
		super(mensagem);
	}

}
