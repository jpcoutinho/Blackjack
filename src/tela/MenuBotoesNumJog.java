package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import observadores.*;
//import controle.*;

public class MenuBotoesNumJog extends MenuPainel implements ActionListener, MenuObservado
{
	
	private static final long serialVersionUID = 2L;
	
	JButton umJog = new JButton("1P");
	JButton doisJog = new JButton("2P");
	JButton tresJog = new JButton("3P");
	JButton quatroJog = new JButton("4P");
	
	JButton voltar = new JButton("Voltar");
	
	private int numJog;

	
	private ArrayList<MenuObservador> listaObservadores = new ArrayList<MenuObservador>();

	
	public MenuBotoesNumJog()
	{	
		BotoesQntJog();
	}
		
	private void BotoesQntJog()
	{
		int larguraBotao = 100;
		int alturaBotao = 50;
			
		umJog.setToolTipText("1 jogador");
		doisJog.setToolTipText("2 jogadores");
		tresJog.setToolTipText("3 jogadores");
		quatroJog.setToolTipText("4 jogadores");
		voltar.setToolTipText("Voltar a tela inicial");
			
		umJog.setBounds    (260,                      800-alturaBotao-50, larguraBotao, alturaBotao);
		doisJog.setBounds  (260+larguraBotao+100,     800-alturaBotao-50, larguraBotao, alturaBotao);
		tresJog.setBounds  (260+(larguraBotao+100)*2, 800-alturaBotao-50, larguraBotao, alturaBotao);
		quatroJog.setBounds(260+(larguraBotao+100)*3, 800-alturaBotao-50, larguraBotao, alturaBotao);
		voltar.setBounds(0, 0, larguraBotao, alturaBotao);
			
		add(umJog);
		add(doisJog);
		add(tresJog);
		add(quatroJog);
		add(voltar);
			
		umJog.addActionListener(this);
		doisJog.addActionListener(this);
		tresJog.addActionListener (this);
		quatroJog.addActionListener(this);
		voltar.addActionListener(this);
	}
		
	public void actionPerformed(ActionEvent e)
	{
			
		if(e.getSource() == voltar){
			notificaObservador("principal", 0);
		}
			
		else
		{
			if(e.getSource() == umJog) {
				numJog = 1;
			}
			
			else 
				if(e.getSource() == doisJog) {
					numJog = 2;
				}
			
			else 
				if(e.getSource() == tresJog)
				{
					numJog = 3;
				}
			
			else 
				if(e.getSource() == quatroJog)
				{
					numJog = 4;
				}
			
			System.out.println("Iniciou jogo para " + numJog + " players");
			notificaObservador("iniciar", numJog);
			 
}
	}

	@Override
	public void adicionaObservador(MenuObservador o) {

		listaObservadores.add(o);
		
	}

	@Override
	public void removeObservador(MenuObservador o) {
		
		listaObservadores.remove(o);
	}

	@Override
	public void notificaObservador(String tela, int qntJog) {
		
		MenuObservador aux;
		
		for (int i = 0; i<listaObservadores.size(); i++ ) 
		{
			
			aux = listaObservadores.get(i);
			
			if (tela == "principal")
			{
				aux.atualizar(new MenuBotoesOpcao()); //atualiza o quadro com um novo painel 
				
			}
			
			if (tela == "iniciar")
			{
				aux.atualizar(qntJog); //atualiza o quadro com um novo painel 
				
			}
		}

}
	
}
