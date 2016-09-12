package hotel_gotemburgo;

import java.util.HashSet;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.StringInvalidaException;

/**
 * O Hotel representa uma entidade de gerenciamento. Ele contem um set de Hospedes, utilizado
 * para armazenamento desses hospedes no sistema. Metodos sao utilizados para operar sobre esse
 * set e realizar operacoes, como busca, consulta e remocoes.
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
	 * Esse metodo cria e retorna um objeto do tipo Hospede, com base nos parametros
	 * recebidos de entrada
	 * @param nome
	 * @param email
	 * @param anoNascimento
	 * @return O Hospede criado
	 * @throws StringInvalidaException Caso a string seja nula ou vazia
	 */
	public Hospede criaHospede(String nome, String email, String anoNascimento) throws StringInvalidaException {
		Hospede hospede = new Hospede(nome, email, anoNascimento);
		return hospede;
	}

	/**
	 * Com base no email recebido de entrada, realiza uma busca no set de hospedes.
	 * Caso um dos hospedes possua esse mesmo email, esse hospede eh retornado. 
	 * Caso nao existam hospedes com esse email, o retorno eh null.
	 * @param email Email do hospede
	 * @return Um hospede, caso encontre. Null, caso nao encontre.
	 */
	private Hospede buscaHospede(String email) {
		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return hospede;
		}
		return null;
	}

	/**
	 * Verifica se existe um hospede com determinado email no set de hospedes
	 * @param email Email do hospede
	 * @return True caso exista o hospede, null caso nao exista
	 */
	private boolean isCadastrado(String email) {
		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return true;
		}
		return false;
	}

	/**
	 * Recebe atributos de criacao de um hospede como entrada. Realiza uma verificaco
	 * para saber se ja existe um hospede com o email recebido. Caso negativo, cria
	 * um novo objeto Hospede com os valores recebidos como parametros, em seguida
	 * adiciona-o ao set de hospedes.
	 * @param nome Nome do hospede
	 * @param email Email do hospede
	 * @param ano Ano de nascimento do hospede
	 * @return 
	 * @throws StringInvalidaException
	 */
	public String cadastraHospede(String nome, String email, String ano) throws StringInvalidaException {
		if (this.isCadastrado(email)) {
			throw new StringInvalidaException("Hospede ja existente.");
		}
		Hospede hospede = this.criaHospede(nome, email, ano);
		this.getHospedes().add(hospede);
		return email;
	}

	/**
	 * Caso um hospede com esse email esteja cadastrado (presente no set
	 * de hospedes), ele eh removido.
	 * @param email Email do hospede
	 * @throws StringInvalidaException
	 */
	public void removeHospede(String email) throws StringInvalidaException {
		if (this.isCadastrado(email)) {
			Hospede hospede = this.buscaHospede(email);
			this.getHospedes().remove(hospede);
		}
	}

	// O monitor Gustavo me explicou que o ID eh o email e o metodo cadastra
	// retorna o ID que seria o email do hospede, entao modifiquei o metodo
	// cadastra para retorna o email e aqui ele vai pesquisar pelo email para
	// pegar informacoes sobre o hospede.

	/**
	 * Retorna informacoes relativas a um hospede (pesquisado atraves do email) 
	 * de acordo com o atributo recebido na entrada.
	 * @param email Email do hospede
	 * @param atributo Uma string representando qual informacao do hospede esta sendo requisitada
	 * @return A informacao requisitada
	 */
	public String getInfoHospede(String email, String atributo) {
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
	 * Atualiza um atributo do cadastro de um hospede, de acordo com uma nova informacao (valor)
	 * recebido na entrada.

	 * @param email Email do hospede que tera o cadastro alterado
	 * @param atributo O atributo que se deseja alterar (ex.: nome)
	 * @param valor Novo valor do atributo
	 */
	public void atualizaCadastro(String email, String atributo, String valor) {
		if (atributo.equalsIgnoreCase("nome")) {
			Hospede hospede = this.buscaHospede(email);
			hospede.setNome(valor);
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			Hospede hospede = this.buscaHospede(email);
			hospede.setAnoNascimento(valor);
		} else if (atributo.equalsIgnoreCase("email")) {
			Hospede hospede = this.buscaHospede(email);
			hospede.setEmail(valor);
		}
	}

	public static void main(String[] args) {
		args = new String[] { "hotel_gotemburgo.Hotel", "diretorio_testes/testes_uc1.txt" };
		EasyAccept.main(args);
	}

}
