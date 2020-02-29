package tela;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Baralho.Carta;

public class JogadorPainel extends JPanel{
	
	Image img;

	private ArrayList<Carta> mao = new ArrayList<Carta>();
	
	public JogadorPainel(ArrayList<Carta> mao)
	{
		this.mao = mao;
		imagem();	
	}

	public void imagem()
	{

		String filename = "/blackjackBKG.png";
		
		try 
		{
				img = ImageIO.read(getClass().getResource(filename));
		
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
		//System.out.println("Tamanho da mao em atalizaCartas" + mao.size());
		this.mao = mao;
		
		repaint();
	}
	
	public void paintComponent (Graphics g)
	{
		
			
		super.paintComponent(g);

		g.drawImage(img, 0, 0, null);
		
		if (mao == null)
			return;
		
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