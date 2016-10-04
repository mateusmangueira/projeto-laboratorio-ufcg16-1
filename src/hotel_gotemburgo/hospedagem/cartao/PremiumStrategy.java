package hotel_gotemburgo.hospedagem.cartao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PremiumStrategy implements CartaoFidelidade {

	final double RATE_BONIFICACAO_PADRAO_PONTOS;
	final int BONIFICACAO_EXTRA_PONTOS;
	final double RATE_DESCONTO;
	final double LIMIAR_PADRAO_DESCONTO;

	// Cada ponto de fidelidade vale R$0,30.
	final double RATE_SAQUE;

	// A base de bonificacao extra eh de a cada 10 pontos convertidos.
	final double BASE_BONIFICACAO;

	// A cada 10 pontos convertidos, o hospede ganha R$0,20 adicionais.
	final double BONIFICACAO_EXTRA_SAQUE;


	/**
	 * Construtor do da Classe Premium que implementa a Interface Cartao
	 * Fidelidade ele inicializa ah bonificacao, o desconto, o limiar padrao e
	 * os pontos
	 */
	public PremiumStrategy() {
		this.RATE_BONIFICACAO_PADRAO_PONTOS = 0.30;
		this.BONIFICACAO_EXTRA_PONTOS = 10;
		this.RATE_DESCONTO = 0.10;
		this.LIMIAR_PADRAO_DESCONTO = 100.0;
		this.RATE_SAQUE = 0.30;
		this.BONIFICACAO_EXTRA_SAQUE = 0.20;
		this.BASE_BONIFICACAO = 10;
	}

	/**
	 * esse metodo adiciona Pontos para o Hospede com Cartao Premium retorna a
	 * devida recompensa para o Hospede Premium
	 */
	@Override
	public int adicionarPontos(double valor) {
		int recompensa = 0;
		recompensa += (this.RATE_BONIFICACAO_PADRAO_PONTOS * valor);

		if (valor > this.LIMIAR_PADRAO_DESCONTO) {
			int pontosExtras = (int) ((valor - this.LIMIAR_PADRAO_DESCONTO) / this.LIMIAR_PADRAO_DESCONTO);
			pontosExtras = pontosExtras * this.BONIFICACAO_EXTRA_PONTOS;
			recompensa += pontosExtras;
		}
		return recompensa;
	}

	/**
	 * esse metodo calcula o desconto para o Hospede com cartao premium e
	 * retorna o novo valor
	 */
	@Override
	public double aplicarDesconto(double valor) {
		double valorComDesconto = valor - (valor * RATE_DESCONTO);
		BigDecimal valorFormatado = new BigDecimal(valorComDesconto).setScale(2, RoundingMode.UP);
		return valorFormatado.doubleValue();
	}

	@Override
	public String convertePontos(int qntPontos) {
		double calculoPremium = (qntPontos * this.RATE_SAQUE) + ((qntPontos / this.BASE_BONIFICACAO) * this.BONIFICACAO_EXTRA_SAQUE);
		return String.format("R$%.2f", calculoPremium);
	}

}
