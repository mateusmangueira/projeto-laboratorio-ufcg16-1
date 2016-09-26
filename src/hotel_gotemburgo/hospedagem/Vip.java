package hotel_gotemburgo.hospedagem;

public class Vip implements CartaoFidelidade {

	private int pontuacao;

	public Vip() {
		this.pontuacao = 0;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public boolean adicionarPontos(String emailHospede) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aplicarDesconto(String emailHospede) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pagarDivida(String emailHospede) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
