
@SuppressWarnings("serial")
public class GeraArquivoException extends Exception {
	
	private String minhaMensagem;

	public GeraArquivoException(String message, String minhaMensagem) {
		
		super(message);
		this.minhaMensagem = minhaMensagem;
				
	}

	public String getminhaMensagem() {
		return minhaMensagem;
	}

}
