package hotel_gotemburgo.hospedagem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vip implements CartaoFidelidade {

	private final double RATE_DESCONTO;
	private final double RATE_BONIFICACAO_PONTOS;

	/*
	 * Desconto no qual pega o valor jah descontado dos 15% e subtrai por esse
	 * desconto, no qual, a cada 100,0 reais em compras, ele recebe um desconto
	 * de 10%.
	 */
	private final double RATE_BASE_DESCONTO_EXTRA;

	// Cada ponto de fidelidade vale R$0,70.
	final double RATE_SAQUE;

	// A base de bonificacao extra eh de a cada 10 pontos convertidos.
	final double BASE_BONIFICACAO;

	// A cada 10 pontos convertidos, o hospede ganha R$0,50 adicionais.
	final double BONIFICACAO_EXTRA_SAQUE;


	/**
	 * Construtor da classe VIP que implementa a interface Cartao de fidelidade
	 * Ele inicia o Desconto, bonificacao e os pontos
	 */
	public Vip() {
		this.RATE_DESCONTO = 0.15;
		this.RATE_BONIFICACAO_PONTOS = 0.5;
		this.RATE_BASE_DESCONTO_EXTRA = 100.0; // A cada 100.0 reais, ele dah um
												// desconto de 10%.
		this.RATE_SAQUE = 0.70;
		this.BASE_BONIFICACAO = 10;
		this.BONIFICACAO_EXTRA_SAQUE = 0.50;
	}

	/**
	 * Esse metodo adiciona Pontos para o Hospede com cartao de fidelidade vip
	 * retorna a recompensa do devido hospede
	 */
	@Override
	public int adicionarPontos(double valor) {
		int recompensa = (int) (valor * this.RATE_BONIFICACAO_PONTOS);
		return recompensa;
	}

	/**
	 * esse metodo aplica o Desconto para o hospede Vip retorna o valor com o
	 * desconto
	 */
	@Override
	public double aplicarDesconto(double valor) {
		
		double valorComDesconto = valor - (valor * this.RATE_DESCONTO);
		
//		valorComDesconto *= 100.0;
//		Math.ceil(valorComDesconto);
//		valorComDesconto /= 100.0;
		
		BigDecimal valorFormatado = new BigDecimal(valorComDesconto).setScale(3, RoundingMode.UP);
		valorComDesconto = valorFormatado.doubleValue();

		int descontoExtra = (int) (valor / this.RATE_BASE_DESCONTO_EXTRA);

		return valorComDesconto - descontoExtra;
	}

	@Override
	public int pagarDivida(double valor) {
		return 0;
	}

	@Override
	public String convertePontos(int qntPontos) {
		return String.format("R$.2f",
				(qntPontos * this.RATE_SAQUE) + ((qntPontos / this.BASE_BONIFICACAO) * this.BONIFICACAO_EXTRA_SAQUE));
	}

}
