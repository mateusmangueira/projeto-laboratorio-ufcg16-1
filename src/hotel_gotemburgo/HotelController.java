package hotel_gotemburgo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import restaurante.RestauranteController;
import restaurante.comida.Comida;
import verificacao.excecoes.*;
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
 * Alem disso, o HotelController possui um outro controller, RestauranteController,
 * que eh responsavel pelas operacoes no sistema referentes ao sistema de venda
 * de alimentacao no Hotel.
 * 
 * @since 12 de Setembro de 2016
 * @see Hospede.java, Transacao.java, TipoDeQuarto.java
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */
public class HotelController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<Hospede> hospedes;
	private Set<String> quartosOcupados;
	private HashMap<String, TipoDeQuarto> tiposQuartos;
	private ArrayList<Transacao> transacoes;
	private RestauranteController restaurante;
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final int ANO_ATUAL = 2016;

	/**
	 * O construtor do HotelController inicia o Set de hospedes, de quartos e de
	 * transacoes. Chama o metodo inicializaTiposDeQuarto para atribuir uma
	 * String a um tipo de quarto. Define o ano atual e também qual a idade que
	 * se atinge a maioridade.
	 */
	public HotelController() {

		this.hospedes = new HashSet<Hospede>();
		this.quartosOcupados = new HashSet<String>();
		this.transacoes = new ArrayList<Transacao>();
		this.restaurante = new RestauranteController();
		this.inicializaTiposDeQuarto();
	}
	
	public void iniciaSistema() {
	}

	public void fechaSistema() {
	}

	/**
	 * Recebe atributos de criacao de um hospede como entrada. Realiza uma
	 * verificaco para saber se ja existe um hospede com o email recebido. Caso
	 * nao exista, cria um novo objeto Hospede com os valores recebidos como
	 * parametros, em seguida adiciona-o ao set de hospedes.
	 * 
	 * @param nome Nome do hospede realizando cadastro
	 * @param email Email do hospede realizando cadastro
	 * @param dataNascimento Data de nascimento do hospede realizando cadastro
	 * @return O email do hospede recem-cadastrado
	 * @throws HotelGotemburgoException Caso: 1)sejam verificados erros no conteudo
	 * ou formato das strings passadas como parametro; ou 2)hospede menor de idade; 
	 */
	public String cadastraHospede(String nome, String email, String dataNascimento) throws HotelGotemburgoException {

		Excecoes.checaString(nome, "Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		Excecoes.checaString(email, "Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		Excecoes.checaString(dataNascimento,
				"Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");

		Excecoes.checaFormatoNome(nome, "Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		Excecoes.checaFormatoEmail(email, "Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		Excecoes.checaFormatoData(dataNascimento, "Erro no cadastro de Hospede. Formato de data invalido.");

		String[] data = dataNascimento.split("/");
		int anoNascimento = Integer.parseInt(data[2]);
		int idade = HotelController.ANO_ATUAL - anoNascimento;
		Excecoes.checaIdade(idade, "Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");

		Hospede hospede = this.criaHospede(nome, email, dataNascimento);
		this.hospedes.add(hospede);
		return email;
	}

	/**
	 * Caso um hospede com o email passado como parametro esteja cadastrado no Hotel 
	 * ele eh removido.
	 * 
	 * @param email Email do hospede que deseja-se remover
	 * @throws HotelGotemburgoException No caso do conteudo do parametro email
	 * ser invalido
	 */
	public boolean removeHospede(String email) throws HotelGotemburgoException {
		Excecoes.checaFormatoEmail(email, "Erro na remocao do Hospede. Formato de email invalido.");
		Hospede hospede = this.buscaHospede(email);
		return this.hospedes.remove(hospede);
	}

	/**
	 * Retorna informacoes relativas a um hospede (pesquisado atraves do email)
	 * de acordo com o atributo recebido na entrada.
	 * 
	 * @param email Email do hospede do qual deseja-se consultar informacoes
	 * @param atributo Representa qual informacao a respeito do hospede 
	 * sera consultada e retornada, podendo ser seu/sua:
	 * 1)nome 2)data de nascimento 3)email 4)pontos
	 * @return Uma string com informacao requisitada
	 * @throws HotelGotemburgoException Em caso de parametros invalidos ou opcao invalida
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
		case "PONTOS":
			return String.format("%d", hospede.getPontos());
		default:
			throw new ConsultaException("Erro na consulta de hospede. Opcao invalida.");
		}
	}

	/**
	 * Retorna informacoes sobre um hospede que possua pelo menos uma estadia no
	 * Hotel. A informacao que sera retornada eh determinada pelo parametro
	 * atributo.
	 * 
	 * @param email Email do hospede que tera suas informacoes de hospedagem
	 * consultadas
	 * @param atributo Definira qual opcao sera consultada, podendo ser:
	 * 1) "Hospedagem ativa" - retorna a quantidade de estadias do hospede; 
	 * 2)"Quarto" - retorna uma string com o id dos quartos de suas estadias; 
	 * 3)"Total" - retorna os gastos com estadia do hospede
	 * @return Uma String representando a informacao desejada
	 * @throws HotelGotemburgoException 1)Em caso de parametros invalidos;
	 * 2) se o hospede nao estiver cadastrado ou hospedado no Hotel;
	 * 3) se a opcao escolhida for invalida
	 */
	public String getInfoHospedagem(String email, String atributo) throws HotelGotemburgoException {

		Excecoes.checaString(email, "Erro ao checar hospedagem ativa. Email do(a) hospede nao pode ser vazio.");
		this.checaCadastro(email, "Erro ao checar hospedagem ativa. Email do(a) hospede esta invalido.");

		Hospede hospede = this.buscaHospede(email);
		this.checaHospedagem(email, String.format("Erro na consulta de hospedagem. Hospede %s nao esta "
				+ "hospedado(a).", hospede.getNome()));

		switch (atributo.toUpperCase()) 
		{
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
	 * @param email Email do hospede que tera alguma informacao alterada
	 * @param atributo Uma string representando qual atributo sera alterado,
	 * podendo ser "nome, "data de nascimento" ou "email
	 * @param novoValor O atributo atualizado que ira substituir o anterior
	 * @throws HotelGotemburgoException Em caso de 1)parametros invalidos;
	 * 2)hospede menor de idade
	 */
	public void atualizaCadastro(String email, String atributo, String novoValor) throws HotelGotemburgoException {
		
		if (atributo.equalsIgnoreCase("nome")) 
		{
			Excecoes.checaString(novoValor, "Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede "
					+ "nao pode ser vazio.");
			Excecoes.checaFormatoNome(novoValor, "Erro na atualizacao do cadastro de Hospede. Nome do(a) "
					+ "hospede esta invalido.");
			
			this.buscaHospede(email).setNome(novoValor);
		}
		else if (atributo.equalsIgnoreCase("data de nascimento")) 
		{
			Excecoes.checaString(novoValor,
					"Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode "
					+ "ser vazio.");
			Excecoes.checaFormatoData(novoValor, "Erro na atualizacao do cadastro de Hospede. Formato de "
					+ "data invalido.");

			String[] data = novoValor.split("/");
			int anoNascimento = Integer.parseInt(data[2]);
			
			int idade = HotelController.ANO_ATUAL - anoNascimento;
			Excecoes.checaIdade(idade, "Erro na atualizacao do cadastro de Hospede. A idade do(a) hospede "
					+ "deve ser maior que 18 anos.");
			
			this.buscaHospede(email).setDataNascimento(novoValor);
		}
		else if (atributo.equalsIgnoreCase("email")) 
		{
			Excecoes.checaString(novoValor, "Erro na atualizacao do cadastro de Hospede. Email do(a) hospede "
					+ "nao pode ser vazio.");

			Excecoes.checaFormatoEmail(novoValor, "Erro na atualizacao do cadastro de Hospede. Email do(a) "
						+ "hospede esta invalido.");
			this.buscaHospede(email).setEmail(novoValor);
		}
		
	}

	/**
	 * Esse metodo eh responsavel por realizar o checkin de um hospede no Hotel.
	 * O hospede precisa previamente estar cadastrado no sistema, entao ele sera
	 * procurado. Caso esteja cadastrado, eh adicionada uma Estadia a esse
	 * hospede. A Estadia eh composta de um quarto (que eh criado com base nos
	 * atributos recebidos como parametro) e uma quantidade de dias (tambem
	 * recebida como parametro).
	 * 
	 * @param email Email do hospede
	 * @param qntDias Quantidade de dias que o Hospede esta na estadia
	 * @param idQuarto ID do quarto da estadia
	 * @param tipoQuarto Tipo do quarto da estadia
	 * @throws HotelGotemburgoException Em caso de 1) atributos invalidos;
	 * 2) hospede nao cadastrado; 3)quarto ja ocupado;
	 */
	public void realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto)
			throws HotelGotemburgoException {

		Excecoes.checaString(email, "Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
		Excecoes.checaInt(qntDias, "Erro ao realizar checkin. Quantidade de dias esta invalida.");
		Excecoes.checaString(idQuarto, "Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");
		Excecoes.checaFormatoEmail(email, "Erro ao realizar checkin. Email do(a) hospede esta invalido.");
		Excecoes.checaFormatoIdQuarto(idQuarto, "Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");

		this.checaCadastro(email, "Erro ao realizar checkin. Hospede de email " + email + " nao foi cadastrado(a).");
		this.checaTipoQuarto(tipoQuarto.toUpperCase(), "Erro ao realizar checkin. Tipo de quarto invalido.");
		this.checaOcupacao(idQuarto, "Erro ao realizar checkin. Quarto " + idQuarto + " ja esta ocupado.");

		Hospede hospede = this.buscaHospede(email);
		TipoDeQuarto tipo = this.getTipoQuarto(tipoQuarto);
		Estadia estadia = new Estadia(idQuarto, tipo, qntDias);
		hospede.addEstadia(estadia);
		this.quartosOcupados.add(idQuarto);
	}

	/**
	 * Metodo responsavel por realizar o checkout de um hospede no hotel,
	 * removendo a estadia no quarto do qual ele esta saindo, retirando esse
	 * quarto do set de quartos ocupados. Eh criada uma Transacao para registrar
	 * essa operacao e em seguida essa transacao eh registrada no array de
	 * transacoes. Tambem eh calculado e retornado o valor total gasto por esse
	 * hospede no Hotel.
	 * 
	 * @param email Email do hospede
	 * @param idQuarto ID do quarto de onde o hospede esta saindo
	 * @return O valor total gasto por esse hospede no Hotel
	 * @throws HotelGotemburgoException
	 */
	public String realizaCheckout(String email, String idQuarto) throws HotelGotemburgoException {

		Excecoes.checaString(email, "Erro ao realizar checkout. Email do(a) hospede nao pode ser vazio.");
		Excecoes.checaFormatoEmail(email, "Erro ao realizar checkout. Email do(a) hospede esta invalido.");
		Excecoes.checaString(idQuarto, "Erro ao realizar checkout. O Id do quarto nao pode ser nulo ou vazio.");
		Excecoes.checaFormatoIdQuarto(idQuarto, "Erro ao realizar checkout. ID do quarto invalido, use apenas numeros ou letras.");

		Hospede hospedeDeSaida = this.buscaHospede(email);
		double gastosEstadia = hospedeDeSaida.getValorEstadia(idQuarto);
		double valorComDesconto = hospedeDeSaida.aplicarDesconto(gastosEstadia);
		Transacao transacao = new Transacao(hospedeDeSaida.getNome(), valorComDesconto, idQuarto);

		this.transacoes.add(transacao);
		if (this.isQuartoOcupado(idQuarto)) {
			this.quartosOcupados.remove(idQuarto);
		}
		
		hospedeDeSaida.removeEstadia(idQuarto);
		int recompensaPorGasto = hospedeDeSaida.adicionarPontos(gastosEstadia);
		hospedeDeSaida.setPontos(hospedeDeSaida.getPontos() + recompensaPorGasto);
		return String.format("R$%.2f", valorComDesconto);
	}

	/**
	 * Consulta o array de hospedes que realizaram checkout e retorna uma
	 * informacao, referente ao atributo recebido.
	 * 
	 * @param atributo Representa a informacao desejada sobre as transacoes
	 * registradas no sistema, podendo ser:
	 * "quantidade" - a quantidade de transacoes ;
	 * "total" - o valor total das transacoes;
	 * "nome" - a lista do nome dos hospedes que realizaram transacoes.
	 * @return A informacao solicitada
	 * @throws HotelGotemburgoException Em caso de opcao invalida
	 */
	public String consultaTransacoes(String atributo) throws HotelGotemburgoException {

		switch (atributo.toUpperCase()) 
		{
		case "QUANTIDADE":
			return String.format("%d", this.transacoes.size());

		case "TOTAL":
			return String.format("R$%.2f", this.getValorTotalTransacoes());

		case "NOME":
			String nomes = "";
			for (Transacao transacao : this.transacoes)
				nomes += ";" + transacao.getNomeHospede();
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
	 * @param atributo Representa a informacao desejada
	 * @param indice Posicao do hospede no array de transacoes
	 * @return A informacao solicitada
	 * @throws HotelGotemburgoException Caso o atributo nao corresponda a uma
	 * opcao valida
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
			return this.transacoes.get(indice).getDescricao();

		default:
			throw new ConsultaException("Erro na consulta de transacoes. Opcao invalida.");
		}
	}

	/**
	 * Esse metodo eh responsavel por receber de um hospede um pedido de refeicao. Uma transacao
	 * eh criada para registrar esse pedido e pontos sao adicionados ao cartao fidelidade do hospede
	 * de acordo com o valor dessa compra. Essa funcionalidade eh responsabilidade do HotelController
	 * pois utilizamos a logica na qual o Hotel eh responsavel por intermediar o pedido entre o 
	 * Hospede e o Restaurante, ja que ele conhece ambos.
	 * 
	 * @param email Email do hospede 
	 * @param item O item que foi pedido
	 * @return Uma string representando o valor desse pedido
	 * @throws HotelGotemburgoException Em caso de atributos invalidos
	 */
	public String realizaPedido(String email, String item) throws HotelGotemburgoException {

		Excecoes.checaString(email, "Erro ao realizar pedido. Email do(a) hospede nao pode ser vazio.");
		Excecoes.checaString(item, "Erro ao realizar pedido. Item nao pode ser nulo ou vazio.");

		Hospede hospede = this.buscaHospede(email);
		Comida refeicao = this.restaurante.buscaRefeicao(item);
		double precoRefeicao = refeicao.getPreco();
		
		double valorComDesconto = hospede.aplicarDesconto(precoRefeicao);
		Transacao transacao = new Transacao(hospede.getNome(), valorComDesconto, item);
		this.transacoes.add(transacao);

		int recompensaPorGasto = hospede.adicionarPontos(precoRefeicao);
		hospede.setPontos(hospede.getPontos() + recompensaPorGasto);
		return String.format("R$%.2f", valorComDesconto);

	}

	/**
	 * Metodo responsavel por converter uma dada quantia de pontos de um hospede em dinheiro,
	 * pesquisando o hospede que deseja realizar a operacao e delegando o metodo convertePontos
	 * da classe Hospede.
	 * 
	 * @param email Email do hospede que sera buscado para realizar a conversao
	 * @param qntPontos Quantidade de pontos que sera convertida
	 * @return A String de retorno do convertePontos da classe Hospede
	 * @throws HotelGotemburgoException Lanca possiveis excecoes recebidas da classe Hospede
	 */
	public String convertePontos(String email, int qntPontos) throws HotelGotemburgoException {
		Hospede hospede = this.buscaHospede(email);
		return hospede.convertePontos(qntPontos);		
	}
	
	/**
	 * Esse metodo eh responsavel por gravar em um arquivo de texto o registro de hospedes
	 * do Hotel, juntamente com os detalhes (toString) desses hospedes.
	 * @throws IOException
	 */
	public void gravaArquivoCadastro() throws IOException {
		
		BufferedWriter bf = new BufferedWriter(new FileWriter("cad_hospedes.txt"));
		bf.write("Cadastro de Hospedes: " + this.hospedes.size() +" hospedes registrados" 
				+ HotelController.LINE_SEPARATOR );
		
		int contador = 0;
		for (Hospede hospede : hospedes) {
			contador += 1;
			bf.write("==> Hospede " + contador + ":" + HotelController.LINE_SEPARATOR + 
					hospede.toString() + HotelController.LINE_SEPARATOR);
		}
		bf.close();
	}
	
	/**
	 * Esse metodo eh responsavel por gravar em um arquivo de texto o cardapio do Restaurante, 
	 * juntamente com os detalhes (toString) dos itens presentes no cardapio.
	 * @throws IOException
	 */
	public void gravaArquivoMenu() throws IOException { // Eh coeso esse metodo estar no HotelController? #anderson
		
		BufferedWriter bf = new BufferedWriter(new FileWriter("cad_restaurante.txt"));
		bf.write("Menu do Restaurante: " + restaurante.getCardapio().size() + " itens no cardapio");
		
		int contador = 0;
		for (Comida item : restaurante.getCardapio()) {
			contador += 1;
			bf.write("==> Item " + contador + ":" + HotelController.LINE_SEPARATOR + item.toString() );
		}
		bf.close();
	}
	
	/* Metodos privados */

	/**
	 * Calcula o valor da soma de todas as transacoes realizadas no hotel, percorrendo
	 * a lista de transacoes.
	 * 
	 * @return O valor da soma das transacoes do hotel,
	 */
	private double getValorTotalTransacoes() {
		double valorTotal = 0.0;
		for (Transacao transacao : transacoes) {
			valorTotal += transacao.getValor();
		}
		return valorTotal;
	}
	
	/**
	 * Esse metodo cria e retorna um objeto do tipo Hospede, com base nos
	 * parametros recebidos de entrada.
	 * 
	 * @param nome Nome do hospede que sera criado
	 * @param email Email do hospede que sera criado
	 * @param anoNascimento Ano de nascimento do hospede que sera criado
	 * @return O Hospede criado
	 * @throws HotelGotemburgoException Caso seja identificado algum erro durante a criacao
	 */
	private Hospede criaHospede(String nome, String email, String dataNascimento) throws HotelGotemburgoException {
		return new Hospede(nome, email, dataNascimento);
	}
	
	/**
	 * Com base no email recebido de entrada, realiza uma busca no set de
	 * hospedes. Caso um dos hospedes possua esse email, o hospede em questao eh
	 * retornado.
	 * 
	 * @param email Email do hospede buscado
	 * @return Um hospede, caso encontrado
	 * @throws HotelGotemburgoException Caso seja identificado algum erro de valor
	 * ou caso o Hospede nao tenha sido encontrado (ou seja, nao esta cadastrado)
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
	 * correspondente a string recebida como parametro, se houver
	 * 
	 * @param tipoQuarto String representando o tipo do quarto, que deve
	 * ser igual a alguma das chaves do mapa de Tipos presente no Controller.
	 * @return constante do Enum TipoDeQuarto
	 * @throws HotelGotemburgoException Caso nao exista, no mapa de tipos, uma
	 * chave que seja igual ao parametro recebido
	 */
	private TipoDeQuarto getTipoQuarto(String tipoQuarto) throws HotelGotemburgoException {
		if (!tiposQuartos.containsKey(tipoQuarto.toUpperCase()))
			throw new ConsultaException("Nao existe um tipo de quarto representado por essa String");
		
		return tiposQuartos.get(tipoQuarto.toUpperCase());
	}

	/**
	 * Metodo utilizado para garantir que uma string que supostamente representa
	 * um Tipo de Quarto esta de acordo com o esperado.
	 * 
	 * @param tipo String representando o tipo de quarto
	 * @return boolean representando a confirmacao da verificacao
	 */
	private boolean verificaTipoQuarto(String tipo) {
		return this.tiposQuartos.containsKey(tipo);
	}
	
	/**
	 * Metodo utilizado para encapsulamento da checagem de excecoes no caso do tipo do quarto
	 * nao ser verificado, chamando o metodo verificaTipoQuarto.
	 * 
	 * @param tipo Tipo do quarto que sera checado
	 * @param mensagem A mensagem da excecao que pode ser lancada
	 * @throws HotelGotemburgoException Caso o tipo do quarto nao tenha sido verificado pelo
	 * metodo verificaTipoQuarto
	 */
	private void checaTipoQuarto(String tipo, String mensagem) throws HotelGotemburgoException {
		if (!this.verificaTipoQuarto(tipo))
			throw new HotelGotemburgoException(mensagem);
	}

	/**
	 * Metodo utilizado para verificar se um determinado quarto - que sera
	 * buscado atraves do ID - esta ou nao ocupado.
	 * 
	 * @param idQuarto ID do quarto a ser verificado
	 * @return boolean boolean representando a confirmacao da verificacao
	 * @throws HotelGotemburgoException Em caso de String invaliad
	 */
	private boolean isQuartoOcupado(String idQuarto) throws HotelGotemburgoException {
		Excecoes.checaString(idQuarto, "O id do quarto nao pode ser nulo ou vazio.");

		for (String quartoOcupado : this.quartosOcupados) {
			if (quartoOcupado.equalsIgnoreCase(idQuarto))
				return true;
		}
		return false;
	}
	
	/**
	 * Metodo utilizado para encapsulamento da checagem de excecoes no caso do quarto estar
	 * ocupado, chamando o metodo isQuartoOcupado.
	 * 
	 * @param idQuarto ID do quarto que sera checado
	 * @param mensagem A mensagem da excecao que pode ser lancada
	 * @throws HotelGotemburgoException Caso o quarto com esse ID esteja ocupado
	 */
	private void checaOcupacao(String idQuarto, String mensagem) throws HotelGotemburgoException {
		if (this.isQuartoOcupado(idQuarto))
			throw new HotelGotemburgoException(mensagem);
	}
	
	/**
	 * Verifica um hospede com determinado email esta presente no set de hospedes.
	 * 
	 * @param email Email do hospede a ser verificado
	 * @return boolean representando a sua presenca no set de Hospedes do hotel
	 * @throws HotelGotemburgoException Caso seja a string seja invalida
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
	 * Metodo utilizado para encapsulamento da checagem de excecoes no caso de um determinado
	 * hospede nao estar cadastrado no sistema do Hotel.
	 * 
	 * @param email Email do hospede que sera checado
	 * @param mensagem A mensagem da excecao que pode ser lancada
	 * @throws HotelGotemburgoException Caso o hospede nao esteja cadastrado
	 */
	private void checaCadastro(String email, String mensagem) throws HotelGotemburgoException {
		if (!this.isCadastrado(email))
			throw new HotelGotemburgoException(mensagem);
	}
	
	/**
	 * Verifica se um hospede esta hospedado no hotel, pela quantidade de
	 * estadias que ele possui (0 = nao esta hospedado)
	 * 
	 * @param email Email do hospede que sera verificada a situacao de hospedagem
	 * no Hotel.
	 * @return boolean representando sua hospedagem no Hotel
	 * @throws HotelGotemburgoException Caso o email recebido como parametro nao
	 * esteja cadastrado no Hotel
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
	 * Metodo utilizado para encapsulamento da checagem de excecoes no caso de um determinado
	 * hospede nao estar hospedado no sistema do Hotel.
	 * 
	 * @param email Email do hospede que sera checado
	 * @param mensagem A mensagem da excecao que pode ser lancada
	 * @throws HotelGotemburgoException Caso o hospede nao esteja hospedado
	 */
	private void checaHospedagem(String email, String mensagem) throws HotelGotemburgoException {
		if (!this.isHospedado(email))
			throw new HotelGotemburgoException(mensagem);
	}

	public boolean cadastraPrato(String nome, double preco, String descricao) throws ValoresException {
		return this.restaurante.cadastraPrato(nome, preco, descricao);
	}

	public boolean cadastraRefeicao(String nome, String descricao, String componentes) throws HotelGotemburgoException {
		return this.restaurante.cadastraRefeicao(nome, descricao, componentes);
	}

	public String consultaRestaurante(String nome, String atributo) throws HotelGotemburgoException {
		return this.restaurante.consultaRestaurante(nome, atributo);
	}

	public String consultaMenuRestaurante() {
		return this.restaurante.consultaMenuRestaurante();
	}

	public void ordenaMenu(String atributo) {
		this.restaurante.ordenaMenu(atributo);
	}
}
