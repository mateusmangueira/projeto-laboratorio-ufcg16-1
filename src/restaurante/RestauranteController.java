package restaurante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import restaurante.comida.Prato;
import restaurante.comida.Comida;
import restaurante.comida.RefeicaoCompleta;
import verificacao.excecoes.ConsultaException;
import verificacao.excecoes.Excecoes;
import verificacao.excecoes.HotelGotemburgoException;
import verificacao.excecoes.LogicaException;
import verificacao.excecoes.ValorException;
import verificacao.excecoes.ValoresException;

/**
 * O RestauranteController eh uma entidade responsavel pela administracao de
 * atividades relacionadas a Comida. Ele cadastra, atualiza e remove pratos
 * e refeicoes para oferece-los a hospedes do Hotel.
 * 
 * @since 18 de Setembro de 2016
 * @see Comida.java, Prato.java, RefeicaoCompleta.java
 */
public class RestauranteController {

	private List<Comida> cardapio;

	/**
	 * O restaurante nao recebe parametros no construtor, apenas inicializa suas
	 * colecoes de pratos e de refeicoes
	 */
	public RestauranteController() {
		this.cardapio = new ArrayList<Comida>();

	}

	public List<Comida> getCardapio() {
		return cardapio;
	}

	/**
	 * Metodo responsavel por cadastrar um prato no restaurante. Ele recebe os
	 * atributos do prato a ser criado e retorna um boolean confirmando a
	 * criacao.
	 * 
	 * @param nome Nome do prato que sera cadastrado
	 * @param preco Preco do prato que sera cadastrado
	 * @param descricao Descricao do prato que sera cadastrado
	 * @return Um boolean que representa o sucesso da operacao
	 * @throws ValoresException Caso os valores de entrada estejam invalidos
	 */
	public boolean cadastraPrato(String nome, double preco, String descricao) throws ValoresException {

		Excecoes.checaString(nome, "Erro no cadastro do prato. Nome do prato esta vazio.");
		Excecoes.checaString(descricao, "Erro no cadastro do prato. Descricao do prato esta vazia.");
		Excecoes.checaDouble(preco, "Erro no cadastro do prato. Preco do prato eh invalido.");

		Comida novoPrato = new Prato(nome, preco, descricao);

		return this.cardapio.add(novoPrato);
	}

	/**
	 * Varre o Set de pratos procurando um prato com um nome especifico. Caso
	 * encontrado, retorna true.
	 * 
	 * @param nome Nome do prato a ser procurado
	 * @return Um boolean que representa o sucesso da operacao
	 */
	private boolean contemPrato(String nome) {
		for (Comida prato : this.cardapio) {
			if (prato.getClass().equals(Prato.class)) {
				if (prato.getNome().equalsIgnoreCase(nome))
					return true;
			}
		}
		return false;
	}

	/**
	 * Varre o Set de pratos procurando um prato com um nome especifico. Caso
	 * encontrado, retorna a referencia ao objeto.
	 * 
	 * @param nome Nome do prato a ser buscado
	 * @return A referencia ao objeto Prato buscado
	 * @throws ConsultaException Caso o objeto buscado nao tenha sido encontrado 
	 */
	private Prato buscaPrato(String nome) throws LogicaException {
		Prato novoPrato = null;
		for (Comida prato : this.cardapio) {
			if (prato.getClass().equals(Prato.class)) {
				if (prato.getNome().equalsIgnoreCase(nome))
					novoPrato = (Prato) prato;
			}
		}
		if (novoPrato != null) {
			return novoPrato;
		}

		throw new ConsultaException("Prato nao encontrado.");
	}

