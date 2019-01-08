
public class Usuario {

	private String _usuario;
	private String _nome;

	public Usuario(String usuario, String nomeUsuario){
		this._usuario = usuario;
		this._nome = nomeUsuario;
	}

	public String getUsuario() {
		return _usuario;
	}

	public String getNome() {
		return _nome;
	}
	
}
