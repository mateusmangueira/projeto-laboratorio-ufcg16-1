package hotel_gotemburgo.hospedagem.cartao;

/**
 * Classe que implementa a interface CartaoFidelidade para descrever uma nova estrategia
 * de calculo para as operacoes relacionadas a um cartao. O comportamento descrito
 * nessa classe eh o padrao, ou seja, o comportamento inicial dos cartoes do sistema.
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br> *
 */
public class PadraoStrategy implements CartaoFidelidade {

	private static final double RECOMPENSA = 0.10;
	// Para cada ponto de fidelidade, o hospede consegue sacar R$0,10;
	private static final double RATE_SAQUE = 0.10;

	/**
	 * Metodo para adicionar pontos ao hospede com cartao fidelidade Padrao
	 * recebe um valor de entrar e retorna a recompensa do determinado hospede
	 */
	@Override
	public int adicionarPontos(double valor) {
		int recompensa = (int) (PadraoStrategy.RECOMPENSA * valor);
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
		double calculoPadrao = (qntPontos * PadraoStrategy.RATE_SAQUE);
		return String.format("R$%.2f", calculoPadrao);
	}

}