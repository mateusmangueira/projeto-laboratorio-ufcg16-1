package restaurante.comida;

import verificacao.excecoes.Excecoes;
import verificacao.excecoes.StringException;
import verificacao.excecoes.ValoresException;

/**
 * Extende a classe abstrata Comida. Essa classe eh responsavel por um objeto do tipo Prato,
 * que representa um prato do restaurante do hotel. Implementa os metodos abstratos de sua 
 * superclasse. Um prato, alem de nome e descricao, tambem possui um preco.
 * 
 * @see Comida.java
 * @since 18 de Setembro de 2016
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 */
public class Prato extends Comida {

	private double preco;

	/**
	 * O Construtor recebe o nome do prato, preco e sua descricao.
	 * 
	 * @param nome String que representa o nome do prato
	 * @param preco O valor do prato, em double
	 * @param descricao String que descreve informacoes sobre o prato
	 * @throws ValoresException Caso os valores de entrada sejam invalidos
	 */
	public Prato(String nome, double preco, String descricao) throws ValoresException {
		
		super(nome, descricao);
		Excecoes.checaString(nome, "O nome do prato nao pode ser nulo ou vazio.");
		Excecoes.checaString(descricao, "A descricao do prato nao pode ser vazia ou nula.");
		Excecoes.checaDouble(preco, "O preco do prato nao pode ser negativo.");
		
		this.preco = preco;
	}
	
	/**
	 * Altera o atributo nome do prato, realizando verificacao.
	 * 
	 * @param nome Nome do prato
	 * @throws StringException Caso a string de entrada seja invalida 
	 */
	@Override
	public void setNome(String nome) throws StringException {
		Excecoes.checaString(nome, "O nome do prato nao pode ser nulo ou vazio.");
		this.nome = nome;
	}

	/**
	 * Retorna a descricao do prato.
	 * 
	 * @return descricao Uma string que descreve informacoes uteis sobre o prato
	 */
	@Override
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Altera o atributo descricao do prato, realizando verificacao.
	 * 
	 * @param descricao A nova descricao do prato
	 * @throws StringException Caso a string de entrada seja invalida
	 */
	@Override
	public void setDescricao(String descricao) throws StringException {
		if (descricao == null || nome.trim().isEmpty()) {
			throw new StringException("O nome do prato nao pode ser nulo ou vazio.");
		}
		this.descricao = descricao;
	}

	/**
	 * Retorna o preco do prato.
	 * 
	 * @return preco O valor do atributo preco
	 */
	@Override
	public double getPreco() {
		return this.preco;
	}

}
