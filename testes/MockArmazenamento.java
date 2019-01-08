import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MockArmazenamento implements ArmazenamentoInterface{

	private String _usuario;
	private String _tipoDoJogo;
	private int _pontos;
	
	
	@Override
	public void gravarPontosDoUsuario(String usuario, String tipoDoJogo, int pontos) {
		_usuario = usuario;
		_tipoDoJogo = tipoDoJogo;
		_pontos = pontos;
		
	}

	public void validagravacaoDosPontosDoUsuario(String usuario, String tipoDoJogo, int pontos) {
		assertEquals(usuario, _usuario);
		assertEquals(tipoDoJogo, _tipoDoJogo);
		assertEquals(pontos, _pontos);
	}

	@Override
	public List<String> buscaTipoDePontosPorUsuario(String usuario) {
		List<String> tiposDePontos = new ArrayList<>();
		tiposDePontos.add("CASH GAME");
		tiposDePontos.add("SIT&GO");
		return tiposDePontos;
	}
	
	public void validaTipoDePontos(String tipoDePonto, List<String> tiposDePontos) {
		assertTrue(tiposDePontos.contains(tipoDePonto));
		
	}
	
	@Override
	public int calculaPontuacaoPorTipoDePontosDoUsuario(String usuario, String tipoDoJogo) {
		if (tipoDoJogo == "CASH GAME")
			return 12;
		if (tipoDoJogo == "SIT&GO")
			return 3;
		if (tipoDoJogo == "TOURNAMENT")
			return 6;
		return 0;
					
	}

	public void validaPontuacao(int pontosEsperados, int pontos) {
		assertEquals(pontosEsperados, pontos);
		
	}

	public List<String> buscaUsuariosPorTipoDePontos(String tipoDePonto) {
		List<String> usuarios = new ArrayList<>();
		usuarios.add("pagador");
		usuarios.add("blefador");
		usuarios.add("medroso");
		return usuarios;
	}

	public void validaUsuarios(String usuarioEsperado, List<String> usuarios) {
		assertTrue(usuarios.contains(usuarioEsperado));
	}

	

	
	

	

}
