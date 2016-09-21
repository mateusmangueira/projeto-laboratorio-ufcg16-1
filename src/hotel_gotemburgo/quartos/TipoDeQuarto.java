package hotel_gotemburgo.quartos;

/**
 * Esse Enum representa o tipo de um quarto. Cada tipo
 * difere do outro apenas pelo valor atribuido a sua constante
 * (SIMPLES, LUXO ou PRESIDENCIAL tem valores distintos).
 */
public enum TipoDeQuarto {
	// SIMPLES(100.0, "simples"), LUXO(250.0, "luxo")...
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
	
