package facade;

import easyaccept.EasyAccept;
import hotel_gotemburgo.HotelController;
import hotel_gotemburgo.quartos.Quarto;
import hotel_gotemburgo.quartos.TipoDeQuarto;
import restaurante.RestauranteController;
import verificacao.excecoes.HotelGotemburgoException;

public class Facade {

	private HotelController hotelController;
	private RestauranteController restauranteController;

	public Facade() {
		this.hotelController = new HotelController();
		this.restauranteController = new RestauranteController();
	}

	public String cadastraHospede(String nome, String email, String dataNascimento) throws HotelGotemburgoException {
		try {
			return this.hotelController.cadastraHospede(nome, email, dataNascimento);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}

	}

	public void removeHospede(String email) throws HotelGotemburgoException {
		try {
			this.hotelController.removeHospede(email);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}

	public String getInfoHospede(String email, String atributo) throws HotelGotemburgoException {
		try {
			return this.hotelController.getInfoHospede(email, atributo);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}

	public String getInfoHospedagem(String email, String atributo) throws HotelGotemburgoException {
		try {
			return this.hotelController.getInfoHospedagem(email, atributo);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}

	public void atualizaCadastro(String email, String atributo, String valor) throws HotelGotemburgoException {
		try {
			this.hotelController.atualizaCadastro(email, atributo, valor);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}
	
	public String realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto) throws HotelGotemburgoException {
		try {
			return this.hotelController.realizaCheckin(email, qntDias, idQuarto, tipoQuarto);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}

	public String realizaCheckout(String email, String idQuarto) throws HotelGotemburgoException {
		try {
			return this.hotelController.realizaCheckout(email, idQuarto);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}

	public String consultaTransacoes(String atributo) throws HotelGotemburgoException {
		try {
			return this.hotelController.consultaTransacoes(atributo);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}

	public String consultaTransacoes(String atributo, int indice) throws HotelGotemburgoException {
		try {
			return this.hotelController.consultaTransacoes(atributo, indice);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}

	public boolean cadastraPrato(String nome, double preco, String descricao) throws HotelGotemburgoException {
		try {
			return this.restauranteController.cadastraPrato(nome, preco, descricao);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws HotelGotemburgoException {
		try {
			this.restauranteController.cadastraRefeicao(nome, descricao, componentes);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}

	public String consultaRestaurante(String nome, String atributo) throws HotelGotemburgoException {
		try {
			return this.restauranteController.consultaRestaurante(nome, atributo);
		} catch (HotelGotemburgoException e) {
			throw new HotelGotemburgoException(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "diretorio_testes/testes_uc1.txt",
				"diretorio_testes/testes_uc1_exception.txt", "diretorio_testes/testes_uc2.txt",
				"diretorio_testes/testes_uc2_exception.txt", "diretorio_testes/testes_uc3.txt",
				"diretorio_testes/testes_uc3_exception.txt", "diretorio_testes/testes_uc3.txt" };
		EasyAccept.main(args);
	}
}
