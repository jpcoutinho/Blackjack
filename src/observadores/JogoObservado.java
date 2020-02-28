package observadores;

public interface JogoObservado {
	
	public void adicionaObservador(JogoObservador o);
	public void removeObservador(JogoObservador o);
	
	public void notificaObservador(int qntJog);
	public void notificaObservador(String caso);
	
	public void notificaObservadorAposta(int valor);
}
