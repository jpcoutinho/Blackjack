	package tela;

import java.awt.*; //ferramentas para posicionamento
import java.util.ArrayList;

import javax.swing.*;

import observadores.*;

public class MenuQuadro extends JFrame implements MenuObservador, JogoObservado
{
	
	final int LARG_PADRAO = 1230;
		final int ALT_PADRAO  = 800;
	
		
		MenuBotoesOpcao botoesOpcaoMenu;
		MenuBotoesNumJog botoesNumJog;
		
		Container c;
		
		private ArrayList<JogoObservador> listaObservadores = new ArrayList<JogoObservador>(); 
		
		private MenuObservado menu;
				
		public MenuQuadro(JogoObservador ob) 
		{
			menu = new MenuBotoesOpcao();
			
			adicionaObservador(ob);
			
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension resolucaoTela = tk.getScreenSize();
			
			int larguraTela = resolucaoTela.width; //Referente a resolucao da tela do computador
			int alturaTela = resolucaoTela.height;
			 
			//System.out.println("LT: " + larguraTela + " / " + "AT: "  + alturaTela);
			
			int x = larguraTela/2 - LARG_PADRAO/2;
			int y = alturaTela/2 - ALT_PADRAO/2;
			
			//painelMenu.img = painelMenu.img.getScaledInstance(LARG_PADRAO, ALT_PADRAO, Image.SCALE_AREA_AVERAGING);
			
			setBounds(x, y, LARG_PADRAO, ALT_PADRAO);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			imprimeBotoes();
				
			setTitle("BlackJack");
			setResizable(false);
			pack();
			setVisible(true);
		
		}
		
		private void imprimeBotoes()
		{
			menu.adicionaObservador(this);
			
			getContentPane().removeAll(); 
			getContentPane().add((Component) menu); 
			
			c = getContentPane();
			
			setContentPane(c);
		}
		
		@Override
		public void atualizar(MenuObservado tela) 
		{
			
			menu = tela;
			imprimeBotoes();
		}
		
		public void atualizar(int qntJog) 
		{
			JogoObservador aux;
			
			for (int i = 0; i<listaObservadores.size(); i++ ) 
			{
				
				aux = listaObservadores.get(i);
				
				aux.atualizar(qntJog); //atualiza o quadro com um novo painel 
					
				
			}
			 
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
			
			
			JogoObservador aux;
			
			for (int i = 0; i<listaObservadores.size(); i++ ) 
			{
				
				aux = listaObservadores.get(i);
				
				
				if (qntJog == 1)
				{
					aux.atualizar(1); //atualiza o quadro com um novo painel 
					
				}
				
				else if (qntJog == 2)
				{
					aux.atualizar(2); //atualiza o quadro com um novo painel 
					
				}
				
				else if (qntJog == 3)
				{
					aux.atualizar(3); //atualiza o quadro com um novo painel 
					
				}
				
				else if (qntJog == 4)
				{
					aux.atualizar(4); //atualiza o quadro com um novo painel 
					
				}
			}

			
		}

		@Override
		public void notificaObservador(String caso) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void notificaObservadorAposta(int valor) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void carregarJogo(String endereco) {


			JogoObservador aux;
			
			for (int i = 0; i<listaObservadores.size(); i++ ) 
			{
				
				aux = listaObservadores.get(i);
				
				
			aux.carregar(endereco); //atualiza o quadro com um novo painel 
			}
	
		}
			
	}


