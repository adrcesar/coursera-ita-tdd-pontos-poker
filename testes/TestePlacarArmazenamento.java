import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class TestePlacarArmazenamento {

	Usuario _usuario;
	Usuario _usuario02;
	Usuario _usuario03;
	Usuario _usuario04;
	
	FabricaPlacar _placar;
	
	@Before
	public void setarUsuarios() {
		String diretorio = "c:/pontuacaoPoker/";
		String arquivo = "pontosUsuario.txt";
		
		File file = new File(diretorio+arquivo);
		file.delete();
		
		String usuario = "blefador";
		String nomeUsuario = "FULANO DE TAL";
		_usuario = new Usuario(usuario, nomeUsuario);
		
		String usuario02 = "pagador";
		String nomeUsuario02 = "CICLANO";
		_usuario02 = new Usuario(usuario02, nomeUsuario02);
		
		String usuario03 = "medroso";
		String nomeUsuario03 = "ELE";
		_usuario03 = new Usuario(usuario03, nomeUsuario03);
		
		String usuario04 = "sonaboa";
		String nomeUsuario04 = "OUTRO";
		_usuario04 = new Usuario(usuario04, nomeUsuario04);
	}
	
	@Test
	public void incluirPontosCashGame() throws GeraArquivoException {
		
		CashGame cash = new CashGame();
		_placar = new FabricaPlacar(cash);
		_placar.geraPontuacaoUsuario(_usuario);
		assertEquals("blefador", _usuario.getUsuario());
		assertEquals(12, _placar.getPontos());
		
	}
	
	@Test
	public void incluirPontosTournament() throws GeraArquivoException {
		
		Tournament tournament = new Tournament();
		_placar = new FabricaPlacar(tournament);
		_placar.geraPontuacaoUsuario(_usuario);
		assertEquals("blefador", _usuario.getUsuario());
		assertEquals(6, _placar.getPontos());
		
	}
	
	@Test
	public void incluirPontosSitGo() throws GeraArquivoException {
		
		SitGo sitGo = new SitGo();
		_placar = new FabricaPlacar(sitGo);
		_placar.geraPontuacaoUsuario(_usuario);
		assertEquals("blefador", _usuario.getUsuario());
		assertEquals(3, _placar.getPontos());
		
	}
	
	@Test
	public void erroNaInclusaoDePontos() throws GeraArquivoException {
		
		//placar sem tipo e sem pontos
		_placar = new FabricaPlacar();
		try {
			_placar.geraPontuacaoUsuario(_usuario);
			fail();
			assertEquals("blefador", _usuario.getUsuario());
			assertEquals(3, _placar.getPontos());
		} catch (RuntimeException e ) {}
		
	}
	
	@Test
	public void retornarTodosPontosDoUsuario() throws GeraArquivoException, LeituraArquivoException {
		
		_placar = new FabricaPlacar();
		
		this.incluirPontosCashGame();
		this.incluirPontosSitGo();
		
		Map<String, Integer> pontuacao = new HashMap<String, Integer>();
				
		pontuacao = _placar.buscarPontuacaoUsuario(_usuario.getUsuario());
		
		assertEquals("blefador", _usuario.getUsuario());
		assertEquals("12", pontuacao.get("CASH GAME").toString());
		assertEquals("3", pontuacao.get("SIT&GO").toString());
		assertNull(pontuacao.get("TOURNAMENT"));
		
	}
	
	@Test
	public void gerarRankingPorTipoDePontosCashGame() throws GeraArquivoException, LeituraArquivoException{
		
		CashGame cash = new CashGame();
		_placar = new FabricaPlacar(cash);
		
		_placar.geraPontuacaoUsuario(_usuario03);
		_placar.geraPontuacaoUsuario(_usuario03);
		_placar.geraPontuacaoUsuario(_usuario03);
		_placar.geraPontuacaoUsuario(_usuario03);
		_placar.geraPontuacaoUsuario(_usuario03);
		
		_placar.geraPontuacaoUsuario(_usuario02);
		_placar.geraPontuacaoUsuario(_usuario02);
		_placar.geraPontuacaoUsuario(_usuario02);
		_placar.geraPontuacaoUsuario(_usuario02);
		_placar.geraPontuacaoUsuario(_usuario02);
		_placar.geraPontuacaoUsuario(_usuario02);
		
		_placar.geraPontuacaoUsuario(_usuario);
		_placar.geraPontuacaoUsuario(_usuario);
		_placar.geraPontuacaoUsuario(_usuario);
		_placar.geraPontuacaoUsuario(_usuario);
		_placar.geraPontuacaoUsuario(_usuario);
		
		Map<String,Integer> ranking = new TreeMap<String, Integer>();
		ranking = _placar.geraRanking();
		
		assertEquals("CASH GAME", _placar.getTipoDePonto());
		assertEquals("{pagador=72, blefador=60, medroso=60}", ranking.toString());
		
		// retorna item por item
		//for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
		//     System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
		//}
		
	}
	
	

}
