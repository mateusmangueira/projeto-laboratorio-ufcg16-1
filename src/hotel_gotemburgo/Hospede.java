package hotel_gotemburgo;

import excecoes.AnoNascInvalidoException;
import excecoes.EmailInvalidoException;
import excecoes.NomeInvalidoException;
import excecoes.StringInvalidaException;

public class Hospede {

	private String nome;
	private String email;
	private String anoNascimento;

	public Hospede(String nomeHospede, String emailHospede, String anoNascHospede) throws StringInvalidaException {

		if (nomeHospede.equals(null) || nomeHospede.trim().isEmpty()) {
			throw new NomeInvalidoException("Nome do hospode nao pode ser vazio ou nulo.");
		}

		if (emailHospede.equals(null) || emailHospede.trim().isEmpty()) {
			throw new EmailInvalidoException("Email do hospede nao pode ser vazio ou nulo.");
		}

		if (anoNascHospede.equals(null) || anoNascHospede.trim().isEmpty()) {
			throw new AnoNascInvalidoException("Ano de Nascimento do hospede nao pode ser vazio ou nulo.");
		}

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
}
