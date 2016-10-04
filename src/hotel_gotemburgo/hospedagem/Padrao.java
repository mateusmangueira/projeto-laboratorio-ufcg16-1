package hotel_gotemburgo.hospedagem;

public class Padrao implements CartaoFidelidade {

	private final double RECOMPENSA;
	
	// Para cada ponto de fidelidade, o hospede consegue sacar R$0,10;
	private final double RATE_SAQUE;
	private int pontos;
	/**
	 * construtor da classe Padrao que implementa a Inteface: Cartao de Fidelidade
	 * esse contruto inicializa os pontos e a recompensa
	 */
	public Padrao() {
		this.RECOMPENSA = 0.1;
		this.RATE_SAQUE = 0.10;
		this.pontos = 0;
	}
	
	/**
	 *Metodo para adicionar pontos ao hospede com cartao fidelidade Padrao
	 *recebe um valor de entrar e retorna a recompensa do determinado hospede
	 */
	@Override
	public int adicionarPontos(double valor) {
		int recompensa = (int) (RECOMPENSA * valor);
		this.setPontos(this.pontos + recompensa);
		return recompensa;
	}
	/**
	 * Como o hospede possui um cartao padrao, ele nao possui desconto
	 * e esse metodo retorna apenas o valor de entrada
	 */
	@Override
	public double aplicarDesconto(double valor) {
		return valor; // Retorna o proprio valor da cobranca.
	}
	/**
	 * 
	 */
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

	@Override
	public String convertePontos(int qntPontos) {
		return String.format("R$%.2f", (qntPontos * this.RATE_SAQUE));
	}

}