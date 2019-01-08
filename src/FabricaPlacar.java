import java.util.Map;

public class FabricaPlacar {

	Placar _placar;
	
	public FabricaPlacar(){
		
	}
	
	public FabricaPlacar(Placar placar){
		this._placar = placar;
	}
	
	public void geraPontuacaoUsuario(Usuario usuario) throws GeraArquivoException{
		_placar.geraPontuacaoUsuario(usuario);
	}
	
	public Map<String, Integer> buscarPontuacaoUsuario(String usuario) throws LeituraArquivoException {
		return _placar.buscarPontuacaoUsuario(usuario);
		
	}

	public Map<String, Integer> geraRanking() throws LeituraArquivoException {
		return _placar.geraRanking();
		
	}
	
	public void set_tipoDePonto(String _tipoDePonto) {
		_placar.set_tipoDePonto(_tipoDePonto); 
	}

	public void set_pontos(int _pontos) {
		_placar.set_pontos(_pontos);
	}
	
	public String getTipoDePonto() {
		return _placar.getTipoDePonto();
	}
	
	public int getPontos() {
		return _placar.getPontos();
	}

	
	
	
	
}
