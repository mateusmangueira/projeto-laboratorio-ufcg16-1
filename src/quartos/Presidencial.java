package quartos;

import excecoes.StringException;

public class Presidencial extends Quarto {
	
	private final double DIARIA_PRESIDENCIAL;

	public Presidencial(String idQuarto) throws StringException {
		super(idQuarto);
		this.DIARIA_PRESIDENCIAL = 450.0;
	}

	@Override
	public double getValorDiaria() {
		return this.DIARIA_PRESIDENCIAL;
	}

}
