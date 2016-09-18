package hotel_gotemburgo.restaurante;

import java.util.ArrayList;
import java.util.HashSet;

import excecoes.StringException;
import excecoes.ValorException;
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
	private HashSet<Refeicao> refeicoes;
	
	/**
	 * O restaurante nao recebe parametros no construtor, apenas inicializa
	 * suas colecoes de pratos e de refeicoes
	 */
	public Restaurante() {
		pratos = new HashSet<Prato>();
		refeicoes = new HashSet<Refeicao>();
	}
	
	/* Pratos */
	
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
		for (Prato prato : this.pratos) {
			if (prato.getNome().equalsIgnoreCase(nome))
				return true;
		}
		return false;
	}
	
	/**
	 * Varre o Set de pratos procurando um prato com um nome específico.
	 * Caso encontrado, retorna a referencia ao objeto.
	 * @param nome Nome do prato a ser buscado
	 * @return A referencia ao objeto
	 * @throws Exception
	 */
	public Prato buscaPrato(String nome) throws Exception
	{
		for (Prato prato : this.pratos) {
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
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("Nome nao pode ser nulo ou vazio");
		if (descricao == null || descricao.trim().isEmpty())
			throw new StringException("Descricao nao pode ser vazia ou nula");
		if (preco < 0)
			throw new ValorException("Preco do prato nao pode ser negativo");
		
		if (removePrato(nome))
			cadastraPrato(nome, preco, descricao);
	}
	
	/* Refeicoes */
	
	/**
	 * Realiza o cadastro de uma refeicao ao set de refeicoes do restaurante.
	 * Para isso, verifica se todos os pratos do array recebido como parametro
	 * estao previamente cadastrados no sistema. Se estiverem, cria uma instancia
	 * de Refeicao e a adiciona no set de refeicoes do restaurante.
	 * @param nome
	 * @param descricao
	 * @param pratosDaRefeicao
	 * @return True se o cadastro foi bem sucedido
	 * @throws Exception
	 */
	public boolean cadastraRefeicao(String nome, String descricao, ArrayList<Prato> pratosDaRefeicao) throws Exception 
	{
		for (Prato prato : pratosDaRefeicao) {
			if (!contemPrato(prato.getNome()))
				throw new Exception("Nao foi possivel cadastrar a refeicao," +
						" pois um ou mais pratos nao estavam previamente cadastrados"); // Criar uma subclasse LogicaException
		}
		Refeicao refeicao = new Refeicao(nome, descricao, pratosDaRefeicao);
		return this.refeicoes.add(refeicao);
	}
	
	/**
	 * Varre o Set de refeicoes procurando uma refeicao com um nome específico.
	 * Caso encontrada, retorna true.
	 * @param nome Nome da refeicao a ser procurada
	 * @return True se a refeicao foi encontrado
	 */
	public boolean contemRefeicao(String nome)
	{
		for (Refeicao refeicao: this.refeicoes) {
			if (refeicao.getNome().equalsIgnoreCase(nome))
				return true;
		}
		return false;
	}
	
	/**
	 * Varre o Set de refeicoes procurando uma refeicao com um nome específico.
	 * Caso encontrada, retorna a referencia ao objeto.
	 * @param nome Nome da refeicao a ser buscada
	 * @return A referencia ao objeto
	 * @throws Exception
	 */
	public Refeicao buscaRefeicao(String nome) throws Exception
	{
		for (Refeicao refeicao : refeicoes) {
			if (refeicao.getNome().equalsIgnoreCase(nome))
				return refeicao;
		}
		throw new Exception("Refeicao nao encontrado"); // Substituir por ElementoNaoEncontradoException
	}
	
	/**
	 * Metodo responsavel por remover uma refeicao do set de refeicoes
	 * @param nome Nome da refeicao
	 * @return True se a remocao foi bem sucedida
	 * @throws Exception 
	 */
	public boolean removeRefeicao(String nome) throws Exception 
	{
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("Nome da refeicao nao pode ser nulo ou vazio");
		
		if (!contemRefeicao(nome))
			throw new Exception("Nenhuma refeicao com esse nome foi encontrado"); // Substituir por ElementoNaoEncontradoException
		
		return (pratos.remove(buscaPrato(nome)));
	}
	
	/**
	 * Atualiza o cadastro de uma refeicao. Caso seja possivel remove-la do set,
	 * significa que essa refeicao esta cadastrada. Logo, apos remove-la, cria-se
	 * um novo objeto com o mesmo nome e os outros atributos recebidos como parametros
	 * @param nome Nome da refeicao
	 * @param descricao Descricao da refeicao
	 * @param pratosDaRefeicao Array de pratos da refeicao
	 * @throws Exception
	 */
	public void atualizaRefeicao(String nome, String descricao, ArrayList<Prato> pratosDaRefeicao) throws Exception
	{
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("Nome nao pode ser nulo ou vazio");
		if (descricao == null || descricao.trim().isEmpty())
			throw new StringException("Descricao nao pode ser vazia ou nula");
		if (pratos == null || pratos.size() < 3 || pratos.size() > 4)
			throw new Exception("Uma refeicao deve ser composta de 3 ou 4 pratos"); // adicionar uma nova Exception na hierarquia
	
		if (removeRefeicao(nome))
			cadastraRefeicao(nome, descricao, pratosDaRefeicao);
	}
	
	
}
