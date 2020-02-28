package tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import observadores.JogoObservado;
import observadores.JogoObservador;

public class JogadorBotoes extends JogadorPainel implements ActionListener, JogoObservado{
	
	ArrayList<JButton> listaBotoes = new ArrayList<JButton>(); //na mesma ordem que a abaixo
	
	JButton bater = new JButton("Bater");
	JButton parar = new JButton("Parar");
	JButton render = new JButton("Render-se");
	JButton sentar = new JButton("Sentar");
	JButton apostar = new JButton("Apostar");
	JButton comprar = new JButton("Comprar fichas");
	JButton dobrar = new JButton("Dobrar");
	
	public JLabel carteira = new JLabel("");
	public JLabel apostaValor = new JLabel("");
	public JLabel pontuacaoMao = new JLabel("");
	
	
	int largQuadro;
	int altQuadro;
	
	private ArrayList<JogoObservador> listaObservadores = new ArrayList<JogoObservador>();
	
	int numJogador;
	
	public JogadorBotoes(JogoObservador ob, int numJogador, int largQuadro, int altQuadro)
	{
		
		super(null);
		adicionaObservador(ob);
		
		listaBotoes.add(bater); //botao 0
		listaBotoes.add(parar); 
		listaBotoes.add(render);
		listaBotoes.add(sentar);
		listaBotoes.add(apostar);
		listaBotoes.add(comprar);
		listaBotoes.add(dobrar); //botao 6
		
		this.numJogador = numJogador;
		
		this.largQuadro = largQuadro;
		this.altQuadro = altQuadro;
		
		BotoesOpcoes();
	}
	
	private void BotoesOpcoes()

	{
		setLayout (null);
		
		int larguraBotao = 100;
		int alturaBotao = 50;
		
		bater.setToolTipText("Pede uma nova carta");
		parar.setToolTipText("Mantem a mao e nao pede mais cartas");
		render.setToolTipText("Desistir da mao");
		sentar.setToolTipText("Senta-se a mesa para participar da rodada");
		apostar.setToolTipText("Executa aposta");
		comprar.setToolTipText("Compra $1000 em fichas");
		dobrar.setToolTipText("Dobra a aposta e pega apenas mais uma carta");
		
		bater.setBounds(0, altQuadro-(alturaBotao*2), larguraBotao, alturaBotao);
		parar.setBounds(larguraBotao+6, altQuadro-(alturaBotao*2), larguraBotao, alturaBotao);
		render.setBounds(0,0, larguraBotao, alturaBotao);
		sentar.setBounds((largQuadro-210)/2, ((altQuadro-larguraBotao)/3)*2, 200, alturaBotao);
		apostar.setBounds((largQuadro-210)/2, ((altQuadro-larguraBotao)/3)*2, 200, alturaBotao);
		comprar.setBounds((largQuadro-140)/2, 0, 140, alturaBotao);
		dobrar.setBounds(0, altQuadro-(alturaBotao*3)-6, larguraBotao, alturaBotao);;
		
		carteira.setBounds((largQuadro-100)/2, altQuadro-80, 500, 28);
		carteira.setForeground(Color.white);
		carteira.setFont(new Font("Arial", Font.BOLD, 28));
		
		pontuacaoMao.setBounds((largQuadro-180)/2, altQuadro/2 + 15, 500, 28);
		pontuacaoMao.setForeground(Color.white);
		pontuacaoMao.setFont(new Font("Arial", Font.BOLD, 28));
		
		apostaValor.setBounds((largQuadro-160)/2, 80, 500, 28);
		apostaValor.setForeground(Color.white);
		apostaValor.setFont(new Font("Arial", Font.BOLD, 28));
		
		add(bater);
		add(parar);
		add(render);
		add(sentar);
		add(apostar);
		add(comprar);
		add(dobrar);
		
		add(carteira);
		add(apostaValor);
		add(pontuacaoMao);
		
	
		
		bater.addActionListener(this);
		parar.addActionListener(this);
		render.addActionListener(this);
		sentar.addActionListener(this);
		apostar.addActionListener(this);
		comprar.addActionListener(this);
		dobrar.addActionListener(this);
		
		apostar.setVisible(false);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == bater)
		{
			notificaObservador("bater");	
		}
		
		if(e.getSource() == parar)
		{
			notificaObservador("parar");
		}
		
		if(e.getSource() == render)
		{
			notificaObservador("render");
		}
		
		if(e.getSource() == sentar)
		{	
			notificaObservador("sentar");	
		}
		
		if(e.getSource() == apostar)
		{	
			notificaObservador("apostar");	
		}
		
		if(e.getSource() == comprar)
		{	
			notificaObservador("comprar");	
		}
		
		if(e.getSource() == dobrar)
		{	
			notificaObservador("dobrar");	
		}
	}
	
	public void alteraEstadoBotao(int botao, boolean estado)
	{
		 listaBotoes.get(botao).setEnabled(estado);
	}
	
	public void alteraVisibilidadeBotao(int botao, boolean visibilidade)
	{
		 listaBotoes.get(botao).setVisible(visibilidade);
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
			
			if (acao == "bater")
			{
				aux.bater(numJogador); 
				
			}	
			
			if (acao == "parar")
			{
				aux.parar(numJogador); 
				
			}	
			
			if (acao == "render")
			{
				aux.render(numJogador); 
				
			}	
			
			if (acao == "sentar")
			{
				apostar.setVisible(true);
				aux.sentar(numJogador); 
				
			}
			
			if (acao == "apostar")
			{
				//aux.apostar(numJogador); 
				aux.apostar(); 
				
			}
			
			if (acao == "comprar")
			{
				//aux.apostar(numJogador); 
				aux.comprar(numJogador); 
				
			}
			
			if (acao == "dobrar")
			{
				//aux.apostar(numJogador); 
				aux.dobrar(numJogador); 
				
			}
		}
		
	}

	@Override
	public void notificaObservadorAposta(int valor) {
		// TODO Auto-generated method stub
		
	}
	

}
