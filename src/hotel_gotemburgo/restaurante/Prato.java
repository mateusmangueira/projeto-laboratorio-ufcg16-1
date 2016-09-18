package hotel_gotemburgo.restaurante;

import excecoes.StringException;
import excecoes.ValorException;
import excecoes.ValoresException;

/**
 * Define um objeto do tipo Prato, que possui um nome, um preco e uma descricao.
 * @since 18 de Setembro de 2016
 */
public class Prato {
	
	private String nome, descricao;
	private double preco;
	
	public Prato(String nome, double preco, String descricao) throws ValoresException {
		
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("Nome nao pode ser nulo ou vazio");
		if (descricao == null || descricao.trim().isEmpty())
			throw new StringException("Descricao nao pode ser vazia ou nula");
		if (preco < 0)
			throw new ValorException("Preco do prato nao pode ser negativo");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s. (R$ %.2f)", getNome(), getDescricao(), getPreco());
	}

	@Override
	public boolean equals(Object anotherObject) 
	{
		if (anotherObject == null)
			return false;
		if (!anotherObject.getClass().equals( this.getClass() ) )
			return false;
		Prato outro = (Prato) anotherObject;
		return this.getNome().equalsIgnoreCase(outro.getNome());
	}
	
	@Override
	public int hashCode()
	{
		final int PRIME = 7;
		int result = 1;
		return PRIME * result + (this.nome == null ? 0 : this.nome.hashCode());
	}
	
	
}
