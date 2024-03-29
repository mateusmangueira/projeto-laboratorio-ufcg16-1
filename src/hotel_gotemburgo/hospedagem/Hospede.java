package hotel_gotemburgo.hospedagem;

import hotel_gotemburgo.hospedagem.cartao.CartaoFidelidade;
import hotel_gotemburgo.hospedagem.cartao.PadraoStrategy;
import hotel_gotemburgo.hospedagem.cartao.PremiumStrategy;
import hotel_gotemburgo.hospedagem.cartao.VipStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import verificacao.excecoes.ConsultaException;
import verificacao.excecoes.Excecoes;
import verificacao.excecoes.StringException;
import verificacao.excecoes.ValorException;
import verificacao.excecoes.ValoresException;

/**
 * Classe responsavel por um objeto que representa um hospede do sistema. O
 * hospede possui atributos (nome, email e ano de nascimento) e metodos que
 * retornam ou modificam esses atributos.
 *
 * @since 12 de Setembro de 2016
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - matricula <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934 <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 */
public class Hospede implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6215041567856321599L;
	
	private String nome;
	private String email;
	private String dataNascimento;
	private ArrayList<Estadia> estadias;
	private CartaoFidelidade cartao;
	private int pontos;
	private static final String LINE_SEPARATOR = System.lineSeparator();

	/**
	 * O construtor recebe 3 parametros, descritos abaixo, e realiza checagem de
	 * excecao em todos eles, para verificar se sao vazio, nulos e se estao
	 * no formato adequado. Alem disso, o construtor inicia o cartao do hospede
	 * no comportamento padrao, e define a quantidade inicial dos pontos do
	 * hospede como 0.
	 * 
	 * @param nomeHospede Uma String representando o nome do hospede
	 * @param emailHospede Uma String unica que representa o email do hospede
	 * @param dataNascHospede Uma String que representa a data de nascimento
	 * do hospede. 
	 * @throws StringException Em caso de valores invalidos de string
	 */
	public Hospede(String nomeHospede, String emailHospede, String dataNascHospede) throws StringException {

		Excecoes.checaString(nomeHospede, "O nome do hospede nao pode ser nulo ou vazio.");
		Excecoes.checaString(emailHospede, "O email do hospede nao pode ser nulo ou vazio.");
		Excecoes.checaString(dataNascHospede, "A data de nascimento do hospede nao pode ser nula ou vazia.");

		this.nome = nomeHospede;
		this.email = emailHospede;
		this.dataNascimento = dataNascHospede;
		this.estadias = new ArrayList<Estadia>();
		this.cartao = new PadraoStrategy();
		this.pontos = 0;
	}

	/**
	 * Retorna o atributo nome do hospede
	 * 
	 * @return nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Recebe um novo nome como parametro e altera o nome atual do hospede,
	 * realizando checagem de excecao
	 * 
	 * @param nome
	 * @throws StringException
	 */
	public void setNome(String nome) throws StringException {
		Excecoes.checaString(nome,
				"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		this.nome = nome;
	}

	/**
	 * Retorna o atributo email do hospede.
	 * 
	 * @return email String representando o email do Hospede
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Recebe um novo email como parametro e altera o email atual do hospede,
	 * realizando checagem de excecao.
	 * 
	 * @param email Novo email que substituira o antigo
	 * @throws StringException Caso o novo email seja invalido
	 */
	public void setEmail(String email) throws StringException {
		Excecoes.checaString(email, "Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
		this.email = email;
	}

	/**
	 * Retorna a data de nascimento do hospede.
	 * 
	 * @return data de nascimento Uma string representando a data de nascimento do hospede
	 */
	public String getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Recebe uma nova data de nascimento como parametro e altera o email atual
	 * do hospede, realizando checagem de excecao
	 * 
	 * @param dataNascimento A nova data de nascimento do cliente que ira constar
	 * no sistema
	 * @throws StringException Em caso de uma String invalida
	 */
	public void setDataNascimento(String dataNascimento) throws StringException {
		Excecoes.checaString(dataNascimento,
				"Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Retorna o atributo quantidade de pontos do hospede.
	 * 
	 * @return Um inteiro que representa a quantidade de pontos que o hospede possui
	 */
	public int getPontos() {
		return this.pontos;
	}

	/**
	 * Atualiza o valor do atributo pontos.
	 * 
	 * @param pontos Recebe uma nova quantidade de pontos como parametro, e substitui
	 * a quantia antiga de pontos pela nova quantia. Apos a alteracao, chama o metodo
	 * upgradeFidelidade para verificar se eh necessaria a mudanca do tipo do cartao,
	 * de acordo com a quantidade atual de pontos do hospede.
	 */
	public void setPontos(int pontos) {
		this.pontos = pontos;
		this.upgradeFidelidade();
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
		this.estadias.add(estadia);
	}

	/**
	 * Esse metodo verifica se o id esta contido na lista de estadias e depois
	 * remove o id passado como parametro
	 * 
	 * @param idQuarto
	 */
	public boolean removeEstadia(String idQuarto) {
		ArrayList<Estadia> estadias = this.estadias;
		Iterator<Estadia> i = estadias.iterator();
		while (i.hasNext()) {
			Estadia estadia = i.next();
			if (estadia.getIdQuarto().equalsIgnoreCase(idQuarto)) {
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
		for (Estadia estadia : this.estadias) {
			info += "," + estadia.getIdQuarto();
		}
		return info.replaceFirst(",", "");
	}



	/**
	 * Metodo responsavel por retornar a quantidade de estadias que esse Hospede possui
	 * atualmente.
	 * 
	 * @return O tamanho da lista de estadias que compoe o Hospede
	 */
	public int getQtdEstadias() {
		return this.estadias.size();
	}

	/**
	 * Retorna os gastos do hospede, relativos as estadias atuais do Hospede.
	 * 
	 * @return Os gastos do Hospede, que sao calculados ao iterar sobre o ArrayList
	 * de estadias e somar o valor atual de todas elas, atraves do metodo getCalculaEstadia().
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
	 * @param idQuarto Uma String representando o ID do quarto que compoe
	 * a estadia que deseja-se consultar o valor
	 * @throws ConsultaException Caso nenhuma estadia no quarto passado como
	 * parametro seja encontrada no array de estadias.
	 */
	public double getValorEstadia(String idQuarto) throws ConsultaException {
		for (Estadia estadia : this.estadias) {
			if (estadia.getIdQuarto().equalsIgnoreCase(idQuarto))
				return estadia.getCalculaEstadia();
		}
		throw new ConsultaException("Nao foi possivel localizar uma estadia nesse quarto");
	}
	
	/**
	 * Metodo que delega a funcao de aplicar desconto ao cartao. O calculo sera realizado 
	 * de acordo com o tipo atual do Cartao Fidelidade do hospede. Cada tipo apresenta 
	 * estrategias de calculo diferentes.
	 * 
	 * @param valor Valor sobre o qual o desconto sera aplicado
	 * @return O valor apos o desconto ser aplicado
	 */
	public double aplicarDesconto(double valor) {
		return this.cartao.aplicarDesconto(valor);
	}
		
	/**
	 * Metodo que delega a funcao de adicionar pontos ao cartao. O calculo sera realizado 
	 * de acordo com o tipo atual do Cartao Fidelidade do hospede. Cada tipo apresenta 
	 * estrategias de calculo diferentes.
	 * 
	 */
	public int adicionarPontos(double valor) {
		return this.cartao.adicionarPontos(valor);
		
	}
	
	/**
	 * Metodo que utiliza do padrao strategy para converter pontos em dinheiro, de 
	 * acordo com o tipo do cartao e a quantidade de pontos dada.
	 * 
	 * @param qntPontos A quantidade de pontos a ser convertida em dinheiro
	 * @return Uma String com a representacao em dinheiro dos pontos convertidos
	 * @throws ValorException 
	 */
	public String convertePontos(int qntPontos) throws ValorException {
		
		if (qntPontos > this.pontos)
			throw new ValorException("Pontos insuficientes para a operacao");
		
		String retorno = this.cartao.convertePontos(qntPontos);
		int novaPontuacao = this.pontos - qntPontos;
		setPontos(novaPontuacao);
		
		return retorno;
	}
	
	/**
	 * Este metodo upa o cartao fidelidade de um Hospede. Todo hospede inicia com seu cartao padrao, no qual
	 * em meio a suas despesas e relacoes com as atividades do Hotel, pode upar de Padrao para Premium ou diretamente
	 * para VIP. Tambem ha possibilidade de passar de Premuium para VIP. 
	 */
	public void upgradeFidelidade() {
		if (this.pontos < 350) {
			this.cartao = new PadraoStrategy();
		}
		else if (this.pontos >= 350 && this.pontos <= 1000) {
			this.cartao = new PremiumStrategy();
		}
		else {
			this.cartao = new VipStrategy();
		}
	}
	
	/**
	 * Representacao em String de um Hospede.
	 */
	@Override
	public String toString() {
		return String.format("Email: %s%sNome: %s%sData de nascimento: %s", 
				this.email, Hospede.LINE_SEPARATOR, this.nome, Hospede.LINE_SEPARATOR, this.dataNascimento);
	}

	/**
	 * Dois objetos do tipo Hospede sao iguais caso possuam o mesmo email.
	 */
	@Override
	public boolean equals(Object outroObjeto) {
		if (outroObjeto instanceof Hospede) {
			Hospede outroHospede = (Hospede) outroObjeto;
			if (this.getEmail().equalsIgnoreCase(outroHospede.getEmail())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Codigo hash de um objeto do tipo Hospede.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return prime * result + ((email == null) ? 0 : email.hashCode());
	}
	
}