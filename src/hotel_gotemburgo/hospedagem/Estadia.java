package hotel_gotemburgo.hospedagem;

import verificacao.excecoes.StringException;
import verificacao.excecoes.ValorException;
import verificacao.excecoes.ValoresException;
import hotel_gotemburgo.quartos.Quarto;

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
	 * @param quarto Quarto que compoe a estadia
	 * @param dias Dias de hospedagem dessa estadia
	 * @throws ValoresException Em caso de insercao de valores invalidos
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
	 * Retorna quarto da Estadia
	 * @return quarto A referencia ao atributo quarto da estadia, que eh
	 * do tipo Quarto.java
	 */
	public Quarto getQuarto() {
		return quarto;
	}

	/**
	 * Retorna o ID do quarto da estadia
	 * @return ID do quarto que compoe a estadia. Para isso, acessa o
	 * quarto desta estadia e solicita que ele informe o seu ID.
	 */
	public String getQuartoID() {
		return this.quarto.getId();
	}

	/**
	 * Retorna a quantidade de dias da Estadia
	 * @return Um inteiro correspondente ao valor do atributo 'dias' dessa estadia,
	 * que corresponde a quantos dias o quarto dessa estadia vem sendo utilizado.
	 */
	public int getDias() {
		return dias;
	}

	/**
	 * Metodo que calcula o valor que deve ser pago pela hospedagem nessa estadia.
	 * 
	 * @return Um double que representa a quantia em reais correspondente ao valor
	 * atual dessa estadia. O calculo eh realizado ao multiplicar a quantidade
	 * de dias e o valor do quarto dessa estadia.
	 */
	public double getCalculaEstadia() {
		return this.getDias() * this.getQuarto().getTipoQuarto().getValor();
	}
	
	/**
	 * Duas estadias sao iguais se possuirem o mesmo quarto
	 */
	@Override
	public boolean equals(Object anotherObject) {
		if (anotherObject == null)
			return false;
		if (anotherObject.getClass() != this.getClass())
			return false;
		Estadia outra = (Estadia) anotherObject;
		return (this.quarto.equals(outra.getQuarto()));
	}
	
	/**
	 * Codigo hash de uma estadia
	 */
	@Override
	public int hashCode() {
		final int PRIME = 7;
		int result = 1;
		return PRIME * result + (this.quarto == null ? 0 : this.quarto.hashCode());
	}
	
	/**
	 * Representacao em String de uma Estadia conta com a representacao do seu
	 * quarto e a quantidade de dias de hospedagem.
	 */
	@Override
	public String toString() {
		return String.format("%s Quantidade de dias de hospedagem: %d.",
				this.quarto, this.dias);
	}
	
}