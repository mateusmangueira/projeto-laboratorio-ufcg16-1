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

	private final double RATE_DESCONTO = 0.15;
	private final double RATE_BONIFICACAO_PONTOS = 0.50;
	/* Desconto no qual pega o valor jah descontado dos 15% e subtrai por esse
	 * desconto, no qual, a cada 100,0 reais em compras, ele recebe um desconto
	 * de 10%. */
	private final double RATE_BASE_DESCONTO_EXTRA = 100.00;
	// Cada ponto de fidelidade vale R$0,70.
	final double RATE_SAQUE = 0.70;
	// A base de bonificacao extra eh de a cada 10 pontos convertidos.
	final double BASE_BONIFICACAO = 10.00;
	// A cada 10 pontos convertidos, o hospede ganha R$0,50 adicionais.
	final double BONIFICACAO_EXTRA_SAQUE = 0.50;
	// A cada a cada R$100,00 reais gastos, o hospede recebe um desconto de R$10,00 dos R$100,00 pagos.
	final int LIMIAR_PADRAO_DESCONTO = 10;

	/**
	 * Esse metodo adiciona Pontos para o Hospede com cartao fidelidade Vip
	 * retorna o valor sacado.
	 */
	@Override
	public int adicionarPontos(double valor) {
		int recompensa = (int) (valor * this.RATE_BONIFICACAO_PONTOS);
		return recompensa;
	}

	/**
	 * Esse metodo aplica um desconto para o hospede com cartao fidelidade Vip 
	 * e retorna o valor com o desconto.
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
	
	 /**
	  * Esse metodo converte pontos para um hospede realizar um saque em dinheiro com
	  * base nos pontos convertidos, de acordo com a estrategia de calculo Vip.
	  */
	@Override
	public String convertePontos(int qntPontos) {
		double calculoVip = (qntPontos * this.RATE_SAQUE) + ((qntPontos / this.BASE_BONIFICACAO) * this.BONIFICACAO_EXTRA_SAQUE);
		return String.format("R$%.2f", calculoVip);
	}

}
