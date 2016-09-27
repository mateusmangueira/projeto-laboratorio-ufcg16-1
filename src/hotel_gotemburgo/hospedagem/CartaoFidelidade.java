package hotel_gotemburgo.hospedagem;

public interface CartaoFidelidade {

	// Eh uma interface pq so tera comportamentos de adicionar pontos, aplicar
	// descontos e pagar dividas. As classes que implementa essa interface que
	// tera o atributo pontuacao que comeca em 0 e vai crescendo ao adicionar
	// mais. by: Mateus

	public boolean adicionarPontos(String emailHospede);

	public boolean aplicarDesconto(String emailHospede);

	public boolean pagarDivida(String emailHospede);

}