package hotel_gotemburgo.hospedagem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Premium implements CartaoFidelidade {

	@Override
	public int adicionarPontos(double valor) {
		
		final double BONIFICACAO = 0.3;
		int recompensa = 0;
		
		recompensa += (BONIFICACAO * valor);
		if (valor> 100){
			int extra = (int) ((valor - 100)/100);// diminuo 100 e dps divido por cem pra saber qnts 100 tem, ou seja: 9 e dps multiplico  por 10
			extra = extra * 10;					// nao sei se ta certo att Kleber
			recompensa += extra;
		}
		return recompensa;
	}

	@Override
	public double aplicarDesconto(double valor) {
		
		final double DESCONTO = 0.1;
        double valorComDesconto =  valor - (valor * DESCONTO);

        BigDecimal valorFormatado = new BigDecimal(valorComDesconto).setScale(3, RoundingMode.UP);

        return valorFormatado.doubleValue();
    }
			
}

	
	

	
	/**public boolean pagarDivida(String emailHospede) {
		// TODO Auto-generated method stub
		return false;
	}*/


