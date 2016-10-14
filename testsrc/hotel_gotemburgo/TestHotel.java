package hotel_gotemburgo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import facade.Facade;
import verificacao.excecoes.HotelGotemburgoException;

public class TestHotel {

	private Facade facade;

	private String cliente_1;
	private String cliente_2;
	private String cliente_3;
	private String cliente_4;
	private String cliente_5;

	@Before
	public void test() throws HotelGotemburgoException {

		facade = new Facade();

		// Criando clientes.

		cliente_1 = facade.cadastraHospede("Lo Porco Voador", "resenhas_del_porco@lol.com", "13/10/1978");
		cliente_2 = facade.cadastraHospede("Insano Bubassalto", "altos_assalto@hotmail.com", "13/10/1991");
		cliente_3 = facade.cadastraHospede("Wisla Canibal", "toda_calibalesca@bol.com", "31/01/1970");
		cliente_4 = facade.cadastraHospede("Amigao Calinfon", "amigao98@yahoo.com.br", "12/02/1995");
		cliente_5 = facade.cadastraHospede("Mateus Mangueira", "mateus_mangueira@hotmail.com", "25/10/1996");

		// Hospedando os clientes.

		facade.realizaCheckin(cliente_1, 10, "101A", "Simples");
		facade.realizaCheckin(cliente_1, 5, "102A", "Simples");
		facade.realizaCheckin(cliente_2, 5, "102B", "Simples");
		facade.realizaCheckin(cliente_3, 2, "25A", "Presidencial");
		facade.realizaCheckin(cliente_3, 2, "25C", "Presidencial");
		facade.realizaCheckin(cliente_4, 5, "103C", "Luxo");
	}

	@Test
	public void testeGetInfoHospedePorNome() throws HotelGotemburgoException {
		Assert.assertEquals("Lo Porco Voador", facade.getInfoHospede(cliente_1, "Nome"));
		Assert.assertEquals("Insano Bubassalto", facade.getInfoHospede(cliente_2, "Nome"));
		Assert.assertEquals("Wisla Canibal", facade.getInfoHospede(cliente_3, "Nome"));
		Assert.assertEquals("Amigao Calinfon", facade.getInfoHospede(cliente_4, "Nome"));

		Assert.assertNotEquals("Cacador de Insanidade", facade.getInfoHospede(cliente_1, "Nome"));
		Assert.assertNotEquals("Olavo do X-Oleo", facade.getInfoHospede(cliente_3, "Nome"));
	}

	@Test
	public void testGetInfoHospedePorEmail() throws HotelGotemburgoException {
		Assert.assertEquals("resenhas_del_porco@lol.com", facade.getInfoHospede(cliente_1, "Email"));
		Assert.assertEquals("altos_assalto@hotmail.com", facade.getInfoHospede(cliente_2, "Email"));
		Assert.assertEquals("toda_calibalesca@bol.com", facade.getInfoHospede(cliente_3, "Email"));
		Assert.assertEquals("amigao98@yahoo.com.br", facade.getInfoHospede(cliente_4, "Email"));

		Assert.assertNotEquals("insano_cacador@gmail.com", facade.getInfoHospede(cliente_1, "Email"));
		Assert.assertNotEquals("triple_x@hotmail.com", facade.getInfoHospede(cliente_3, "Email"));
	}

	@Test
	public void testGetInfoHospedePorDataAnoNascimento() throws HotelGotemburgoException {
		Assert.assertEquals("13/10/1978", facade.getInfoHospede(cliente_1, "Data de Nascimento"));
		Assert.assertEquals("13/10/1991", facade.getInfoHospede(cliente_2, "Data de Nascimento"));
		Assert.assertEquals("31/01/1970", facade.getInfoHospede(cliente_3, "Data de Nascimento"));
		Assert.assertEquals("12/02/1995", facade.getInfoHospede(cliente_4, "Data de Nascimento"));

		Assert.assertNotEquals("12/10/1996", facade.getInfoHospede(cliente_1, "Data de Nascimento"));
		Assert.assertNotEquals("13/02/1994", facade.getInfoHospede(cliente_4, "Data de Nascimento"));
	}
	
