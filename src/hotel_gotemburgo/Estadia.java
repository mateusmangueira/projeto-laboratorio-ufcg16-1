package hotel_gotemburgo;

import quartos.Quarto;
import excecoes.*;

/**
 * Estadia - Classe que representa uma Estadia, eh composta por um Quarto e tem
 * uma quantidade de dias
 * 
 * Criada em 12 de Setembro, 2016
 * 
 * @author Anderson Vital matricula <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo matricula <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christhoper Matricula <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */

public class Estadia {

	private Quarto quarto;
	private int dias;

	/**
	 * Construtor padrao da classe Estadia que recebe um quarto e a quantidade
	 * de dias
	 * 
	 * @param quarto
	 * @param dias
	 * @throws HotelException
	 */
	public Estadia(Quarto quarto, int dias) throws HotelException {

		if (quarto.equals(null)) {
			throw new QuartoInvalidoException("O quarto nao pode ser nulo.");
		}

		if (dias <= 0) {
			throw new ValorInvalidoException("A quantidade de dias nao pode ser menor ou igual a zero.");
		}

		this.quarto = quarto;
		this.dias = dias;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	/**
	 * Metodo que calcula o valor que sera pago pelo hospede por ter ficado
	 * determinados dias em um quarto
	 * 
	 * @param quarto
	 * @return double
	 */
	public double calculaDiaria(Quarto quarto) {
		return this.dias * quarto.getValorDiaria();
	}
}
