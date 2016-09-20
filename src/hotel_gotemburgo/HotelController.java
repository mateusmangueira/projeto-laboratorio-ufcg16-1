package hotel_gotemburgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.*;
import hotel_gotemburgo.hospedagem.*;
import hotel_gotemburgo.quartos.*;

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
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */
public class HotelController {

	private final int ANO_ATUAL;
	private final int MAIORIDADE;
	private Set<Hospede> hospedes;
	private Set<Quarto> quartosOcupados;
	private HashMap<String, TipoDeQuarto> tiposQuartos;
	private ArrayList<Hospede> checkouts;
	private double totalArrecadado;
	
	/**
	 * O construtor do HotelController inicia o Set de hospedes, de quartos,
	 * define o ano atual e também qual a idade que se atinge a maioridade.
	 */
	public HotelController() {

		this.hospedes = new HashSet<Hospede>();
		this.quartosOcupados = new HashSet<Quarto>();
		this.checkouts = new ArrayList<Hospede>();
		
		this.ANO_ATUAL = 2016;
		this.MAIORIDADE = 18;
		this.initializaMapa();
		totalArrecadado = 0.0;
	}

	/**
	 * Retorna o set de hospedes do hotel
	 * @return set de hospedes do hotel
	 */
	public Set<Hospede> getHospedes() {
		return hospedes;
	}

	public Set<Quarto> getQuartosOcupados() {
		return quartosOcupados;
	}

	public HashMap<String, TipoDeQuarto> getTiposQuartos() {
		return tiposQuartos;
	}

	public void setTiposQuartos(HashMap<String, TipoDeQuarto> tiposQuartos) throws ValoresException {
		if (tiposQuartos == null) {
			throw new ValorException("O mapa nao pode ser nulo.");
		}
		this.tiposQuartos = tiposQuartos;
	}
	
	public ArrayList<Hospede> getCheckouts() {
		return this.checkouts;
	}
	
