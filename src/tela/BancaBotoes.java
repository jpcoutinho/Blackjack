package tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import observadores.*;


public class BancaBotoes extends BancaPainel implements ActionListener, MouseListener, JogoObservado
{
	ArrayList<JButton> listaBotoes = new ArrayList<JButton>(); //na mesma ordem que a abaixo
	
	
	JButton salvar = new JButton("Salvar");
	JButton encerrar = new JButton("Fechar");
	JButton iniciar = new JButton("Iniciar rodada");
	JButton abrirAposta = new JButton("Abrir apostas");
	JButton iniciarNovaRodada = new JButton("Iniciar nova rodada");
	
	public JLabel pontuacaoMao = new JLabel("");

	int largQuadro;
	int altQuadro;
	
	private ArrayList<JogoObservador> listaObservadores = new ArrayList<JogoObservador>();
	
	boolean estadoMouseListener;
	
	public BancaBotoes (JogoObservador ob, int largQuadro, int altQuadro)
	{
		
		super(null);
		
		estadoMouseListener = false;
		
		listaBotoes.add(salvar);
		listaBotoes.add(encerrar);
		listaBotoes.add(iniciar);
		listaBotoes.add(abrirAposta);
		listaBotoes.add(iniciarNovaRodada);
		
		adicionaObservador(ob);
		
		this.largQuadro = largQuadro;
		this.altQuadro = altQuadro;
		
		BotoesOpcoes();
	}
	
	
	private void BotoesOpcoes()
	{
		setLayout (null);
		
		int larguraBotao = 100;
		int alturaBotao = 50;
		
		salvar.setToolTipText("Salva o atual estado do jogo");
		encerrar.setToolTipText("Retorna a tela principal");
		iniciar.setToolTipText("Inicia uma nova rodada");
		abrirAposta.setToolTipText("Inicia a rodada de apostas");
		iniciarNovaRodada.setToolTipText("Inicia a rodada de apostas");
		
		salvar.setBounds(5, altQuadro-larguraBotao, larguraBotao, alturaBotao);
		encerrar.setBounds(largQuadro-larguraBotao-10, altQuadro-larguraBotao, larguraBotao, alturaBotao);
		iniciar.setBounds((largQuadro-210)/2, ((altQuadro-larguraBotao)/3)*2, 200, alturaBotao);
		abrirAposta.setBounds((largQuadro-210)/2, ((altQuadro-larguraBotao)/3)*2, 200, alturaBotao);
		iniciarNovaRodada.setBounds((largQuadro-210)/2, altQuadro-larguraBotao, 200, alturaBotao);
		
		add(salvar);
		add(encerrar);
		add(iniciar);
		add(abrirAposta);
		add(iniciarNovaRodada);
		
		pontuacaoMao.setBounds((largQuadro-180)/2, altQuadro/2 + 15, 500, 28);
		pontuacaoMao.setForeground(Color.white);
		pontuacaoMao.setFont(new Font("Arial", Font.BOLD, 28));
		
		add(pontuacaoMao);
		
		salvar.addActionListener(this);
		encerrar.addActionListener(this);
		iniciar.addActionListener(this);
		abrirAposta.addActionListener(this);
		iniciarNovaRodada.addActionListener(this);
		
		addMouseListener(this);
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == salvar)
		{
			notificaObservador("salvar");
		}
		
		if(e.getSource() == encerrar)
		{
		
			Object[] options = {"Encerrar jogo",
            "Continuar jogando"};

			int n = JOptionPane.showOptionDialog(new JFrame(),

					"Você realmente deseja encerrar o jogo atual e retornar à tela principal?",
					"Encerrar jogo",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
			
			
			if (n == 0)
			{
				notificaObservador("fechar");
				
			}
			
		}
		
		if(e.getSource() == iniciar)
		{
			
			notificaObservador("novaPartida");
		}
		
		if(e.getSource() == abrirAposta)
		{
			
			notificaObservador("apostas");
		}
		
		if(e.getSource() == iniciarNovaRodada)
		{
			
			notificaObservador("novaRodada");
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int xMouse = e.getX();
		int yMouse = e.getY();
		
		//System.out.println("Click do mouse em: X: "+ xMouse + " Y: " + yMouse);
		
		if(yMouse <= 60 && xMouse >= LARG_PADRAO/2 - 60*3 && estadoMouseListener == true) //posicao da ficha
		{
			if(xMouse < LARG_PADRAO/2 - 60*2 ) { // ficha 1
				
				notificaObservadorAposta(1);
				System.out.println("Ficha de 1 clicada");	
			}
			
			else if (xMouse < LARG_PADRAO/2 - 60*1) { // ficha2
				
				notificaObservadorAposta(5);
				System.out.println("Ficha de 5 clicada");
			}
				
			else if (xMouse < LARG_PADRAO/2 - 60*0) { //ficha3
				
				notificaObservadorAposta(10);
				System.out.println("Ficha de 10 clicada");
			} 
			
			else if(xMouse < LARG_PADRAO/2 + 60*1) { //ficha4
				
				notificaObservadorAposta(20);
				System.out.println("Ficha de 20 clicada");
			} 
			
			else if(xMouse < LARG_PADRAO/2 + 60*2) { //5
				
				notificaObservadorAposta(50);
				System.out.println("Ficha de 50 clicada");
			}
			
			else if(xMouse < LARG_PADRAO/2 + 60*3) { //6
				
				notificaObservadorAposta(100);
				System.out.println("Ficha de 100 clicada");
			}
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
			
			if (acao == "fechar")
			{
				aux.atualizar("fechar"); 
				
			}
			
			if (acao == "novaPartida")
			{
				aux.atualizar("novaPartida"); 
				
			}
			
			if (acao == "apostas")
			{
				aux.atualizar("apostas"); 
				
			}
			
			if (acao == "novaRodada")
			{
				aux.atualizar("novaRodada"); 
				
			}
			
			if (acao == "salvar")
			{
				aux.atualizar("salvar"); 
				
			}
		}
		
	}

	public void notificaObservadorAposta(int valor) {
		
		JogoObservador aux;
		
		for (int i = 0; i<listaObservadores.size(); i++ ) 
		{
			
			aux = listaObservadores.get(i);
			
			aux.adicionarValor(valor); 
	
		}
	}
	
		
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}


	

