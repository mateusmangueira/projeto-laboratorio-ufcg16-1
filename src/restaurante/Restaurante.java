package restaurante;

import hotel_gotemburgo.hospedagem.Hospede;

import java.util.ArrayList;
import java.util.HashSet;

import excecoes.CadastroException;
import excecoes.ConsultaException;
import excecoes.LogicaException;
import excecoes.StringException;
import excecoes.ValorException;
import excecoes.ValoresException;

/**
 * O Restaurante deve ser responsavel por cadastrar, atualizar
 * e remover pratos e refeicoes para oferece-los a hospedes
 * do hotel.
 * 
 * @since 18 de Setembro de 2016
 * @see Prato.java, Refeicao.java, Hospede.java
 */
public class Restaurante {

	/* OBS1: Optei por implementar com HashSet, passivel de HashMap #anderson
	 */
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
	public boolean cadastraPrato(String nome, double preco, String descricao) throws ValoresException {
		Prato novoPrato = new Prato(nome, preco, descricao);
		return this.pratos.add(novoPrato);
	}
	
	/**
	 * Varre o Set de pratos procurando um prato com um nome específicoç
	 * Caso encontrado, retorna true.
	 * @param nome Nome do prato a ser procurado
	 * @return True se o prato foi encontrado
	 */
	public boolean contemPrato(String nome) {
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
	 * @throws LogicaException 
	 * @throws Exception
	 */
	public Prato buscaPrato(String nome) throws LogicaException {
		for (Prato prato : this.pratos) {
			if (prato.getNome().equalsIgnoreCase(nome))
				return prato;
		}
		throw new ConsultaException("Prato nao encontrado."); // Substituir por ElementoNaoEncontradoException
	}
	
	/**
	 * Metodo responsavel por remover um prato do set de pratos
	 * @param nome Nome do prato
	 * @return True se a remocao foi bem sucedida
	 * @throws ValoresException 
	 * @throws LogicaException 
	 * @throws Exception 
	 */
	public boolean removePrato(String nome) throws ValoresException, LogicaException {
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("O nome do prato nao pode ser nulo ou vazio.");
		
		if (!contemPrato(nome))
			throw new ConsultaException("Nenhum prato com este nome foi encontrado."); // Substituir por ElementoNaoEncontradoException
		
		return (pratos.remove(buscaPrato(nome)));
	}
	
	/**
	 * Atualiza o cadastro de um prato. Caso seja possivel remove-lo do set,
	 * significa que esse prato esta cadastrado. Logo, apos remove-lo, cria-se
	 * um novo objeto com o mesmo nome e os outros atributos recebidos como parametros
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @throws ValoresException 
	 * @throws LogicaException 
	 * @throws Exception
	 */
	public void atualizaPrato(String nome, double preco, String descricao) throws ValoresException, LogicaException 
	{
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("O nome do prato nao pode ser nulo ou vazio.");
		if (descricao == null || descricao.trim().isEmpty())
			throw new StringException("A descricao do prato nao pode ser nula ou vazia.");
		if (preco < 0)
			throw new ValorException("O preco do prato nao pode ser negativo.");
		
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
	 * @throws LogicaException 
	 * @throws ValoresException 
	 * @throws Exception
	 */
	public boolean cadastraRefeicao(String nome, String descricao, ArrayList<Prato> pratosDaRefeicao) throws LogicaException, ValoresException {
		for (Prato prato : pratosDaRefeicao) {
			if (!contemPrato(prato.getNome()))
				throw new CadastroException("Nao foi possivel cadastrar a refeicao," +
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
	public boolean contemRefeicao(String nome) {
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
	 * @throws LogicaException 
	 * @throws Exception
	 */
	public Refeicao buscaRefeicao(String nome) throws LogicaException {
		for (Refeicao refeicao : refeicoes) {
			if (refeicao.getNome().equalsIgnoreCase(nome))
				return refeicao;
		}
		throw new ConsultaException("Refeicao nao encontrado"); // Substituir por ElementoNaoEncontradoException
	}
	
	/**
	 * Metodo responsavel por remover uma refeicao do set de refeicoes
	 * @param nome Nome da refeicao
	 * @return True se a remocao foi bem sucedida
	 * @throws ValoresException 
	 * @throws LogicaException 
	 * @throws Exception 
	 */
	public boolean removeRefeicao(String nome) throws ValoresException, LogicaException {
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("O nome da refeicao nao pode ser nulo ou vazio.");
		
		if (!contemRefeicao(nome))
			throw new ConsultaException("Nenhuma refeicao com esse nome foi encontrada."); // Substituir por ElementoNaoEncontradoException
		
		return (pratos.remove(buscaPrato(nome)));
	}
	
	/**
	 * Atualiza o cadastro de uma refeicao. Caso seja possivel remove-la do set,
	 * significa que essa refeicao esta cadastrada. Logo, apos remove-la, cria-se
	 * um novo objeto com o mesmo nome e os outros atributos recebidos como parametros
	 * @param nome Nome da refeicao
	 * @param descricao Descricao da refeicao
	 * @param pratosDaRefeicao Array de pratos da refeicao
	 * @throws ValoresException 
	 * @throws LogicaException 
	 * @throws Exception
	 */
	public void atualizaRefeicao(String nome, String descricao, ArrayList<Prato> pratosDaRefeicao) throws ValoresException, LogicaException {
		if (nome == null || nome.trim().isEmpty())
			throw new StringException("Nome nao pode ser nulo ou vazio");
		if (descricao == null || descricao.trim().isEmpty())
			throw new StringException("Descricao nao pode ser vazia ou nula");
		if (pratos == null || pratos.size() < 3 || pratos.size() > 4)
			throw new ValoresException("Uma refeicao deve ser composta de 3 ou 4 pratos"); // adicionar uma nova Exception na hierarquia
	
		if (removeRefeicao(nome))
			cadastraRefeicao(nome, descricao, pratosDaRefeicao);
	}

	/**
	 * Esse metodo vende um prato solto a um hospede cliente do restaurante,
	 * e adiciona o valor desse prato aos seus gastos no hotel.
	 * @param prato Prato a ser vendido
	 * @param cliente Um hospede que esta consumindo esse prato
	 * @throws LogicaException 
	 * @throws Exception
	 */
	public void vendePrato(String prato, Hospede cliente) throws LogicaException {
		if (!contemPrato(prato))
			throw new ConsultaException("Este prato nao existe no restaurante."); // trocar por uma subclasse de LogicaException
		
		double valor = buscaPrato(prato).getPreco();
		//cliente.addGastos(valor);     // esse metodo nao foi implementado na classe Hospede ainda
	}
	
	/**
	 * Esse metodo vende um prato solto a um hospede cliente do restaurante,
	 * e adiciona o valor desse prato aos seus gastos no hotel.
	 * @param prato Prato a ser vendido
	 * @param cliente Um hospede que esta consumindo esse prato
	 * @throws LogicaException 
	 * @throws Exception
	 */
	public void vendeRefeicao(String refeicao, Hospede cliente) throws LogicaException {
		if (!contemRefeicao(refeicao))
			throw new ConsultaException("Essa refeicao nao existe no restaurante");  // trocar por uma subclasse de LogicaException
		
		double valor = buscaRefeicao(refeicao).getValor();
		//cliente.addGastos(valor);     // esse metodo nao foi implementado na classe Hospede ainda
	}
	
}
