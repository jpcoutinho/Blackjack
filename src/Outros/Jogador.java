package Outros;

import java.util.ArrayList;
import Baralho.Carta;
import observadores.JogoObservado;
import observadores.JogoObservador;

public class Jogador implements JogoObservado{
	
	public int numJogador;
	
	public boolean sentadoMesa; //variavel de controle que diz se o jogador esta sentado a mesa para rodada atual
	public boolean jogando;
	public boolean blackjack; //se true, o jogador fez um blackjack
	public boolean rendeu; //se true, o jogador fez um blackjack
	
	
	public ArrayList<Carta> mao; 
	
	public int dinheiro;
	public int aposta;
	
	public int pedidoCredito;
	
	private ArrayList<JogoObservador> listaObservadores = new ArrayList<JogoObservador>();
	
	
	public Jogador(JogoObservador ob, int numJogador)
	{
		adicionaObservador(ob);
		
		mao = new ArrayList<Carta>();
		
		this.numJogador = numJogador;
		
		dinheiro = 1000;
		pedidoCredito = 2;
		 
		sentadoMesa = false;
		jogando = false;
		blackjack = false;
		rendeu = false;
		
		if(numJogador == 0)
		{
			sentadoMesa = true;
			jogando = true;
		}
		
	}
	
	
	public boolean obtemSentadoMesa()
	{
		return sentadoMesa;
	}
	
	public void alteraSentadoMesa(boolean sentadoMesa)
	{
		this.sentadoMesa = sentadoMesa;
	}
	
	public boolean obtemSituacaoJogador()
	{
		return jogando;
	}
	
	public void alteraSituacaoJogador(boolean jogando)
	{
		this.jogando = jogando;
	}
	
	public void alteraBlackjack(boolean estado)
	{
		blackjack = estado;
	}
	
	
	public int valorMao()
	{	
		int valor = 0;
	
		
		ArrayList <Integer> posAs = new ArrayList <Integer>(); //vetor com a posicao dos ases na mao do jogador

		
		// buscar na mao carta com valor de 11
		
		for (int i = 0; i < mao.size(); i++)
		{
			if (mao.get(i).cartaValor == 11)
			{
				posAs.add(i);
			}
			
			valor += mao.get(i).cartaValor;
			
		}
		
		if (valor > 21 && posAs.size() > 0)
		{
			
			while(valor > 21 && posAs.size() != 0)
			{
				mao.get(posAs.get(0)).cartaValor = 1;
				
				posAs.remove(0);
			}
			
			//recalcula valor da mao
			
			valor = 0;
			
			for (int i = 0; i < mao.size(); i++)
			{
				if (mao.get(i).cartaValor == 11)
				{
					posAs.add(i);
				}
				
				valor += mao.get(i).cartaValor;
				
			}
		}
		
		if (valor == 21 && mao.size() == 2 && numJogador != 0 )
		{
			notificaObservador("blackjack");
		}
		
		if (valor > 21 && numJogador != 0)
		{

			notificaObservador("estorou");
		}
			
		return valor;
	}


	@Override
	public void adicionaObservador(JogoObservador ob) {
		listaObservadores.add(ob);
		
	}


	@Override
	public void removeObservador(JogoObservador ob) {
		listaObservadores.remove(ob);
		
	}


	@Override
	public void notificaObservador(int qntJog) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void notificaObservador(String acao) {

		JogoObservador aux;
		
		for (int i = 0; i<listaObservadores.size(); i++ ) 
		{
			
			aux = listaObservadores.get(i);
			
			if (acao == "blackjack")
			{
				aux.blackjack(); 
				
			}
			
			if (acao == "estorou" && jogando == true)
			{
				aux.estourouMao(); 
				
			}	
		}	
	}


	@Override
	public void notificaObservadorAposta(int valor) {
		// TODO Auto-generated method stub	
	}
}
