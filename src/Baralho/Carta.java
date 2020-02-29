package Baralho;


import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Carta extends JComponent{
	
	private Image cartaImagem;
	private Image cartaFundo;
	
	public int cartaValor;
	public char naipe;
	
	String filename;
	String filename2 = "/deck1.gif";
	
	private boolean cartaVisibilidade;
	
	public Carta(String filename, int cartaValor)
	{
		cartaVisibilidade = true;
		
		if (cartaValor > 10)
			this.cartaValor = 10;
		
		else if (cartaValor == 1) // faz os ases comecarem valendo 11
			this.cartaValor = 11;
		
		else
			this.cartaValor = cartaValor;
		
		this.filename = filename;
		
		imagem();	
		
	}
	
	public void imagem()
	{
			try 
			{
				cartaImagem = ImageIO.read(getClass().getResource(filename));
				//cartaFundo = ImageIO.read(new File(filename2));
				cartaFundo = ImageIO.read(getClass().getResource(filename2));
			
			}
			catch(IOException e) 
			{
				System.out.println(e.getMessage());
				System.exit(1);
			}
			
			
	}
	
	public Image ExibirCarta()
	{
		if (obtemVisibilidade() == true)
	        return cartaImagem;
		
		else 
			return cartaFundo;
	}
	
	public boolean obtemVisibilidade()
	{
	        return cartaVisibilidade;
	}
	
	public void alteraVisibilidade(boolean novaVisibilidade)
	{
	       cartaVisibilidade = novaVisibilidade;
	}
	
}


