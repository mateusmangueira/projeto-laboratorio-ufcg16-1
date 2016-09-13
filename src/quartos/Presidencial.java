package quartos;

public class Presidencial extends Quarto {
	
	private final double DIARIA_PRESIDENCIAL;

	public Presidencial(String idQuarto) throws Exception {
		super(idQuarto);
		this.DIARIA_PRESIDENCIAL = 400.0;
	}

	@Override
	public double getValorDiaria() {
		return this.DIARIA_PRESIDENCIAL;
	}

}
