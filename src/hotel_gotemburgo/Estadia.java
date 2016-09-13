package hotel_gotemburgo;

import quartos.Quarto;

public class Estadia {
	
	private Quarto quarto;
	private int dias;
	
	public Estadia(Quarto quarto, int dias) throws Exception {
		if(quarto.equals(null)) {
			throw new Exception("Quarto nao pode ser nulo.");
		} if(dias <= 0) {
			throw new Exception("Quantidade de dias nao pode ser menor ou igual a zero.");
		}
		
		this.quarto = quarto;
		this.dias = dias;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public double calculaDiaria(Quarto quarto) {
		return this.dias * quarto.getValorDiaria();
	}
}
