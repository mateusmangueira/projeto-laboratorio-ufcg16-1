package hotel_gotemburgo.quartos;

import excecoes.StringException;
import excecoes.ValorException;
import excecoes.ValoresException;

public class Quarto {

	private String id;
	private TipoDeQuarto tipoQuarto;

	public Quarto(String idQuarto, TipoDeQuarto tipoQuarto) throws StringException {
		if (idQuarto == null || idQuarto.trim().isEmpty()) {
			throw new StringException("O Id do quarto nao pode ser nulo ou vazio.");
		}

		this.id = idQuarto;
		this.tipoQuarto = tipoQuarto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws ValoresException {
		if (id == null || id.trim().isEmpty()) {
			throw new StringException("O id nao pode ser nulo ou vazio.");
		}
		this.id = id;
	}

	public TipoDeQuarto getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipoQuarto(TipoDeQuarto tipoQuarto) throws ValoresException {
		if (tipoQuarto == null) {
			throw new ValorException("O tipo de quarto nao pode ser nulo.");
		}
		this.tipoQuarto = tipoQuarto;
	}
	
	//public abstract double getValorDiaria();

	// https://www.edestinos.com.br/dicas-de-viagem/hoteis/estadia-no-hotel/o-que-e-uma-diaria-de-hotel
	/*
	 * http://oquee.com/estadia/ : A palavra Estadia é uma palavra que pode ser
	 * classificada na sua classe gramatical como um substantivo feminino e pode
	 * em seu modo de flexão ser classificado como o ato de repousar em um local
	 * que não seja seu própria casa.
	 * 
	 */

}
