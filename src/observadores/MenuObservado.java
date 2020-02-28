package observadores;


public interface MenuObservado {

	public void adicionaObservador(MenuObservador o);
	public void removeObservador(MenuObservador o);
	
	public void notificaObservador(String tela, int qntJog);
	
}
