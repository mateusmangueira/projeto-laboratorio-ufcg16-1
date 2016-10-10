package hotel_gotemburgo.hospedagem.cartao;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe que implementa a interface CartaoFidelidade para descrever uma nova estrategia
 * de calculo para as operacoes relacionadas a um cartao. O comportamento descrito
 * nessa classe eh o Premium, que oferece vantagens.
 * 
 * Comportamento:
 * - ao adicionar pontos: bonificacao normal + bonificacao a cada 100 reais
 * - ao aplicar desconto: aplica um desconto padrao em todas os gastos
 * - ao converter pontos: converte os pontos em dinheiro com base no percentual 
 * padrao + um retorno extra a cada certa quantidade de pontos
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 */
public class PremiumStrategy implements CartaoFidelidade {

	final static double RATE_BONIFICACAO_PADRAO_PONTOS = 0.30;
	final static int BONIFICACAO_EXTRA_PONTOS = 10;
	final static double RATE_DESCONTO = 0.10;
	final static double LIMIAR_PADRAO_DESCONTO = 100;
	final static double RATE_SAQUE = 0.30; // Cada ponto de fidelidade vale R$0,30.
	final static double BASE_BONIFICACAO = 10; // A base de bonificacao extra eh de a cada 10 pontos convertidos.
	final static double BONIFICACAO_EXTRA_SAQUE = 0.20; // A cada 10 pontos convertidos, o hospede ganha R$0,20 adicionais.

	/**
	 * Esse metodo adiciona Pontos para o Hospede com Cartao Premium e retorna a
	 * devida recompensa.
	 */
	@Override
	public int adicionarPontos(double valor) {
		int recompensa = 0;
		recompensa += (PremiumStrategy.RATE_BONIFICACAO_PADRAO_PONTOS * valor);

		if (valor > PremiumStrategy.LIMIAR_PADRAO_DESCONTO) {
			int pontosExtras = (int) ((valor / PremiumStrategy.LIMIAR_PADRAO_DESCONTO));
			pontosExtras = pontosExtras * PremiumStrategy.BONIFICACAO_EXTRA_PONTOS;
			recompensa += pontosExtras;
		}
		return recompensa;
	}

	/**
	 * Esse metodo calcula o desconto em operacoes para um Hospede com Cartao Premium 
	 * e retorna o novo valor.
	 */
	@Override
	public double aplicarDesconto(double valor) {
		double valorComDesconto = valor - (valor * RATE_DESCONTO);
		BigDecimal valorFormatado = new BigDecimal(valorComDesconto).setScale(2, RoundingMode.UP);
		return valorFormatado.doubleValue();
	}

	 /**
	  * Esse metodo converte pontos para um hospede realizar um saque em dinheiro com
	  * base nos pontos convertidos, de acordo com a estrategia de calculo Premium.
	  */
	@Override
	public String convertePontos(int qntPontos) {
		double calculoPremium = 0.0;
		if (qntPontos >= PremiumStrategy.BASE_BONIFICACAO) {
			calculoPremium = (qntPontos * PremiumStrategy.RATE_SAQUE) + ((qntPontos / PremiumStrategy.BASE_BONIFICACAO) * PremiumStrategy.BONIFICACAO_EXTRA_SAQUE);
		} else {
			calculoPremium = (qntPontos * PremiumStrategy.RATE_SAQUE);
		}
		return String.format("R$%.2f", calculoPremium);
	}

}
