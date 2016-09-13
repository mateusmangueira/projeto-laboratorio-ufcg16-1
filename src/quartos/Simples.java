package quartos;

import excecoes.*;

public class Simples extends Quarto {
	
	private final double DIARIA_SIMPLES;
	
	public Simples(String idQuarto) throws HotelException {
		super(idQuarto);
		this.DIARIA_SIMPLES = 100.0;
	}

	@Override
	public double getValorDiaria() {
		return this.DIARIA_SIMPLES;
	}

}
