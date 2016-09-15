package quartos;

public enum TipoDeQuarto {
	
	SIMPLES, LUXO, PRESIDENCIAL;
	
	public double getValor(){
		
		switch(this){
		
		case SIMPLES: return 100.0;
		case LUXO: return 250.0;
		case PRESIDENCIAL: return 450.0;
		default: return 0;
		}
	}
	
}
