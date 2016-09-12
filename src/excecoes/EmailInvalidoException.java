package excecoes;

public class EmailInvalidoException extends StringInvalidaException {
	
	private static final long serialVersionUID = 1L;

	public EmailInvalidoException(String mensagem) {
		super(mensagem);
	}

}
