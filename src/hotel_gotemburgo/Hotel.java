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

	public String cadastraHospede(String nome, String email, String ano) throws StringInvalidaException {
		if (this.isCadastrado(email)) {
			throw new StringInvalidaException("Hospede ja existente.");
		}
		Hospede hospede = this.criaHospede(nome, email, ano);
		this.getHospedes().add(hospede);
		return email;
	}

	public void removeHospede(String email) throws StringInvalidaException {
		if (this.isCadastrado(email)) {
			Hospede hospede = this.buscaHospede(email);
			this.getHospedes().remove(hospede);
		}
	}

	// O monitor Gustavo me explicou que o ID eh o email e o metodo cadastra
	// retorna o ID que seria o email do hospede, entao modifiquei o metodo
	// cadastra para retorna o email e aqui ele vai pesquisar pelo email para
	// pegar informacoes sobre o hospede.

	public String getInfoHospede(String email, String atributo) {
		if (atributo.equalsIgnoreCase("nome")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getNome();
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getAnoNascimento();
		} else if (atributo.equalsIgnoreCase("email")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getEmail();
		}
		return null;
	}

	public void atualizaCadastro(String email, String atributo, String valor) {
		if (atributo.equalsIgnoreCase("nome")) {
			Hospede hospede = this.buscaHospede(email);
			hospede.setNome(valor);
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			Hospede hospede = this.buscaHospede(email);
			hospede.setAnoNascimento(valor);
		} else if (atributo.equalsIgnoreCase("email")) {
			Hospede hospede = this.buscaHospede(email);
			hospede.setEmail(valor);
		}
	}

	public static void main(String[] args) {
		args = new String[] { "hotel_gotemburgo.Hotel", "diretorio_testes/testes_uc1.txt" };
		EasyAccept.main(args);
	}

}
