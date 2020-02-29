package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import observadores.*;



public class MenuBotoesOpcao extends MenuPainel implements ActionListener, 	MenuObservado {
	
	private static final long serialVersionUID = 1L;
	
	JButton novoJogo = new JButton("Novo jogo");
	JButton carregar = new JButton("Carregar jogo");
	
	JFileChooser carregarSave = new JFileChooser(); 
	
	String endereco;

	
	MenuQuadro menu;
	
	private ArrayList<MenuObservador> observers = new ArrayList<MenuObservador>();
	
	public  MenuBotoesOpcao()
	{
		
		BotoesOpcoes();
	}
		
	private void BotoesOpcoes()
	{
			
		int larguraBotao = 150;
		int alturaBotao = 50;
			
		novoJogo.setToolTipText("Inicia uma nova partida");
		carregar.setToolTipText("Carrega um jogo anterior");
			
			
		novoJogo.setBounds(400, 800-alturaBotao-50, larguraBotao, alturaBotao);
		carregar.setBounds(400+larguraBotao+100, 800-alturaBotao-50, larguraBotao, alturaBotao);
		
			
		add(novoJogo);
		add(carregar);
			
	
		novoJogo.addActionListener(this);
		carregar.addActionListener(this);
		
			
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == novoJogo)
		{
			
			notificaObservador("numJog", 0); //notifica que deve trocar para uma nova tela
		}
		
		if(e.getSource() == carregar)
		{
			 
		  if (carregarSave.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		  {
		   
			  endereco = carregarSave.getSelectedFile().getAbsolutePath();
			  
			  System.out.println("Endereco do save: " + endereco);
			  
			  notificaObservador("endereco", 0);
		  }
		}
		
		   
	}

	
	@Override
	public void adicionaObservador(MenuObservador o) {

		observers.add(o);
	}

	@Override
	public void removeObservador(MenuObservador o) {
		
		observers.remove(o);
	}

	@Override
	public void notificaObservador(String tela, int qntJog) {
		
		MenuObservador aux;
		
		for (int i = 0; i<observers.size(); i++ ) 
		{
			
			aux = observers.get(i);
			
			if (tela == "numJog")
			{
				System.out.println("Clicou em novo jogo");
				aux.atualizar(new MenuBotoesNumJog()); //atualiza o quadro com um novo painel 
				
			}
			
			if (tela == "endereco")
			{
				aux.carregarJogo(endereco);
				
			}
		}

}
}
