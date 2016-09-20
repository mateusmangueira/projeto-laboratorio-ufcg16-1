package hotel_gotemburgo;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.*;
import quartos.Quarto;
import quartos.TipoDeQuarto;

public class TestHotel {

	private HotelController hotel;
	
	private Hospede cliente_1;
	private Hospede cliente_2;
	private Hospede cliente_3;
	private Hospede cliente_4;
	
	private Quarto quarto_1;
	private Quarto quarto_2;
	private Quarto quarto_3;
	
	
	@Before
	public void test() throws HotelException {
		hotel = new HotelController();
		
		// Criando clientes.
		cliente_1 = hotel.criaHospede("Lo Porco Voador", "resenhas_del_porco@lol.com", "13/10/2013");
		cliente_2 = hotel.criaHospede("Insano Bubassalto", "altos_assalto@hotmail.com", "13/10/2013");
		cliente_3 = hotel.criaHospede("Wisla Canibal", "toda_calibalesca@bol.com", "31/01/1970");
		cliente_4 = hotel.criaHospede("Amigao Calinfon", "amigao98@yahoo.com.br", "12/02/1998");
		
		// Criando hospedes.
		quarto_1 = hotel.criaQuartos("100D", TipoDeQuarto.SIMPLES);
		quarto_2 = hotel.criaQuartos("607B", TipoDeQuarto.LUXO);
		quarto_3 = hotel.criaQuartos("1001A", TipoDeQuarto.PRESIDENCIAL);
		
	}

	@Test
	public void testGetNome() {
		Assert.assertEquals("Lo Porco Voador", cliente_1.getNome());
		Assert.assertEquals("Insano Bubassalto", cliente_2.getNome());
		Assert.assertEquals("Wisla Canibal", cliente_3.getNome());
		Assert.assertEquals("Amigao Calinfon", cliente_4.getNome());

		Assert.assertNotEquals("Cacador de Insanidade", cliente_2.getNome());
		Assert.assertNotEquals("Olavo do X-Oleo", cliente_3.getNome());
	}

	@Test
	public void testGetEmail() {
		Assert.assertEquals("resenhas_del_porco@lol.com", cliente_1.getEmail());
		Assert.assertEquals("altos_assalto@hotmail.com", cliente_2.getEmail());
		Assert.assertEquals("toda_calibalesca@bol.com", cliente_3.getEmail());
		Assert.assertEquals("amigao98@yahoo.com.br", cliente_4.getEmail());

		Assert.assertNotEquals("lugin_insano@hotmail.com", cliente_1.getEmail());
		Assert.assertNotEquals("amigao98@yaho.com.br", cliente_4.getEmail());
	}

	@Test
	public void testGetAnoNascimento() {
		Assert.assertEquals("13/10/2013", cliente_1.getDataNascimento());
		Assert.assertEquals("13/10/2013", cliente_2.getDataNascimento());
		Assert.assertEquals("31/01/1970", cliente_3.getDataNascimento());
		Assert.assertEquals("12/02/1998", cliente_4.getDataNascimento());

		Assert.assertNotEquals("12/10/2013", cliente_1.getDataNascimento());
		Assert.assertNotEquals("13/02/1998", cliente_4.getDataNascimento());
	}
	
	@Test
	public void testCadastraHospede() throws HotelException {
		hotel.cadastraHospede("Jubileu Maxista", "jubileu_max@outlook.com", "25/06/1969");
		hotel.cadastraHospede("Francisco Maxssuel Padre Cicero", "maxssuel_padre@yahoo.com.br", "31/08/1949");
		hotel.cadastraHospede("Antonio Natan Dias", "antonio_natan@live.com", "06/09/1987");
	}
		
	@Test
	public void testBuscaHospede() throws HotelException {
		
		this.testCadastraHospede();		
		
		Assert.assertEquals("Jubileu Maxista: jubileu_max@outlook.com (25/06/1969).", hotel.buscaHospede("jubileu_max@outlook.com").toString());
		Assert.assertEquals("Antonio Natan Dias: antonio_natan@live.com (06/09/1987).", hotel.buscaHospede("antonio_natan@live.com").toString());
		Assert.assertNotEquals("Jubileu Maxista: jubileu_max@outlook.com (25/06/1969)", hotel.buscaHospede("jubileu_max@outlook.com").toString());
		Assert.assertNotEquals("Meio Besta: o_incorruptivel@lol.com (03/33/2019)", hotel.buscaHospede("jubileu_max@outlook.com").toString());
		
	}
	
