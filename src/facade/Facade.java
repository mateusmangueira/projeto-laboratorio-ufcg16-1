package facade;

import excecoes.HotelException;
import hotel_gotemburgo.HotelController;
import hotel_gotemburgo.hospedagem.Hospede;
import restaurante.RestauranteController;

public class Facade {

	private HotelController hotelController;
	private RestauranteController restauranteController;

	public Facade() {
		this.hotelController = new HotelController();
		this.restauranteController = new RestauranteController();
	}

	public Hospede cadastraHospede(String nome, String email, String dataNascimento) {
		try {
			this.hotelController.cadastraHospede(nome, email, dataNascimento);
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

	public void getInfoHospede(String email, String atributo) {
		try {
			this.hotelController.getInfoHospede(email, atributo);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void getInfoHospedagem(String email, String atributo) {
		try {
			this.hotelController.getInfoHospedagem(email, atributo);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void atualizaCadastro(String email, String atributo, String valor) {
		try {
			this.hotelController.atualizaCadastro(email, atributo, valor);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto) {
		try {
			this.hotelController.realizaCheckin(email, qntDias, idQuarto, tipoQuarto);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void realizaCheckout(String email, String idQuarto) {
		try {
			this.hotelController.realizaCheckout(email, idQuarto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void consultaTransacoes(String atributo) {
		try {
			this.hotelController.consultaTransacoes(atributo);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void consultaTransacoes(String atributo, int indice) {
		try {
			this.hotelController.consultaTransacoes(atributo, indice);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void cadastraPrato(String nome, double preco, String descricao) {
		try {
			this.restauranteController.cadastraPrato(nome, preco, descricao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) {
		try {
			this.restauranteController.cadastraRefeicao(nome, descricao, componentes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void consultaRestaurante(String nome, String atributo) {
		try {
			this.restauranteController.consultaRestaurante(nome, atributo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
