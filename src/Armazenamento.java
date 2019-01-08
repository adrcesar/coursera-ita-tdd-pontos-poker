import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Armazenamento {

	private String _diretorio = "c:/pontuacaoPoker/";
	private String _arquivo = "pontosUsuario.txt";

	public void gravarPontosDoUsuario(String usuario, String tipoDoJogo, int pontos)
			throws GeraArquivoException {

		String registro = usuario + ";" + tipoDoJogo + ";" + pontos;
		gravarRegistro(registro);

	}

	private void gravarRegistro(String registro) throws GeraArquivoException {
		try {
			FileWriter fw = new FileWriter(_diretorio + _arquivo, true);
			BufferedWriter conexao = new BufferedWriter(fw);
			conexao.write(registro);
			conexao.newLine();
			conexao.close();
		} catch (IOException e) {
			throw new GeraArquivoException(e.getMessage(), " - Erro ao gerar arquivo");
		}
	}

	public int calculaPontuacaoPorTipoDePontosDoUsuario(String usuario, String tipoDoJogo)
			throws LeituraArquivoException {

		List<String> linhas = new ArrayList<>();
		linhas = this.lerArquivo();

		int total = 0;
		for (int i = 0; i < linhas.size(); i++) {
			String linha = linhas.get(i);
			String[] quebraDaLinha = linha.split(";");
			
			if (quebraDaLinha[0].equals(usuario) && quebraDaLinha[1].equals(tipoDoJogo))
				total += Integer.parseInt(quebraDaLinha[2]);
		}
		
		return total;
	}
	
	public List<String> buscaUsuariosPorTipoDePontos(String tipoDoJogo) throws LeituraArquivoException {
		List<String> linhas = new ArrayList<>();
		linhas = this.lerArquivo();
		
		List<String> usuarios = new ArrayList<>();

		for (int i = 0; i < linhas.size(); i++) {
			String linha = linhas.get(i);
			String[] quebraDaLinha = linha.split(";");

			if (quebraDaLinha[1].equals(tipoDoJogo)){
				if (!usuarios.contains(quebraDaLinha[0]))
					usuarios.add(quebraDaLinha[0]);
			}	
		}
		return usuarios;
		
	}
	
	public List<String> buscaTipoDePontosPorUsuario(String usuario) throws LeituraArquivoException{
		List<String> linhas = new ArrayList<>();
		linhas = this.lerArquivo();
		
		List<String> tipoDePontos = new ArrayList<>();

		for (int i = 0; i < linhas.size(); i++) {
			String linha = linhas.get(i);
			String[] quebraDaLinha = linha.split(";");

			if (quebraDaLinha[0].equals(usuario)){
				if (!tipoDePontos.contains(quebraDaLinha[1]))
					tipoDePontos.add(quebraDaLinha[1]);
			}	
		}
		return tipoDePontos;
	}
	
	private List<String> lerArquivo()
			throws LeituraArquivoException {

		List<String> linhas = new ArrayList<>();

		File file = new File(_diretorio + _arquivo);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			while (sc.hasNext()) {
				try {
					String linha = sc.nextLine();
					String[] indiceValor = linha.split(";");
					if (indiceValor.length > 3)
						throw new LeituraArquivoException("Formato de arquivo inválido", "Formato de arquivo inválido");
					linhas.add(linha);
				} catch (Exception e) {
					throw new LeituraArquivoException(e.getMessage(), "Formato de arquivo inválido");
				}
			}
			sc.close();
			return linhas;
		} catch (IOException e) {
			throw new LeituraArquivoException(e.getMessage(), "Erro ao abrir o arquivo");
		} finally {
			if (sc != null)
				sc.close();
		}
	}

	

	
}
