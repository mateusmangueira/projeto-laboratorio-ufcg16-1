package restaurante;

import java.util.ArrayList;
import java.util.HashSet;

import easyaccept.EasyAccept;

import excecoes.ConsultaException;
import excecoes.LogicaException;
import excecoes.StringException;
import excecoes.ValorException;
import excecoes.ValoresException;

/**
 * O Restaurante deve ser responsavel por cadastrar, atualizar e remover pratos
 * e refeicoes para oferece-los a hospedes do hotel.
 * 
 * @since 18 de Setembro de 2016
 * @see Prato.java, Refeicao.java
 */
public class RestauranteController {

	private HashSet<Prato> pratos;
	private HashSet<Refeicao> refeicoes;

	/**
	 * O restaurante nao recebe parametros no construtor, apenas inicializa suas
	 * colecoes de pratos e de refeicoes
	 */
	public RestauranteController() {
		pratos = new HashSet<Prato>();
		refeicoes = new HashSet<Refeicao>();
	}

	/* Pratos */

	/**
	 * Metodo responsavel por cadastrar um prato no restaurante. Ele recebe os
	 * atributos do prato a ser criado e retorna um boolean confirmando a
	 * criacao.
	 * 
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @return True se a operacao foi bem sucedida
	 * @throws Exception
	 */
	public boolean cadastraPrato(String nome, double preco, String descricao) throws Exception {
		if (nome == null || nome.trim().isEmpty()) {
			throw new Exception("Erro no cadastro do prato. Nome do prato esta vazio.");
		}
		if (preco < 0) {
			throw new Exception("Erro no cadastro do prato. Preco do prato eh invalido.");
		}
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new Exception("Erro no cadastro do prato. Descricao do prato esta vazia.");
		}
		Prato novoPrato = new Prato(nome, preco, descricao);
		return this.pratos.add(novoPrato);
	}

	/**
	 * Varre o Set de pratos procurando um prato com um nome espec�fico� Caso
	 * encontrado, retorna true.
	 * 
	 * @param nome
	 *            Nome do prato a ser procurado
	 * @return True se o prato foi encontrado
	 */
	public boolean contemPrato(String nome) {
		for (Prato prato : this.pratos) {
			if (prato.getNome().equalsIgnoreCase(nome))
				return true;
		}
		return false;
	}

	/**
	 * Varre o Set de pratos procurando um prato com um nome espec�fico. Caso
	 * encontrado, retorna a referencia ao objeto.
	 * 
	 * @param nome Nome do prato a ser buscado
	 * @return A referencia ao objeto Prato buscado
	 * @throws LogicaException
	 * @throws Exception
	 */
	public Prato buscaPrato(String nome) throws LogicaException {
		for (Prato prato : this.pratos) {
			if (prato.getNome().equalsIgnoreCase(nome))
				return prato;
		}
		throw new ConsultaException("Prato nao encontrado."); // Substituir por
																// ElementoNaoEncontradoException
	}

	/**
	 * Metodo responsavel por remover um prato do set de pratos
	 * 
	 * @param nome Nome do prato
	 * @return True se a remocao foi bem sucedida
	 * @throws ValoresException
	 * @throws LogicaException
	 * @throws Exception
	 */
	public boolean removePrato(String nome) throws ValoresException, LogicaException {
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("O nome do prato nao pode ser nulo ou vazio.");

		if (!contemPrato(nome))
			throw new ConsultaException("Nenhum prato com este nome foi encontrado.");																	// ElementoNaoEncontradoException

		return (pratos.remove(buscaPrato(nome)));
	}

	/**
	 * Atualiza o cadastro de um prato. Caso seja possivel remove-lo do set,
	 * significa que esse prato esta cadastrado. Logo, apos remove-lo, cria-se
	 * um novo objeto com o mesmo nome e os outros atributos recebidos como
	 * parametros
	 * 
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @throws Exception
	 */
	public void atualizaPrato(String nome, double preco, String descricao) throws Exception {
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("O nome do prato nao pode ser nulo ou vazio.");
		if (descricao == null || descricao.trim().isEmpty())
			throw new StringException("A descricao do prato nao pode ser nula ou vazia.");
		if (preco < 0)
			throw new ValorException("O preco do prato nao pode ser negativo.");

		if (removePrato(nome))
			cadastraPrato(nome, preco, descricao);
	}

	/* Refeicoes */

	/**
	 * Realiza o cadastro de uma refeicao ao set de refeicoes do restaurante.
	 * Para isso, verifica se todos os pratos do array recebido como parametro
	 * estao previamente cadastrados no sistema. Se estiverem, cria uma
	 * instancia de Refeicao e a adiciona no set de refeicoes do restaurante.
	 * 
	 * @param nome
	 * @param descricao
	 * @param pratosDaRefeicao
	 * @return True se o cadastro foi bem sucedido
	 * @throws Exception
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		if (nome == null || nome.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de refeicao. Nome da refeicao esta vazio.");
		}
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de refeicao. Descricao da refeicao esta vazia.");
		}
		if (componentes == null || componentes.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
		}

		String[] nomeDosPratos = componentes.split(";");

		ArrayList<Prato> pratos = new ArrayList<Prato>();

		if ((nomeDosPratos.length < 3) || (nomeDosPratos.length > 4))
			throw new Exception(
					"Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir " +
					"no minimo 3 e no maximo 4 pratos.");

		for (int i = 0; i < nomeDosPratos.length; i++) {
			if (!this.contemPrato(nomeDosPratos[i])) {
				throw new Exception(
						"Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes " +
						"com pratos ja cadastrados.");
			}
			Prato prato = this.buscaPrato(nomeDosPratos[i]);
			pratos.add(prato);
		}
		Refeicao refeicao = new Refeicao(nome, descricao, pratos);
		this.refeicoes.add(refeicao);
	}

	/**
	 * Varre o Set de refeicoes procurando uma refeicao com um nome especifico.
	 * Caso encontrada, retorna true.
	 * 
	 * @param nome Nome da refeicao a ser procurada
	 * @return True se a refeicao foi encontrada
	 */
	public boolean contemRefeicao(String nome) {
		for (Refeicao refeicao : this.refeicoes) {
			if (refeicao.getNome().equalsIgnoreCase(nome))
				return true;
		}
		return false;
	}

	/**
	 * Varre o Set de refeicoes procurando uma refeicao com um nome especifico.
	 * Caso encontrada, retorna a referencia ao objeto.
	 * 
	 * @param nome
	 *            Nome da refeicao a ser buscada
	 * @return A referencia ao objeto
	 * @throws LogicaException
	 * @throws Exception
	 */
	public Refeicao buscaRefeicao(String nome) throws LogicaException {
		for (Refeicao refeicao : refeicoes) {
			if (refeicao.getNome().equalsIgnoreCase(nome))
				return refeicao;
		}
		throw new ConsultaException("Refeicao nao encontrado"); // Substituir
																// por
																// ElementoNaoEncontradoException
	}

	/**
	 * Metodo responsavel por remover uma refeicao do set de refeicoes
	 * 
	 * @param nome
	 *            Nome da refeicao
	 * @return True se a remocao foi bem sucedida
	 * @throws ValoresException
	 * @throws LogicaException
	 * @throws Exception
	 */
	public boolean removeRefeicao(String nome) throws ValoresException, LogicaException {
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("O nome da refeicao nao pode ser nulo ou vazio.");

		if (!contemRefeicao(nome))
			throw new ConsultaException("Nenhuma refeicao com esse nome foi encontrada."); 
		
		return (refeicoes.remove(buscaRefeicao(nome)));
	}

	/**
	 * Esse metodo consulta informacoes de um prato ou refeicao do restaurante. 
	 * A informacao que sera retornada eh definida atraves do parametro "atributo".
	 * 
	 * @param nome Nome do prato ou refeicao
	 * @param atributo Qual informacao que se deseja obter
	 * @return A informacao desejada
	 * @throws Exception
	 */
	public String consultaRestaurante(String nome, String atributo) throws Exception { // Criar
																						// RestauranteException

		if (nome == null || nome.trim().isEmpty())
			throw new ConsultaException("Erro na consulta do restaurante. Nome do prato esto vazio.");

		if (atributo == null || atributo.trim().isEmpty())
			throw new ConsultaException("Erro na consulta do restaurante. Atributo do prato esta vazio.");

		switch (atributo.toUpperCase()) 
		{
		case "PRECO":
			for (Prato prato : pratos) {
				if (prato.getNome().equalsIgnoreCase(nome)) {
					return String.format("R$%.2f", prato.getPreco());
				}
			}
			for (Refeicao refeicao : refeicoes) {
				if (refeicao.getNome().equalsIgnoreCase(nome)) {
					return String.format("R$%.2f", refeicao.getValor());
				}
			}

		case "DESCRICAO":
			for (Prato prato : pratos) {
				if (prato.getNome().equalsIgnoreCase(nome)) {
					return prato.getDescricao();
				}
			}
			for (Refeicao refeicao : refeicoes) {
				if (refeicao.getNome().equalsIgnoreCase(nome)) {
					return refeicao.getDescricao();
				}
			}

		default:
			throw new Exception("Erro na consulta ao restaurante: opcao invalida");
		}

	}

	public static void main(String[] args) {
		args = new String[] { "restaurante.RestauranteController", "diretorio_testes/testes_uc4.txt",
				"diretorio_testes/testes_uc4_exception.txt" };
		EasyAccept.main(args);
	}

}