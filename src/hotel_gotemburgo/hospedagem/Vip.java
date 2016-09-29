package hotel_gotemburgo.hospedagem;

public class Vip implements CartaoFidelidade {


	@Override
	public int adicionarPontos(double valor) {
		
		return (int) (valor * 0.5);
	}

	@Override
	public double aplicarDesconto(double valor ) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	
	
	

}
