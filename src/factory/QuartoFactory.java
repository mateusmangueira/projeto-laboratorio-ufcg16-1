package factory;

import quartos.*;
import excecoes.*;

/**
 * QuartoFactory - Classe que representa uma fabrica de Quartos, responsavel por
 * criar novos quartos de tres tipos, Simples, Luxo e Presidencial
 * 
 * Criada em 12 de Setembro, 2016
 * 
 * @author Anderson Vital matricula <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo matricula <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 */

public class QuartoFactory {
	/**
	 * Construtor padrao da classe, sem nenhum parametro ou instacia.
	 * 
	 */
	public QuartoFactory() {}

	/**
	 * Metodo que cria um quarto do tipo Simples
	 * 
	 * @param id
	 * @param tipo
	 * @return Quarto Simples
	 * @throws HotelException
	 */
	public Quarto criaQuartoSimples(String id, String tipo) throws ValoresException {

		if (id == null || id.trim().isEmpty()) {
			throw new StringException("Id do quarto nao pode ser vazio ou nulo.");
		}
		if (tipo == null || tipo.trim().isEmpty()) {
			throw new StringException("Tipo nao pode ser vazio ou nulo.");
		}
		if (tipo.equalsIgnoreCase("simples")) {
			Quarto quarto = new Simples(id);
			return quarto;
		}
		throw new StringException("Erro ao criar o quarto Simples.");
	}

	/**
	 * Metodo que cria um quarto do tipo Luxo
	 * 
	 * @param id
	 * @param tipo
	 * @return Quarto Luxo
	 * @throws HotelException
	 */
	public Quarto criaQuartoLuxo(String id, String tipo) throws ValoresException {

		if (id == null || id.trim().isEmpty()) {
			throw new StringException("Id do quarto nao pode ser vazio ou nulo.");
		}
		if (tipo == null || tipo.trim().isEmpty()) {
			throw new StringException("Tipo nao pode ser vazio ou nulo.");
		}
		if (tipo.equalsIgnoreCase("luxo")) {
			Quarto quarto = new Luxo(id);
			return quarto;
		}
		throw new StringException("Erro ao criar o quarto de Luxo.");
	}

	/**
	 * Metodo que cria um quarto do tipo Presidencial
	 * 
	 * @param id
	 * @param tipo
	 * @return Quarto Presidencial
	 * @throws HotelException
	 */

	public Quarto criaQuartoPresidencial(String id, String tipo) throws ValoresException {

		if (id == null || id.trim().isEmpty()) {
			throw new StringException("Id do quarto nao pode ser vazio ou nulo.");
		}
		if (tipo == null || tipo.trim().isEmpty()) {
			throw new StringException("Tipo nao pode ser vazio ou nulo.");
		}
		if (tipo.equalsIgnoreCase("presidencial")) {
			Quarto quarto = new Presidencial(id);
			return quarto;
		}
		throw new StringException("Erro ao criar o quarto de Luxo.");
	}

}
