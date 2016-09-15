package quartos;

import excecoes.StringException;

public abstract class Quarto {

	private String id;
	// private int

	public Quarto(String idQuarto) throws StringException {
		if (idQuarto == null || idQuarto.trim().isEmpty()) {
			throw new StringException("O Id do quarto nao pode ser nulo ou vazio.");
		}

		this.id = idQuarto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public abstract double getValorDiaria();

	// https://www.edestinos.com.br/dicas-de-viagem/hoteis/estadia-no-hotel/o-que-e-uma-diaria-de-hotel
	/*
	 * http://oquee.com/estadia/ : A palavra Estadia é uma palavra que pode ser
	 * classificada na sua classe gramatical como um substantivo feminino e pode
	 * em seu modo de flexão ser classificado como o ato de repousar em um local
	 * que não seja seu própria casa.
	 * 
	 */

}
