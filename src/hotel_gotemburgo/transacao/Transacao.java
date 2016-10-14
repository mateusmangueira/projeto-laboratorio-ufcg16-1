package hotel_gotemburgo.transacao;

import java.io.Serializable;

/**
 * A classe Transacao eh responsavel por representar uma transacao do sistema, que possui o nome
 * de um Hospede, o valor dessa transacao e sua descricao.
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */
public class Transacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4084223959368361456L;
	
	private String nomeHospede;
	private double valor;
	private String descricao;

	/**
	 * O construtor recebe 3 parametros, instanciando todos os atributos da classe.
	 * 
	 * @param nome Uma String que representa nome do Hospede que esta efetuando a transacao
	 * @param valor Um double que representa o valor total da transacao que esta sendo efetuada
	 * @param descricao Uma String que descreve detalhes sobre a transacao
	 */
	public Transacao(String nome, double valor, String descricao) {
		
		this.nomeHospede = nome;
		this.valor = valor;
		this.descricao = descricao;
	}

	/**
	 * Retorna o atributo nome do hospede.
	 * @return A string que representa o nome do hospede que esta realizando a transacao
	 */
	public String getNomeHospede() {
		return this.nomeHospede;
	}

	/**
	 * Retorna o atributo valor da transacao.
	 * @return O double que representa o valor da transacao
	 */
	public double getValor() {
		return this.valor;
	}

	/**
	 * Retorna o atributo descricao da transacao.
	 * @return A String que representa a descricao dos detalhes da transacao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Codigo hash de um objeto do tipo Transacao.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeHospede == null) ? 0 : nomeHospede.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Duas transacoes sao iguais caso possuam o mesmo nome de hospede, o mesmo valor e a mesma descricao.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (nomeHospede == null) {
			if (other.nomeHospede != null)
				return false;
		} else if (!nomeHospede.equals(other.nomeHospede))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}