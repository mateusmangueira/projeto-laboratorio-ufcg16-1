package hotel_gotemburgo.hospedagem.cartao;

public class PadraoStrategy implements CartaoFidelidade {

	private final double RECOMPENSA;

	// Para cada ponto de fidelidade, o hospede consegue sacar R$0,10;
	private final double RATE_SAQUE;

	/**
	 * construtor da classe Padrao que implementa a Inteface: Cartao de
	 * Fidelidade esse contruto inicializa os pontos e a recompensa
	 */
	public PadraoStrategy() {
		this.RECOMPENSA = 0.10;
		this.RATE_SAQUE = 0.10;
	}

	/**
	 * Metodo para adicionar pontos ao hospede com cartao fidelidade Padrao
	 * recebe um valor de entrar e retorna a recompensa do determinado hospede
	 */
	@Override
	public int adicionarPontos(double valor) {
		int recompensa = (int) (RECOMPENSA * valor);
		return recompensa;
	}

	/**
	 * Como o hospede possui um cartao padrao, ele nao possui desconto e esse
	 * metodo retorna apenas o valor de entrada
	 */
	@Override
	public double aplicarDesconto(double valor) {
		return valor;
	}
	
	@Override
	public String convertePontos(int qntPontos) {
		double calculoPadrao = (qntPontos * this.RATE_SAQUE);
		return String.format("R$%.2f", calculoPadrao);
	}

}