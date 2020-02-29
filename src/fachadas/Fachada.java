package fachadas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Baralho.Baralho;
import Outros.Jogador;
import observadores.JogoObservador;
import tela.*;


public class Fachada {
	
	static MenuQuadro menu;
	static JogoQuadro janelas[];
	static Jogador listaJogadores[];
	static Baralho baralho = new Baralho();
	
	static boolean jogoIniciado;
	
	static int jogadorAtual;
	
	
	public static void iniciaMenuPrincipal (JogoObservador ob) 
	{
		jogoIniciado = false;
		menu = new MenuQuadro(ob);
	}
	
	public static void iniciaMesa(JogoObservador ob, int qntJog, Jogador jogadores[]) 
	{
		janelas = new JogoQuadro[qntJog+1];
		
		
		menu.dispose(); 
		
		for (int i = 0; i <= qntJog; i++)
		{
			janelas[i] = new JogoQuadro(ob, i);
			//listaJogadores[i] = new Jogador(ob, i);
			
			listaJogadores = new Jogador[qntJog+1];
		}
		
		for (int i = 0; i < jogadores.length; i++)
 		{
 			listaJogadores[i] = jogadores[i];
 		}
 		
		
		{//bloco que exibe a tela de apostas
			
			janelas[0].alteraVisibilidadeBotao(0, true);
 			janelas[0].alteraVisibilidadeBotao(1, true);
 			janelas[0].alteraVisibilidadeBotao(2, false);
 			janelas[0].alteraVisibilidadeBotao(3, true);
 			janelas[0].alteraVisibilidadeBotao(4, false);

 			janelas[0].alteraEstadoBotao(0, false);
 			janelas[0].alteraEstadoBotao(3, true);
 			
			
			for (int i = 1; i<janelas.length; i++)
		 	{		
				/*if (i == 1)
		 		{*/
		 			janelas[i].alteraVisibilidadeBotao(0, false);
		 			janelas[i].alteraVisibilidadeBotao(1, false);
		 			janelas[i].alteraVisibilidadeBotao(2, false);
		 			janelas[i].alteraVisibilidadeBotao(3, true);
		 			janelas[i].alteraVisibilidadeBotao(4, false);
		 			janelas[i].alteraVisibilidadeBotao(5, false);
		 			janelas[i].alteraVisibilidadeBotao(6, false);
		 			
		 			//janelas[i].alteraEstadoBotao(4, true);
		 			
		 		/*}*/
					
			/*	else //caso nao seja o jogador atual
				{
					
					janelas[i].alteraVisibilidadeBotao(0, false);
					janelas[i].alteraVisibilidadeBotao(1, false);
					janelas[i].alteraVisibilidadeBotao(2, false);
					janelas[i].alteraVisibilidadeBotao(3, true);
					janelas[i].alteraVisibilidadeBotao(4, false);
						
					//janelas[i].alteraEstadoBotao(4, false);
				}*/
				
				//janelas[i].botoesJogador.apostaValor.setText("Aposta: $" + Integer.toString(listaJogadores[i].aposta));
	 			janelas[i].botoesJogador.carteira.setText("$" + Integer.toString(listaJogadores[i].dinheiro));
				
	 			janelas[i].botoesJogador.repaint();
		 	}	
		}
		
		for (int i = 1; i < janelas.length; i++)
		{
			if(listaJogadores[i].dinheiro == 0 && listaJogadores[i].pedidoCredito == 0)
			{
				janelas[i].dispose();
			}
		}
			
	}
	
	public static void fechaMesa(JogoObservador ob)
	{
		for (int i = 0; i < janelas.length; i++)
		{
			janelas[i].dispose();
	
			
		}	
	}
	
