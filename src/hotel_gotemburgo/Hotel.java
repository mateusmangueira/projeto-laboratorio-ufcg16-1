package hotel_gotemburgo;

import java.util.HashSet;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.*;

/**
 * O Hotel representa uma entidade de gerenciamento. Ele contem um set de
 * Hospedes, utilizado para armazenamento desses hospedes no sistema. Metodos
 * sao utilizados para operar sobre esse set e realizar operacoes, como busca,
 * consulta e remocoes.
 * 
 * Criada em 12 de Setembro, 2016
 * 
 * @author Anderson Vital matricula <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo matricula <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christhoper Matricula <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */

public class Hotel {

	private Set<Hospede> hospedes;

	public Hotel() {
		this.hospedes = new HashSet<Hospede>();
	}

	public Set<Hospede> getHospedes() {
		return hospedes;
	}

	/**
	 * Esse metodo cria e retorna um objeto do tipo Hospede, com base nos
	 * parametros recebidos de entrada.
	 * 
	 * @param nome
	 * @param email
	 * @param anoNascimento
	 * @return O Hospede criado.
	 * @throws StringInvalidaException
	 */
	public Hospede criaHospede(String nome, String email, String anoNascimento) throws HotelException {
		Hospede hospede = new Hospede(nome, email, anoNascimento);
		return hospede;
	}

	/**
	 * Com base no email recebido de entrada, realiza uma busca no set de
	 * hospedes. Caso um dos hospedes possua esse mesmo email, esse hospede eh
	 * retornado. Caso nao existam hospedes com esse email, o retorno eh null.
	 * 
	 * @param email
	 * @return Um hospede, caso encontre. Null, caso nao encontre.
	 * @throws EmailInvalidoException
	 */
	private Hospede buscaHospede(String email) throws HotelException {

		if (email.trim().isEmpty() || email == null) {
			throw new EmailInvalidoException("O email do hospede nao pode ser nulo ou vazio.");
		}
		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return hospede;
		}
		throw new BuscaInvalidaException(
				"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
	}

	/**
	 * Verifica se existe um hospede com determinado email no set de hospedes.
	 * 
	 * @param email
	 * @return True: caso exista o hospede, null: caso nao exista.
	 * @throws EmailInvalidoException
	 */
	private boolean isCadastrado(String email) throws HotelException {

		if (email.trim().isEmpty() || email == null) {
			throw new EmailInvalidoException("O email do hospede nao pode ser nulo ou vazio.");
		}
		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return true;
		}
		return false;
	}

	/**
	 * Recebe atributos de criacao de um hospede como entrada. Realiza uma
	 * verificaco para saber se ja existe um hospede com o email recebido. Caso
	 * negativo, cria um novo objeto Hospede com os valores recebidos como
	 * parametros, em seguida adiciona-o ao set de hospedes.
	 * 
	 * @param nome
	 * @param email
	 * @param ano
	 * @return
	 * @throws StringInvalidaException
	 */
	public String cadastraHospede(String nome, String email, String ano) throws HotelException {

		if (this.isCadastrado(email)) {
			throw new CadastroInvalidoException("Hospede jah existente.");
		}
		Hospede hospede = this.criaHospede(nome, email, ano);
		this.getHospedes().add(hospede);
		return email;
	}

	/**
	 * Caso um hospede com esse email esteja cadastrado (presente no set de
	 * hospedes), ele eh removido.
	 * 
	 * @param email
	 * @throws StringInvalidaException
	 */
	public void removeHospede(String email) throws HotelException {
		if (email.trim().isEmpty() || email == null) {
			throw new EmailInvalidoException("O email do hospede nao pode ser nulo ou vazio.");
		}

		if (!this.isCadastrado(email)) {
			throw new CadastroInvalidoException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		Hospede hospede = this.buscaHospede(email);
		this.getHospedes().remove(hospede);
	}

	/**
	 * Retorna informacoes relativas a um hospede (pesquisado atraves do email)
	 * de acordo com o atributo recebido na entrada.
	 * 
	 * @param email
	 * @param atributo
	 * @return A informacao requisitada
	 * @throws EmailInvalidoException
	 * @throws AtributoInvalidoException
	 */
	public String getInfoHospede(String email, String atributo) throws HotelException {
		if (atributo.trim().isEmpty() || atributo == null) {
			throw new AtributoInvalidoException("O atributo nao pode ser nulo ou vazio.");
		}
		if (atributo.equalsIgnoreCase("nome")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getNome();
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getAnoNascimento();
		} else if (atributo.equalsIgnoreCase("email")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getEmail();
		}
		return null;
	}

	/**
	 * Atualiza um atributo do cadastro de um hospede, de acordo com uma nova
	 * informacao (valor) recebido na entrada.
	 * 
	 * @param email
	 * @param atributo
	 * @param valor
	 * @throws EmailInvalidoException
	 * @throws AtributoInvalidoException
	 * @throws ValorInvalidoException
	 */
	public void atualizaCadastro(String email, String atributo, String valor) throws HotelException {
		if (atributo.equals(null) || atributo.trim().isEmpty()) {
			throw new AtributoInvalidoException("O atributo nao pode ser nulo ou vazio.");
		}
		if (valor.equals(null) || valor.trim().isEmpty()) {
			throw new ValorInvalidoException("O valor nao pode ser nulo ou vazio.");
		}
		if (atributo.equalsIgnoreCase("nome")) {
			this.buscaHospede(email).setNome(valor);// o busca email ja retorna
													// um hospede
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			this.buscaHospede(email).setAnoNascimento(valor);
		} else if (atributo.equalsIgnoreCase("email")) {
			this.buscaHospede(email).setEmail(valor);

		}
	}

	public static void main(String[] args) {
		args = new String[] { "hotel_gotemburgo.Hotel", "diretorio_testes/testes_uc1.txt" };
		EasyAccept.main(args);
	}

}
