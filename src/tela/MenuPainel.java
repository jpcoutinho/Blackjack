package tela;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;

import javax.swing.JPanel;

public class MenuPainel extends JPanel{

	private Image img;

	public MenuPainel()
	{
		imagem();	
	}

	public void imagem()
	{

		String filename = "imagens/menu/menu.jpg";

		try 
		{
				img = ImageIO.read(new File(filename));
		
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
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(img, 0, 0, null);
	}

}
