package hotel_gotemburgo.quartos;

import excecoes.StringException;
import excecoes.ValorException;
import excecoes.ValoresException;

/**
 * Essa classe representa um Quarto, que possui um ID
 * (uma String que identifica esse quarto) e um tipo,
 * que eh dado atraves de uma constante do Enum
 * TipoDeQuarto
 */
public class Quarto {

	private String id;
	private TipoDeQuarto tipoQuarto;

	/**
	 * O construtor recebe um ID (identificacao unica) e um tipo
	 * de Quarto, representado por uma constante do Enum TipoDeQuarto.
	 * 
	 * @param idQuarto String
	 * @param tipoQuarto
	 * @throws StringException
	 */
	public Quarto(String idQuarto, TipoDeQuarto tipoQuarto) throws StringException {
		if (idQuarto == null || idQuarto.trim().isEmpty()) {
			throw new StringException("O Id do quarto nao pode ser nulo ou vazio.");
		}

		this.id = idQuarto;
		this.tipoQuarto = tipoQuarto;
	}

	/**
	 * Retorna a String que representa o ID(uma identificacao unica) do
	 * quarto
	 * 
	 * @return constante TipoDeQuarto
	 */
	public String getId() {
		return id;
	}

	/**
	 * Altera o ID atual do quarto, apos verificacao.
	 * @param id
	 * 
	 * @throws ValoresException
	 */
	public void setId(String id) throws ValoresException {
		if (id == null || id.trim().isEmpty()) {
			throw new StringException("O id nao pode ser nulo ou vazio.");
		}
		this.id = id;
	}

	/**
	 * Retorna a constante do enum TipoDeQuarto que representa
	 * o tipo desse quarto. (Ex.: TipoDeQuarto.SIMPLES)
	 * 
	 * @return constante TipoDeQuarto
	 */
	public TipoDeQuarto getTipoQuarto() {
		return tipoQuarto;
	}

	/**
	 * Altera o tipo atual do quarto, recebendo uma constante
	 * do Enum TipoDeQuarto
	 * 
	 * @param tipoQuarto
	 * @throws ValoresException
	 */
	public void setTipoQuarto(TipoDeQuarto tipoQuarto) throws ValoresException {
		if (tipoQuarto == null) {
			throw new ValorException("O tipo de quarto nao pode ser nulo.");
		}
		this.tipoQuarto = tipoQuarto;
	}

}
