package restaurante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import restaurante.comida.Prato;
import restaurante.comida.Refeicao;
import verificacao.excecoes.ConsultaException;
import verificacao.excecoes.Excecoes;
import verificacao.excecoes.LogicaException;
import verificacao.excecoes.ValorException;
import verificacao.excecoes.ValoresException;
import easyaccept.EasyAccept;

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
	 * @return true se a operacao foi bem sucedida
	 * @throws ValoresException 
	 * @throws Exception
	 */
	public boolean cadastraPrato(String nome, double preco, String descricao) throws ValoresException {
		
		Excecoes.checaString(nome, "Erro no cadastro do prato. Nome do prato esta vazio.");
		Excecoes.checaString(descricao, "Erro no cadastro do prato. Descricao do prato esta vazia.");
		Excecoes.checaDouble(preco, "Erro no cadastro do prato. Preco do prato eh invalido.");

		Prato novoPrato = new Prato(nome, preco, descricao);
		return this.pratos.add(novoPrato);
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
		for (Prato prato : this.pratos) {
			if (prato.getNome().equalsIgnoreCase(nome))
				return true;
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
		for (Prato prato : this.pratos) {
			if (prato.getNome().equalsIgnoreCase(nome))
				return prato;
		}
		throw new ConsultaException("Prato nao encontrado.");
	}

	/* Refeicoes */

	private boolean contemRefeicao(String nome) throws ConsultaException {
		for (Refeicao refeicao : this.refeicoes) {
			if (refeicao.getNome().equalsIgnoreCase(nome))
				return true;
		}
		return false;
	}

	private Refeicao buscaRefeicao(String nome) throws ConsultaException {
		for (Refeicao refeicao : this.refeicoes) {
			if (refeicao.getNome().equalsIgnoreCase(nome))
				return refeicao;
		}
		throw new ConsultaException("Refeicao nao encontrado.");

	}

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
	 * @throws ValoresException 
	 * @throws ValoresException 
	 * @throws LogicaException 
	 * @throws ValorException 
	 * @throws Exception
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws ValoresException, LogicaException {
		
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
		Refeicao refeicao = new Refeicao(nome, descricao, pratos);
		this.refeicoes.add(refeicao);
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
			for (Prato prato : pratos) {
				if (prato.getNome().equalsIgnoreCase(nome))
					return String.format("R$%.2f", prato.getPreco());
			}
			for (Refeicao refeicao : refeicoes) {
				if (refeicao.getNome().equalsIgnoreCase(nome))
					return String.format("R$%.2f", refeicao.getValor());
			}

		case "DESCRICAO":
			for (Prato prato : pratos) {
				if (prato.getNome().equalsIgnoreCase(nome))
					return prato.getDescricao();
			}
			for (Refeicao refeicao : refeicoes) {
				if (refeicao.getNome().equalsIgnoreCase(nome))
					return refeicao.getDescricao();
			}
		default:
			throw new ConsultaException("Erro na consulta ao restaurante: opcao invalida");
		}
	}

	public void ordenaPeloNome(ArrayList<Prato> pratos) {
		Collections.sort(pratos, new Comparator<Prato>() {
			@Override
			public int compare(Prato prato, Prato outroPrato) {
				return prato.getNome().compareTo(outroPrato.getNome());
			}
		});
	}

	public void ordenaPeloPreco(ArrayList<Prato> pratos) {
		Collections.sort(pratos, new Comparator<Prato>() {
			@Override
			public int compare(Prato prato, Prato outroPrato) {
				Double double1 = prato.getPreco();
				Double double2 = outroPrato.getPreco();
				return double1.compareTo(double2);
			}
		});
	}
	
	public void consultaMenuRestaurante(String tipo) {

	}

	public static void main(String[] args) {
		args = new String[] { "restaurante.RestauranteController", "diretorio_testes/testes_uc4.txt",
				"diretorio_testes/testes_uc4_exception.txt"};
		EasyAccept.main(args);
	}

}