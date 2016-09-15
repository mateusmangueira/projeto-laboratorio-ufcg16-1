package hotel_gotemburgo;

import java.util.HashSet;
import java.util.Set;

import excecoes.StringException;
import excecoes.ValoresException;

/**
 * Classe responsavel por um objeto que representa um hospede do Hotel. O
 * hospede possui atributos (nome, email e ano de nascimento) e metodos que
 * retornam e alteram esses atributos.
 *
 * @since 12 de Setembro de 2016
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - matricula <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */
public class Hospede {

	private String nome;
	private String email;
	private String dataNascimento;
	private Set<Estadia> estadias;

	/**
	 * O construtor recebe 3 parametros, descritos abaixo, e realiza chechagem de excecao
	 * em todos eles
	 * @param nomeHospede
	 * @param emailHospede
	 * @param dataNascHospede
	 * @throws StringException
	 */
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
		this.setEstadias(new HashSet<Estadia>());
	}

	/**
	 * Retorna o atributo nome do hospede
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Recebe um novo nome como parametro e altera o nome atual do hospede, realizando
	 * checagem de excecao
	 * @param nome
	 * @throws StringException
	 */
	public void setNome(String nome) throws StringException {
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("O nome do hospede nao pode ser nulo ou vazio.");
		this.nome = nome;
	}

	/**
	 * Retorna o atributo email do hospede
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Recebe um novo email como parametro e altera o email atual do hospede, realizando
	 * checagem de excecao
	 * @param email 
	 * @throws StringException
	 */
	public void setEmail(String email) throws StringException {
		if (email == null || email.trim().isEmpty())
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");
		this.email = email;
	}
	
	/**
	 * @return data de nascimento
	 */
	public String getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Recebe uma nova data de nascimento como parametro e altera o email atual do hospede, 
	 * realizando checagem de excecao
	 * @param dataNascimento
	 * @throws ValoresException
	 */
	public void setDataNascimento(String dataNascimento) throws ValoresException {
		if (dataNascimento == null || dataNascimento.trim().isEmpty())
			throw new StringException("A data de nascimento do hospede nao pode ser nula ou vazia.");
		this.dataNascimento = dataNascimento;
	}

	public Set<Estadia> getEstadias() {
		return estadias;
	}

	public void setEstadias(Set<Estadia> estadias) {
		this.estadias = estadias;
	}

	/*
	 * ToString que fiz apenas para os testes JUnit. Se quiserem, podemos mudar sua forma:
	 * <Nome_hospede>: <email_hospede> (data_nascimento).
	 */
	/**
	 * Representacao em String de um Hospede
	 */
	@Override
	public String toString(){
		return String.format("%s: %s (%s).", this.getNome(), this.getEmail(), this.getDataNascimento());
	}
	
	/**
	 * Dois objetos do tipo Hospede sao iguais caso possuam o mesmo email
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
	
	/**
	 * Codigo hash de um objeto do tipo Hospede
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return prime * result + ((email == null) ? 0 : email.hashCode());
	}


}
