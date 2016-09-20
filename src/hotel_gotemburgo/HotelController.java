package hotel_gotemburgo;

import hotel_gotemburgo.hospedagem.Estadia;
import hotel_gotemburgo.hospedagem.Hospede;
import hotel_gotemburgo.quartos.Quarto;
import hotel_gotemburgo.quartos.TipoDeQuarto;

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
	private Set<Quarto> quartos;

	/**
	 * O construtor do HotelController inicia o Set de hospedes, de quartos,
	 * define o ano atual e também qual a idade que se atinge a maioridade.
	 */
	public HotelController() {

		this.hospedes = new HashSet<Hospede>();
		this.quartos = new HashSet<Quarto>();
		this.ANO_ATUAL = 2016;
		this.MAIORIDADE = 18;
	}

	/**
	 * Retorna o set de hospedes do hotel
	 * 
	 * @return set de hospedes do hotel
	 */
	public Set<Hospede> getHospedes() {
		return hospedes;
	}

	public void setHospedes(Set<Hospede> hospedes) {
		this.hospedes = hospedes;
	}

	public Set<Quarto> getQuartos() {
		return quartos;
	}

	public void setQuartos(Set<Quarto> quartos) throws HotelException {
		if (quartos == null) {
			throw new ValorException("O conjunto de quartos nao pode ser nulo.");
		}
		this.quartos = quartos;
	}

	/**
	 * Esse metodo determina um padrao de data para nao ocorrer erros de data
	 * 
	 * @param data
	 * @return boolean
	 */
	private boolean validaData(String data) {
		String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}$";
		return data.matches(regex);
	}

	/**
	 * Esse metodo determina um padrao de email para nao ocorrer erros de email
	 * 
	 * @param email
	 * @return boolean
	 */
	private boolean validaEmail(String email) {
		String regex = "\\b[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}\\b";
		return email.matches(regex);
	}

	/**
	 * Esse metodo determina um padrao de nome para nao ocorrer erros +
	 * 
	 * @param nome
	 * @return boolean
	 */
	private boolean validaNome(String nome) {
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
	public boolean validaQuarto(String quarto) {
		String regex = "[[0-9]]*[[A-Z]]*";
		return quarto.matches(regex);
	}

	public boolean validaTipoQuarto(String valor) {
		if (!valor.equalsIgnoreCase("Simples")
				|| !valor.equalsIgnoreCase("Luxo")
				|| !valor.equalsIgnoreCase("Presidencial")) {
			return true;
		}
		return false;
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
	public Hospede criaHospede(String nome, String email, String dataNascimento)
			throws HotelException {
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

		Excecoes.checaEmail(email);

		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return hospede;
		}
		throw new ConsultaException(
				"Erro na consulta de hospede. Hospede de email " + email
						+ " nao foi cadastrado(a).");
	}

	/**
	 * Verifica se existe um hospede com determinado email no set de hospedes.
	 * 
	 * @param email
	 * @return True: caso exista o hospede, null: caso nao exista.
	 * @throws EmailInvalidoException
	 */
	public boolean isCadastrado(String email) throws HotelException {

		Excecoes.checaEmail(email);

		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return true;
		}
		return false;
	}

	/**
	 * Verifica se um hospede esta hospedado no hotel, pela quantidade de
	 * estadias que ele possui (0 = nao esta hospedado)
	 * 
	 * @param email
	 *            Email do hospede
	 * @return Boolean
	 * @throws Exception
	 */
	public boolean isHospedado(String email) throws HotelException {
		if (!isCadastrado(email))
			throw new CadastroException("Este hospede nao estah cadastrado.");
		Hospede hospede = buscaHospede(email);
		if (hospede.getQtdEstadias() == 0)
			return false;

		return true;
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
	public String cadastraHospede(String nome, String email,
			String dataNascimento) throws HotelException {

		Excecoes.checaCadastroNome(nome);
		Excecoes.checaCadastroEmail(email);
		Excecoes.checaCadastroDataNascimento(dataNascimento);

		if (!this.validaData(dataNascimento)) {
			throw new HotelException(
					"Erro no cadastro de Hospede. Formato de data invalido.");
		}
		if (!this.validaEmail(email)) {
			throw new HotelException(
					"Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		if (!this.validaNome(nome)) {
			throw new HotelException(
					"Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}

		String[] data = dataNascimento.split("/");
		int anoNascimento = Integer.parseInt(data[2]);
		int idade = this.ANO_ATUAL - anoNascimento;
		if (idade < this.MAIORIDADE) {
			throw new CadastroException(
					"Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
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
		if (!this.validaEmail(email)) {
			throw new HotelException(
					"Erro na remocao do Hospede. Formato de email invalido.");
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
	public String getInfoHospede(String email, String atributo)
			throws HotelException {

		Excecoes.checaEmail(email);
		Excecoes.checaAtributo(atributo);

		Hospede hospede = this.buscaHospede(email);

		switch (atributo.toUpperCase()) {
		case "NOME":
			return hospede.getNome();
		case "DATA DE NASCIMENTO":
			return hospede.getDataNascimento();
		case "EMAIL":
			return hospede.getEmail();
		default:
			return "erro";
		}
	}

	public String getInfoHospedagem(String email, String atributo)
			throws HotelException {

		final String HOSPEDAGEM_ATIVA = "Hospedagens ativas";
		final String QUARTO = "Quarto";
		final String TOTAL = "Total";

		if (!isCadastrado(email))
			throw new ConsultaException("Esse hospede nao existe");

		Hospede hospede = this.buscaHospede(email);

		if (!isHospedado(email))
			throw new ConsultaException(
					String.format(
							"Erro na consulta de hospedagem. Hospede %s nao esta hospedado(a).",
							hospede.getNome()));

		switch (atributo) {
		case HOSPEDAGEM_ATIVA:
			return String.format("%d", hospede.getQuantidadeDeEstadias());
		case QUARTO:
			return hospede.getEstadias();
		case TOTAL:
			return String.format("R$%.2f", hospede.getGastos());
		default:
			return "erro";
		}
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
	public void atualizaCadastro(String email, String atributo, String valor)
			throws HotelException {

		if (atributo.equalsIgnoreCase("nome")) {
			if (valor == null || valor.trim().isEmpty()) {
				throw new StringException(
						"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
			}
			if (!this.validaNome(valor)) {
				throw new HotelException(
						"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede esta invalido.");
			}
			this.buscaHospede(email).setNome(valor);

		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			if (valor == null || valor.trim().isEmpty()) {
				throw new StringException(
						"Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
			}
			if (!this.validaData(valor)) {
				throw new HotelException(
						"Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
			}

			String[] data = valor.split("/");
			int anoNascimento = Integer.parseInt(data[2]);
			int idade = this.ANO_ATUAL - anoNascimento;
			if (idade < this.MAIORIDADE) {
				throw new CadastroException(
						"Erro na atualizacao do cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
			}
			this.buscaHospede(email).setDataNascimento(valor);

		} else if (atributo.equalsIgnoreCase("email")) {
			if (valor == null || valor.trim().isEmpty()) {
				throw new StringException(
						"Erro na atualizacao do cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
			}
			if (!this.validaEmail(valor)) {
				throw new HotelException(
						"Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
			}
			this.buscaHospede(email).setEmail(valor);
		}
	}

	public Quarto criaQuartos(String idQuarto, TipoDeQuarto tipoQuarto)
			throws StringException {
		Quarto quarto = new Quarto(idQuarto, tipoQuarto);
		quartos.add(quarto);
		return quarto;
	}

	public TipoDeQuarto getTipo(String tipoQuarto) {
		if (tipoQuarto.equals("Presidencial")) {
			return TipoDeQuarto.PRESIDENCIAL;
		}
		if (tipoQuarto.equals("Simples")) {
			return TipoDeQuarto.SIMPLES;
		}
		if (tipoQuarto.equals("Luxo")) {
			return TipoDeQuarto.LUXO;
		}
		return null;
	}

	public boolean verificaOcupacao(String id) {
		for (Quarto quartosOcupados : this.getQuartos()) {
			if (quartosOcupados.getId().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}

	public void realizaCheckin(String email, int qntDias, String idQuarto,
			String tipoQuarto) throws HotelException {
		if (email == null || email.trim().isEmpty()) {
			throw new HotelException(
					"Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
		}
		if (!this.validaEmail(email)) {
			throw new HotelException(
					"Erro ao realizar checkin. Email do(a) hospede esta invalido.");
		}
		if (qntDias < 0) {
			throw new HotelException(
					"Erro ao realizar checkin. Quantidade de dias esta invalida.");
		}
		if (idQuarto.trim().isEmpty() || !this.validaQuarto(idQuarto)) {
			throw new HotelException(
					"Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");

		}
		if (!this.validaTipoQuarto(tipoQuarto)) {
			throw new HotelException(
					"Erro ao realizar checkin. Tipo de quarto invalido.");
		}

		Hospede hospede = this.buscaHospede(email);
		TipoDeQuarto tipo = this.getTipo(tipoQuarto);
		if (this.verificaOcupacao(idQuarto)) {
			throw new ConsultaException("Erro ao realizar checkin. Quarto "
					+ idQuarto + " ja esta ocupado.");
		}
		Quarto quarto = this.criaQuartos(idQuarto, tipo);
		Estadia estadia = new Estadia(quarto, qntDias);
		hospede.addEstadia(estadia);
	}

	public static void main(String[] args) {
		args = new String[] { "hotel_gotemburgo.HotelController",
				"diretorio_testes/testes_uc1.txt",
				"diretorio_testes/testes_uc1_exception.txt",
				"diretorio_testes/testes_uc2.txt",
				"diretorio_testes/testes_uc2_exception.txt" };
		EasyAccept.main(args);
	}

}