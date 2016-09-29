package hotel_gotemburgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import restaurante.RestauranteController;
import restaurante.comida.Refeicao;
import verificacao.excecoes.*;
import verificacao.validacao.*;
import hotel_gotemburgo.hospedagem.*;
import hotel_gotemburgo.quartos.*;
import hotel_gotemburgo.transacao.*;

/**
 * O HotelController representa uma entidade de gerenciamento, responsavel por
 * administrar as operacoes do sistema relacionadas aos quartos e a hospedagem.
 * 
 * Contem um set de Hospedes, utilizado para armazenamento desses hospedes no
 * sistema. Metodos sao utilizados para operar sobre esse set e realizar
 * operacoes, como busca, consulta e remocoes. Um HashMap eh iniciado para
 * associar os tipos de quarto do hotel a uma chave em string com o seu nome. Ha
 * tambem um Set de quartos ocupados, que registra quais quartos estao ocupados
 * no momento. O array de Transacoes armazena referencias do tipo Transacao para
 * registrar os checkouts do Hotel.
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
	private Set<Quarto> quartosOcupados;
	private HashMap<String, TipoDeQuarto> tiposQuartos;
	private ArrayList<Transacao> transacoes;
	private RestauranteController restaurante;

	public RestauranteController getRestaurante() {
		return restaurante;
	}

	/**
	 * O construtor do HotelController inicia o Set de hospedes, de quartos e de
	 * transacoes. Chama o metodo inicializaTiposDeQuarto para atribuir uma
	 * String a um tipo de quarto. Define o ano atual e também qual a idade que
	 * se atinge a maioridade.
	 */
	public HotelController() {

		this.hospedes = new HashSet<Hospede>();
		this.quartosOcupados = new HashSet<Quarto>();
		this.transacoes = new ArrayList<Transacao>();
		this.restaurante = new RestauranteController();

		this.inicializaTiposDeQuarto();

		this.ANO_ATUAL = 2016;
		this.MAIORIDADE = 18;

	}

	/**
	 * Esse metodo cria e retorna um objeto do tipo Hospede, com base nos
	 * parametros recebidos de entrada.
	 * 
	 * @param nome
	 * @param email
	 * @param anoNascimento
	 * @return O Hospede criado.
	 * @throws HotelGotemburgoException
	 */
	private Hospede criaHospede(String nome, String email, String dataNascimento) throws HotelGotemburgoException {
		return new Hospede(nome, email, dataNascimento);
	}

	/**
	 * Com base no email recebido de entrada, realiza uma busca no set de
	 * hospedes. Caso um dos hospedes possua esse email, o hospede em questao eh
	 * retornado. Caso nao exista um hospede com esse email, o retorno eh null.
	 * 
	 * @param email
	 * @return Um hospede, caso encontrado. Null, caso nao encontrado.
	 * @throws HotelGotemburgoException
	 */
	private Hospede buscaHospede(String email) throws HotelGotemburgoException {

		Excecoes.checaString(email, "O email do hospede nao pode ser nulo ou vazio.");

		for (Hospede hospede : this.hospedes) {
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
	 *            Email do hospede a ser verificado o cadastro
	 * @return true: caso exista o hospede/false: caso nao exista.
	 * @throws HotelGotemburgoException
	 */
	private boolean isCadastrado(String email) throws HotelGotemburgoException {

		Excecoes.checaString(email, "O email do hospede nao pode ser nulo ou vazio.");

		for (Hospede hospede : this.hospedes) {
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
	 * @return boolean
	 * @throws HotelGotemburgoException
	 */
	private boolean isHospedado(String email) throws HotelGotemburgoException {

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
	 * @throws HotelGotemburgoException
	 */
	public String cadastraHospede(String nome, String email, String dataNascimento) throws HotelGotemburgoException {

		Excecoes.checaString(nome, "Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		Excecoes.checaString(email, "Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		Excecoes.checaString(dataNascimento,
				"Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");

		Excecoes.checaFormatoNome(nome, "Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		Excecoes.checaFormatoEmail(email, "Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		Excecoes.checaFormatoData(dataNascimento, "Erro no cadastro de Hospede. Formato de data invalido.");

		Validacoes.validaData(dataNascimento);
		Validacoes.validaEmail(email);
		Validacoes.validaNome(nome);

		String[] data = dataNascimento.split("/");
		int anoNascimento = Integer.parseInt(data[2]);
		int idade = this.ANO_ATUAL - anoNascimento;
		if (idade < this.MAIORIDADE) {
			throw new CadastroException(
					"Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
		}

		Hospede hospede = this.criaHospede(nome, email, dataNascimento);
		this.hospedes.add(hospede);
		return email;
	}

	/**
	 * Caso um hospede com esse email esteja cadastrado (presente no set de
	 * hospedes), ele eh removido.
	 * 
	 * @param email
	 * @throws HotelGotemburgoException
	 */
	public void removeHospede(String email) throws HotelGotemburgoException {

		Excecoes.checaFormatoEmail(email, "Erro na remocao do Hospede. Formato de email invalido.");
		Hospede hospede = this.buscaHospede(email);

		this.hospedes.remove(hospede);
	}

	/**
	 * Retorna informacoes relativas a um hospede (pesquisado atraves do email)
	 * de acordo com o atributo recebido na entrada.
	 * 
	 * @param email
	 * @param atributo
	 * @return A informacao requisitada
	 * @throws HotelGotemburgoException
	 */
	public String getInfoHospede(String email, String atributo) throws HotelGotemburgoException {

		Excecoes.checaString(email, "O email do hospede nao pode ser nulo ou vazio.");
		Excecoes.checaString(email, "O atributo nao pode ser nulo ou vazio.");

		Hospede hospede = this.buscaHospede(email);

		switch (atributo.toUpperCase()) {
		case "NOME":
			return hospede.getNome();
		case "DATA DE NASCIMENTO":
			return hospede.getDataNascimento();
		case "EMAIL":
			return hospede.getEmail();
		default:
			throw new ConsultaException("Erro na consulta de hospede. Opcao invalida.");
		}
	}

	/**
	 * Retorna informacoes sobre um hospede que possua pelo menos uma estadia no
	 * Hotel. A informacao que sera retornada eh determinada pelo parametro
	 * atributo, podendo ser: "Hospedagem ativa" - retorna a quantidade de
	 * estadias do hospede; "Quarto" - retorna uma string com o id dos quartos
	 * de suas estadias; "Total" - retorna os gastos com estadia do hospede
	 * 
	 * @param email
	 *            Email do hospede
	 * @param atributo
	 *            Hospedagem ativa, Quarto ou Total
	 * @return A informacao desejada
	 * @throws HotelGotemburgoException
	 */
	public String getInfoHospedagem(String email, String atributo) throws HotelGotemburgoException {

		Excecoes.checaString(email, "Erro ao checar hospedagem ativa. Email do(a) hospede nao pode ser vazio.");

		if (!isCadastrado(email))
			throw new ConsultaException("Erro ao checar hospedagem ativa. Email do(a) hospede esta invalido.");

		Hospede hospede = this.buscaHospede(email);
		if (!isHospedado(email))
			throw new ConsultaException(String
					.format("Erro na consulta de hospedagem. Hospede %s nao esta hospedado(a).", hospede.getNome()));

		switch (atributo.toUpperCase()) {

		case "HOSPEDAGENS ATIVAS":
			return String.format("%d", hospede.getQtdEstadias());
		case "QUARTO":
			return hospede.getRepresentaEstadias();
		case "TOTAL":
			return String.format("R$%.2f", hospede.getGastosTotal());
		default:
			throw new ConsultaException("Erro na consulta de hospedagem. Opcao invalida.");
		}
	}

	/**
	 * Atualiza um atributo do cadastro de um hospede, de acordo com uma nova
	 * informacao (valor) recebido na entrada.
	 * 
	 * @param email
	 * @param atributo
	 *            Uma string representando qual atributo deseja-se alterar
	 * @param novoValor
	 *            A nova informacao
	 * @throws HotelGotemburgoException
	 */
	public void atualizaCadastro(String email, String atributo, String novoValor) throws HotelGotemburgoException {
		if (atributo.equalsIgnoreCase("nome")) {

			Excecoes.checaString(novoValor,
					"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");

			if (!Validacoes.validaNome(novoValor)) {
				throw new HotelGotemburgoException(
						"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede esta invalido.");
			}
			this.buscaHospede(email).setNome(novoValor);
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {

			Excecoes.checaString(novoValor,
					"Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");

			if (!Validacoes.validaData(novoValor)) {
				throw new HotelGotemburgoException(
						"Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
			}

			String[] data = novoValor.split("/");
			int anoNascimento = Integer.parseInt(data[2]);
			int idade = this.ANO_ATUAL - anoNascimento;
			if (idade < this.MAIORIDADE) {
				throw new CadastroException(
						"Erro na atualizacao do cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
			}
			this.buscaHospede(email).setDataNascimento(novoValor);

		} else if (atributo.equalsIgnoreCase("email")) {

			Excecoes.checaString(novoValor,
					"Erro na atualizacao do cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");

			if (!Validacoes.validaEmail(novoValor)) {
				throw new HotelGotemburgoException(
						"Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
			}
			this.buscaHospede(email).setEmail(novoValor);
		}
	}

	/**
	 * Metodo responsavel por criar um objeto quarto e adiciona-lo no set de
	 * quartos do hotel. O quarto criado eh retornado.
	 * 
	 * @param idQuarto
	 *            ID (uma string unica representando o quarto)
	 * @param tipoQuarto
	 *            (O tipo do quarto, podendo ser "Simples", "Luxo" ou
	 *            "Presidencial".
	 * @return O Quarto que foi criado
	 * @throws HotelGotemburgoException
	 */
	private Quarto criaQuartos(String idQuarto, TipoDeQuarto tipoQuarto) throws HotelGotemburgoException {
		return new Quarto(idQuarto, tipoQuarto);
	}

	/**
	 * Metodo utilizado no construtor para iniciar o mapa que associa uma String
	 * que representa um Enum de TipoDeQuarto, a uma constante desse Enum. Ex:
	 * key- "SIMPLES" value- TipoDeQuarto.SIMPLES
	 */
	private void inicializaTiposDeQuarto() {

		this.tiposQuartos = new HashMap<String, TipoDeQuarto>();
		tiposQuartos.put("SIMPLES", TipoDeQuarto.SIMPLES);
		tiposQuartos.put("LUXO", TipoDeQuarto.LUXO);
		tiposQuartos.put("PRESIDENCIAL", TipoDeQuarto.PRESIDENCIAL);

	}

	/**
	 * Consulta o mapa de tipos e retorna o valor (constante do Enum)
	 * correspondente a string tipoQuarto.
	 * 
	 * @param tipoQuarto
	 *            String representando o tipo do quarto (chave do mapa
	 *            tiposQuartos)
	 * @return Constante do Enum TipoDeQuarto
	 */
	private TipoDeQuarto getTipo(String tipoQuarto) {
		return tiposQuartos.get(tipoQuarto.toUpperCase());
	}

	/**
	 * Metodo utilizado para garantir que uma string que supostamente representa
	 * um Tipo de Quarto esta de acordo com o esperado.
	 * 
	 * @param tipo
	 *            String representando o tipo de quarto
	 * @return boolean
	 */
	private boolean verificaTipoQuarto(String tipo) {
		return this.tiposQuartos.containsKey(tipo);
	}

	/**
	 * Metodo utilizado para verificar se um determinado quarto - que sera
	 * buscado atraves do ID - esta ou nao ocupado.
	 * 
	 * @param id
	 *            ID do quarto a ser verificado
	 * @return boolean
	 */
	private boolean verificaOcupacao(String id) {
		for (Quarto quartosOcupados : this.quartosOcupados) {
			if (quartosOcupados.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo responsavel por buscar atraves do ide retornar, caso exista, um
	 * quarto do hotel que ja esteja ocupado.
	 * 
	 * @param idQuarto
	 * @return um quarto ocupado
	 * @throws HotelGotemburgoException
	 * @throws Exception
	 */
	private Quarto buscaQuartoOcupado(String idQuarto) throws HotelGotemburgoException {
		Excecoes.checaString(idQuarto, "O id do quarto nao pode ser nulo ou vazio.");

		for (Quarto quarto : this.quartosOcupados) {
			if (quarto.getId().equalsIgnoreCase(idQuarto))
				return quarto;
		}
		throw new ConsultaException("Quarto nao encontrado.");
	}

	/**
	 * Esse metodo eh responsavel por realizar o checkin de um hospede no Hotel.
	 * O hospede precisa previamente estar cadastrado no sistema, entao ele sera
	 * procurado. Caso esteja cadastrado, eh adicionada uma Estadia a esse
	 * hospede. A Estadia eh composta de um quarto (que eh criado com base nos
	 * atributos recebidos como parametro) e uma quantidade de dias (tambem
	 * recebida como parametro).
	 * 
	 * @param email
	 *            Email do hospede
	 * @param qntDias
	 *            Quantidade de dias hospedado na estadia
	 * @param idQuarto
	 *            ID do quarto da estadia
	 * @param tipoQuarto
	 *            Tipo do quarto da estadia
	 * @throws HotelGotemburgoException
	 */
	public String realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto)
			throws HotelGotemburgoException {

		Excecoes.checaString(email, "Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
		Excecoes.checaInt(qntDias, "Erro ao realizar checkin. Quantidade de dias esta invalida.");
		Excecoes.checaString(idQuarto,
				"Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");

		if (!Validacoes.validaEmail(email))
			throw new HotelGotemburgoException("Erro ao realizar checkin. Email do(a) hospede esta invalido.");

		if (!Validacoes.validaQuarto(idQuarto))
			throw new HotelGotemburgoException(
					"Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");

		if (!isCadastrado(email))
			throw new ConsultaException(
					"Erro ao realizar checkin. Hospede de email " + email + " nao foi cadastrado(a).");

		Hospede hospede = this.buscaHospede(email);

		if (!verificaTipoQuarto(tipoQuarto.toUpperCase()))
			throw new VerificacaoException("Erro ao realizar checkin. Tipo de quarto invalido.");

		TipoDeQuarto tipo = this.getTipo(tipoQuarto);

		if (this.verificaOcupacao(idQuarto))
			throw new ConsultaException("Erro ao realizar checkin. Quarto " + idQuarto + " ja esta ocupado.");

		Quarto quarto = this.criaQuartos(idQuarto, tipo);
		Estadia estadia = new Estadia(quarto, qntDias);
		hospede.addEstadia(estadia);
		this.quartosOcupados.add(quarto);
		return email;
	}

	/**
	 * Calcula o valor da soma de todas as transacoes realizadas no hotel.
	 * 
	 * @return valor da soma das transacoes do hotel,
	 */
	private double getValorTotalTransacoes() {

		double valorTotal = 0.0;

		for (Transacao transacao : transacoes) {
			valorTotal += transacao.getValor();
		}
		return valorTotal;
	}

	/**
	 * Metodo responsavel por realizar o checkout de um hospede no hotel,
	 * removendo a estadia no quarto do qual ele esta saindo, retirando esse
	 * quarto do set de quartos ocupados. Eh criada uma Transacao para registrar
	 * essa operacao e em seguida essa transacao eh registrada no array de
	 * transacoes. Tambem eh calculado e retornado o valor total gasto por esse
	 * hospede no Hotel.
	 * 
	 * @param email
	 *            Email do hospede
	 * @param idQuarto
	 *            ID do quarto de onde o hospede esta saindo
	 * @return O valor total gasto por esse hospede no Hotel
	 * @throws HotelGotemburgoException
	 */
	public String realizaCheckout(String email, String idQuarto) throws HotelGotemburgoException {

		Excecoes.checaString(email, "Erro ao realizar checkout. Email do(a) hospede nao pode ser vazio.");
		Excecoes.checaFormatoEmail(email, "Erro ao realizar checkout. Email do(a) hospede esta invalido.");
		Excecoes.checaString(idQuarto, "Erro ao realizar checkout. O Id do quarto nao pode ser nulo ou vazio.");

		if (!Validacoes.validaQuarto(idQuarto))
			throw new ValidacaoException(
					"Erro ao realizar checkout. ID do quarto invalido, use apenas numeros ou letras.");

		Hospede hospedeDeSaida = this.buscaHospede(email);
		double gastosEstadia = hospedeDeSaida.getValorEstadia(idQuarto);

		Transacao transacao = new Transacao(hospedeDeSaida.getNome(), gastosEstadia);

		this.transacoes.add(transacao);
		Quarto quarto = this.buscaQuartoOcupado(idQuarto);
		this.quartosOcupados.remove(quarto);
		hospedeDeSaida.removeEstadia(idQuarto);

		return String.format("R$%.2f", gastosEstadia);

	}

	/**
	 * Consulta o array de hospedes que realizaram checkout e retorna uma
	 * informacao, referente ao atributo recebido.
	 * 
	 * @param atributo
	 *            Representa a informacao desejada
	 * @return A informacao desejada
	 * @throws HotelGotemburgoException
	 */
	public String consultaTransacoes(String atributo) throws HotelGotemburgoException {

		switch (atributo.toUpperCase()) {
		case "QUANTIDADE":
			return String.format("%d", this.transacoes.size());
		case "TOTAL":
			return String.format("R$%.2f", this.getValorTotalTransacoes());
		case "NOME":
			String nomes = "";
			for (Transacao hospede : this.transacoes) {
				nomes += ";" + hospede.getNomeHospede();
			}
			return nomes.replaceFirst(";", "");

		default:
			throw new ConsultaException("Erro na consulta de transacoes. Opcao invalida.");
		}
	}

	/**
	 * Sobrecarga de metodo, que agora recebe um indice referente a posicao de
	 * um Hospede especifico no array de transacoes. As informacoes solicitadas
	 * sao agora "nome" e "total", e se referem apenas ao hospede em questao.
	 * 
	 * @param atributo
	 *            Representa a informacao desejada
	 * @param indice
	 *            Posicao do hospede no array de transacoes
	 * @return A informacao solicitada
	 * @throws HotelGotemburgoException
	 */
	public String consultaTransacoes(String atributo, int indice) throws HotelGotemburgoException {

		if (indice < 0)
			throw new ValidacaoException("Erro na consulta de transacoes. Indice invalido.");

		switch (atributo.toUpperCase()) {
		case "TOTAL":
			return String.format("R$%.2f", this.transacoes.get(indice).getValor());
		case "NOME":
			return this.transacoes.get(indice).getNomeHospede();
		case "DETALHES":

		default:
			throw new ConsultaException("Erro na consulta de transacoes. Opcao invalida.");
		}
	}

	public String realizaPedido(String email, String item) throws HotelGotemburgoException {
		Excecoes.checaString(email, "Erro ao realizar pedido. Email do(a) hospede nao pode ser vazio.");
		Excecoes.checaString(item, "Erro ao realizar pedido. Item nao pode ser nulo ou vazio.");

		if (this.isHospedado(email))
			throw new ConsultaException("Erro ao realizar pedido. Cliente nao hospedado.");

		Hospede hospede = this.buscaHospede(email);

		double valorItem = 0.0;
		for (Refeicao refeicao : this.restaurante.getCardapio()) {
			if (refeicao.getNome().equalsIgnoreCase(item)) {
				valorItem += refeicao.getPreco();
			}
		}
		Transacao transacao = new Transacao(hospede.getNome(), valorItem);
		this.transacoes.add(transacao);
		return String.format("R$%.2f", valorItem);
	}
}
