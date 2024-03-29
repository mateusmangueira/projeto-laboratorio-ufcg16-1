package arquivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import hotel_gotemburgo.HotelController;
import restaurante.RestauranteController;
import verificacao.excecoes.LogicaException;
import verificacao.excecoes.VerificacaoException;

public class ManipuladorArquivos implements Serializable {

	private static final long serialVersionUID = -2124687044623072966L;

	/**
	 * Metodo no qual grava arquivos, recebendo como parametro uma string de balanco total (balando de Hospedes, Menu ou Transacoes)
	 * e um caminho no qual o arquivo criado serah armazenado.
	 * @param string Balanco total (Seja de Hospedes, do Menu ou de Transacoes)
	 * @param filename string que representa o caminho no qual serah gravado o arquivo a ser criado
	 * @throws IOException Caso a criacao do arquivo for invalida
	 */
	public void gravaArquivo(String string, String filename) throws IOException {
		File meuArquivo = new File(filename);
		if (!meuArquivo.exists()) {
			meuArquivo.createNewFile();
		}
		BufferedWriter bf = new BufferedWriter(new FileWriter(meuArquivo));
		bf.write(string);
		bf.close();

	}
	
	/*Metodos delegadores de gravaArquivo*/

	public void gravaArquivoCadastroHospede(String string) throws IOException {
		this.gravaArquivo(string, "C:\\Users\\Christopher\\Documents\\Projeto_Lp2\\arquivos_sistema\\relatorios\\cad_hospedes.txt");
	}

	public void gravaArquivoMenu(String string) throws IOException {
		this.gravaArquivo(string, "C:\\Users\\Christopher\\Documents\\Projeto_Lp2\\arquivos_sistema\\relatorios\\cad_restaurante.txt");
	}

	public void gravaArquivoTransacoes(String string) throws IOException {
		this.gravaArquivo(string, "C:\\Users\\Christopher\\Documents\\Projeto_Lp2\\arquivos_sistema\\relatorios\\cad_transacoes.txt");
	}

	public void gravaArquivoEstadoHotel(String string) throws IOException {
		this.gravaArquivo(string, "C:\\Users\\Christopher\\Documents\\Projeto_Lp2\\arquivos_sistema\\relatorios\\hotel_principal.txt");
	}
	
	/**
	 * Este metodo armazena um objeto do tipo Hotel em um arquivo, guardando seu estado atual e serializando-o.
	 * @throws IOException
	 */
	public void gravaHotel(HotelController hotel) throws IOException {
		File arquivo = new File("C:\\Users\\Christopher\\Documents\\Projeto_Lp2\\arquivos_sistema\\hug.dat");
		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(arquivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(hotel);
		
		oos.close();
	}
	
	/**
	 * Este metodo carrega um objeto do tipo Hotel e retorna-o para o sistema.
	 * @return Um objeto do tipo Hotel.
	 * @throws IOException
	 * @throws LogicaException 
	 * @throws ClassNotFoundException 
	 */
	public HotelController leHotel() throws IOException, LogicaException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Christopher\\Documents\\Projeto_Lp2\\arquivos_sistema\\hug.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Object objeto = ois.readObject();
		if (objeto == null)
			throw new VerificacaoException("O objeto nao pode ser nulo.");
		
		HotelController novoObjeto = (HotelController) objeto;
		return novoObjeto;
		
	}
	
	/**
	 * Este metodo armazena um objeto do tipo Restaurante em um arquivo, guardando seu estado atual e serializando-o.
	 * @throws IOException
	 */
	public void gravaRestaurante(RestauranteController restaurante) throws IOException {
		File arquivo = new File("C:\\Users\\Christopher\\Documents\\Projeto_Lp2\\arquivos_sistema\\rug.dat");
		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(arquivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(restaurante);
		
		oos.close();
	}
	
	/**
	 * Este metodo carrega um objeto do tipo Restaurante e retorna-o para o sistema.
	 * @return Um objeto do tipo Restaurante
	 * @throws IOException
	 * @throws LogicaException
	 * @throws ClassNotFoundException
	 */
	public RestauranteController leRestaurante() throws IOException, LogicaException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Christopher\\Documents\\Projeto_Lp2\\arquivos_sistema\\rug.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Object objeto = ois.readObject();
		if (objeto == null)
			throw new VerificacaoException("O objeto nao pode ser nulo.");
		
		RestauranteController novoObjeto = (RestauranteController) objeto;
		return novoObjeto;
		
	}

}