	public double getTotalArrecadado() {
		return totalArrecadado;
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

		Excecoes.checaEmail(email);

		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return hospede;
		}
		throw new ConsultaException("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
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
	public String cadastraHospede(String nome, String email, String dataNascimento) throws HotelException {

		Excecoes.checaCadastroNome(nome);
		Excecoes.checaCadastroEmail(email);
		Excecoes.checaCadastroDataNascimento(dataNascimento);

		if (!this.validaData(dataNascimento)) {
			throw new HotelException("Erro no cadastro de Hospede. Formato de data invalido.");
		}
		if (!this.validaEmail(email)) {
			throw new HotelException("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		if (!this.validaNome(nome)) {
			throw new HotelException("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}

		String[] data = dataNascimento.split("/");
		int anoNascimento = Integer.parseInt(data[2]);
		int idade = this.ANO_ATUAL - anoNascimento;
		if (idade < this.MAIORIDADE) {
			throw new CadastroException("Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
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
			throw new HotelException("Erro na remocao do Hospede. Formato de email invalido.");
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
			throw new ConsultaException("Erro na consulta de hospede. Opcao invalida.");
		}
	}
	
	/**
	 * Retorna informacoes sobre um hospede que possua pelo menos uma estadia
	 * no Hotel. A informacao que sera retornada eh determinada pelo parametro
	 * atributo, podendo ser: "Hospedagem ativa" - retorna a quantidade de estadias
	 * do hospede; "Quarto" - retorna uma string com o id dos quartos de suas estadias;
	 * "Total" - retorna os gastos com estadia do hospede
	 * 
	 * @param email Email do hospede
	 * @param atributo Hospedagem ativa, Quarto ou Total
	 * @return A informacao desejada
	 * @throws HotelException
	 */
	public String getInfoHospedagem(String email, String atributo) throws HotelException {
		
		final String HOSPEDAGEM_ATIVA = "Hospedagens ativas";
		final String QUARTO = "Quarto";
		final String TOTAL = "Total";

		Excecoes.checaGetInfoHospedagemEmail(email);
		
		if (!isCadastrado(email))
			throw new ConsultaException("Erro ao checar hospedagem ativa. Email do(a) hospede esta invalido.");

		Hospede hospede = this.buscaHospede(email);

		if (!isHospedado(email))
			throw new ConsultaException(String.format("Erro na consulta de hospedagem. Hospede %s nao esta hospedado(a).", hospede.getNome()));

		switch (atributo) {
		case HOSPEDAGEM_ATIVA:
			return String.format("%d", hospede.getQuantidadeDeEstadias());
		case QUARTO:
			return hospede.getRepresentaEstadias();
		case TOTAL:
			return String.format("R$%.2f", hospede.getGastos());
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
	 * @param valor
	 *            A nova informacao
	 * @throws HotelException
	 */
	public void atualizaCadastro(String email, String atributo, String valor) throws HotelException {

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
				throw new HotelException("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
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

	/**
	 * Metodo responsavel por criar um objeto quarto e adiciona-lo no set de quartos do hotel.
	 * O quarto criado eh retornado.
	 * 
	 * @param idQuarto ID (uma string unica representando o quarto)
	 * @param tipoQuarto (O tipo do quarto, podendo ser "Simples", "Luxo" ou "Presidencial".
	 * @return O Quarto que foi criado
	 * @throws StringException
	 */
	public Quarto criaQuartos(String idQuarto, TipoDeQuarto tipoQuarto) throws StringException {
		return new Quarto(idQuarto, tipoQuarto);
		// Futuramente pode ser preciso adicionar esse quarto em um set de quartos do Hotel
	}

	
	/**
	 * Consultar o mapa de tipos e retornar o valor (constante do Enum) correspondente
	 * a string tipoQuarto.
	 * 
	 * @param tipoQuarto String representando o tipo do quarto (chave do mapa tiposQuartos)
	 * @return Constante do Enum TipoDeQuarto
	 */
	public TipoDeQuarto getTipo(String tipoQuarto) {
		return tiposQuartos.get(tipoQuarto.toUpperCase());
	}
	
	/**
	 * Metodo utilizado no construtor para iniciar o mapa que associa uma String
	 * que representa um Enum de TipoDeQuarto, a uma constante desse Enum.
	 * Ex: key- "SIMPLES" value- TipoDeQuarto.SIMPLES 
	 */
	private void initializaMapa() {
		
		this.tiposQuartos = new HashMap<String, TipoDeQuarto>();
		tiposQuartos.put("SIMPLES", TipoDeQuarto.SIMPLES);
		tiposQuartos.put("LUXO", TipoDeQuarto.LUXO);
		tiposQuartos.put("PRESIDENCIAL", TipoDeQuarto.PRESIDENCIAL);
		
	}
	
	/**
	 * Metodo utilizado para garantir que uma string que supostamente
	 * representa um Tipo de Quarto esta de acordo com o esperado.
	 * 
	 * @param tipo String representando o tipo de quarto
	 * @return boolean
	 */
	public boolean verificaTipoQuarto(String tipo) {
		return this.getTiposQuartos().containsKey(tipo);
	}

	/**
	 * Metodo utilizado para verificar se um determinado quarto - que sera
	 * buscado atraves do ID - esta ou nao ocupado.
	 * 
	 * @param id ID do quarto a ser verificado
	 * @return boolean
	 */
	public boolean verificaOcupacao(String id) {
		for (Quarto quartosOcupados : this.getQuartosOcupados()) {
			if (quartosOcupados.getId().equalsIgnoreCase(id)) {
				return true;
			}
		} return false;
	}
	
	/**
	 * Esse metodo eh responsavel por realizar o checkin de um hospede no Hotel.
	 * O hospede precisa previamente estar cadastrado no sistema, entao ele sera
	 * procurado. Caso esteja cadastrado, eh adicionada uma Estadia a esse hospede.
	 * A Estadia eh composta de um quarto (que eh criado com base nos atributos 
	 * recebidos como parametro) e uma quantidade de dias (tambem recebida como
	 * parametro).
	 * 
	 * @param email Email do hospede
	 * @param qntDias Quantidade de dias hospedado na estadia
	 * @param idQuarto ID do quarto da estadia
	 * @param tipoQuarto Tipo do quarto da estadia
	 * @throws HotelException
	 */
	public void realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto) throws HotelException {
		if (email == null || email.trim().isEmpty()) {
			throw new HotelException("Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
		}
		if (!this.validaEmail(email)) {
			throw new HotelException("Erro ao realizar checkin. Email do(a) hospede esta invalido.");
		}
		if (qntDias < 0) {
			throw new HotelException("Erro ao realizar checkin. Quantidade de dias esta invalida.");
		}
		if (idQuarto.trim().isEmpty() || !this.validaQuarto(idQuarto)) {
			throw new HotelException("Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");
		}

		if (!isCadastrado(email))
			throw new ConsultaException("Erro ao realizar checkin. Hospede de email " + email + " nao foi cadastrado(a).");
			
		Hospede hospede = this.buscaHospede(email);
		
		if (!verificaTipoQuarto(tipoQuarto.toUpperCase()))
			throw new VerificacaoException("Erro ao realizar checkin. Tipo de quarto invalido.");
		
		TipoDeQuarto tipo = this.getTipo(tipoQuarto);
		
		if (this.verificaOcupacao(idQuarto))
			throw new ConsultaException("Erro ao realizar checkin. Quarto " + idQuarto + " ja esta ocupado.");
		
		Quarto quarto = this.criaQuartos(idQuarto, tipo);
		Estadia estadia = new Estadia(quarto, qntDias);
		hospede.addEstadia(estadia);
		quartosOcupados.add(quarto);
	}
	
	/**
	 * Metodo responsavel por realizar o checkout de um hospede no hotel, registrando a sua saida,
	 * removendo a estadia no quarto do qual ele esta saindo e adicionando esse hospede no array 
	 * de checkouts. Tambem eh calculado e retornado o valor total gasto por esse hospede no Hotel.
	 * 
	 * @param email Email do hospede
	 * @param idQuarto ID do quarto de onde o hospede esta saindo
	 * @return O valor total gasto por esse hospede no Hotel
	 * @throws HotelException
	 */
	public String realizaCheckout(String email, String idQuarto) throws HotelException {
		
		Hospede hospedeDeSaida = this.buscaHospede(email);
		double valorTotal = 0.0;
				
		for (Estadia estadia : hospedeDeSaida.getEstadias()) 
		{
			if (estadia.getQuarto().getId().equalsIgnoreCase(idQuarto)) {
				valorTotal = valorTotal + estadia.calculaEstadia();
				checkouts.add(hospedeDeSaida);
				hospedeDeSaida.getEstadias().remove(estadia);
				this.totalArrecadado = totalArrecadado + valorTotal;
			}
		}
		return String.format("R$%.2f", valorTotal);
	}
	
	/**
	 * Consulta o array de hospedes que realizaram checkout e retorna uma informacao,
	 * referente ao atributo recebido.
	 * 
	 * @param atributo Representa a informacao desejada
	 * @return A informacao desejada
	 * @throws HotelException 
	 */
	public String consultaTransacoes(String atributo) throws HotelException 
	{
		switch (atributo.toUpperCase()) 
		{
		case "QUANTIDADE":
			return String.format("%d", this.getCheckouts().size() );
		case "TOTAL":
			return String.format("R$%.2f", getTotalArrecadado());
		case "NOME":
			String nomes = "";
			for (Hospede hospede: this.getCheckouts()) {
				nomes += ";" + hospede.getNome();
			} 
			return nomes.replaceFirst(";", "");
				
		default:
			throw new ConsultaException("Erro na consulta de transacoes. Opcao invalida.");
		}
	}
	
	/**
	 * 
	 * @param atributo
	 * @param indice
	 * @return
	 * @throws ConsultaException 
	 */
	public String consultaTransacoes(String atributo, int indice) throws ConsultaException
	{	
		switch (atributo.toUpperCase()) 
		{
		case "TOTAL":
			return String.format("R$%.2f", this.getCheckouts().get(indice).getGastos());
		case "NOME":
			return this.getCheckouts().get(indice).getNome();
				
		default:
			throw new ConsultaException("Erro na consulta de transacoes. Opcao invalida.");
			}	
	}
	
	public static void main(String[] args) {
		args = new String[] { "hotel_gotemburgo.HotelController", "diretorio_testes/testes_uc1.txt",
				"diretorio_testes/testes_uc1_exception.txt", "diretorio_testes/testes_uc2.txt",
				"diretorio_testes/testes_uc2_exception.txt", "diretorio_testes/testes_uc3.txt",
				"diretorio_testes/testes_uc4.txt", "diretorio_testes/testes_uc4_exception.txt"};
		EasyAccept.main(args);
	}

}