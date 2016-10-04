package hotel_gotemburgo.hospedagem.cartao;

public interface CartaoFidelidade {

	public int adicionarPontos(double valor);

	public double aplicarDesconto(double valor);

	public String convertePontos(int qntPontos);

}
