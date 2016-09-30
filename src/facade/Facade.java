package facade;

import easyaccept.EasyAccept;
import hotel_gotemburgo.HotelController;
import verificacao.excecoes.HotelGotemburgoException;

public class Facade {

	private HotelController hotelController;

	public Facade() {
		this.hotelController = new HotelController();
	}

	public String cadastraHospede(String nome, String email, String dataNascimento) throws HotelGotemburgoException {
		return this.hotelController.cadastraHospede(nome, email, dataNascimento);
	}

	public void removeHospede(String email) throws HotelGotemburgoException {
		this.hotelController.removeHospede(email);
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

	public String realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto)
			throws HotelGotemburgoException {
		return this.hotelController.realizaCheckin(email, qntDias, idQuarto, tipoQuarto);
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

	public void cadastraPrato(String nome, double preco, String descricao) throws HotelGotemburgoException {
		this.hotelController.cadastraPrato(nome, preco, descricao);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws HotelGotemburgoException {
		this.hotelController.cadastraRefeicao(nome, descricao, componentes);
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

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "diretorio_testes/testes_uc1.txt",
				"diretorio_testes/testes_uc1_exception.txt", "diretorio_testes/testes_uc2.txt",
				"diretorio_testes/testes_uc2_exception.txt", "diretorio_testes/testes_uc3.txt",
				"diretorio_testes/testes_uc3_exception.txt", "diretorio_testes/testes_uc3.txt",
				"diretorio_testes/testes_uc4_exception.txt", "diretorio_testes/testes_uc4.txt",
				"diretorio_testes/testes_uc5.txt" };
		EasyAccept.main(args);
	}
}