package hotel_gotemburgo;

import excecoes.*;

/**
 * Classe responsavel por um objeto que representa um hospede do Hotel. O hospede possui atributos
 * (nome, email e ano de nascimento) e metodos que retornam e alteram esses atributos.
 *
 */
public class Hospede {

	private String nome;
	private String email;
	private String anoNascimento;

	public Hospede(String nomeHospede, String emailHospede, String anoNascHospede) throws HotelException {

		if (nomeHospede.equals(null) || nomeHospede.trim().isEmpty())
			throw new NomeInvalidoException("O nome do hospode nao pode ser nulo ou vazio.");

		if (emailHospede.equals(null) || emailHospede.trim().isEmpty())
			throw new EmailInvalidoException("O email do hospede nao pode ser nulo ou vazio.");

		if (anoNascHospede.equals(null) || anoNascHospede.trim().isEmpty())
			throw new AnoNascInvalidoException("O ano de Nascimento do hospede nao pode ser nulo ou vazio.");

		this.nome = nomeHospede;
		this.email = emailHospede;
		this.anoNascimento = anoNascHospede;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAnoNascimento() {
		return anoNascimento;
	}

	public void setAnoNascimento(String anoNascimento) {
		this.anoNascimento = anoNascimento;
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
		return outro.getEmail().equals(this.getEmail() );
	}
	
}
