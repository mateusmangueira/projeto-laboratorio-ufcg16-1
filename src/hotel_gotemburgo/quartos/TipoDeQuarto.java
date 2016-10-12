package hotel_gotemburgo.quartos;

/**
 * Esse Enum representa o tipo de um quarto. Cada tipo
 * difere do outro apenas pelo valor atribuido a sua constante
 * (SIMPLES, LUXO ou PRESIDENCIAL tem valores distintos).
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */


public enum TipoDeQuarto {

	SIMPLES, LUXO, PRESIDENCIAL;
	
	public double getValor() {

		switch(this) {
		
		case SIMPLES: return 100.0;
		case LUXO: return 250.0;
		case PRESIDENCIAL: return 450.0;
		default: return 0;
		}
	}
	

}
	
