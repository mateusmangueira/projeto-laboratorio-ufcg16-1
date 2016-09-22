package hotel_gotemburgo.transacao;

public class Transacao {

	private String nomeHospede;
	private double valor;

	public Transacao(String nome, double valor) {
		this.nomeHospede = nome;
		this.valor = valor;
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

}
