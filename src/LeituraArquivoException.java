
public class LeituraArquivoException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String minhaMensagem;

	public LeituraArquivoException(String message, String minhaMensagem) {
		super(message);
		this.minhaMensagem = minhaMensagem;
				
	}

	public String getminhaMensagem() {
		return minhaMensagem;
	}
	

}
