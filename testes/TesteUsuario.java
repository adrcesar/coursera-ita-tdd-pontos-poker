import static org.junit.Assert.*;

import org.junit.Test;

public class TesteUsuario {

	@Test
	public void setarUsuario() {
		String usuario = "blefador";
		String nomeUsuario = "FULANO DE TAL";
		Usuario user = new Usuario(usuario, nomeUsuario);
		assertEquals(usuario, user.getUsuario());
		assertEquals(nomeUsuario, user.getNome());
	}

}
