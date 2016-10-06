package hotel_gotemburgo.hospedagem.cartao;

/**
 * Essa interface eh utilizada como abstracao de um cartao de pontos. Ela declara tres
 * metodos: adicionarPontos(), aplicarDesconto() e convertePontos().
 *
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 */
public interface CartaoFidelidade {

	public int adicionarPontos(double valor);

	public double aplicarDesconto(double valor);

	public String convertePontos(int qntPontos);

}
