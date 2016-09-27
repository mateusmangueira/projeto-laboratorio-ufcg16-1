package comparadores;

import java.util.Comparator;

import restaurante.comida.Prato;

public class NomeComparatorPrato implements Comparator<Prato> {

	@Override
	public int compare(Prato prato, Prato outroPrato) {
		return prato.getNome().compareTo(outroPrato.getNome());
	}

}
