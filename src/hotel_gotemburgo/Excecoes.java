package hotel_gotemburgo;

import excecoes.HotelException;
import excecoes.StringException;

public class Excecoes {

	public static void checaEmail(String email) throws StringException {
		if (email == null || email.trim().isEmpty())
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");
	}
	
	public static void checaAtributo(String atributo) throws StringException {
		if (atributo == null || atributo.trim().isEmpty())
			throw new StringException("O atributo nao pode ser nulo ou vazio.");
	}
	
	/* Cadastro */
	public static void checaCadastroNome(String nome) throws HotelException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new HotelException("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
	}
	
	public static void checaCadastroEmail(String email) throws HotelException {
		if (email == null || email.trim().isEmpty()) {
			throw new HotelException("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		}
	}
	
	public static void checaCadastroDataNascimento(String dataNascimento) throws HotelException {
		if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
			throw new HotelException(
					"Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
	}
	
	/* Atualiza cadastro */
	
	public static void checaAttCadastroNome(String valor) throws StringException {
		if (valor == null || valor.trim().isEmpty()) {
			throw new StringException("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
	}
	
	public static void checaAttCadastroDataNascimento(String valor) throws StringException {
		if (valor == null || valor.trim().isEmpty()) {
			throw new StringException("Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
	}
	
	public static void checaAttCadastroEmail(String valor) throws StringException {
		if (valor == null || valor.trim().isEmpty()) {
			throw new StringException("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		}
	}
	
}
