package hotel_gotemburgo.hospedagem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vip implements CartaoFidelidade {

	private final double RATE_DESCONTO;
	private final double RATE_BONIFICACAO_PONTOS;
	private int pontos;

	public Vip() {
		this.RATE_DESCONTO = 0.15;
		this.RATE_BONIFICACAO_PONTOS = 0.5;
		this.pontos = 0;
	}

	@Override
	public int adicionarPontos(double valor) {
		int recompensa = (int) (valor * RATE_BONIFICACAO_PONTOS);
		this.setPontos(this.pontos + recompensa);
		return recompensa;
	}

	@Override
	public double aplicarDesconto(double valor) {
		double valorComDesconto = valor - (valor * RATE_DESCONTO);
		BigDecimal valorFormatado = new BigDecimal(valorComDesconto).setScale(3, RoundingMode.UP);
		valorComDesconto = valorFormatado.doubleValue();

		int descontoExtra = (int) (valor / 100.0);

		return valorComDesconto - descontoExtra;
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

	@Override
	public String convertePontos(int qntPontos) {
		return String.format("R$.2f", (qntPontos*0.70) + (qntPontos/10) * 0.20);
	}

}
