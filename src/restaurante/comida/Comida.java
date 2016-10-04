package restaurante.comida;

import verificacao.excecoes.ValoresException;

public abstract class Comida {
	
	protected String nome;
	protected String descricao;
	
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

}
