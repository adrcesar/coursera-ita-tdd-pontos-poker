import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class TestePlacar {

	Usuario _usuario;
	Usuario _usuario02;
	Usuario _usuario03;
	Usuario _usuario04;
	
	FabricaPlacar _placar;
	
	MockArmazenamento mock = new MockArmazenamento();
	
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
		
		mock = new MockArmazenamento();
		CashGame cash = new CashGame();
		_placar = new FabricaPlacar(cash);
		_placar.geraPontuacaoUsuario(_usuario);
		mock.gravarPontosDoUsuario(_usuario.getUsuario(), _placar.getTipoDePonto(), _placar.getPontos());
		mock.validagravacaoDosPontosDoUsuario("blefador", "CASH GAME", 12);
		
	}
	
	@Test
	public void incluirPontosTournament() throws GeraArquivoException {
		
		mock = new MockArmazenamento();
		Tournament tournament = new Tournament();
		_placar = new FabricaPlacar(tournament);
		_placar.geraPontuacaoUsuario(_usuario);
		mock.gravarPontosDoUsuario(_usuario.getUsuario(), _placar.getTipoDePonto(), _placar.getPontos());
		mock.validagravacaoDosPontosDoUsuario("blefador", "TOURNAMENT", 6);
		
	}
	
	@Test
	public void incluirPontosSitGo() throws GeraArquivoException {
		
		mock = new MockArmazenamento();
		SitGo sitGo = new SitGo();
		_placar = new FabricaPlacar(sitGo);
		_placar.geraPontuacaoUsuario(_usuario);
		mock.gravarPontosDoUsuario(_usuario.getUsuario(), _placar.getTipoDePonto(), _placar.getPontos());
		mock.validagravacaoDosPontosDoUsuario("blefador", "SIT&GO", 3);
		
	}
	
	@Test
	public void erroNaInclusaoDePontos() throws GeraArquivoException {
		
		mock = new MockArmazenamento();
		//placar sem tipo e sem pontos
		_placar = new FabricaPlacar();
		try {
			_placar.geraPontuacaoUsuario(_usuario);
			mock.gravarPontosDoUsuario(_usuario.getUsuario(), _placar.getTipoDePonto(), 0);
			fail();
			mock.validagravacaoDosPontosDoUsuario("blefador", "SIT&GO", 3);
		} catch (Exception e ) {}
		
	}
	
	@Test
	public void retornarTodosPontosDoUsuario() throws GeraArquivoException, LeituraArquivoException {
		
		_placar = new FabricaPlacar();
		mock = new MockArmazenamento();
		
		this.incluirPontosCashGame();
		this.incluirPontosSitGo();
		
		Map<String, Integer> pontuacao = new HashMap<String, Integer>();
				
		pontuacao = _placar.buscarPontuacaoUsuario(_usuario.getUsuario());
		
		List<String> tiposDePontos = new ArrayList<>();		
		tiposDePontos = mock.buscaTipoDePontosPorUsuario(_usuario.getUsuario());
		mock.validaTipoDePontos("CASH GAME", tiposDePontos);
		mock.validaTipoDePontos("SIT&GO", tiposDePontos);
		
		int pontos;
		pontos = mock.calculaPontuacaoPorTipoDePontosDoUsuario(_usuario.getUsuario(), tiposDePontos.get(0));
		mock.validaPontuacao(12, pontos);
		
		pontos = mock.calculaPontuacaoPorTipoDePontosDoUsuario(_usuario.getUsuario(), tiposDePontos.get(1));
		mock.validaPontuacao(3, pontos);
		
		assertEquals("blefador", _usuario.getUsuario());
		assertEquals("12", pontuacao.get("CASH GAME").toString());
		assertEquals("3", pontuacao.get("SIT&GO").toString());
		assertNull(pontuacao.get("TOURNAMENT"));
	
	}
	
	@Test
	public void gerarRankingPorTipoDePontosCashGame() throws GeraArquivoException, LeituraArquivoException{
		
		CashGame cash = new CashGame();
		_placar = new FabricaPlacar(cash);
		mock = new MockArmazenamento();
		
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
		
		List<String> usuarios = new ArrayList<>();
		usuarios = mock.buscaUsuariosPorTipoDePontos(_placar.getTipoDePonto());
		mock.validaUsuarios("pagador", usuarios);
		mock.validaUsuarios("blefador", usuarios);
		mock.validaUsuarios("medroso", usuarios);
		
		assertEquals("CASH GAME", _placar.getTipoDePonto());
		assertEquals("{pagador=72, blefador=60, medroso=60}", ranking.toString());
		
		// retorna item por item
		//for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
		//     System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
		//}
		
	}

}