	public static void sentaMesa(int numJogador)
	{	
		
		listaJogadores[numJogador].alteraSentadoMesa(true);
		
		janelas[numJogador].alteraVisibilidadeBotao(0, false);
		janelas[numJogador].alteraVisibilidadeBotao(1, false);
		janelas[numJogador].alteraVisibilidadeBotao(2, false);
		janelas[numJogador].alteraVisibilidadeBotao(3, true);
		janelas[numJogador].alteraVisibilidadeBotao(4, false);
		
		
		janelas[numJogador].alteraEstadoBotao(3, false);
		
		janelas[numJogador].botoesJogador.repaint();
		
	}
	
	
	public static void apostaMesa(int numJogador)
	{	
		janelas[numJogador].alteraVisibilidadeBotao(3, false);
		janelas[numJogador].alteraEstadoBotao(4, false);
	}
	
	
	public static void iniciaApostas(ArrayList<Integer> jogadoresValidos)
	{	
		if (verificaJogadoresSentados() == false)
		{
			System.out.println("Não existem jogadores sentados à mesa, portanto rodada de apostas não foi iniciada");
			
			JOptionPane.showMessageDialog(new JFrame(), "Nao existem jogadores sentados a mesa", "Rodada de apostas nao iniciada", JOptionPane.ERROR_MESSAGE);
			return; //partida nao inicia pois nao tem jogadores na mesa
		}

		for (int i = 0; i < janelas.length; i++)
		{
			if (listaJogadores[i].obtemSentadoMesa() == true)
			{
				jogadoresValidos.add(i);		
			}
			
		}
		
		janelas[0].alteraMouseListener(true);
		
		System.out.println("Abriu apostas para os jogadores sentados à mesa");

	}
	
	public static int iniciaPartida()
	{
	
		for (int i = 0; i < janelas.length; i++)
		{
			
			if (listaJogadores[i].obtemSentadoMesa() == true && listaJogadores[i].obtemSituacaoJogador() == false)
			{
				System.out.println("Existe jogador que ainda nao efetuou aposta, portanto a partida nao foi iniciada");
				
				JOptionPane.showMessageDialog(new JFrame(), "Existe jogador que ainda nao efetuou aposta", "Partida nao iniciada", JOptionPane.ERROR_MESSAGE);
				return -1; //partida nao inicia pois nao tem jogadores na mesa
			}

		}
		
		jogoIniciado = true;
		
		
		for (int i = 0; i < janelas.length; i++)
		{
			if (listaJogadores[i].obtemSentadoMesa() == true)
			{
			
				
				for (int j = 0; j<2; j++)
				{
					
					listaJogadores[i].mao.add(baralho.PegarCarta());
					
					if (i == 0 && j == 1)
					listaJogadores[i].mao.get(j).alteraVisibilidade(false); // esconde a segunda carta da banca
					
					
				}
				
				alteraSituacaoJogador(i, true);
			}
			
		}
		
		System.out.println("Cartas devidamente distribuidas");
		
		for (int i = 0; i < janelas.length; i++)
			janelas[i].atualizaQuadro(listaJogadores[i].mao, listaJogadores[i].obtemSentadoMesa());	
		
		System.out.println("Partida iniciada");
		
		return 1;
			
	}
	
	public static void vezJogador(int numJog)
	{
		
			jogadorAtual = numJog;
			int aux;
			
			
			aux = listaJogadores[jogadorAtual].valorMao();
			
			
			System.out.println("Valor da mao do jogador " + numJog + ": " + aux + " pontos");
			
			janelas[0].alteraVisibilidadeBotao(0, true);
		 	janelas[0].alteraVisibilidadeBotao(1, true);
		 	janelas[0].alteraVisibilidadeBotao(2, false);
	 	 	janelas[0].alteraVisibilidadeBotao(3, false);
	 	 	janelas[0].alteraVisibilidadeBotao(4, false);
	 	 	
	 	 	janelas[0].alteraEstadoBotao(0, false);
	 	 	
		
			for (int i = 1; i < janelas.length; i++)
			{
				aux = listaJogadores[i].valorMao();
				
				if (i == numJog)
				{
					janelas[i].alteraVisibilidadeBotao(0, true);
			 	 	janelas[i].alteraVisibilidadeBotao(1, true);
			 	 	janelas[i].alteraVisibilidadeBotao(2, true);
			 	 	janelas[i].alteraVisibilidadeBotao(3, false);
			 	 	janelas[i].alteraVisibilidadeBotao(4, false);
			 	 	janelas[i].alteraVisibilidadeBotao(5, false);
			 	 	janelas[i].alteraVisibilidadeBotao(6, true);
			 	 	
			 	 	janelas[i].alteraEstadoBotao(0, true);
			 	 	janelas[i].alteraEstadoBotao(1, true);
			 	 	janelas[i].alteraEstadoBotao(2, true);	
			 	 	janelas[i].alteraEstadoBotao(6, true);	
				}
				
				else
				{
					janelas[i].alteraVisibilidadeBotao(0, true);
			 	 	janelas[i].alteraVisibilidadeBotao(1, true);
			 	 	janelas[i].alteraVisibilidadeBotao(2, true);
			 	 	janelas[i].alteraVisibilidadeBotao(3, false);
			 	 	janelas[i].alteraVisibilidadeBotao(4, false);
			 	 	janelas[i].alteraVisibilidadeBotao(5, false);
			 		janelas[i].alteraVisibilidadeBotao(6, true);
			 	 	
			 	 	janelas[i].alteraEstadoBotao(0, false);
			 	 	janelas[i].alteraEstadoBotao(1, false);
			 	 	janelas[i].alteraEstadoBotao(2, false);	
			 	 	janelas[i].alteraEstadoBotao(6, false);	
				}
				
				janelas[i].botoesJogador.pontuacaoMao.setText(Integer.toString(aux) + " pontos");
	 			
				janelas[i].botoesJogador.repaint();
			} 	 
			
	 	 	 		
	}
	
