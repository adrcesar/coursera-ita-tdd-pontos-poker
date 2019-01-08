import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class TesteArmazenamento {

	@Before
	public void setupArquivoTXT() throws GeraArquivoException{
		String diretorio = "c:/pontuacaoPoker/";
		String arquivo = "pontosUsuario.txt";
		File file = new File(diretorio+arquivo);
		file.delete();
		Armazenamento armazenamento = new Armazenamento();
		armazenamento.gravarPontosDoUsuario("pagador", "CASH GAME", 10);
		armazenamento.gravarPontosDoUsuario("pagador", "SIT&GO", 2);
		armazenamento.gravarPontosDoUsuario("medroso", "TOURNAMENT", 5);
		armazenamento.gravarPontosDoUsuario("blefador", "CASH GAME", 10);
		armazenamento.gravarPontosDoUsuario("blefador", "CASH GAME", 10);
	}
	
	@Test
	public void recuperarPontosPorTipoDePontosDoUsuario() throws LeituraArquivoException{
		String usuario = "blefador";
		String usuario2 = "pagador";
		String usuario3 = "medroso";
		String tipoDePonto = "CASH GAME";
		String tipoDePonto2 = "SIT&GO";
		String tipoDePonto3 = "TOURNAMENT";
		
		Armazenamento armazenamento = new Armazenamento();
		assertEquals(20, armazenamento.calculaPontuacaoPorTipoDePontosDoUsuario(usuario, tipoDePonto));
		assertEquals(10, armazenamento.calculaPontuacaoPorTipoDePontosDoUsuario(usuario2, tipoDePonto));
		assertEquals(2, armazenamento.calculaPontuacaoPorTipoDePontosDoUsuario(usuario2, tipoDePonto2));
		assertEquals(5, armazenamento.calculaPontuacaoPorTipoDePontosDoUsuario(usuario3, tipoDePonto3));
	}
	
	@Test
	public void recuperarUsuariosPorTipoDePontos() throws LeituraArquivoException{
		String tipoDePonto = "CASH GAME";
		String usuario1 = "pagador";
		String usuario2 = "blefador";
		Armazenamento armazenamento = new Armazenamento();
		assertTrue(armazenamento.buscaUsuariosPorTipoDePontos(tipoDePonto).contains(usuario1));
		assertTrue(armazenamento.buscaUsuariosPorTipoDePontos(tipoDePonto).contains(usuario2));
	}
	
	@Test
	public void recuperarTiposDePontosPorUsuario() throws LeituraArquivoException{
		String tipoDeJogo1 = "CASH GAME";
		String tipoDeJogo2 = "SIT&GO";
		String usuario = "pagador";
		Armazenamento armazenamento = new Armazenamento();
		assertTrue(armazenamento.buscaTipoDePontosPorUsuario(usuario).contains(tipoDeJogo1));
		assertTrue(armazenamento.buscaTipoDePontosPorUsuario(usuario).contains(tipoDeJogo2));
	}

}
