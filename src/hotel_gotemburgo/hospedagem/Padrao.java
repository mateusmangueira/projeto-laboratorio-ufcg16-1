package hotel_gotemburgo.hospedagem;

public class Padrao implements CartaoFidelidade {

	

	@Override
	public int adicionarPontos(double valor) {
		
		return (int) (0.1 * valor);
	}

	@Override
	public double aplicarDesconto(double valor) {
		return valor;
	}

	
	/**public int pagarDivida(double valor) {
		// TODO Auto-generated method stub
		return 0;
	}*/

}
