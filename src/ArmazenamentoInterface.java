import java.util.List;

public interface ArmazenamentoInterface {
	
	public void gravarPontosDoUsuario(String usuario, String tipoDoJogo, int pontos);
	
	public int calculaPontuacaoPorTipoDePontosDoUsuario(String usuario, String tipoDoJogo);

	List<String> buscaTipoDePontosPorUsuario(String usuario);

}
