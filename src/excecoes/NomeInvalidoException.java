package excecoes;

public class NomeInvalidoException extends StringInvalidaException {
	
	private static final long serialVersionUID = 1L;
	
	public NomeInvalidoException(String mensagem) {
		super(mensagem);
	}

}
