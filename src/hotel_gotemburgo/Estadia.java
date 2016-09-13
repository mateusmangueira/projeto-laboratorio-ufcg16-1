package hotel_gotemburgo;

import quartos.Quarto;
import excecoes.*;

public class Estadia {
	
	private Quarto quarto;
	private int dias;
	
	public Estadia(Quarto quarto, int dias) throws HotelException {
		
		if(quarto.equals(null)) {
			throw new QuartoInvalidoException("O quarto nao pode ser nulo.");
		}
		
		if(dias <= 0) {
			throw new ValorInvalidoException("A quantidade de dias nao pode ser menor ou igual a zero.");
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
