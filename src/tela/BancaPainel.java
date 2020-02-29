package tela;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Baralho.Carta;


public class BancaPainel extends JPanel{
	
	int LARG_PADRAO;
	int ALT_PADRAO;
	
	Image img;
	
	Image ficha1;
	Image ficha5;
	Image ficha10;
	Image ficha20;
	Image ficha50;
	Image ficha100;
	
	
	private ArrayList<Carta> mao = new ArrayList<Carta>();
	

	public BancaPainel(ArrayList<Carta> mao)
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension resolucaoTela = tk.getScreenSize();
		
		int larguraTela = resolucaoTela.width; //Referente a resolucao da tela do computador
		int alturaTela = resolucaoTela.height - 50; //esse -50 e referente a barra do windows 
		
		LARG_PADRAO = larguraTela/3;
		ALT_PADRAO = alturaTela/2;
		
		this.mao = mao;
		imagem ();	
	}

	public void imagem ()
	{

		String filename = "/blackjackBKG.png";

		try 
		{
				img = ImageIO.read(new File(filename));
				
				ficha1 = ImageIO.read(getClass().getResource("/ficha 1$.png"));
				ficha5 = ImageIO.read(getClass().getResource("/ficha 5$.png"));
				ficha10 = ImageIO.read(getClass().getResource("/ficha 10$.png"));
				ficha20 = ImageIO.read(getClass().getResource("/ficha 20$.png"));
				ficha50 = ImageIO.read(getClass().getResource("/ficha 50$.png"));
				ficha100 = ImageIO.read(getClass().getResource("/ficha 100$.png"));
		
		}
		catch(IOException e) 
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		//o bloco abaixo fornece as informacoes sobre a imagem para que o pack do frame funcione perfeitamente
				{
					
					Dimension tamanho = new Dimension(img.getWidth(null), img.getHeight(null));
				    
					setPreferredSize(tamanho);
				    setMinimumSize(tamanho);
				    setMaximumSize(tamanho);
				    setSize(tamanho);
				    
				    setLayout(null);
				    
				}//fim do bloco
	}
	
	public void atualizaCartas(ArrayList<Carta> mao) 
	{
		this.mao = mao;
		
		repaint();
	}
	
	public void paintComponent (Graphics g)
	{
			
		super.paintComponent(g);

		g.drawImage(img, 0, 0, null);
		
		if (mao == null)
		{
			int larguraFicha = 60;
			int alturaFicha = 60;

			g.drawImage(ficha1,  LARG_PADRAO/2 - larguraFicha*3, 0, larguraFicha, alturaFicha, null);
			g.drawImage(ficha5,  LARG_PADRAO/2 - larguraFicha*2, 0, larguraFicha, alturaFicha, null);
			g.drawImage(ficha10, LARG_PADRAO/2 - larguraFicha*1, 0, larguraFicha, alturaFicha, null);
			g.drawImage(ficha20, LARG_PADRAO/2 + larguraFicha*0, 0, larguraFicha, alturaFicha, null);
			g.drawImage(ficha50, LARG_PADRAO/2 + larguraFicha*1, 0, larguraFicha, alturaFicha, null);
			g.drawImage(ficha100,LARG_PADRAO/2 + larguraFicha*2, 0, larguraFicha, alturaFicha, null);
			return;
		}
		
		int x = (img.getWidth(null) - mao.get(0).ExibirCarta().getWidth(null))/2;
		int y = (img.getHeight(null) - mao.get(0).ExibirCarta().getHeight(null))/3;
		
		if (mao.size() > 1)
			x -= (35 * mao.size());
		
		for (int i = 0; i < mao.size(); i++ )
		{
			g.drawImage(mao.get(i).ExibirCarta(), x, y, 75, 100, null);
			x +=82;
		}
	}
}

