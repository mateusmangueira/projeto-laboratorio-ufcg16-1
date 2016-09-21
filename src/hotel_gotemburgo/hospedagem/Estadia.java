package hotel_gotemburgo.hospedagem;

import hotel_gotemburgo.quartos.Quarto;
import excecoes.*;

/**
 * Estadia - Classe que representa uma Estadia, eh composta por um Quarto e tem
 * uma quantidade de dias
 * 
 * @since 12 de Setembro de 2016
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - matricula <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
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
	 * @throws ValoresException
	 */
	public Estadia(Quarto quarto, int dias) throws ValoresException {

		if (quarto == null)
			throw new StringException("O quarto nao pode ser nulo.");

		if (dias <= 0)
			throw new ValorException("A quantidade de dias nao pode ser menor ou igual a zero.");

		this.quarto = quarto;
		this.dias = dias;
	}
	
	/**
	 * Retorna o atributo quarto da Estadia
	 * @return quarto
	 */
	public Quarto getQuarto() {
		return quarto;
	}

	/**
	 * Retorna o ID do quarto da estadia
	 * @return ID do quarto
	 */
	public String getQuartoID() {
		return this.quarto.getId();
	}
	
	/**
	 * Altera o atributo quarto da Estadia com uma nova referencia recebida como parametro
	 * @param quarto
	 * @throws ValoresException 
	 */
	public void setQuarto(Quarto quarto) throws ValoresException {
		if (quarto == null) {
			throw new ValorException("O quarto nao pode ser nulo.");
		}
		this.quarto = quarto;
	}

	/**
	 * Retorna a quantidade de dias que a Estadia vem sendo utilizada
	 * @return
	 */
	public int getDias() {
		return dias;
	}
	/**
	 * Altera o atributo dias da Estadia com uma nova quantia recebida como parametro
	 * @param dias
	 * @throws ValoresException 
	 */
	public void setDias(int dias) throws ValoresException {
		if (dias < 0) {
			throw new ValorException("A quantidade de dias nao pode ser negativa.");
		}
		this.dias = dias;
	}

	/**
	 * Metodo que calcula o valor que sera pago pelo hospede por ter ficado
	 * determinados dias em um quarto
	 * 
	 * @param quarto
	 * @return double
	 */
	public double getCalculaEstadia() {
		return this.getDias() * this.getQuarto().getTipoQuarto().getValor();
	}
	
	
	
	
}
