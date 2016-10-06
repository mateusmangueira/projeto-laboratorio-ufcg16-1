package hotel_gotemburgo.hospedagem;

import verificacao.excecoes.Excecoes;
import verificacao.excecoes.ValoresException;
import hotel_gotemburgo.quartos.TipoDeQuarto;

/**
 * Essa classe representa uma estadia. A estadia nada mais eh do que um objeto
 * que representa a presenca em um quarto do hotel. Sendo assim, ela eh composta
 * de:
 * - ID do quarto
 * - Tipo do quarto
 * - Quantidade de dias de estadia nesse quarto
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
	 * Construtor padrao da classe Estadia. Recebe uma String representando o ID do quarto,
	 * um objeto TipoDeQuarto, e um inteiro que representa a quantidade de dias nessa estadia.
	 * 
	 * @param idQuarto Uma String representando o ID do quarto, que eh uma identificacao unica
	 * para esse quarto, composta de letras e numeros
	 * @param dias Dias de hospedagem nessa estadia
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
	
	/**
	 * Retorna o ID do quarto que compoe estadia
	 * @return String que representa um quarto de forma unica, atraves de letras e numeros
	 */
	public String getIdQuarto() {
		return this.idQuarto;
	}

	/**
	 * Modifica o ID do quarto atual para um novo
	 * @param idQuarto Novo ID do quarto que substituira o antigo
	 */
	public void setIdQuarto(String idQuarto) {
		this.idQuarto = idQuarto;
	}

	/**
	 * Retorna uma constante do Enum TipoDeQuarto. Essa constante representa o tipo
	 * do quarto que compoe a estadia
	 * @return Constante de um enum TipoDeQuarto, representando o tipo do quarto
	 */
	public TipoDeQuarto getTipoQuarto() {
		return this.tipoQuarto;
	}

	/**
	 * Modifica o ID do quarto atual para um novo valor
	 * @param idQuarto Novo ID do quarto que substituira o antigo
	 */
	public void setTipo(TipoDeQuarto tipo) {
		this.tipoQuarto = tipo;
	}
	
	/**
	 * Retorna a quantidade de dias da Estadia
	 * @return Um inteiro correspondente ao valor do atributo 'dias' dessa estadia,
	 * que corresponde a quantos dias o quarto dessa estadia vem sendo utilizado.
	 */
	public int getDias() {
		return this.dias;
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
	
	/**
	 * Representacao em String de uma Estadia
	 */
	@Override
	public String toString() {
		return String.format("%s Quantidade de dias de hospedagem: %d.",
				this.idQuarto, this.dias);
	}	
	
}