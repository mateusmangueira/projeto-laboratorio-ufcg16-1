package quartos;

import excecoes.StringException;

public abstract class Quarto {
	
	private String id;
	
	public Quarto(String idQuarto) throws StringException {
		if(idQuarto == null || idQuarto.trim().isEmpty()) {
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
	

}
