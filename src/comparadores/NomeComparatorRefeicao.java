package comparadores;

import java.util.Comparator;

import restaurante.comida.Refeicao;

public class NomeComparatorRefeicao implements Comparator<Refeicao> {

	public int compare(Refeicao refeicao, Refeicao outraRefeicao) {
		return refeicao.getNome().compareTo(outraRefeicao.getNome());
	}

}
