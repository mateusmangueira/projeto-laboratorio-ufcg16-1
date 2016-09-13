package quartos;

import excecoes.*;

public abstract class Quarto {
	
	private String id;
	//private double valorDiaria;
	
	public Quarto(String idQuarto) throws HotelException {
		if(idQuarto.equals(null) || idQuarto.trim().isEmpty()) {
			throw new IdInvalidoException("O Id do quarto nao pode ser nulo ou vazio.");
		}
	
		this.id = idQuarto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}*/
	
	public abstract double getValorDiaria();
	

}
