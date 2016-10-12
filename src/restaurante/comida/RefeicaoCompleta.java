package restaurante.comida;

import java.util.ArrayList;

import verificacao.excecoes.Excecoes;
import verificacao.excecoes.StringException;
import verificacao.excecoes.ValorException;
import verificacao.excecoes.ValoresException;

/**
 * Representa uma refeicao completa, que eh composta por pratos. Possui um nome,
 * uma descricao propria, e uma sequencia de pratos, podendo englobar: entrada,
 * prato principal, sobremesa e petit four. Sobremesa ou o petit four sao
 * opcionais, logo, uma refeicao deve possuir 3 ou 4 pratos, nao menos do que
 * isso. A ordem dos pratos eh importante, pois representara a refeicao a que se
 * refere.
 * 
 * @since 18 de Setembro de 2016
 * @see Prato.java
 */
public class RefeicaoCompleta extends Comida {

	private ArrayList<Prato> pratos;
	private static final String LINE_SEPARATOR = System.lineSeparator();

	/**
	 * O Construtor recebe como parametro o nome e descricao do prato, alem de
	 * uma lista de objetos do tipo Prato que compoe uma refeicao (3 ou 4
	 * pratos).
	 * 
	 * @param nome O nome da refeicao completa
	 * @param descricao Detalhes sobre a refeicao
	 * @param pratos Uma lista de pratos que compoe a refeicao
	 * @throws ValoresException Caso o construtor recebe valores invalidos como parametros
	 */
	public RefeicaoCompleta(String nome, String descricao, ArrayList<Prato> pratos) throws ValoresException {
		
		super(nome, descricao);
		
		Excecoes.checaString(nome, "O nome da refeicao nao pode ser nulo ou vazio.");
		Excecoes.checaString(descricao, "A descricao da refeicao nao pode ser nula ou vazia.");
		
		if (pratos == null || pratos.size() < 3 || pratos.size() > 4)
			throw new ValorException("Uma refeicao deve ser composta de 3 ou 4 pratos.");

		this.pratos = pratos;
	}

	/**
	 * Altera o atributo nome da refeicao.
	 * 
	 * @param nome O novo nome da refeicao
	 * @throws StringException Caso o novo nome seja invalido
	 */
	@Override
	public void setNome(String nome) throws StringException {
		Excecoes.checaString(nome, "O nome da refeicao nao pode ser nulo ou vazio.");
		this.nome = nome;
	}

	/**
	 * Retorna uma string que representa a descricao da refeicao. Alem da
	 * descricao recebida como parametro no construtor, a String criada e
	 * retornada nesse metodo tambem adiciona a ordem e o nome dos pratos.
	 * 
	 * @return Descricao da refeicao
	 */
	@Override
	public String getDescricao() {
		String retorno = this.descricao + " Serao servidos: ";
		for (int i = 0; i < this.pratos.size(); i++) {
			retorno += ", (" + (i + 1) + ") " + this.pratos.get(i).getNome();
		}
		return retorno.replaceFirst(", ", "") + ".";
	}
	
	/**
	 * Altera a descricao da refeicao.
	 * 
	 * @param descricao A nova descricao da refeicao
	 */
	@Override
	public void setDescricao(String descricao) throws ValoresException {
		Excecoes.checaString(descricao, "A descricao da refeicao nao pode ser nula ou vazia.");
		this.descricao = descricao;
	}

	/**
	 * Calcula o valor total da refeicao. Eh oferecido um desconto sobre o valor
	 * da soma dos pratos dessa refeicao.
	 * 
	 * @return Valor total da refeicao
	 */
	public double getPreco() {
		double soma = 0;
		final double DESCONTO = 0.1;

		for (Comida prato : this.pratos)
			soma = soma + prato.getPreco();

		return soma - (soma * DESCONTO);
	}

	/**
	 * Representacao em String de um objeto do tipo RefeicaoCompleta.
	 */
	@Override
	public String toString() {
		String toString = "Pratos contidos nessa refeicao:";

		for (Comida prato : pratos)
			toString += RefeicaoCompleta.LINE_SEPARATOR + "-> " + prato;

		toString += RefeicaoCompleta.LINE_SEPARATOR + "Valor total: " + getPreco();
		return toString;
	}

	/**
	 * Dois objetos do tipo Refeicao sao iguais se possuirem o mesmo nome ou o
	 * mesmo conjunto de pratos.
	 */
	@Override
	public boolean equals(Object anotherObject) {
		if (anotherObject == null)
			return false;
		if (!anotherObject.getClass().equals(this.getClass()))
			return false;

		RefeicaoCompleta outra = (RefeicaoCompleta) anotherObject;
		return this.getNome().equalsIgnoreCase(outra.getNome()) || this.pratos.equals(outra.pratos);
	}

	/**
	 * Codigo hash de um objeto do tipo Refeicao.
	 */
	@Override
	public int hashCode() {
		final int PRIME = 7;
		int result = 1;
		return PRIME * result + (this.nome == null ? 0 : this.nome.hashCode())
				+ (this.pratos == null ? 0 : this.pratos.hashCode());
	}

}
