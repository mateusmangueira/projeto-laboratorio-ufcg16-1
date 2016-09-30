package hotel_gotemburgo.hospedagem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Premium implements CartaoFidelidade {

	final double RATE_BONIFICACAO_PADRAO_PONTOS;
	final int BONIFICACAO_EXTRA_PONTOS;
	final double RATE_DESCONTO;
	final double LIMIAR_PADRAO_DESCONTO;
	private int pontos;
	
	public Premium() {
		this.RATE_BONIFICACAO_PADRAO_PONTOS = 0.3;
		this.BONIFICACAO_EXTRA_PONTOS = 10;
		this.RATE_DESCONTO = 0.1;
		this.LIMIAR_PADRAO_DESCONTO = 100.0;
	}
	
	@Override
	public int adicionarPontos(double valor) 
	{
		int recompensa = 0;
		recompensa += (this.RATE_BONIFICACAO_PADRAO_PONTOS * valor);
		
		if (valor> this.LIMIAR_PADRAO_DESCONTO) 
		{
			int pontosExtras = (int) ((valor - this.LIMIAR_PADRAO_DESCONTO) / this.LIMIAR_PADRAO_DESCONTO);
			pontosExtras = pontosExtras * this.BONIFICACAO_EXTRA_PONTOS;					
			recompensa += pontosExtras;
		}
		this.setPontos(this.pontos + recompensa);
		return recompensa;
	}

	@Override
	public double aplicarDesconto(double valor) 
	{
        double valorComDesconto =  valor - (valor * RATE_DESCONTO);

        BigDecimal valorFormatado = new BigDecimal(valorComDesconto).setScale(3, RoundingMode.UP);

        return valorFormatado.doubleValue();
    }

	@Override
	public int pagarDivida(double valor) 
	{
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
