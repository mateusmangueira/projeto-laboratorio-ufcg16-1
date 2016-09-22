package verificacao.excecoes;

import verificacao.excecoes.HotelException;
import verificacao.validacao.Validacoes;

/**
 * Classe responsavel por realizar a verificacao de parametros de metodos
 * do HotelController, para melhor organizacao do codigo.
 */
public class Excecoes {

	 public static void checaString(String string, String mensagem) throws StringException {
		if (string == null || string.trim().isEmpty())
			throw new StringException(mensagem);
	}

	public static void checaInt(int inteiro, String mensagem) throws ValorException {
		if(inteiro < 0)
			throw new ValorException(mensagem);
	}

	/* Validacoes de formato */

	public static void checaFormatoEmail(String email, String mensagem) throws HotelException {
		if (!Validacoes.validaEmail(email)) {
			throw new HotelException(mensagem);
		}
	}

	public static void checaFormatoNome(String nome, String mensagem) throws HotelException {
		if (!Validacoes.validaNome(nome)) {
			throw new HotelException(mensagem);
		}
	}

	public static void checaFormatoData(String dataNascimento, String mensagem) throws HotelException {
		if (!Validacoes.validaData(dataNascimento)) {
			throw new HotelException(mensagem);
		}
	}
	
	public static void checaFormatoIdQuarto(String idQuarto, String mensagem) throws HotelException {
		if (!Validacoes.validaQuarto(idQuarto)) {
			throw new HotelException(mensagem);
		}
	}
	

}