	public static void vezJogadorAposta(int numJog)
	{
		
			jogadorAtual = numJog;
			//int aux;
			
			janelas[0].alteraVisibilidadeBotao(0, true);
			janelas[0].alteraVisibilidadeBotao(1, true);
			janelas[0].alteraVisibilidadeBotao(2, true);
			janelas[0].alteraVisibilidadeBotao(3, false);
			janelas[0].alteraVisibilidadeBotao(4, false);
			
			janelas[0].alteraEstadoBotao(0, false);
			
			if (numJog == -1)
			{
				janelas[0].alteraMouseListener(false);
				
				for (int i = 1; i<janelas.length; i++)
	 	 		{	
					janelas[i].alteraVisibilidadeBotao(0, false);
					janelas[i].alteraVisibilidadeBotao(1, false);
					janelas[i].alteraVisibilidadeBotao(2, false);
					janelas[i].alteraVisibilidadeBotao(3, false);
					janelas[i].alteraVisibilidadeBotao(4, true);
					janelas[i].alteraVisibilidadeBotao(5, true);
								
					janelas[i].alteraEstadoBotao(4, false);
					janelas[i].alteraEstadoBotao(5, false);
						
					janelas[i].botoesJogador.repaint();
	 	 		}
				
				return;
	 	 	}
				
				
			for (int i = 1; i<janelas.length; i++)
 	 		{		
				if (i == jogadorAtual)
		 			{
		 				janelas[i].alteraVisibilidadeBotao(0, false);
		 				janelas[i].alteraVisibilidadeBotao(1, false);
		 				janelas[i].alteraVisibilidadeBotao(2, false);
		 				janelas[i].alteraVisibilidadeBotao(3, false);
		 				janelas[i].alteraVisibilidadeBotao(4, true);
		 				janelas[i].alteraVisibilidadeBotao(5, true);
		 				
		 				janelas[i].alteraEstadoBotao(4, true);
		 				janelas[i].alteraEstadoBotao(5, true);
		 				
		 				
		 				janelas[i].botoesJogador.apostaValor.setText("Aposta: $" + Integer.toString(listaJogadores[i].aposta));
		 				janelas[i].botoesJogador.carteira.setText("$" + Integer.toString(listaJogadores[i].dinheiro));
		 			}
					
					else //caso nao seja o jogador atual
					{
					
						janelas[i].alteraVisibilidadeBotao(0, false);
						janelas[i].alteraVisibilidadeBotao(1, false);
						janelas[i].alteraVisibilidadeBotao(2, false);
						janelas[i].alteraVisibilidadeBotao(3, false);
						janelas[i].alteraVisibilidadeBotao(4, true);
						janelas[i].alteraVisibilidadeBotao(5, true);
						
						janelas[i].alteraEstadoBotao(4, false);
						janelas[i].alteraEstadoBotao(5, false);
					}
				
				janelas[i].botoesJogador.repaint();
 	 		}	
			
			System.out.println("Jogador " + jogadorAtual + " fazendo aposta");
	}
	
