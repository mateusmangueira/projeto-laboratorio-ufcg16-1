package hotel_gotemburgo.restaurante;

import java.util.ArrayList;

import excecoes.StringException;

/**
 * Representa uma refeicao completa, que eh composta por pratos. 
 * Possui um nome, uma descricao propria, e uma sequencia de pratos,
 * podendo englobar: entrada, prato principal, sobremesa e petit four.
 * Sobremesa ou o petit four sao opcionais, logo, uma refeicao deve
 * possuir 3 ou 4 pratos, nao menos do que isso. A ordem dos pratos
 * eh importante, pois representara a refeicao a que se refere.
 * 
 * @since 18 de Setembro de 2016
 * @see Prato.java
 */
public class Refeicao {

	private String nome, descricao;
	private ArrayList<Prato> pratos;
	
	public Refeicao(String nome, String descricao, ArrayList<Prato> pratos) throws Exception {
		
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("Nome nao pode ser nulo ou vazio");
		if (descricao == null || descricao.trim().isEmpty())
			throw new StringException("Descricao nao pode ser vazia ou nula");
		if (pratos == null || pratos.size() < 3 || pratos.size() > 4)
			throw new Exception("Uma refeicao deve ser composta de 3 ou 4 pratos"); // adicionar uma nova Exception na hierarquia
	
		this.nome = nome;
		this.descricao = descricao;
		this.pratos = pratos;
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

	public ArrayList<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(ArrayList<Prato> pratos) {
		this.pratos = pratos;
	}

	/**
	 * Calcula o valor total da refeicao. Eh oferecido um desconto sobre
	 * a soma total dos pratos dessa refeicao.
	 * @return valor total da refeicao
	 */
	public double calculaValor()
	{
		double soma = 0;
		final double DESCONTO = 0.1;
		
		for (Prato prato : getPratos())
			soma = soma + prato.getPreco();

		return soma * DESCONTO;
	}
	
	@Override
	public String toString() 
	{
		String toString = "Pratos contidos nessa refeicao:";
				
		for (Prato prato: pratos)
			toString += "\n-> " + prato;
		
		toString += "\nValor total: " + calculaValor();
		return toString;
	}
	
	/**
	 * Dois objetos do tipo Refeicao sao iguais se possuirem
	 * o mesmo nome ou o mesmo conjunto de pratos
	 */
	@Override
	public boolean equals(Object anotherObject)
	{
		if (anotherObject == null)
			return false;
		if (!anotherObject.getClass().equals( this.getClass() ) )
			return false;
		
		Refeicao outra = (Refeicao) anotherObject;
		return this.getNome().equalsIgnoreCase(outra.getNome()) 
				|| this.getPratos().equals(outra.getPratos());
	}
	
	@Override
	public int hashCode()
	{
		final int PRIME = 7;
		int result = 1;
		return PRIME * result + (this.pratos == null ? 0 : this.pratos.hashCode());
	}
	
	
}
