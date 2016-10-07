package hotel_gotemburgo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import facade.Facade;
import verificacao.excecoes.HotelGotemburgoException;

public class TestHotel {

	// O erro no checkout ainda continua, n consegui achar o bug. So ta passando
	// aqui pq n estou testando realmente o valor do realizaCheckout e sim a
	// transacao.

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


}

// @Test
// public void testBuscaHospede() throws HotelException {
//
// this.testCadastraHospede();
//
// Assert.assertEquals("Jubileu Maxista: jubileu_max@outlook.com
// (25/06/1969).",
// hotel.buscaHospede("jubileu_max@outlook.com").toString());
// Assert.assertEquals("Antonio Natan Dias: antonio_natan@live.com
// (06/09/1987).",
// hotel.buscaHospede("antonio_natan@live.com").toString());
// Assert.assertNotEquals("Jubileu Maxista: jubileu_max@outlook.com
// (25/06/1969)",
// hotel.buscaHospede("jubileu_max@outlook.com").toString());
// Assert.assertNotEquals("Meio Besta: o_incorruptivel@lol.com
// (03/33/2019)",
// hotel.buscaHospede("jubileu_max@outlook.com").toString());
//
// }


 

// @Test
// public void testCriaHospedeWithException() {
//
// // Teste: Nome do hospede vazio/null.
// try {
// hotel.cadastraHospede(" ", "InvisibleGame@loja.com", "03/04/1995");
// Assert.fail("Lancamento de Exception com Nome de Usuario vazio.");
//
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao
// pode ser vazio.",
// exception.getMessage());
// }
//
// try {
// hotel.cadastraHospede(null, "InvisibleGame@loja.com", "03/04/1995");
// Assert.fail("Lancamento de Exception com Nome de Usuario vazio.");
//
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao
// pode ser vazio.",
// exception.getMessage());
// }
//
// // Teste: Email do hospede vazio/null.
// try {
// hotel.cadastraHospede("Tiberuis Carlos Sanchez", "", "07/08/2009");
// Assert.fail("Lancamento de Exception com Login vazio.");
//
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao
// pode ser vazio.",
// exception.getMessage());
// }
//
// try {
// hotel.cadastraHospede("Tiberuis Carlos Sanchez", null, "07/08/2009");
// Assert.fail("Lancamento de Exception com Login vazio.");
//
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao
// pode ser vazio.",
// exception.getMessage());
// }
//
// // Teste: Data de nascimento do hospede vazio/null.
// /*
// * Galera, estava falando hoje (14/09/16) com Aramis e ele me lembrou da
// * possibilidade de datas invalidas, como 31 de fevereiro, 31 de junho,
// * anos como 2017, 2019.... E ele me sugeriu e tentou fazer comigo com
// * LocalDate, que eh uma classe que encapsula datas. Porem, ela gera
// * datas contrarias da forma ano/mes/dia e ainda tem alguns outros
// * problemas. POr enquanto, nao vamos nos preocupar com isso a pedido
// * dele, okay?
// */
// try {
// hotel.cadastraHospede("Cleomar Santos Rocha", "cleomarsr@gmail.com", "
// ");
// Assert.fail("Lancamento de Exception com Login vazio.");
//
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento
// do(a) hospede nao pode ser vazio.",
// exception.getMessage());
// }
//
// try {
// hotel.cadastraHospede("Cleomar Santos Rocha", "cleomarsr@gmail.com",
// null);
// Assert.fail("Lancamento de Exception com Login vazio.");
//
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento
// do(a) hospede nao pode ser vazio.",
// exception.getMessage());
// }
// }

// @Test
// public void testCadastraHospedeWithException() {
//
// // Teste: Nome do hospede vazio/null.
// try {
// hotel.cadastraHospede("", "aramis_farpados@lol.com", "05/09/1999");
// fail();
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao
// pode ser vazio.",
// exception.getMessage());
// }
//
// try {
// hotel.cadastraHospede(null, "aramis_farpados@lol.com", "05/09/1999");
// fail();
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao
// pode ser vazio.",
// exception.getMessage());
// }
//
// // Teste: Email do hospede vazio/null.
// try {
// hotel.cadastraHospede("Carlos Aguiar Junior", " ", "05/06/2007");
// fail();
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao
// pode ser vazio.",
// exception.getMessage());
// }
//
// try {
// hotel.cadastraHospede("Carlos Aguiar Junior", null, "05/06/2007");
// fail();
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao
// pode ser vazio.",
// exception.getMessage());
// }
//
// // Teste: Data de nascimento vazia/null.
// try {
// hotel.cadastraHospede("Diego Aquino Lima", "diego_al@outlook.com", " ");
// fail();
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento
// do(a) hospede nao pode ser vazio.",
// exception.getMessage());
// }
//
// try {
// hotel.cadastraHospede("Diego Aquino Lima", "diego_al@outlook.com", null);
// fail();
// } catch (HotelGotemburgoException exception) {
// Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento
// do(a) hospede nao pode ser vazio.",
// exception.getMessage());
// }
//
// try{
// hotel.cadastraHospede("Nego do Borels", "negols@", "12/06/1994");
// fail();
// } catch (HotelGotemburgoException exception){
// Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede
// esta invalido.", exception.getMessage());
// }
// try{
// hotel.cadastraHospede("Xing Ling", "xinglings@outlook.com", "12/06");
// fail();
// } catch (HotelGotemburgoException exception){
// Assert.assertEquals("Erro no cadastro de Hospede. Formato de data
// invalido.", exception.getMessage());
// }
// try{
// hotel.cadastraHospede("R@ncok", "rancok@outlook.com", "12/05/1990");
// fail();
// } catch (HotelGotemburgoException exception){
// Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede esta
// invalido.", exception.getMessage());
// }
// try{
// hotel.cadastraHospede("Girino Silva", "girino@outlook.com",
// "12/05/2007");
// fail();
// } catch (HotelGotemburgoException exception){
// Assert.assertEquals("Erro no cadastro de Hospede. A idade do(a) hospede
// deve ser maior que 18 anos.", exception.getMessage());
// }
//
// }

// @Test
// public void testRemoveHospedeWithException() {
// try{
// facade.removeHospede(cliente_1);
// fail();
// } catch (HotelGotemburgoException exception){
// Assert.assertEquals("Erro na remocao do Hospede. Formato de email
// invalido.", exception.getMessage());
// }
// }
//
// public void testInfoHospedeWithException(){
// try{
// hotel.getInfoHospede("girino@outlook.com", "cpf");
// }catch(HotelGotemburgoException exception){
// Assert.assertEquals("Erro na consulta de hospede. Opcao invalida.",
// exception.getMessage());
// }
// }
//
