package restaurante.comida;

import java.io.Serializable;

import verificacao.excecoes.ValoresException;

/**
 * Classe que representa a abstra��o de uma Comida do Restaurante. Uma comida possui
 * nome, descricao, e metodos para alterar e retornar esses atributos. Com excecao
 * do metodo getNome, todos os outros metodos possuem diferentes implementacoes
 * nas subclasses, portanto sao declarados como abstratos.
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 */
public abstract class Comida implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9050532268097788573L;
	
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
	
	/**
	 * Representacao em String de uma Comida.
	 */
	@Override
	public String toString() {
		return String.format("Nome: %s%sPreco: R$ %.2f%sDescricao: %s", 
				this.nome, Comida.LINE_SEPARATOR, this.getPreco(), Comida.LINE_SEPARATOR, this.descricao);
	}

	/**
	 * Comparacao entre uma Comida com outro objeto. Duas comidas sao 
	 * iguais caso possuam o mesmo nome.
	 */
	@Override
	public boolean equals(Object anotherObject) {
		if (anotherObject == null)
			return false;
		if (!anotherObject.getClass().equals(this.getClass()))
			return false;
		Prato outro = (Prato) anotherObject;
		return this.getNome().equalsIgnoreCase(outro.getNome());
	}
	
	/**
	 * Codigo hash de uma Comida.
	 */
	@Override
	public int hashCode() {
		final int PRIME = 7;
		int result = 1;
		return PRIME * result + (this.nome == null ? 0 : this.nome.hashCode());
	}

}
