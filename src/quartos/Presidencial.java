package quartos;

import excecoes.*;

public class Presidencial extends Quarto {
	
	private final double DIARIA_PRESIDENCIAL;

	public Presidencial(String idQuarto) throws HotelException {
		super(idQuarto);
		this.DIARIA_PRESIDENCIAL = 450.0;
	}

	@Override
	public double getValorDiaria() {
		return this.DIARIA_PRESIDENCIAL;
	}

}
