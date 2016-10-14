package facade;

import easyaccept.EasyAccept;
import hotel_gotemburgo.HotelController;
import restaurante.RestauranteController;
import verificacao.excecoes.HotelGotemburgoException;

/**
 * Utilizamos o padrao de projeto Facade para fornecer uma interface
 * simplificada, de nivel mais alto, para agrupar e fornecer acesso as
 * funcionalidades do sistema. A Facade possui dois Controllers (Hotel e
 * Restaurante), e delega seus metodos nesta classe.
 * 
 * @author Anderson Vital - 115210091 <anderson.vital@ccc.ufcg.edu.br>
 * @author Kleber Diogo - 115211239 <kleber.albuquerque@ccc.ufcg.edu.br>
 * @author Lucas Christopher - 115210934
 *         <lucas.christopher.silva@ccc.ufcg.edu.br>
 * @author Mateus Pinto Mangueira - 115211466 <mateus.mangueira@ccc.ufcg.edu.br>
 * 
 * 
 */
public class Facade {

	private HotelController hotelController;
	private RestauranteController restauranteController;

	public Facade() {
		this.hotelController = new HotelController();
		this.restauranteController = new RestauranteController();
	}

	/* Funcionalidades do HotelController */
	public void iniciaSistema() {
		this.hotelController.iniciaSistema();
	}
	
	public void fechaSistema() {
		this.hotelController.fechaSistema();
	}
	
	public String cadastraHospede(String nome, String email, String dataNascimento) throws HotelGotemburgoException {
		return this.hotelController.cadastraHospede(nome, email, dataNascimento);
	}

	public boolean removeHospede(String email) throws HotelGotemburgoException {
		return this.hotelController.removeHospede(email);
	}

	public String getInfoHospede(String email, String atributo) throws HotelGotemburgoException {
		return this.hotelController.getInfoHospede(email, atributo);
	}

	public String getInfoHospedagem(String email, String atributo) throws HotelGotemburgoException {
		return this.hotelController.getInfoHospedagem(email, atributo);
	}

	public void atualizaCadastro(String email, String atributo, String valor) throws HotelGotemburgoException {
		this.hotelController.atualizaCadastro(email, atributo, valor);
	}

	public void realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto)
			throws HotelGotemburgoException {
		this.hotelController.realizaCheckin(email, qntDias, idQuarto, tipoQuarto);
	}

	public String realizaCheckout(String email, String idQuarto) throws HotelGotemburgoException {
		return this.hotelController.realizaCheckout(email, idQuarto);
	}

	public String consultaTransacoes(String atributo) throws HotelGotemburgoException {
		return this.hotelController.consultaTransacoes(atributo);
	}

	public String consultaTransacoes(String atributo, int indice) throws HotelGotemburgoException {
		return this.hotelController.consultaTransacoes(atributo, indice);
	}

	public String realizaPedido(String email, String item) throws HotelGotemburgoException {
		return this.hotelController.realizaPedido(email, item);
	}

	public String convertePontos(String email, int qntPontos) throws HotelGotemburgoException {
		return this.hotelController.convertePontos(email, qntPontos);
	}

	/* Funcionalidades do RestauranteController */
	public boolean cadastraPrato(String nome, double preco, String descricao) throws HotelGotemburgoException {
		return this.restauranteController.cadastraPrato(nome, preco, descricao);
	}

	public boolean cadastraRefeicao(String nome, String descricao, String componentes) throws HotelGotemburgoException {
		return this.restauranteController.cadastraRefeicao(nome, descricao, componentes);
	}

	public String consultaRestaurante(String nome, String atributo) throws HotelGotemburgoException {
		return this.restauranteController.consultaRestaurante(nome, atributo);
	}

	public String consultaMenuRestaurante() throws HotelGotemburgoException {
		return this.restauranteController.consultaMenuRestaurante();
	}

	public void ordenaMenu(String atributo) throws HotelGotemburgoException {
		this.restauranteController.ordenaMenu(atributo);
	}


	/**
	 * Metodo main utilizado para controle e execucao dos testes de aceitacao.
	 */
	public static void main(String[] args) {
		args = new String[] { "hotel_gotemburgo.HotelController", "diretorio_testes/testes_uc1.txt",
				"diretorio_testes/testes_uc1_exception.txt", "diretorio_testes/testes_uc2.txt",
				"diretorio_testes/testes_uc2_exception.txt", "diretorio_testes/testes_uc3.txt",
				"diretorio_testes/testes_uc3_exception.txt", "diretorio_testes/testes_uc4.txt",
				"diretorio_testes/testes_uc4_exception.txt", "diretorio_testes/testes_uc5.txt",
				"diretorio_testes/testes_uc6.txt", "diretorio_testes/testes_uc7.txt" };
		EasyAccept.main(args);
	}

}