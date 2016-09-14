package hotel_gotemburgo;

import excecoes.StringException;
import excecoes.ValoresException;

/**
 * Classe responsavel por um objeto que representa um hospede do Hotel. O
 * hospede possui atributos (nome, email e ano de nascimento) e metodos que
 * retornam e alteram esses atributos.
 *
 * Criada em 12 de Setembro, 2016
 * 
 * @author Anderson Vital - matricula <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - matricula <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */

public class Hospede {

	private String nome;
	private String email;
	private String dataNascimento;

	public Hospede(String nomeHospede, String emailHospede, String dataNascHospede) throws StringException {

		if (nomeHospede == null || nomeHospede.trim().isEmpty())
			throw new StringException("O nome do hospede nao pode ser nulo ou vazio.");

		if (emailHospede == null || emailHospede.trim().isEmpty())
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");

		if (dataNascHospede == null || dataNascHospede.trim().isEmpty())
			throw new StringException("A data de nascimento do hospede nao pode ser nula ou vazia.");
		
		this.nome = nomeHospede;
		this.email = emailHospede;
		this.dataNascimento = dataNascHospede;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws ValoresException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringException("O nome do hospede nao pode ser nulo ou vazio.");
		}
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws ValoresException {
		if (email == null || email.trim().isEmpty()) {
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");
		}
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) throws ValoresException {
		if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
			throw new StringException("A data de nascimento do hospede nao pode ser nula ou vazia.");
		}
		this.dataNascimento = dataNascimento;
	}

	/*
	 * ToString que fiz apenas para os testes JUnit. Se quiserem, podemos mudar sua forma:
	 * <Nome_hospede>: <email_hospede> (data_nascimento).
	 */
	
	@Override
	public String toString(){
		return String.format("%s: %s (%s).", this.getNome(), this.getEmail(), this.getDataNascimento());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/**
	 * Dois objetos do tipo Hospede sao iguais caso possuam o mesmo email.
	 */
	@Override
	public boolean equals(Object anotherObject) {
		if (anotherObject == null)
			return false;
		if (!anotherObject.getClass().equals(this.getClass()))
			return false;
		Hospede outro = (Hospede) anotherObject;
		return outro.getEmail().equals(this.getEmail());
	}

}
