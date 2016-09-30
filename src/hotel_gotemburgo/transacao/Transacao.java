package hotel_gotemburgo.transacao;

public class Transacao {

	private String nomeHospede;
	private double valor;
	private String descricao;

	public Transacao(String nome, double valor, String descricao) {
		this.nomeHospede = nome;
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public void setNomeHospede(String nomeHospede) {
		this.nomeHospede = nomeHospede;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return this.descricao;
	}

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