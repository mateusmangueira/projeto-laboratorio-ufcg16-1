package hotel_gotemburgo.hospedagem;

import java.util.ArrayList;
import java.util.Iterator;

import verificacao.excecoes.StringException;
import verificacao.excecoes.ValorException;
import verificacao.excecoes.ValoresException;

/**
 * Classe responsavel por um objeto que representa um hospede do Hotel. O
 * hospede possui atributos (nome, email e ano de nascimento) e metodos que
 * retornam e alteram esses atributos.
 *
 * @since 12 de Setembro de 2016
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - matricula <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934
 *         <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */
public class Hospede {

	private String nome;
	private String email;
	private String dataNascimento;
	private ArrayList<Estadia> estadias;
	private CartaoFidelidade cartao;
	private int pontuacao;

	/**
	 * O construtor recebe 3 parametros, descritos abaixo, e realiza checagem de
	 * excecao em todos eles
	 * 
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
		this.estadias = new ArrayList<Estadia>();
		this.cartao = new Padrao();
	}

	/**
	 * Retorna o atributo nome do hospede
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Recebe um novo nome como parametro e altera o nome atual do hospede,
	 * realizando checagem de excecao
	 * 
	 * @param nome
	 * @throws StringException
	 */
	public void setNome(String nome) throws StringException {
		if (email == null || email.trim().isEmpty())
			throw new StringException(
					"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		this.nome = nome;
	}

	/**
	 * Retorna o atributo email do hospede
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Recebe um novo email como parametro e altera o email atual do hospede,
	 * realizando checagem de excecao
	 * 
	 * @param email
	 * @throws StringException
	 */
	public void setEmail(String email) throws StringException {
		if (email == null || email.trim().isEmpty())
			throw new StringException("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
		this.email = email;
	}

	/**
	 * @return data de nascimento
	 * @throws StringException
	 */
	public String getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Recebe uma nova data de nascimento como parametro e altera o email atual
	 * do hospede, realizando checagem de excecao
	 * 
	 * @param dataNascimento
	 * @throws ValoresException
	 */
	public void setDataNascimento(String dataNascimento) throws ValoresException {
		if (dataNascimento == null || dataNascimento.trim().isEmpty())
			throw new StringException(
					"Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		this.dataNascimento = dataNascimento;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	/**
	 * Esse metodo eh utilizado para adicionar uma estadia ao array e calcular
	 * os gastos. Tem como entrada uma estadia, verifica se essa estadia eh nula
	 * depois calcula os gastos e adiciona no Array e nao retorna nada
	 * 
	 * @param estadia
	 * @throws ValoresException
	 */
	public void addEstadia(Estadia estadia) throws ValoresException {
		if (estadia == null) {
			throw new ValorException("Estadia nao pode ser null");
		}
		this.getEstadias().add(estadia);
	}

	/**
	 * Esse metodo verifica se o id esta contido na lista de estadias e depois
	 * remove o id passado como parametro
	 * 
	 * @param idQuarto
	 */
	public boolean removeEstadia(String idQuarto) {
		ArrayList<Estadia> estadias = this.getEstadias();
		Iterator<Estadia> i = estadias.iterator();
		while (i.hasNext()) {
			Estadia estadia = i.next();
			if (estadia.getQuarto().getId().equalsIgnoreCase(idQuarto)) {
				i.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Cria e retorna uma string com o ID dos quartos das estadias do hospede
	 * 
	 * @return uma string com o ID dos quartos das estadias do hospede
	 */
	public String getRepresentaEstadias() {

		String info = "";
		for (Estadia estadia : estadias) {
			info += "," + estadia.getQuarto().getId();
		}
		return info.replaceFirst(",", "");

	}

	/**
	 * get do array de estadias
	 * 
	 * @return estadias
	 */
	public ArrayList<Estadia> getEstadias() {
		return estadias;
	}

	/**
	 * esse metodo retorna ah quantidade estadias que estao no Hotel
	 * 
	 * @return o tamanho do array de estadias
	 */
	public int getQtdEstadias() {
		return estadias.size();
	}

	/**
	 * Retorna os gastos do hospede no hotel
	 * 
	 * @return gastos do hospede no hotel
	 */
	public double getGastosTotal() {
		double total = 0.0;
		for (Estadia estadia : estadias) {
			total += estadia.getCalculaEstadia();
		}
		return total;
	}

	/**
	 * Retorna o valor de uma estadia especifica do Hospede.
	 * 
	 * @param valor
	 *            Valor gasto pelo Hospede na operacao
	 * @throws ValorException
	 */

	public double getValorEstadia(String idQuarto) {
		for (Estadia estadia : estadias) {
			if (estadia.getQuartoID().equals(idQuarto)) {
				return estadia.getCalculaEstadia();
			}
		}
		return 0.0;
	}

	/**
	 * Representacao em String de um Hospede
	 */
	@Override
	public String toString() {
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