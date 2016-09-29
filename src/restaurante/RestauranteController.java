package restaurante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import restaurante.comida.Prato;
import restaurante.comida.Refeicao;
import restaurante.comida.RefeicaoCompleta;
import verificacao.excecoes.ConsultaException;
import verificacao.excecoes.Excecoes;
import verificacao.excecoes.HotelGotemburgoException;
import verificacao.excecoes.LogicaException;
import verificacao.excecoes.ValorException;
import verificacao.excecoes.ValoresException;

/**
 * O Restaurante deve ser responsavel por cadastrar, atualizar e remover pratos
 * e refeicoes para oferece-los a hospedes do hotel.
 * 
 * @since 18 de Setembro de 2016
 * @see Prato.java, Refeicao.java
 */
public class RestauranteController {

	private List<Refeicao> cardapio;

	/**
	 * O restaurante nao recebe parametros no construtor, apenas inicializa suas
	 * colecoes de pratos e de refeicoes
	 */
	public RestauranteController() {
		this.cardapio = new ArrayList<Refeicao>();

	}

	public List<Refeicao> getCardapio() {
		return cardapio;
	}

	/* Pratos */

	/**
	 * Metodo responsavel por cadastrar um prato no restaurante. Ele recebe os
	 * atributos do prato a ser criado e retorna um boolean confirmando a
	 * criacao.
	 * 
	 * @param nome
	 *            Nome do prato a ser cadastrado
	 * @param preco
	 * @param descricao
	 * @return true se a operacao foi bem sucedida
	 * @throws ValoresException
	 * @throws @throws
	 *             ValoresException
	 * @throws Exception
	 */
	public boolean cadastraPrato(String nome, double preco, String descricao) throws ValoresException {

		Excecoes.checaString(nome, "Erro no cadastro do prato. Nome do prato esta vazio.");
		Excecoes.checaString(descricao, "Erro no cadastro do prato. Descricao do prato esta vazia.");
		Excecoes.checaDouble(preco, "Erro no cadastro do prato. Preco do prato eh invalido.");

		Refeicao novoPrato = new Prato(nome, preco, descricao);

		return this.cardapio.add(novoPrato);
	}

	/**
	 * Varre o Set de pratos procurando um prato com um nome especifico. Caso
	 * encontrado, retorna true.
	 * 
	 * @param nome
	 *            Nome do prato a ser procurado
	 * @return True se o prato foi encontrado
	 */
	private boolean contemPrato(String nome) {
		for (Refeicao prato : this.cardapio) {
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
	 * @param nome
	 *            Nome do prato a ser buscado
	 * @return A referencia ao objeto Prato buscado
	 * @throws LogicaException
	 * @throws Exception
	 */
	private Prato buscaPrato(String nome) throws LogicaException {
		Prato novoPrato = null;
		for (Refeicao prato : this.cardapio) {
			if (prato.getClass().equals(Prato.class)) {
				if (prato.getNome().equalsIgnoreCase(nome)) {
					novoPrato = (Prato) prato;
				}
			}
		}
		if (novoPrato != null) {
			return novoPrato;
		}

		throw new ConsultaException("Prato nao encontrado.");
	}

	/* Refeicoes */

	private boolean contemRefeicao(String nome) throws ConsultaException {
		for (Refeicao refeicao : this.cardapio) {
			if (refeicao.getClass().equals(RefeicaoCompleta.class)) {
				if (refeicao.getNome().equalsIgnoreCase(nome))
					return true;
			}
		}
		return false;
	}

	private Refeicao buscaRefeicao(String nome) throws ConsultaException {
		for (Refeicao refeicao : this.cardapio) {
			if (refeicao.getClass().equals(RefeicaoCompleta.class)) {
				if (refeicao.getNome().equalsIgnoreCase(nome))
					return refeicao;
			}
		}
		throw new ConsultaException("Refeicao nao encontrado.");

	}

	/**
	 * Realiza o cadastro de uma refeicao ao set de refeicoes do Restaurante.
	 * Para isso, verifica se todos os pratos do array recebido como parametro
	 * estao previamente cadastrados no sistema. Se estiverem, cria uma
	 * instancia de Refeicao e a adiciona no set de refeicoes do restaurante.
	 * Adiciona tambem essa refeicao ao menu.
	 * 
	 * @param nome
	 *            Nome da refeicao
	 * @param descricao
	 *            Descricao da refeicao
	 * @param componentes
	 *            Os pratos que irao compor a refeicao, recebidos como String e
	 *            depois separados. Depois sera verificado se existem pratos com
	 *            esses nomes para entao poder ser criada uma colecao com os
	 *            objetos Prato correspondentes.
	 * @return True se o cadastro foi bem sucedido
	 * @throws HotelGotemburgoException
	 *             Caso haja um problema no cadastro
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
		Refeicao refeicao = new RefeicaoCompleta(nome, descricao, pratos);
		return this.cardapio.add(refeicao);
	}

	/* Operacoes */

	/**
	 * Esse metodo consulta informacoes de um prato ou refeicao do restaurante.
	 * A informacao que sera retornada eh definida atraves do parametro
	 * "atributo".
	 * 
	 * @param nome
	 *            Nome do prato ou refeicao
	 * @param atributo
	 *            Qual informacao que se deseja obter
	 * @return A informacao desejada
	 * @throws ConsultaException
	 * @throws Exception
	 */
	public String consultaRestaurante(String nome, String atributo) throws ValoresException, ConsultaException {

		Excecoes.checaString(nome, "Erro na consulta do restaurante. Nome do prato esto vazio.");
		Excecoes.checaString(atributo, "Erro na consulta do restaurante. Atributo do prato esta vazio.");

		switch (atributo.toUpperCase()) {
		case "PRECO":
			for (Refeicao comida : this.cardapio) {
				if (comida.getNome().equalsIgnoreCase(nome)) {
					return String.format("R$%.2f", comida.getPreco());
				}
			}

		case "DESCRICAO":
			for (Refeicao refeicao : this.cardapio) {
				if (refeicao.getNome().equalsIgnoreCase(nome)) {
					return refeicao.getDescricao();
				}
			}
		default:
			throw new ConsultaException("Erro na consulta ao restaurante: opcao invalida");
		}
	}

	public void ordenaMenu(String tipoOrdenacao) {

		switch (tipoOrdenacao.toUpperCase()) {

		case "NOME":
			Collections.sort(this.cardapio,
					(Refeicao comida, Refeicao outraComida) -> comida.getNome().compareTo(outraComida.getNome()));
			break;
		case "PRECO":
			Collections.sort(this.cardapio, (Refeicao comida, Refeicao outraComida) -> {
				return Double.compare(comida.getPreco(), outraComida.getPreco());
			});
			break;
		}
	}

	public String consultaMenuRestaurante() {

		String retorno = "";
		for (Refeicao comida : this.cardapio) {
			retorno += ";" + comida.getNome();
		}
		return retorno.replaceFirst(";", "");
	}

	// public static void main(String[] args) {
	// args = new String[] { "restaurante.RestauranteController",
	// "diretorio_testes/testes_uc4.txt",
	// "diretorio_testes/testes_uc4_exception.txt",
	// "diretorio_testes/testes_uc5.txt" };
	// EasyAccept.main(args);
	// }

}