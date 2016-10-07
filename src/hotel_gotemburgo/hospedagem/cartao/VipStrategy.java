package hotel_gotemburgo.hospedagem.cartao;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe que implementa a interface CartaoFidelidade para descrever uma nova estrategia
 * de calculo para as operacoes relacionadas a um cartao. O comportamento descrito
 * nessa classe eh o Premium, que oferece vantagens.
 * 
 * Comportamento:
 * - ao adicionar pontos: uma bonificacao padrao bastante elevada
 * - ao aplicar desconto: aplica um alto desconto padrao para todos os gastos
 * - ao converter pontos: converte os pontos em dinheiro com base no percentual 
 * padrao + um retorno extra, que eh alto, a cada certa quantidade de pontos
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 */
public class VipStrategy implements CartaoFidelidade {

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
	
	// A cada a cada R$100,00 reais gastos, o hospede recebe um desconto de R$10,00 dos R$100,00 pagos.
	final int LIMIAR_PADRAO_DESCONTO;

	/**
	 * Construtor da classe VIP que implementa a interface Cartao de fidelidade
	 * Ele inicia o Desconto, bonificacao e os pontos
	 */
	public VipStrategy() {
		this.RATE_DESCONTO = 0.15;
		this.RATE_BONIFICACAO_PONTOS = 0.50;
		this.RATE_BASE_DESCONTO_EXTRA = 100.00;
		this.RATE_SAQUE = 0.70;
		this.BASE_BONIFICACAO = 10.00;
		this.BONIFICACAO_EXTRA_SAQUE = 0.50;
		this.LIMIAR_PADRAO_DESCONTO = 10;
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
		BigDecimal valorFormatado = new BigDecimal(valorComDesconto).setScale(2, RoundingMode.UP);
		valorComDesconto = valorFormatado.doubleValue();
		if (valor > this.RATE_BASE_DESCONTO_EXTRA) {
			int descontoExtra = (int) (valor / this.LIMIAR_PADRAO_DESCONTO);
			
			//O descontoExtra eh o desconto de R$10,00 reais a cada R$100,00 gastos.
			valorComDesconto -= descontoExtra;
			
		}
		return valorComDesconto;
	}
	
	@Override
	public String convertePontos(int qntPontos) {
		double calculoVip = (qntPontos * this.RATE_SAQUE) + ((qntPontos / this.BASE_BONIFICACAO) * this.BONIFICACAO_EXTRA_SAQUE);
		return String.format("R$%.2f", calculoVip);
	}

}