 	public static void pedirCarta(int numJog)
	{
		if(baralho.qntCartas() == 0)
		{
			baralho = new Baralho();
		}
		
 		listaJogadores[numJog].mao.add(baralho.PegarCarta());
		janelas[numJog].atualizaQuadro(listaJogadores[numJog].mao, listaJogadores[numJog].obtemSentadoMesa());
		
		int aux = listaJogadores[numJog].valorMao();
		
		System.out.println("Valor da mao do jogador " + numJog + ": " + aux + " pontos");
		janelas[numJog].botoesJogador.pontuacaoMao.setText(Integer.toString(aux) + " pontos");
	}
 	
 	public static void jogadaBanca() {
 			
 		System.out.println("A banca efetuou sua jogada");
 		
 		for (int i = 1; i < janelas.length; i++)
		{
 			janelas[i].alteraEstadoBotao(6, false);	
		}
 		
 		listaJogadores[0].mao.get(1).alteraVisibilidade(true);
 		
 		listaJogadores[0].mao.get(1).alteraVisibilidade(true); //torna visivel a carta oculta da banca
 		
 		while(listaJogadores[0].valorMao() <= 16 && listaJogadores[0].mao.size() <=5 )
 			listaJogadores[0].mao.add(baralho.PegarCarta());
 		
 		janelas[0].botoesBanca.pontuacaoMao.setText(Integer.toString(listaJogadores[0].valorMao() ) + " pontos");
 		janelas[0].atualizaQuadro(listaJogadores[0].mao, listaJogadores[0].obtemSentadoMesa());
 		
 		
 		
 	}
 	
 	public static void determinaVencedores(Jogador jogadores[])
 	{
 		int pontucaoBanca = listaJogadores[0].valorMao();
 		
 		for (int i = 1; i < listaJogadores.length; i++)
 		{
 			if (listaJogadores[i].rendeu == true)
 			{
 				listaJogadores[i].dinheiro += listaJogadores[i].aposta/2;
 			}
 			
 			else if (pontucaoBanca <= 21 && pontucaoBanca < listaJogadores[i].valorMao() && listaJogadores[i].valorMao() <= 21 && listaJogadores[i].blackjack == false)
 			{

 				listaJogadores[i].dinheiro += listaJogadores[i].aposta*2;
 				
 			}
 				
 			else if (pontucaoBanca <= 21 && pontucaoBanca == listaJogadores[i].valorMao() && listaJogadores[i].valorMao() <= 21 && listaJogadores[i].blackjack == false)
 			{
 				listaJogadores[i].dinheiro += listaJogadores[i].aposta;
 				
 			}
 			
 			else if (pontucaoBanca > 21 && listaJogadores[i].valorMao() <= 21)
 			{
 				listaJogadores[i].dinheiro += listaJogadores[i].aposta*2;
 			}
 			
 				
 			listaJogadores[i].aposta = 0; 
 		}
 		
 		System.out.println("Os vecedores receberam seus pagamentos");
 		
 		for (int i = 0; i<jogadores.length; i++)
 		{
 			jogadores[i] = listaJogadores[i];
 		}
 		
 		imprimeTela("fimJogo");
 		
 		
 	}
 
 		
 	private static void imprimeTela(String tela) 
 	{
 		
 		if (tela == "fimJogo")
 		{
	 		
	 		janelas[0].alteraVisibilidadeBotao(0, true);
			janelas[0].alteraVisibilidadeBotao(1, true);
			janelas[0].alteraVisibilidadeBotao(2, false);
			janelas[0].alteraVisibilidadeBotao(3, false);
			janelas[0].alteraVisibilidadeBotao(4, true);
			
			janelas[0].alteraEstadoBotao(0, true);
					
	 		
	 		for (int i = 1; i<janelas.length; i++)
		 		{		
				
		 			janelas[i].alteraVisibilidadeBotao(0, true);
		 			janelas[i].alteraVisibilidadeBotao(1, true);
		 			janelas[i].alteraVisibilidadeBotao(2, true);
		 			janelas[i].alteraVisibilidadeBotao(3, false);
		 			janelas[i].alteraVisibilidadeBotao(4, false);
		 				
		 			janelas[i].alteraEstadoBotao(0, false);
		 			janelas[i].alteraEstadoBotao(1, false);
		 			janelas[i].alteraEstadoBotao(2, false);
		 				
		 			janelas[i].botoesJogador.apostaValor.setText("Aposta: $" + Integer.toString(listaJogadores[i].aposta));
		 			janelas[i].botoesJogador.carteira.setText("$" + Integer.toString(listaJogadores[i].dinheiro));
					
		 			janelas[i].botoesJogador.repaint();
		 		}	
 		}
		
	}

