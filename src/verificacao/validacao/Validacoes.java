package verificacao.validacao;

public class Validacoes {
	
	/**
	 * Esse metodo determina um padrao de data para nao ocorrer erros de data
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean validaData(String data) {
		String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}$";
		return data.matches(regex);
	}

	/**
	 * Esse metodo determina um padrao de email para nao ocorrer erros de email
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean validaEmail(String email) {
		String regex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}";
		return email.matches(regex);
	}

	/**
	 * Esse metodo determina um padrao de nome para nao ocorrer erros de nome
	 * 
	 * @param nome
	 * @return boolean
	 */
	public static boolean validaNome(String nome) {
		String regex = "[A-Z][a-z]+[[ ][A-Z][a-z]+]*";
		return nome.matches(regex);
	}
	
	/**
	 * Esse metodo determina um padrao de nome para nao ocorrer erros de criacao
	 * de Quartos
	 * 
	 * @param quarto
	 * @param tipo
	 * @return boolean
	 */
	public static boolean validaQuarto(String idQuarto) {
		String regex = "[[0-9]]*[[A-Z]]*";
		return idQuarto.matches(regex);
	}
	
	

}
