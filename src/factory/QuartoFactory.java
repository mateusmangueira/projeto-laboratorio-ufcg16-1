package factory;

import quartos.*;


public class QuartoFactory {

	public QuartoFactory() {
	}

	public Quarto criaQuartoSimples(String id, String tipo) throws Exception {
		if (id.equals(null) || id.trim().isEmpty()) {
			throw new Exception("Id do quarto nao pode ser vazio ou nulo.");
		}
		if (tipo.equals(null) || tipo.trim().isEmpty()) {
			throw new Exception("Tipo nao pode ser vazio ou nulo.");
		}
		if (tipo.equalsIgnoreCase("simples")) {
			Quarto quarto = new Simples(id);
			return quarto;
		}
		throw new Exception("Erro ao criar o quarto Simples.");
	}

	public Quarto criaQuartoLuxo(String id, String tipo) throws Exception {
		if (id.equals(null) || id.trim().isEmpty()) {
			throw new Exception("Id do quarto nao pode ser vazio ou nulo.");
		}
		if (tipo.equals(null) || tipo.trim().isEmpty()) {
			throw new Exception("Tipo nao pode ser vazio ou nulo.");
		}
		if (tipo.equalsIgnoreCase("luxo")) {
			Quarto quarto = new Luxo(id);
			return quarto;
		}
		throw new Exception("Erro ao criar o quarto de Luxo.");
	}
	
	public Quarto criaQuartoPresidencial(String id, String tipo) throws Exception {
		if (id.equals(null) || id.trim().isEmpty()) {
			throw new Exception("Id do quarto nao pode ser vazio ou nulo.");
		}
		if (tipo.equals(null) || tipo.trim().isEmpty()) {
			throw new Exception("Tipo nao pode ser vazio ou nulo.");
		}
		if (tipo.equalsIgnoreCase("presidencial")) {
			Quarto quarto = new Presidencial(id);
			return quarto;
		}
		throw new Exception("Erro ao criar o quarto de Luxo.");
	}
	
	
}
