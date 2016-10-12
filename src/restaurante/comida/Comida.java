package restaurante.comida;

import verificacao.excecoes.ValoresException;

/**
 * Classe que representa a abstração de uma Comida do Restaurante. Uma comida possui
 * nome, descricao, e metodos para alterar e retornar esses atributos. Com excecao
 * do metodo getNome, todos os outros metodos possuem diferentes implementacoes
 * nas subclasses, portanto sao declarados como abstratos.
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 */
public abstract class Comida {
	
	protected String nome;
	protected String descricao;
	private static final String LINE_SEPARATOR = System.lineSeparator();

	public Comida(String nome, String descricao) {
		
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public abstract String getDescricao();

	public abstract void setNome(String nome) throws ValoresException;
	
	public abstract void setDescricao(String descricao) throws ValoresException;
	
	public abstract double getPreco();
	
	@Override
	public String toString() {
		return String.format("Nome: %s%sPreco: R$ %.2f%sDescricao: %s", 
				this.nome, Comida.LINE_SEPARATOR, this.getPreco(), Comida.LINE_SEPARATOR, this.descricao);
	}

}