	public static boolean verificaSituacaoJogador(int numJogador) //verifica se o jogador ainda pode jogar na rodada
	{
			
 		if (numJogador >= listaJogadores.length)
 			return false;
 			
		if (listaJogadores[numJogador].obtemSituacaoJogador() == true)
				return true;
				
		return false;
	}
 	
 	public static void alteraSituacaoJogador(int numJogador, boolean jogando) //verifica se o jogador ainda pode jogar na rodada
	{		
		listaJogadores[numJogador].alteraSituacaoJogador(jogando);
				
	}
	
 	
 	
 	public static boolean verificaJogadoresSentados() //verifica se algum jogador sentou-se a mesa. Sem jogadores sentados, a partida nao inicia
	{
		for (int i = 1; i<janelas.length; i++)
		{
			if (listaJogadores[i].obtemSentadoMesa() == true)
				return true;
		
		}
		
		return false;
	}
 	
 	public static boolean verificaJogadoresJogando() //exceto banca
 	{
 		for (int i = 1; i < listaJogadores.length; i++)
		{
			if (listaJogadores[i].obtemSituacaoJogador() == true)
				return true;
		
		}
 		
 		return false;
 		
 	}
 	
 	public static boolean verificaJogadorSentado(int numJogador) //verifica se o jogador ainda pode jogar na rodada
	{
 			
		if (listaJogadores[numJogador].obtemSentadoMesa() == true)
				return true;
				
		return false;
	}
 	

	public static void jogadoresMesa(int jogValidos[]) {
		
		for (int i = 0; i<janelas.length; i++)
		{
			if (listaJogadores[i].obtemSentadoMesa() == true)
				jogValidos[i] = listaJogadores[i].numJogador;
		}
	}
	
	public static void atribuiAposta(int numJogador, int valor) {
		
		int aux = listaJogadores[numJogador].dinheiro;
		
		if(aux - valor < 0  ) //nao pode apostar... fundos insuficientes
			return;
		
		listaJogadores[numJogador].aposta += valor;
		listaJogadores[numJogador].dinheiro -= valor;
		
		janelas[numJogador].botoesJogador.apostaValor.setText("Aposta: $" + Integer.toString(listaJogadores[numJogador].aposta));
		janelas[numJogador].botoesJogador.carteira.setText("$" + Integer.toString(listaJogadores[numJogador].dinheiro));
		
		janelas[numJogador].botoesJogador.repaint();
	}
	
	public static void pagaBlackjack(int numJogador)
	{
		listaJogadores[numJogador].alteraBlackjack(true);
		
		listaJogadores[numJogador].dinheiro += listaJogadores[numJogador].aposta*2.5;
	}
	
	public static void reiniciaJogadores(Jogador jogadores[])
	{
		jogadores[0].sentadoMesa = true;
		jogadores[0].jogando = true;
		jogadores[0].blackjack = false; 
		jogadores[0].rendeu = false; 
		jogadores[0].mao.clear(); 
		
		
		for (int i = 1; i < jogadores.length; i++)
		{
			jogadores[i].sentadoMesa = false;
			jogadores[i].jogando = false;
			jogadores[i].blackjack = false; 
			jogadores[i].rendeu = false; 
			jogadores[i].mao.clear(); 
		}
		
	}

	public static int jogadorRendeu(int numJog) {
		
		if (listaJogadores[numJog].mao.size() == 2)
		{
			listaJogadores[numJog].rendeu = true;
			return 1;
		}
		
		
		JOptionPane.showMessageDialog(new JFrame(), "Nao é possivel se render após ter pedido carta", "Rendição não permitida",JOptionPane.ERROR_MESSAGE);	
		
		return 0;
	}
	
