package hotel_gotemburgo;

import java.util.HashSet;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.StringInvalidaException;

public class Hotel {

	private Set<Hospede> hospedes;

	public Hotel() {
		this.hospedes = new HashSet<Hospede>();
	}

	public Set<Hospede> getHospedes() {
		return hospedes;
	}

	public Hospede criaHospede(String nome, String email, String anoNascimento) throws StringInvalidaException {
		Hospede hospede = new Hospede(nome, email, anoNascimento);
		return hospede;
	}

	private Hospede buscaHospede(String email) {
		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email)) {
				return hospede;
			}
		}
		return null;
	}

	private boolean isCadastrado(String email) {
		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

	public void cadastraHospede(String nome, String email, String ano) throws StringInvalidaException {
		if (this.isCadastrado(email)) {
			throw new StringInvalidaException("Hospede ja existente.");
		}
		Hospede hospede = this.criaHospede(nome, email, ano);
		this.getHospedes().add(hospede);
	}

	public void removeHospede(String email) {
		if (this.isCadastrado(email)) {
			Hospede hospede = this.buscaHospede(email);
			this.getHospedes().remove(hospede);
		}

	}

	// Implementei esse metodo pra buscar o hospede pelo email, mas nos teste
	// esta pelo ID. nao entendi como seria esta busca.

	public String getInfoHospede(String email, String atributo) {
		if (atributo.equalsIgnoreCase("nome")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.toStringNome();
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.toStringData();
		}
		return null;
	}

	public void atualizaCadastro(String id, String atributo, String valor) {
	}

	public static void main(String[] args) {
		args = new String[] { "hotel_gotemburgo.Hotel", "diretorio_testes/testes_uc1.txt" };
		EasyAccept.main(args);
	}

}
