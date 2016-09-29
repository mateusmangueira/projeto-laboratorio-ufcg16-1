package restaurante.comida;

import verificacao.excecoes.ValoresException;

public abstract class Refeicao {
	
	protected String nome;
	protected String descricao;
	
	public Refeicao(String nome, String descricao) {
		
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
