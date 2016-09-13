package quartos;

public class Luxo extends Quarto {

	private final double DIARIA_LUXO;

	public Luxo(String idQuarto) throws Exception {
		super(idQuarto);
		this.DIARIA_LUXO = 250.0;
	}

	@Override
	public double getValorDiaria() {
		return this.DIARIA_LUXO;
	}

}
