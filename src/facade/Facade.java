package facade;

import easyaccept.EasyAccept;
import excecoes.HotelException;
import excecoes.LogicaException;
import excecoes.StringException;
import excecoes.ValoresException;
import hotel_gotemburgo.HotelController;
import hotel_gotemburgo.quartos.TipoDeQuarto;
import restaurante.RestauranteController;

public class Facade {

	private HotelController hotelController;
	private RestauranteController restauranteController;

	public Facade() {
		this.hotelController = new HotelController();
		this.restauranteController = new RestauranteController();
	}

	public void validaData(String data) {
		this.hotelController.validaData(data);
	}

	public void validaEmail(String email) {
		this.hotelController.validaEmail(email);
	}

	public void validaNome(String nome) {
		this.hotelController.validaNome(nome);
	}

	public void validaQuarto(String quarto) {
		this.hotelController.validaQuarto(quarto);
	}

	public void verificaOcupacao(String id) {
		this.hotelController.verificaOcupacao(id);
	}

	public void verificaTipoQuarto(String tipoQuarto) {
		this.hotelController.verificaTipoQuarto(tipoQuarto);
	}

	public void criaHospede(String nome, String email, String dataNascimento) {
		try {
			this.hotelController.criaHospede(nome, email, dataNascimento);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscaHospede(String email) {
		try {
			this.hotelController.buscaHospede(email);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void isCadastrado(String email) {
		try {
			this.hotelController.isCadastrado(email);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void isHospedado(String email) {
		try {
			this.hotelController.isHospedado(email);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void cadastraHospede(String nome, String email, String dataNascimento) {
		try {
			this.hotelController.cadastraHospede(nome, email, dataNascimento);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}

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

	public void criaQuarto(String idQuarto, TipoDeQuarto tipoQuarto) {
		try {
			this.hotelController.criaQuartos(idQuarto, tipoQuarto);
		} catch (StringException e) {
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

	public void contemPrato(String nome) {
		this.restauranteController.contemPrato(nome);
	}

	public void buscaPrato(String nome) {
		try {
			this.restauranteController.buscaPrato(nome);
		} catch (LogicaException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removePrato(String nome) {
		try {
			this.restauranteController.removePrato(nome);
		} catch (StringException e) {
			System.out.println(e.getMessage());
		} catch (LogicaException e) {
			System.out.println(e.getMessage());
		}
	}

	public void atualizaPrato(String nome, double preco, String descricao) {
		try {
			this.restauranteController.atualizaPrato(nome, preco, descricao);
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

	public void removeRefeicao(String nome) {
		try {
			this.restauranteController.removeRefeicao(nome);
		} catch (ValoresException e) {
			System.out.println(e.getMessage());
		} catch (LogicaException e) {
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

	public static void main(String[] args) {
		args = new String[] {};
		EasyAccept.main(args);
	}
}
