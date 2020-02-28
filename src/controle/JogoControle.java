package controle;

import java.io.IOException;
import java.util.ArrayList;

import Outros.Jogador;
import fachadas.Fachada;

import observadores.JogoObservador;


public class JogoControle implements JogoObservador {
	
	
	int indiceAtual;
	ArrayList <Integer> jogadoresValidos = new ArrayList<Integer> ();
	
	int qntJog; //quantidade de jogadores selecionado no menu
	Jogador jogadores[];
	

	public JogoControle() 
	{	
		System.out.println("Iniciou o menu principal");
		Fachada.iniciaMenuPrincipal(this);
	
	}
	
	public void controleJanelas() 
	{	
		
		if(indiceAtual == 0)
		{
			System.out.println("Encerrando partida");
			encerrarPartida();
		
		}
		
		else
		{
			System.out.println("Vez do jogador " + jogadoresValidos.get(indiceAtual));
			Fachada.vezJogador(jogadoresValidos.get(indiceAtual));
		}
		
		
	}
	
	public void passaVez() 
	{	
		/*if (Fachada.verificaJogadoresJogando() == false) 
		{
			System.out.println("Todos os jogadores terminaram suas jogadas");
			
			indiceAtual = 0;
			
			return;
			
		}*/
		
		
		if(indiceAtual+1 == jogadoresValidos.size())
		{
			System.out.println("Todos os jogadores terminaram suas jogadas");
			indiceAtual = 0;
		}
		
		else
			indiceAtual++;
			
	/*	if (Fachada.verificaSituacaoJogador(jogadoresValidos.get(indiceAtual)) == true) //
		{
			return;
		}
			
		else
		{
				System.out.println("Entrei no else da passaVez");
				passaVez();
		}
		*/
	}
	
	
	public void encerrarPartida()
	{
		
		Fachada.jogadaBanca();
		
		Fachada.determinaVencedores(jogadores);
	}
	
	
	
	@Override
	public void atualizar(int qntJog) {
		
		this.qntJog = qntJog;
		jogadores = new Jogador[qntJog+1];
		
		for (int i = 0; i <= qntJog; i++)
		{
			jogadores[i] = new Jogador(this, i);
		}
		
		Fachada.iniciaMesa(this, qntJog, jogadores);
	}

	@Override
	public void atualizar(String acao) {
		
		if (acao == "fechar")
		{
			Fachada.fechaMesa(this);
			
			indiceAtual = 1;
			jogadoresValidos.clear();
			
			Fachada.iniciaMenuPrincipal(this);
			
			 
		}
		
		if (acao == "apostas") //reacao ao botao iniciar apostas da banca
		{
			
			Fachada.iniciaApostas(jogadoresValidos);
			
			if(Fachada.verificaJogadoresSentados() == true)
			{
				indiceAtual = 1; //determina o jogador que comecara a jogar
			
				Fachada.vezJogadorAposta(jogadoresValidos.get(indiceAtual));
			}
			
		}
				
		if (acao == "novaPartida")
		{
			
			if (Fachada.iniciaPartida() == 1)
			{
				System.out.println("Jogadores validos: " );
				for (Integer jogador : jogadoresValidos)
					System.out.println(" " + jogador);
					
				
				indiceAtual = 1; //determina o jogador que comecara a jogar
				
				
				controleJanelas();
			}
		}
		
		if (acao == "novaRodada")
		{
			Fachada.reiniciaJogadores(jogadores);
			
			jogadoresValidos.clear();
			
			
			Fachada.fechaMesa(null);		
			Fachada.iniciaMesa(this, qntJog, jogadores);	
			
		}
		
		if (acao == "salvar")
		{
			try {
				Fachada.salvaJogo();
			} 
			
			catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		
	
	}


	@Override
	public void sentar(int numJog) {
		
		System.out.println("Jogador " + numJog + " sentou-se à mesa");
		Fachada.sentaMesa(numJog);
		
	}

	@Override
	public void bater(int numJog) {
		
		System.out.println("Jogador " + numJog + " pediu mais uma carta");
		Fachada.pedirCarta(numJog);
		
	}

	@Override
	public void render(int numJog) 
	{
		if (Fachada.jogadorRendeu(numJog) == 1)
		{
			passaVez();	
			controleJanelas();
		}
		
	}

	@Override
	public void parar(int numJog) 
	{
		System.out.println("O jogador " + numJog + " parou");
		
		Fachada.alteraSituacaoJogador(jogadoresValidos.get(indiceAtual), false);
		
		passaVez();	
		
		controleJanelas();
		
	}

	@Override
	public void estourouMao() 
	{
	
		System.out.println("A mao do jogador " + jogadoresValidos.get(indiceAtual) + " estourou");
		
		
		Fachada.alteraSituacaoJogador(jogadoresValidos.get(indiceAtual), false);
		passaVez();	
		 
		controleJanelas();
	}

	@Override
	public void blackjack() {
		
		Fachada.alteraSituacaoJogador(jogadoresValidos.get(indiceAtual), false);
	
		Fachada.pagaBlackjack(jogadoresValidos.get(indiceAtual));
		passaVez();
		
		controleJanelas();
		
	}

	@Override
	public void apostar() {
		
		if (Fachada.apostaVazia(jogadoresValidos.get(indiceAtual)) == true)
			return;
		
		Fachada.alteraSituacaoJogador(jogadoresValidos.get(indiceAtual), true);
		
		System.out.println("Jogador " + jogadoresValidos.get(indiceAtual) + " terminou sua aposta");
			
		indiceAtual++;
			
		if(indiceAtual >= jogadoresValidos.size()) //se todos os jogadores ja tiverem apostado
		{	
			indiceAtual = -1;
			Fachada.vezJogadorAposta(indiceAtual);
			
			System.out.println("Todos os jogadores efetuaram suas apostas");
			return;
		}
			
	/*	while(Fachada.verificaJogadorSentado(jogadoresValidos.get(indiceAtual)) == false)
		{
			indiceAtual++;
		}*/
		
			
		Fachada.vezJogadorAposta(jogadoresValidos.get(indiceAtual));
		
			
		
	}

	@Override
	public void adicionarValor(int valor) {
		Fachada.atribuiAposta(jogadoresValidos.get(indiceAtual), valor);
		
	}

	@Override
	public void comprar(int numJog) {
		
		Fachada.compraFichas(numJog);
		
	}

	@Override
	public void carregar(String endereco) {

		int qntJog;
		this.qntJog = qntJog = Fachada.qntJogSave(endereco);
		
		jogadores = new Jogador[qntJog+1];
		
		for (int i = 0; i <= qntJog; i++)
		{
			jogadores[i] = new Jogador(this, i);
		}
		
		Fachada.carregarJogo(this, endereco, jogadores);
		
		Fachada.iniciaMesa(this, jogadores.length-1, jogadores);
		
	}

	@Override
	public void dobrar(int numJogador) {

		if(Fachada.dobraAposta(numJogador) == 1)
		{
			System.out.println("Jogador " + numJogador + " dobrou a aposta");
			Fachada.alteraSituacaoJogador(jogadoresValidos.get(indiceAtual), false);
			
			Fachada.pedirCarta(numJogador);
		
			passaVez();
			controleJanelas();
		}
		
		else
		{
			//controleJanelas();	
		}
				
	}

}