	public static void salvaJogo() throws IOException
	{
		
	    FileWriter save = new FileWriter("save/save.blckj");
	    PrintWriter gravarSave= new PrintWriter(save);
	 
	    //gravarSave.println(listaJogadores[i].numJogador);
	    
	    for (int i = 1; i < listaJogadores.length; i++)
	    {
	    	gravarSave.println(listaJogadores[i].numJogador);
	    	gravarSave.println(listaJogadores[i].dinheiro);
	    	gravarSave.println(listaJogadores[i].pedidoCredito);
	    }
	 
	    save.close();
	 
	    JOptionPane.showMessageDialog(new JFrame(), "Jogo salvo com sucesso", "Salvar jogo",JOptionPane.INFORMATION_MESSAGE);
	    System.out.printf("Jogo salvo com sucesso");
	}

	public static void compraFichas(int numJog) {
		
		if (listaJogadores[numJog].pedidoCredito > 0)
		{
			listaJogadores[numJog].dinheiro += 500;
			listaJogadores[numJog].pedidoCredito--;
			
			
			janelas[numJog].botoesJogador.carteira.setText("$" + Integer.toString(listaJogadores[numJog].dinheiro));
			janelas[numJog].botoesJogador.repaint();
		}
		
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "Nao foi possivel executar a ordem de compra. Limite de compras de fichas excedido", "Compra de fichas nao efetuada",JOptionPane.ERROR_MESSAGE);
			System.out.printf("Limite de compras de fichas excedido");
		}
	}

	public static void carregarJogo(JogoObservador ob, String endereco, Jogador jogadores[]) {

		int aux = 0;
		int numJogAux = 0;
		int qntJog =  0;
		
		File file = new File(endereco);
		BufferedReader reader = null;

		
		try 
		{
			reader = new BufferedReader(new FileReader(file));
		    String text = null;

		    while ((text = reader.readLine()) != null) 
		    {
		    	aux++;
		    }
		   
		    qntJog = aux/3;
		    aux = 0;
		    
		    //System.out.println(qntJog);
		    
		    
		    reader = new BufferedReader(new FileReader(file));
		    text = null;

		    while ((text = reader.readLine()) != null) 
		    {

		    	if (aux == 0)
		    	{
		    		numJogAux = Integer.parseInt(text);
		    		aux++;
		    	}
		    	
		    	else if (aux == 1)
		    	{
		    		// System.out.println("numJogAux: " + numJogAux);
		    		jogadores[numJogAux].dinheiro = Integer.parseInt(text);
		    		aux ++;
		    	}
		    	
		    	else if (aux == 2)
		    	{
		    		jogadores[numJogAux].pedidoCredito = Integer.parseInt(text);
		    		aux = 0;
		    	}
		    		
		    }
		} 
		
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		} 
		finally {
		    try {
		        	if (reader != null) {
		        		reader.close();
		        	}
		    	} 
		    catch (IOException e) {
		 
		    }
		}

		//print out the list
		//System.out.println(list);
		
	}
	
	public static int qntJogSave(String endereco) {

		int aux = 0;
	
		int qntJog =  0;
		
		File file = new File(endereco);
		BufferedReader reader = null;

		
		try 
		{
			reader = new BufferedReader(new FileReader(file));
		    String text = null;

		    while ((text = reader.readLine()) != null) 
		    {
		    	aux ++;
		    }
		   
		    qntJog = aux/3;
		} 
		
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		} 
		finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}

		return qntJog;
		
	}

	public static int dobraAposta(int numJog) 
	{
		if (listaJogadores[numJog].mao.size() == 2)
		{
			
			if (listaJogadores[numJog].dinheiro >= listaJogadores[numJog].aposta)
			{
				atribuiAposta(numJog, listaJogadores[numJog].aposta);	
				return 1;
				
			}
			
			else
			{
				
				JOptionPane.showMessageDialog(new JFrame(), "Nao foi possivel dobrar aposta. Jogador com fundos insuficientes", "Fundos insuficientes",JOptionPane.ERROR_MESSAGE);
				return -1;
			}
			
			
		}
		
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "Nao é possivel dobrar após ter pedido carta", "Dobrar não permitido",JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		
	}

	public static boolean apostaVazia(int numJogador) {
		
		if (listaJogadores[numJogador].aposta == 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Nao e possivel participar de uma rodada sem apostar alguma quantia", "Aposta nao efetuada",JOptionPane.ERROR_MESSAGE);
			return true;
			
		}
		
		return false;
	}
		
}
