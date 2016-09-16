package hotel_gotemburgo;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import easyaccept.EasyAccept;
import excecoes.*;

/**
 * O Hotel representa uma entidade de gerenciamento. Ele contem um set de
 * Hospedes, utilizado para armazenamento desses hospedes no sistema. Metodos
 * sao utilizados para operar sobre esse set e realizar operacoes, como busca,
 * consulta e remocoes.
 * 
 * @since 12 de Setembro de 2016
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - matricula <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */
public class HotelController {
	
	Pattern SearchPattern = Pattern.compile("[a-z]");

	private Set<Hospede> hospedes;
	

	public HotelController() {
		this.hospedes = new HashSet<Hospede>();
	}

	public Set<Hospede> getHospedes() {
		return hospedes;
	}

	/**
	 * Esse metodo cria e retorna um objeto do tipo Hospede, com base nos
	 * parametros recebidos de entrada.
	 * 
	 * @param nome
	 * @param email
	 * @param anoNascimento
	 * @return O Hospede criado.
	 * @throws StringException
	 */
	public Hospede criaHospede(String nome, String email, String dataNascimento) throws HotelException {
		return new Hospede(nome, email, dataNascimento);
	}

	/**
	 * Com base no email recebido de entrada, realiza uma busca no set de
	 * hospedes. Caso um dos hospedes possua esse email, o hospede em questao
	 * eh retornado. Caso nao exista um hospede com esse email, o retorno eh null.
	 * 
	 * @param email
	 * @return Um hospede, caso encontrado. Null, caso nao encontrado.
	 * @throws EmailInvalidoException
	 */
	public Hospede buscaHospede(String email) throws HotelException {

		if (email == null || email.trim().isEmpty())
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");
		
		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return hospede;
		}
		throw new ConsultaException("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
	}

	/**
	 * Verifica se existe um hospede com determinado email no set de hospedes.
	 * 
	 * @param email
	 * @return True: caso exista o hospede, null: caso nao exista.
	 * @throws EmailInvalidoException
	 */
	public boolean isCadastrado(String email) throws HotelException {

		if (email == null || email.trim().isEmpty())
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");

		for (Hospede hospede : this.getHospedes()) {
			if (hospede.getEmail().equalsIgnoreCase(email))
				return true;
		}
		return false;
	}

	/**
	 * Recebe atributos de criacao de um hospede como entrada. Realiza uma
	 * verificaco para saber se ja existe um hospede com o email recebido. Caso
	 * nao exista, cria um novo objeto Hospede com os valores recebidos como
	 * parametros, em seguida adiciona-o ao set de hospedes.
	 * 
	 * @param nome
	 * @param email
	 * @param ano
	 * @return O email do hospede recem-cadastrado
	 * @throws StringException
	 */
	public String cadastraHospede(String nome, String email, String ano) throws HotelException {
		if(nome.trim().isEmpty()){
			throw new HotelException("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
		if(email.trim().isEmpty()){
			throw new HotelException("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		}
		if(ano.trim().isEmpty()){
			throw new HotelException("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
		if(!Pattern.matches(nome, "A-Z{1}+a-z + A-Z{1}+a-z")){
			throw new HotelException("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}
		else if(!Pattern.matches(email, "a-z+@+a-z+.com")){
			throw new HotelException("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		else if(!Pattern.matches(ano, "12/05/1996")){// Esse metodo define uma regra para o nome, se nao for desse estilo ele retorna a exception
			throw new HotelException("Erro no cadastro de Hospede. Formato de data invalido.");
		}
		if (this.isCadastrado(email)){
			throw new CadastroException("Hospede jah existente.");
		}
		
		Hospede hospede = this.criaHospede(nome, email, ano);
		this.getHospedes().add(hospede);
		
		return email;
	}

	/**
	 * Caso um hospede com esse email esteja cadastrado (presente no set de
	 * hospedes), ele eh removido.
	 * 
	 * @param email
	 * @throws StringException
	 */
	public void removeHospede(String email) throws HotelException {
		if (email == null || email.trim().isEmpty())
			throw new StringException("O email do hospede nao pode ser nulo ou vazio.");

		if (!this.isCadastrado(email))
			throw new ConsultaException("Erro na consulta de hospede. Hospede de email " 
					+ email + " nao foi cadastrado(a).");
		
		Hospede hospede = this.buscaHospede(email);
		this.getHospedes().remove(hospede);
	}

	/**
	 * Retorna informacoes relativas a um hospede (pesquisado atraves do email)
	 * de acordo com o atributo recebido na entrada.
	 * 
	 * @param email
	 * @param atributo
	 * @return A informacao requisitada
	 * @throws EmailInvalidoException
	 * @throws AtributoInvalidoException
	 */
	public String getInfoHospede(String email, String atributo) throws HotelException {
		
		if (atributo == null || atributo.trim().isEmpty())
			throw new StringException("O atributo nao pode ser nulo ou vazio.");

		if (atributo.equalsIgnoreCase("nome")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getNome();
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getDataNascimento().toString();
		} else if (atributo.equalsIgnoreCase("email")) {
			Hospede hospede = this.buscaHospede(email);
			return hospede.getEmail();
		}
		return null;
	}

	/**
	 * Atualiza um atributo do cadastro de um hospede, de acordo com uma nova
	 * informacao (valor) recebido na entrada.
	 * 
	 * @param email
	 * @param atributo Uma string representando qual atributo deseja-se alterar
	 * @param valor A nova informacao
	 * @throws HotelException
	 */
	public void atualizaCadastro(String email, String atributo, String valor) throws HotelException {
		
		if (atributo == null || atributo.trim().isEmpty())
			throw new StringException("O atributo nao pode ser nulo ou vazio.");
		if (valor == null || valor.trim().isEmpty())
			throw new StringException("O valor nao pode ser nulo ou vazio.");
		
		if (atributo.equalsIgnoreCase("nome")) {
			this.buscaHospede(email).setNome(valor);// o busca email ja retorna um hospede
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			this.buscaHospede(email).setDataNascimento(valor);
		} else if (atributo.equalsIgnoreCase("email")) {
			this.buscaHospede(email).setEmail(valor);
			
		}
	}

	public static void main(String[] args) {
		args = new String[] { "hotel_gotemburgo.HotelController", "diretorio_testes/testes_uc1_exception.txt" };
		EasyAccept.main(args);
	}

}