	/*
	public void testAtualizaCadastro() throws HotelException {
		
		hotel.atualizaCadastro("kleber@bol.com", "Email", "kleber1@bol.com");
		hotel.atualizaCadastro("EanesMoral@yahoo.com", "Email", "EanesMoral123@yahoo.com");
	}	
	 */
	
	@Test
	public void testGetQuaetoID() {

		Assert.assertEquals("100D", quarto_1.getId());
		Assert.assertEquals("607B", quarto_2.getId());
		Assert.assertEquals("1001A", quarto_3.getId());

		Assert.assertNotEquals("10A", quarto_1.getId());
		Assert.assertNotEquals("5010HH", quarto_2.getId());
	}
		
	@Test
	public void testCriaHospedeWithException() {

		// Teste: Nome do hospede vazio/null.
		try {
			hotel.cadastraHospede("  ", "InvisibleGame@loja.com", "03/04/1995");
			Assert.fail("Lancamento de Exception com Nome de Usuario vazio.");

		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.", exception.getMessage());
		}

		try {
			hotel.cadastraHospede(null, "InvisibleGame@loja.com", "03/04/1995");
			Assert.fail("Lancamento de Exception com Nome de Usuario vazio.");

		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.", exception.getMessage());
		}

		// Teste: Email do hospede vazio/null.
		try {
			hotel.cadastraHospede("Tiberuis Carlos Sanchez", "", "07/08/2009");
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.", exception.getMessage());
		}

		try {
			hotel.cadastraHospede("Tiberuis Carlos Sanchez", null, "07/08/2009");
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.", exception.getMessage());
		}
		
		// Teste: Data de nascimento do hospede vazio/null.
		/*
		 * Galera, estava falando hoje (14/09/16) com Aramis e ele me lembrou da possibilidade de datas invalidas,
		 * como 31 de fevereiro, 31 de junho, anos como 2017, 2019.... E ele me sugeriu e tentou fazer comigo com
		 * LocalDate, que eh uma classe que encapsula datas. Porem, ela gera datas contrarias da forma ano/mes/dia
		 * e ainda tem alguns outros problemas. POr enquanto, nao vamos nos preocupar com isso a pedido dele, okay?
		 */
		try {
			hotel.cadastraHospede("Cleomar Santos Rocha", "cleomarsr@gmail.com", "  ");
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.", exception.getMessage());
		}

		try {
			hotel.cadastraHospede("Cleomar Santos Rocha", "cleomarsr@gmail.com", null);
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.", exception.getMessage());
		}
	}

	@Test
	public void testCadastraHospedeWithException() {
		
		// Teste: Nome do hospede vazio/null.
		try {
			hotel.cadastraHospede("", "aramis_farpados@lol.com", "05/09/1999");
			fail();
		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.", exception.getMessage());
		}
		
		try {
			hotel.cadastraHospede(null, "aramis_farpados@lol.com", "05/09/1999");
			fail();
		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.", exception.getMessage());
		}
		
		// Teste: Email do hospede vazio/null.
		try {
			hotel.cadastraHospede("Carlos Aguiar Junior", "  ", "05/06/2007");
			fail();
		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.", exception.getMessage());
		}
		
		try {
			hotel.cadastraHospede("Carlos Aguiar Junior", null, "05/06/2007");
			fail();
		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.", exception.getMessage());
		}
		
		// Teste: Data de nascimento vazia/null.
		try {
			hotel.cadastraHospede("Diego Aquino Lima", "diego_al@outlook.com", "  ");
			fail();
		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.", exception.getMessage());
		}
		
		try {
			hotel.cadastraHospede("Diego Aquino Lima", "diego_al@outlook.com", null);
			fail();
		} catch (HotelException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.", exception.getMessage());
		}
		
	}
		
}
