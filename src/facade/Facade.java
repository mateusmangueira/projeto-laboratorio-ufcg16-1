package facade;

import easyaccept.EasyAccept;
import excecoes.HotelException;
import hotel_gotemburgo.HotelController;
import restaurante.RestauranteController;

public class Facade {

	private HotelController hotelController;
	private RestauranteController restauranteController;

	public Facade() {
		this.hotelController = new HotelController();
		this.restauranteController = new RestauranteController();
	}

	public String cadastraHospede(String nome, String email, String dataNascimento) {
		try {
			return this.hotelController.cadastraHospede(nome, email, dataNascimento);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public void removeHospede(String email) {
		try {
			this.hotelController.removeHospede(email);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getInfoHospede(String email, String atributo) {
		try {
			return this.hotelController.getInfoHospede(email, atributo);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String getInfoHospedagem(String email, String atributo) {
		try {
			return this.hotelController.getInfoHospedagem(email, atributo);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void atualizaCadastro(String email, String atributo, String valor) {
		try {
			this.hotelController.atualizaCadastro(email, atributo, valor);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public String realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto) {
		try {
			return this.hotelController.realizaCheckin(email, qntDias, idQuarto, tipoQuarto);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String realizaCheckout(String email, String idQuarto) {
		try {
			return this.hotelController.realizaCheckout(email, idQuarto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String consultaTransacoes(String atributo) {
		try {
			return this.hotelController.consultaTransacoes(atributo);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String consultaTransacoes(String atributo, int indice) {
		try {
			return this.hotelController.consultaTransacoes(atributo, indice);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public boolean cadastraPrato(String nome, double preco, String descricao) {
		try {
			return this.restauranteController.cadastraPrato(nome, preco, descricao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) {
		try {
			this.restauranteController.cadastraRefeicao(nome, descricao, componentes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String consultaRestaurante(String nome, String atributo) {
		try {
			return this.restauranteController.consultaRestaurante(nome, atributo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
