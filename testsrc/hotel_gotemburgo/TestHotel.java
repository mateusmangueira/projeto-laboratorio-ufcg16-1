package hotel_gotemburgo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.HotelException;

public class TestHotel {

	private Hospede cliente_1;
	private Hospede cliente_2;
	private Hospede cliente_3;
	private Hospede cliente_4;

	@Before
	public void test() throws HotelException {
		cliente_1 = new Hospede("Lo Porco Voador", "resenhas_del_porco@lol.com", "13/10/2013");
		cliente_2 = new Hospede("Insano Bubassalto", "altos_assalto@hotmail.com", "13/10/2013");
		cliente_3 = new Hospede("Wisla Canibal", "toda_calibalesca@bol.com", "31/01/1970");
		cliente_4 = new Hospede("Amigao Calinfon", "amigao98@yahoo.com.br", "12/02/1998");
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

	/**
	 * Esse teste serah mais util quando for decidido a hierarquia de Pessoa e
	 * Hospede, para que, no caso, verificaremos futuramente se realmente o
	 * cliente eh um hospede, ou uma pessoa que estah apenas cadastrada.
	 */
	@Test
	public void testEquals() {
		Assert.assertEquals(Hospede.class, cliente_1.getClass());
		Assert.assertEquals(Hospede.class, cliente_2.getClass());
		Assert.assertEquals(Hospede.class, cliente_3.getClass());
		Assert.assertEquals(Hospede.class, cliente_4.getClass());

	}

	@Test
	public void testConstrutorWithException() {

		// Teste: Nome do hospede vazio/null.
		try {
			Hospede hospede = new Hospede("  ", "InvisibleGame@loja.com", "03/04/1995");
			Assert.fail("Lancamento de Exception com Nome de Usuario vazio.");

		} catch (Exception exception) {
			Assert.assertEquals("O nome do hospede nao pode ser nulo ou vazio.", exception.getMessage());
		}

		try {
			Hospede hospede = new Hospede(null, "InvisibleGame@loja.com", "03/04/1995");
			Assert.fail("Lancamento de Exception com Nome de Usuario vazio.");

		} catch (Exception exception) {
			Assert.assertEquals("O nome do hospede nao pode ser nulo ou vazio.", exception.getMessage());
		}

		// Teste: Email do hospede vazio/null.
		try {
			Hospede hospede = new Hospede("Tiberuis Carlos Sanchez", "", "07/08/2009");
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (Exception exception) {
			Assert.assertEquals("O email do hospede nao pode ser nulo ou vazio.", exception.getMessage());
		}

		try {
			Hospede hospede = new Hospede("Tiberuis Carlos Sanchez", null, "07/08/2009");
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (Exception exception) {
			Assert.assertEquals("O email do hospede nao pode ser nulo ou vazio.", exception.getMessage());
		}
		
		// Teste: Data de nascimento do hospede vazio/null.
		try {
			Hospede hospede = new Hospede("Cleomar Santos Rocha", "cleomarsr@gmail.com", "  ");
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (Exception exception) {
			Assert.assertEquals("A data de nascimento do hospede nao pode ser nula ou vazia.", exception.getMessage());
		}

		try {
			Hospede hospede = new Hospede("Cleomar Santos Rocha", "cleomarsr@gmail.com", null);
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (Exception exception) {
			Assert.assertEquals("A data de nascimento do hospede nao pode ser nula ou vazia.", exception.getMessage());
		}
	}

}
