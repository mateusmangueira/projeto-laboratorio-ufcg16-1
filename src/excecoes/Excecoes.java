package excecoes;

import excecoes.HotelException;

/**
 * Classe responsavel por realizar a verificacao de parametros de metodos
 * do HotelController, para melhor organizacao do codigo.
 */
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
		if (nome == null || nome.trim().isEmpty()) 
			throw new HotelException("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
	}
	
	public static void checaCadastroEmail(String email) throws HotelException {
		if (email == null || email.trim().isEmpty())
			throw new HotelException("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
	}
	
	public static void checaCadastroDataNascimento(String dataNascimento) throws HotelException {
		if (dataNascimento == null || dataNascimento.trim().isEmpty())
			throw new HotelException("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
	}
	
	/* Atualiza cadastro */
	
	public static void checaAttCadastroNome(String valor) throws StringException {
		if (valor == null || valor.trim().isEmpty())
			throw new StringException("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
	}
	
	public static void checaAttCadastroDataNascimento(String valor) throws StringException {
		if (valor == null || valor.trim().isEmpty())
			throw new StringException("Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
	}
	
	public static void checaAttCadastroEmail(String valor) throws StringException {
		if (valor == null || valor.trim().isEmpty())
			throw new StringException("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
	}
	
	/* GetInfoHospedagem */
	
	public static void checaGetInfoHospedagemEmail (String email) throws StringException {
		if (email == null || email.trim().isEmpty())
			throw new StringException("Erro ao checar hospedagem ativa. Email do(a) hospede nao pode ser vazio.");
		
	}
	/* Realiza Checkin */
	
	public static void checaCheckinEmail(String valor) throws HotelException {
		if(valor == null || valor.trim().isEmpty())
			throw new HotelException("Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
	}
	
	public static void checaCheckinDias(int dias) throws ValorException {
		if (dias < 0)
			throw new ValorException("Erro ao realizar checkin. Quantidade de dias esta invalida.");
	}
	
	/* Realiza Checkout */
	
	public static void checaCheckoutEmail(String email) throws StringException {
		if (email == null || email.trim().isEmpty())
			throw new StringException("Erro ao realizar checkout. Email do(a) hospede nao pode ser vazio.");
	}
	
}