	/**
	 * Realiza o cadastro de uma refeicao ao set de refeicoes do Restaurante.
	 * Para isso, verifica se todos os pratos do array recebido como parametro
	 * estao previamente cadastrados no sistema. Se estiverem, cria uma
	 * instancia de Refeicao e a adiciona no set de refeicoes do restaurante.
	 * Adiciona tambem essa refeicao ao menu.
	 * 
	 * @param nome Nome da refeicao
	 * @param descricao Descricao da refeicao
	 * @param componentes Os pratos que irao compor a refeicao, recebidos como 
	 * String e depois separados. Depois eh verificado se existem pratos com 
	 * esses nomes no set, para entao poder ser criada uma colecao com os objetos 
	 * correspondentes aos nomes.
	 * @return Um boolean que representa o sucesso do cadastro
	 * @throws HotelGotemburgoException Caso haja um problema no cadastro
	 */
	public boolean cadastraRefeicao(String nome, String descricao, String componentes) throws HotelGotemburgoException {

		Excecoes.checaString(nome, "Erro no cadastro de refeicao. Nome da refeicao esta vazio.");
		Excecoes.checaString(descricao, "Erro no cadastro de refeicao. Descricao da refeicao esta vazia.");
		Excecoes.checaString(componentes, "Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");

		String[] nomeDosPratos = componentes.split(";");

		ArrayList<Prato> pratos = new ArrayList<Prato>();

		if ((nomeDosPratos.length < 3) || (nomeDosPratos.length > 4))
			throw new ValorException("Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir "
					+ "no minimo 3 e no maximo 4 pratos.");

		for (int i = 0; i < nomeDosPratos.length; i++) {
			if (!this.contemPrato(nomeDosPratos[i]))
				throw new ValorException("Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes "
						+ "com pratos ja cadastrados.");

			Prato prato = this.buscaPrato(nomeDosPratos[i]);
			pratos.add(prato);
		}
		Comida refeicao = new RefeicaoCompleta(nome, descricao, pratos);
		return this.cardapio.add(refeicao);
	}

	/**
	 * Esse metodo consulta informacoes de uma comida do restaurante.
	 * A informacao que sera retornada eh definida atraves do parametro
	 * "atributo".
	 * 
	 * @param nome Nome da comida
	 * @param atributo Qual informacao que se deseja obter
	 * @return A informacao desejada
	 * @throws HotelGotemburgoException Caso ocorra um erro na consulta
	 */
	public String consultaRestaurante(String nome, String atributo) throws HotelGotemburgoException {

		Excecoes.checaString(nome, "Erro na consulta do restaurante. Nome do prato esto vazio.");
		Excecoes.checaString(atributo, "Erro na consulta do restaurante. Atributo do prato esta vazio.");

		switch (atributo.toUpperCase()) 
		{
		case "PRECO":
			for (Comida comida : this.cardapio) {
				if (comida.getNome().equalsIgnoreCase(nome))
					return String.format("R$%.2f", comida.getPreco());
			}

		case "DESCRICAO":
			for (Comida refeicao : this.cardapio) {
				if (refeicao.getNome().equalsIgnoreCase(nome))
					return refeicao.getDescricao();
			}
		default:
			throw new ConsultaException("Erro na consulta ao restaurante: opcao invalida");
		}
	}

	/**
	 * Esse metodo ordena os elementos do atributo cardapio de acordo com um criterio
	 * passado como parametro.
	 * 
	 * @param tipoOrdenacao Uma String representando o criterio de ordenacao que deseja-se
	 * aplicar para ordenar o Menu. As diferentes formas de ordenacao sao implementadas
	 * atraves de um sort que utiliza expressoes lambda para efetuar as operacoes,
	 * que mudam de acordo com a String recebida.
	 */
	public void ordenaMenu(String tipoOrdenacao) {

		switch (tipoOrdenacao.toUpperCase()) {

		case "NOME":
			Collections.sort(this.cardapio,
					(Comida comida, Comida outraComida) -> comida.getNome().compareTo(outraComida.getNome()));
			break;
		case "PRECO":
			Collections.sort(this.cardapio, (Comida comida, Comida outraComida) -> {
				return Double.compare(comida.getPreco(), outraComida.getPreco());
			});
			break;
		}
	}

	/**
	 * Acessa o cardapio e retorna o nome das comidas separados por ponto e virgula.
	 * 
	 * @return Uma String que contem o nome das comidas do cardapio, separadas por
	 * ponto e virgula
	 */
	public String consultaMenuRestaurante() {

		String retorno = "";
		for (Comida comida : this.cardapio) {
			retorno += ";" + comida.getNome();
		}
		return retorno.replaceFirst(";", "");
	}

	/**
	 * Procura uma comida no cardapio do Restaurante, com base no seu nome.
	 * 
	 * @param nome Nome da comida que sera buscada
	 * @return Uma referencia ao objeto encontrado, do tipo Comida
	 * @throws ConsultaException Caso nao exista uma comida com esse nome no cardapio
	 */
	public Comida buscaRefeicao(String nome) throws ConsultaException {
		for (Comida refeicao : this.getCardapio()) {
			if (refeicao.getNome().equalsIgnoreCase(nome))
				return refeicao;
		}
		throw new ConsultaException("Refeicao nao existe no cardapio.");
	}
	
}