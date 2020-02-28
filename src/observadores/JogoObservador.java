package observadores;


public interface JogoObservador {
	
	
	public void atualizar(int qntJog);
	
	public void atualizar(String acao);
	
	//funcoes referentes ao quadro do jogador
	public void sentar(int numJog); //permite que o jogador jogue uma partida
	
	public void bater(int numJog); //faz com que o jogador peca uma carta
	
	public void render(int numJog);
	
	public void parar(int numJog);
	//
	
	//funcoes referentes ao jogador em si
	public void estourouMao();
	public void blackjack();

	public void apostar();
	
	public void adicionarValor(int valor);

	public void comprar(int numJogador);
	
	public void carregar (String endereco);

	public void dobrar(int numJogador);
	
	

}
