package hotel_gotemburgo;

import java.util.HashSet;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.*;
import quartos.Quarto;
import quartos.TipoDeQuarto;

/**
 * O Hotel representa uma entidade de gerenciamento. Ele contem um set de
 * Hospedes, utilizado para armazenamento desses hospedes no sistema. Metodos
 * sao utilizados para operar sobre esse set e realizar operacoes, como busca,
 * consulta e remocoes.
 * 
 * @since 12 de Setembro de 2016
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934
 *         <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */
public class HotelController {

	private final int ANO_ATUAL;
	private final int MAIORIDADE;
	private Set<Hospede> hospedes;

	public HotelController() {
		this.hospedes = new HashSet<Hospede>();
		this.ANO_ATUAL = 2016;
		this.MAIORIDADE = 18;
	}

	public Set<Hospede> getHospedes() {
		return hospedes;
	}

	/**
	 * Esse metodo determina um padrao de data para nao ocorrer erros
	 * 
	 * @param data
	 * @return boolean
	 */
	private boolean validaData(String data) {
		String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}$";
		return data.matches(regex);
	}

	/**
	 * Esse metodo determina um padrao de email para nao ocorrer erros
	 * 
	 * @param email
	 * @return boolean
	 */

	private boolean validaEmail(String email) {
		String regex = "\\b[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}\\b";
		return email.matches(regex);

	}

	/**
	 * + * Esse metodo determina um padrao de nome para nao ocorrer erros +
	 * * @param nome + * @return boolean +
	 */

	private boolean validaNome(String nome) {
		String regex = "[A-Z][a-z]+[[ ][A-Z][a-z]+]*";
		return nome.matches(regex);
	}

	/**
	 * Esse metodo cria e retorna um objeto do tipo Hospede, com base nos
	 * parametros recebidos de entrada.
	 * 
	 * @param nome
	 * @param email
	 * @param anoNascimento
	 * @return O Hospede criado.
	 * @throws StringException
	 */
	public Hospede criaHospede(String nome, String email, String dataNascimento) throws HotelException {
		return new Hospede(nome, email, dataNascimento);
	}

	/**
	 * Com base no email recebido de entrada, realiza uma busca no set de
	 * hospedes. Caso um dos hospedes possua esse email, o hospede em questao eh
	 * retornado. Caso nao exista um hospede com esse email, o retorno eh null.
	 * 
	 * @param email
	 * @return Um hospede, caso encontrado. Null, caso nao encontrado.
	 * @throws EmailInvalidoException
	 */
	public Hospede buscaHospede(String email) throws HotelException {

		if (email == null || email.trim().isEmpty())
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");

		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return hospede;
		}
		throw new ConsultaException(
				"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
	}

	/**
	 * Verifica se existe um hospede com determinado email no set de hospedes.
	 * 
	 * @param email
	 * @return True: caso exista o hospede, null: caso nao exista.
	 * @throws EmailInvalidoException
	 */
	public boolean isCadastrado(String email) throws HotelException {

		if (email == null || email.trim().isEmpty())
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");

		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return true;
		}
		return false;
	}

	/**
	 * Recebe atributos de criacao de um hospede como entrada. Realiza uma
	 * verificaco para saber se ja existe um hospede com o email recebido. Caso
	 * nao exista, cria um novo objeto Hospede com os valores recebidos como
	 * parametros, em seguida adiciona-o ao set de hospedes.
	 * 
	 * @param nome
	 * @param email
	 * @param ano
	 * @return O email do hospede recem-cadastrado
	 * @throws Exception
	 * @throws StringException
	 */
	public String cadastraHospede(String nome, String email, String dataNascimento) throws HotelException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new HotelException("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
		if (email == null || email.trim().isEmpty()) {
			throw new HotelException("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		}
		if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
			throw new HotelException(
					"Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}

		if (!this.validaData(dataNascimento)) {
			throw new HotelException("Erro no cadastro de Hospede. Formato de data invalido.");
		}
		if (!this.validaEmail(email)) {
			throw new HotelException("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		if (!this.validaNome(nome)) {
			throw new HotelException("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}
		Hospede hospede = this.criaHospede(nome, email, dataNascimento);
		this.getHospedes().add(hospede);
		return email;
	}

	/**
	 * Caso um hospede com esse email esteja cadastrado (presente no set de
	 * hospedes), ele eh removido.
	 * 
	 * @param email
	 * @throws StringException
	 */
	public void removeHospede(String email) throws HotelException {
		if (email == null || email.trim().isEmpty())
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");

		if (!this.isCadastrado(email))
			throw new ConsultaException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");

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
		if (email == null || email.trim().isEmpty()) {
			throw new StringException("Email nao pode ser nulo ou vazio.");
		}
		if (atributo == null || atributo.trim().isEmpty())
			throw new StringException("O atributo nao pode ser nulo ou vazio.");

		if (atributo.equalsIgnoreCase("nome")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getNome();
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getDataNascimento().toString();
		} else if (atributo.equalsIgnoreCase("email")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getEmail();
		}
		return null;
	}

	// Esse metodo eh oq pede nos teste, mas nao esta correto ainda. by: Mateus

	public String getInfoHospedagem(String email, String atributo) throws HotelException {
		if (email == null || email.trim().isEmpty()) {
			throw new StringException("Email nao pode ser nulo ou vazio.");
		}
		if (atributo == null || atributo.trim().isEmpty())
			throw new StringException("O atributo nao pode ser nulo ou vazio.");

		if (atributo.equalsIgnoreCase("hospedagem ativas")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getEstadias().toString();
		}
		return null;
	}

	/**
	 * Atualiza um atributo do cadastro de um hospede, de acordo com uma nova
	 * informacao (valor) recebido na entrada.
	 * 
	 * @param email
	 * @param atributo
	 *            Uma string representando qual atributo deseja-se alterar
	 * @param valor
	 *            A nova informacao
	 * @throws HotelException
	 */
	public void atualizaCadastro(String email, String atributo, String valor) throws HotelException {
		if (email == null || email.trim().isEmpty()) {
			throw new StringException("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		if (atributo == null || atributo.trim().isEmpty() && atributo.equals("Nome")) {
			throw new StringException("O atributo nao pode ser nulo ou vazio.");
		}

		if (valor == null || valor.trim().isEmpty()) {
			throw new StringException("O valor nao pode ser nulo ou vazio.");
		}

		if (atributo.equalsIgnoreCase("nome")) {
			this.buscaHospede(email).setNome(valor);
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			this.buscaHospede(email).setDataNascimento(valor);
		} else if (atributo.equalsIgnoreCase("email")) {
			this.buscaHospede(email).setEmail(valor);
		}
	}

	public void realizaCheckin(String email, int qntDias, String quarto, String tipoQuarto) throws HotelException {
		// Falta implementar o metodo
	}

	public static void main(String[] args) {
		args = new String[] { "hotel_gotemburgo.HotelController", "diretorio_testes/testes_uc1.txt",
				"diretorio_testes/testes_uc2.txt" };
		EasyAccept.main(args);
	}

}