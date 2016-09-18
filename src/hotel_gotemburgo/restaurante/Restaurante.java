package hotel_gotemburgo.restaurante;

import java.util.HashSet;

import excecoes.StringException;
import excecoes.ValoresException;

/**
 * O Restaurante deve ser responsavel por cadastrar, atualizar
 * e remover pratos e refeicoes para oferece-los a hospedes
 * do hotel.
 * @since 18 de Setembro de 2016
 * @see Prato.java, Refeicao.java
 */
public class Restaurante {

	// Optei por implementar com HashSet, passivel de HashMap
	private HashSet<Prato> pratos;
	private HashSet<Refeicao> refeicoes; // Falta implementar os metodos das refeicoes
	
	/**
	 * O restaurante nao recebe parametros no construtor, apenas inicializa
	 * suas colecoes de pratos e de refeicoes
	 */
	public Restaurante() {
		pratos = new HashSet<Prato>();
		refeicoes = new HashSet<Refeicao>();
	}
	
	/**
	 * Metodo responsavel por cadastrar um prato no restaurante. Ele recebe os
	 * atributos do prato a ser criado e retorna um boolean confirmando a criacao.
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @return True se a operacao foi bem sucedida
	 * @throws ValoresException
	 */
	public boolean cadastraPrato(String nome, double preco, String descricao) throws ValoresException  
	{
		Prato novoPrato = new Prato(nome, preco, descricao);
		return this.pratos.add(novoPrato);
	}
	
	/**
	 * Varre o Set de pratos procurando um prato com um nome específicoç
	 * Caso encontrado, retorna true.
	 * @param nome Nome do prato a ser procurado
	 * @return True se o prato foi encontrado
	 */
	public boolean contemPrato(String nome)
	{
		for (Prato prato : pratos) {
			if (prato.getNome().equalsIgnoreCase(nome))
				return true;
		}
		return false;
	}
	
	/**
	 * Varre o Set de pratos procurando um prato com um nome específicoç
	 * Caso encontrado, retorna a referencia ao objeto.
	 * @param nome Nome do prato a ser buscado
	 * @return A referencia ao objeto
	 * @throws Exception
	 */
	public Prato buscaPrato(String nome) throws Exception
	{
		for (Prato prato : pratos) {
			if (prato.getNome().equalsIgnoreCase(nome))
				return prato;
		}
		throw new Exception("Prato nao encontrado"); // Substituir por ElementoNaoEncontradoException
	}
	
	/**
	 * Metodo responsavel por remover um prato do set de pratos
	 * @param nome Nome do prato
	 * @return True se a remocao foi bem sucedida
	 * @throws Exception 
	 */
	public boolean removePrato(String nome) throws Exception 
	{
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("Nome do prato nao pode ser nulo ou vazio");
		
		if (!contemPrato(nome))
			throw new Exception("Nenhum prato com esse nome foi encontrado"); // Substituir por ElementoNaoEncontradoException
		
		return (pratos.remove(buscaPrato(nome)));
	}
	
	/**
	 * Atualiza o cadastro de um prato. Caso seja possivel remove-lo do set,
	 * significa que esse prato esta cadastrado. Logo, apos remove-lo, cria-se
	 * um novo objeto com o mesmo nome e os outros atributos recebidos como parametros
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @throws Exception
	 */
	public void atualizaPrato(String nome, double preco, String descricao) throws Exception 
	{
		if (removePrato(nome))
			cadastraPrato(nome, preco, descricao);
	}
	
	
}
