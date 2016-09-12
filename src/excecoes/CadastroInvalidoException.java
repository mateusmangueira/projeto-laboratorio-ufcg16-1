package excecoes;

public class CadastroInvalidoException extends StringInvalidaException{
	
	public static final long SerialVersionUID = 1L;
	
	public CadastroInvalidoException(String mensagem){
		super(mensagem);
	}

}
