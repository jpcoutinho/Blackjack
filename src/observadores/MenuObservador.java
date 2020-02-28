package observadores;

public interface MenuObservador {
	
	public void atualizar(MenuObservado observado);
	
	public void atualizar(int qntJog);
	
	public void carregarJogo(String endereco);

}
