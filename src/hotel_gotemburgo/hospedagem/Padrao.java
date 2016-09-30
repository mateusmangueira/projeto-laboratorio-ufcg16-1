package hotel_gotemburgo.hospedagem;

public class Padrao implements CartaoFidelidade {

	private final double RECOMPENSA;
	private int pontos;
	
	public Padrao() {
		this.RECOMPENSA = 0.1;
		this.pontos = 0;
	}
	
	@Override
	public int adicionarPontos(double valor) {
		int recompensa = (int) (RECOMPENSA * valor);
		this.setPontos(this.pontos + recompensa);
		return recompensa;
	}

	@Override
	public double aplicarDesconto(double valor) {
		return valor;
	}

	@Override
	public int pagarDivida(double valor) {
		return 0;
	}

	@Override
	public int getPontos() {
		return this.pontos;
	}

	@Override
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

}
