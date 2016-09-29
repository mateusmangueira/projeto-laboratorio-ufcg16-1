package hotel_gotemburgo.hospedagem;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Premium implements CartaoFidelidade {

	@Override
	public int adicionarPontos(double valor) {
		int recompensa = 0;
		
		recompensa += (0.3 * valor);
		if(valor> 100){
			int extra = (int) ((valor - 100)/100);// diminuo 100 e dps divido por cem pra saber qnts 100 tem, ou seja: 9 e dps multiplico  por 10
			extra = extra * 10;					// nao sei se ta certo att Kleber
			recompensa += extra;
		}
		return recompensa;
	}

	@Override
	public double aplicarDesconto(double valor) {
        double valorComDesconto =  valor - (valor * 0.1);

        DecimalFormat formatador = new DecimalFormat("#.00");// esse metodo formata o valores decimais(que nesse caso e para duas casas decimais)
        formatador.setRoundingMode(RoundingMode.UP);// esse metodo arredonda para cima o dable retornado

        double valorComDescontoFormatado = Double.parseDouble(formatador.format(valorComDesconto)); // usamos parseDouble pois o Decimal format retorna uma string
        return valorComDescontoFormatado;
    }
		
		
	}

	
	

	
	/**public boolean pagarDivida(String emailHospede) {
		// TODO Auto-generated method stub
		return false;
	}*/


