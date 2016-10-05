package facade;

import easyaccept.EasyAccept;
import hotel_gotemburgo.HotelController;
import verificacao.excecoes.HotelGotemburgoException;

/* Notas de Mattheus:
 * 1) Remover os metodos delegadores de Restaurante em HotelController, Restaurante volta a ser
 * Controller e se conecta diretamente a Facade
 * 2) Nos strategys de cartao, utilizar constantes estaticas
 * 3) Terminar de ajeitar o javadoc
 */
public class Facade {

	private HotelController hotelController;

	public Facade() {
		this.hotelController = new HotelController();
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

	public void realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto) throws HotelGotemburgoException {
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

	public boolean cadastraPrato(String nome, double preco, String descricao) throws HotelGotemburgoException {
		return this.hotelController.cadastraPrato(nome, preco, descricao);
	}

	public boolean cadastraRefeicao(String nome, String descricao, String componentes) throws HotelGotemburgoException {
		return this.hotelController.cadastraRefeicao(nome, descricao, componentes);
	}

	public String consultaRestaurante(String nome, String atributo) throws HotelGotemburgoException {
		return this.hotelController.consultaRestaurante(nome, atributo);
	}

	public String consultaMenuRestaurante() {
		return this.hotelController.consultaMenuRestaurante();
	}

	public String realizaPedido(String email, String item) throws HotelGotemburgoException {
		return this.hotelController.realizaPedido(email, item);
	}

	public void ordenaMenu(String atributo) {
		this.hotelController.ordenaMenu(atributo);
	}

	public String convertePontos(String email, int qntPontos) throws HotelGotemburgoException {
		return this.hotelController.convertePontos(email, qntPontos);
	}

	/**
	 * Para controle dos testes de aceitacao.
	 * 
	 * @param args
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