package quartos;

public class Simples extends Quarto {
	
	private final double DIARIA_SIMPLES;
	
	public Simples(String idQuarto) throws Exception {
		super(idQuarto);
		this.DIARIA_SIMPLES = 100.0;
	}

	@Override
	public double getValorDiaria() {
		return this.DIARIA_SIMPLES;
	}

}
