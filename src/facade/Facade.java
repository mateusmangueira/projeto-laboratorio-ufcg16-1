package facade;

import excecoes.HotelException;
import excecoes.StringException;
import hotel_gotemburgo.HotelController;
import hotel_gotemburgo.hospedagem.Hospede;
import hotel_gotemburgo.quartos.Quarto;
import hotel_gotemburgo.quartos.TipoDeQuarto;
import restaurante.RestauranteController;

public class Facade {

	private HotelController hotelController;
	private RestauranteController restauranteController;

	public Facade() {
		this.hotelController = new HotelController();
		this.restauranteController = new RestauranteController();
	}

	public HotelController getHotelController() {
		return hotelController;
	}

	public RestauranteController getRestauranteController() {
		return restauranteController;
	}

	public Hospede criaHospede(String nome, String email, String dataNascimento) {
		try {
			return this.getHotelController().criaHospede(nome, email, dataNascimento);
		} catch (HotelException e) {
			System.out.println("Erro na criacao do Hospede: " + e.getMessage());
		}
		return null;
	}

	public Hospede buscaHospede(String email) {
		try {
			return this.getHotelController().buscaHospede(email);
		} catch (HotelException e) {
			System.out.println("Erro na busca do Hospede: " + e.getMessage());
		}
		return null;
	}

	public boolean isCadastrado(String email) {
		try {
			return this.getHotelController().isCadastrado(email);
		} catch (HotelException e) {
			System.out.println("Erro na consulta ao Hospede: " + e.getMessage());
		}
		return false;
	}

	public boolean isHospedado(String email) {
		try {
			return this.getHotelController().isHospedado(email);
		} catch (HotelException e) {
			System.out.println("Erro na consulta ao Hospede: " + e.getMessage());
		}
		return false;
	}

	public String cadastraHospede(String nome, String email, String dataNascimento) {
		try {
			return this.getHotelController().cadastraHospede(nome, email, dataNascimento);
		} catch (HotelException e) {
			System.out.println("Erro no cadastro do Hospede: " + e.getMessage());
		}
		return null;
	}

	public void removeHospede(String email) {
		try {
			this.getHotelController().removeHospede(email);
		} catch (HotelException e) {
			System.out.println("Erro na remocao do Hospede: " + e.getMessage());
		}
	}

	public String getInfoHospede(String email, String atributo) {
		try {
			return this.getHotelController().getInfoHospede(email, atributo);
		} catch (HotelException e) {
			System.out.println("Erro na consulta de informacaoes do Hospede: " + e.getMessage());
		}
		return null;
	}

	public String getInfoHospedagem(String email, String atributo) {
		try {
			return this.getHotelController().getInfoHospedagem(email, atributo);
		} catch (HotelException e) {
			System.out.println("Erro na consulta de informacoes da hospedagem do Hospede: " + e.getMessage());
		}
		return null;
	}

	public void atualizaCadastro(String email, String atributo, String valor) {
		try {
			this.getHotelController().atualizaCadastro(email, atributo, valor);
		} catch (HotelException e) {
			System.out.println("Erro na atualizacao do cadastro: " + e.getMessage());
		}
	}

	public Quarto criaQuarto(String idQuarto, TipoDeQuarto tipoQuarto) {
		try {
			return this.getHotelController().criaQuartos(idQuarto, tipoQuarto);
		} catch (StringException e) {
			System.out.println("Erro na criacao do quarto: " + e.getMessage());
		}
		return null;
	}

	public String realizaCheckin(String email, int qntDias, String idQuarto, String tipoQuarto) {
		try {
			return this.getHotelController().realizaCheckin(email, qntDias, idQuarto, tipoQuarto);
		} catch (HotelException e) {
			System.out.println("Erro ao realizar Check-in: " + e.getMessage());
		}
		return null;
	}

	public String realizaCheckout(String email, String idQuarto) {
		try {
			return this.getHotelController().realizaCheckout(email, idQuarto);
		} catch (Exception e) {
			System.out.println("Erro ao realizar Check-out: " + e.getMessage());
		}
		return null;
	}

	public String consultaTransacoes(String atributo) {
		try {
			return this.getHotelController().consultaTransacoes(atributo);
		} catch (HotelException e) {
			System.out.println("Erro na consulta de transacoes do Hospede: " + e.getMessage());
		}
		return null;
	}

	public String consultaTransacoes(String atributo, int indice) {
		try {
			return this.getHotelController().consultaTransacoes(atributo, indice);
		} catch (HotelException e) {
			System.out.println("Erro na consulta de transacoes do Hospede: " + e.getMessage());
		}
		return null;
	}
}
