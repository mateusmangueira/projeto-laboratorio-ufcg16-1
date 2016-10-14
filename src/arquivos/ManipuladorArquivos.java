package arquivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManipuladorArquivos {

	public void gravaArquivo(String string) throws IOException {
		File meuArquivo = new File("E:\\workspace\\Projeto_Lp2\\src\\cad_hospedes.txt");
		if (!meuArquivo.exists()) {
			meuArquivo.createNewFile();
		}
		BufferedWriter bf = new BufferedWriter(new FileWriter(meuArquivo));
		bf.write(string);
		bf.close();

	}

	public void gravaArquivoCadastro(String string) throws IOException {
		this.gravaArquivo(string);
	}

	public void gravaArquivoMenu(String string) throws IOException {
		this.gravaArquivo(string);
	}

	public void gravaArquivoTransacoes(String string) throws IOException {
		this.gravaArquivo(string);
	}

	public void gravaArquivoEstadoHotel(String string) throws IOException {
		this.gravaArquivo(string);
	}

}