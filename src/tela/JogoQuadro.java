package tela;
	
import java.awt.*;

import java.util.ArrayList;

import javax.swing.JFrame;


import Baralho.Carta;

import observadores.JogoObservador;


public class JogoQuadro extends JFrame {
	
	public int LARG_PADRAO;
	public int ALT_PADRAO;
	
	
	
	public BancaBotoes botoesBanca;
	public JogadorBotoes botoesJogador;
	
	int numJogador;
	
	Container c;
	
	
	public JogoQuadro(JogoObservador ob, int numJogador/*, String nomeJogador*/) 
	{
		
		this.numJogador = numJogador;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension resolucaoTela = tk.getScreenSize();
		
		int larguraTela = resolucaoTela.width; //Referente a resolucao da tela do computador
		int alturaTela = resolucaoTela.height - 50; //esse -50 e referente a barra do windows 
		 
		LARG_PADRAO = larguraTela/3;
		ALT_PADRAO = alturaTela/2;
		
		botoesBanca = new BancaBotoes (ob, LARG_PADRAO, ALT_PADRAO);
		botoesBanca.img = botoesBanca.img.getScaledInstance(LARG_PADRAO, ALT_PADRAO, Image.SCALE_AREA_AVERAGING);

		botoesJogador = new JogadorBotoes (ob, numJogador, LARG_PADRAO, ALT_PADRAO);
		botoesJogador.img = botoesJogador.img.getScaledInstance(LARG_PADRAO, ALT_PADRAO, Image.SCALE_AREA_AVERAGING);
		
		
		int x = 0;
		int y = 0;
		
		switch (numJogador)
		{
			case 1:{
				x = 0;
				y = 0;
				break;
			}
			
			case 2:{
				x = LARG_PADRAO*2;
				y = 0;
				break;
			}
			
			case 3:{
				x = 0;
				y = ALT_PADRAO;
				break;
			}
			
			case 4:{
				x = LARG_PADRAO*2;
				y = ALT_PADRAO;
				break;
			}
			
			default:{
				
				x = larguraTela/2 - LARG_PADRAO/2;
				y = alturaTela/2 - ALT_PADRAO/2;
				
				/*JOptionPane.showMessageDialog(c,"Erro: numero jogadores para a mesa invalido");
				System.exit(0);*/
			}
		}
		
		setBounds(x, y, LARG_PADRAO, ALT_PADRAO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		imprimeBotoes();
		
		setResizable(false);
		//pack();
		setVisible(true);
	}
	
	void imprimeBotoes()
	{
		if (numJogador == 0)
		{
			setTitle("Banca");
			getContentPane().removeAll(); 
			getContentPane().add(botoesBanca); 
				
			c = getContentPane();
				
			setContentPane(c);
		}
		
		else
		{
			setTitle("Jogador " + numJogador);
			getContentPane().removeAll(); 
			getContentPane().add(botoesJogador); 
				
			c = getContentPane();
				
			setContentPane(c);
		}
			
		
	}
	
	public void alteraMouseListener(boolean estado)
	{
		botoesBanca.estadoMouseListener = estado;
	}
	
	public void alteraEstadoBotao(int botao, boolean estado)
	{
		if (numJogador == 0)
			botoesBanca.listaBotoes.get(botao).setEnabled(estado);
		
		else
			botoesJogador.listaBotoes.get(botao).setEnabled(estado);
	}
	
	public void alteraVisibilidadeBotao(int botao, boolean visibilidade)
	{
		if (numJogador == 0)
			botoesBanca.listaBotoes.get(botao).setVisible(visibilidade);
		
		else
			botoesJogador.listaBotoes.get(botao).setVisible(visibilidade);
	}
	
	public void atualizaQuadro(ArrayList<Carta> mao, boolean sentadoMesa) {
		
		if (numJogador == 0)
			botoesBanca.atualizaCartas(mao);
		
		else
		{
			if (sentadoMesa == true)
				botoesJogador.atualizaCartas(mao);
		}
	}
	
	/*public void atualizaBotoes() {
	
		botoesJogador.repaint();
	}*/
	
	
	/*public boolean obtemSentadoMesa()
	{
		return jogador.sentadoMesa;
	}
	
	public void alteraSentadoMesa(boolean sentadoMesa)
	{
		jogador.sentadoMesa = sentadoMesa;
	}*/

}
