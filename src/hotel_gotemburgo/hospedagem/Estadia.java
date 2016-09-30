package hotel_gotemburgo.hospedagem;

import verificacao.excecoes.Excecoes;
import verificacao.excecoes.ValoresException;
import hotel_gotemburgo.quartos.TipoDeQuarto;

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

	private String idQuarto;
	private TipoDeQuarto tipoQuarto;
	private int dias;

	/**
	 * Construtor padrao da classe Estadia que recebe um quarto e a quantidade
	 * de dias
	 * 
	 * @param quarto Quarto que compoe a estadia
	 * @param dias Dias de hospedagem dessa estadia
	 * @throws ValoresException Em caso de insercao de valores invalidos
	 */
	public Estadia(String idQuarto, TipoDeQuarto tipo, int dias) throws ValoresException {

		Excecoes.checaString(idQuarto, "Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");
		Excecoes.checaEnum(tipo, "O tipo do quarto nao pode ser nulo.");
		Excecoes.checaInt(dias, "A quantidade de dias nao pode ser menor ou igual a zero.");
		
		this.idQuarto = idQuarto;
		this.tipoQuarto = tipo;
		this.dias = dias;
	}
	
	public String getIdQuarto() {
		return idQuarto;
	}

	public void setIdQuarto(String idQuarto) {
		this.idQuarto = idQuarto;
	}

	public TipoDeQuarto getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipo(TipoDeQuarto tipo) {
		this.tipoQuarto = tipo;
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
		return this.getDias() * this.getTipoQuarto().getValor();
	}
	
	/**
	 * Codigo hash de uma estadia
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idQuarto == null) ? 0 : idQuarto.hashCode());
		return result;
	}

	/**
	 * Representacao em String de uma Estadia conta com a representacao do seu
	 * quarto e a quantidade de dias de hospedagem.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estadia other = (Estadia) obj;
		if (idQuarto == null) {
			if (other.idQuarto != null)
				return false;
		} else if (!idQuarto.equals(other.idQuarto))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%s Quantidade de dias de hospedagem: %d.",
				this.idQuarto, this.dias);
	}	
	
}