	@Test
	public void testeGetInfoHospedePorPontos() throws HotelGotemburgoException {

		Assert.assertEquals("2", facade.getInfoHospedagem(cliente_1, "Hospedagens Ativas"));
		Assert.assertEquals("101A,102A", facade.getInfoHospedagem(cliente_1, "Quarto"));
		//Cliente_1 ainda tem cartao Padrao.
		facade.realizaCheckout(cliente_1, "101A"); // gastou 1000,00
		facade.realizaCheckout(cliente_1, "102A"); // gastou 500,00
		Assert.assertEquals("R$1500,00", facade.consultaTransacoes("Total"));
		Assert.assertEquals("150", facade.getInfoHospede(cliente_1, "Pontos"));
		
		//Cliente_1 passara a ser Premium.
		facade.realizaCheckin(cliente_1, 5, "101A", "Presidencial");
		facade.realizaCheckout(cliente_1, "101A");
		
		//Premium tem 10% de desconto em gastos no Hotel.
		Assert.assertEquals("375", facade.getInfoHospede(cliente_1, "Pontos"));
		facade.realizaCheckin(cliente_1, 3, "505C", "Luxo");
		facade.realizaCheckout(cliente_1, "505C");
		
		Assert.assertEquals("R$675,00", facade.consultaTransacoes("total", 3));
	}

	@Test
	public void testeGetInfoHospedagemPorQuantidade() throws HotelGotemburgoException {
		Assert.assertEquals("2", facade.getInfoHospedagem(cliente_1, "Hospedagens Ativas"));
		Assert.assertEquals("1", facade.getInfoHospedagem(cliente_2, "Hospedagens Ativas"));
		Assert.assertEquals("2", facade.getInfoHospedagem(cliente_3, "Hospedagens Ativas"));
		Assert.assertEquals("1", facade.getInfoHospedagem(cliente_4, "Hospedagens Ativas"));

		Assert.assertNotEquals("3", facade.getInfoHospedagem(cliente_4, "Hospedagens Ativas"));
		Assert.assertNotEquals("2", facade.getInfoHospedagem(cliente_2, "Hospedagens Ativas"));
	}

	@Test
	public void testeGetInfoHospedagemPorQuarto() throws HotelGotemburgoException {
		Assert.assertEquals("101A,102A", facade.getInfoHospedagem(cliente_1, "Quarto"));
		Assert.assertEquals("25A,25C", facade.getInfoHospedagem(cliente_3, "Quarto"));
		Assert.assertEquals("102B", facade.getInfoHospedagem(cliente_2, "Quarto"));
		Assert.assertEquals("103C", facade.getInfoHospedagem(cliente_4, "Quarto"));

	}

	@Test
	public void testeGetInfoHospedagensPorTotal() throws HotelGotemburgoException {
		Assert.assertEquals("R$1500,00", facade.getInfoHospedagem(cliente_1, "Total"));
		Assert.assertEquals("R$500,00", facade.getInfoHospedagem(cliente_2, "Total"));
		Assert.assertEquals("R$1800,00", facade.getInfoHospedagem(cliente_3, "Total"));
		Assert.assertEquals("R$1250,00", facade.getInfoHospedagem(cliente_4, "Total"));
	}
	
	public void testeAtualizaCadastro() throws HotelGotemburgoException{
		 facade.atualizaCadastro(cliente_1,"Email", "porquito@Negresco.com");
		 facade.atualizaCadastro(cliente_1,"Nome", "Porquito Negresco Branco");
	}

	@Test
	public void testeRemoveHospede() throws HotelGotemburgoException {
		Assert.assertTrue(facade.removeHospede(cliente_5));
	}

	@Test
	public void testeRealizaCheckout() throws HotelGotemburgoException {

		Assert.assertEquals("2", facade.getInfoHospedagem(cliente_1, "Hospedagens Ativas"));

		facade.realizaCheckout(cliente_1, "102A");

		Assert.assertEquals("1", facade.getInfoHospedagem(cliente_1, "Hospedagens Ativas"));
		Assert.assertEquals("101A", facade.getInfoHospedagem(cliente_1, "Quarto"));
		Assert.assertEquals("R$1000,00", facade.getInfoHospedagem(cliente_1, "Total"));
		Assert.assertEquals("1", facade.consultaTransacoes("Quantidade"));
		Assert.assertEquals("R$500,00", facade.consultaTransacoes("Total"));
		Assert.assertEquals("Lo Porco Voador", facade.consultaTransacoes("Nome"));

		Assert.assertNotEquals("Mateus Mangueira", facade.consultaTransacoes("Nome"));
	}
	
	
}