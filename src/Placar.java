import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Placar {

	private String _tipoDePonto = null;
	private int _pontos = 0;
	private Armazenamento armazenamento;
	
	public void geraPontuacaoUsuario(Usuario usuario) throws GeraArquivoException {
		if (usuario.equals(null) || usuario.equals(""))
			throw new GeraArquivoException("Usuário inválido", "Usuário inválido");
		if (getTipoDePonto().equals(null))
			throw new GeraArquivoException("Tipo de ponto inválido", "Tipo de ponto inválido");
		if (getPontos() == 0)
			throw new GeraArquivoException("Número de pontos deve ser maior do que zero", "Número de pontos deve ser maior do que zero");
		armazenamento = new Armazenamento();
		armazenamento.gravarPontosDoUsuario(usuario.getUsuario(), getTipoDePonto(), getPontos());
	}
	
	public Map<String, Integer> buscarPontuacaoUsuario(String usuario) throws LeituraArquivoException {
		List<String> tiposDePontos = new ArrayList<>();
		armazenamento = new Armazenamento();
		tiposDePontos = armazenamento.buscaTipoDePontosPorUsuario(usuario);
		
		Map<String, Integer> pontuacaoUsuario = new HashMap<String, Integer>();
		
		for(String tipoDePonto : tiposDePontos)
			pontuacaoUsuario.put(tipoDePonto.toString(), armazenamento.calculaPontuacaoPorTipoDePontosDoUsuario(usuario, tipoDePonto));
			
		return pontuacaoUsuario;
	}
	
	public Map<String, Integer> geraRanking() throws LeituraArquivoException {
		List<String> usuarios = new ArrayList<>();
		armazenamento = new Armazenamento();
		usuarios = armazenamento.buscaUsuariosPorTipoDePontos(getTipoDePonto());
		
		Map<String, Integer> ranking = new HashMap<String, Integer>();
		
		int pontos = 0;
		for(int i=0; i<usuarios.size(); i++){
			pontos = armazenamento.calculaPontuacaoPorTipoDePontosDoUsuario(usuarios.get(i), getTipoDePonto());
			ranking.put(usuarios.get(i), pontos);
		}
		
		return ordenarMap(ranking);
		
		
	}
	
	private Map<String, Integer> ordenarMap(Map<String, Integer> ranking){
		
		OrdenaMapDecrescente ordenaMap = new OrdenaMapDecrescente(ranking);
		Map<String, Integer> rankingOrdenado = new TreeMap<String, Integer>(ordenaMap);
		rankingOrdenado.putAll(ranking);
		return rankingOrdenado;
		
	}
	
	public void set_tipoDePonto(String _tipoDePonto) {
		this._tipoDePonto = _tipoDePonto;
	}

	public void set_pontos(int _pontos) {
		this._pontos = _pontos;
	}

	public int getPontos() {
		return _pontos;
	}
	
	public String getTipoDePonto() {
		return _tipoDePonto;
	}

	

}
