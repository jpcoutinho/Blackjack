package Baralho;

import java.awt.Graphics;

import javax.swing.JPanel;


public class CartaPainel extends JPanel {
	
	private Carta carta;
	
	public CartaPainel(Carta carta, int positionX, int positionY) {
		
		this.setBounds(positionX, positionY, 20, 100);
		setSize(20,100);
		
		this.carta = carta;
	
		setVisible(true);
		
	}

	@Override
	public void paintComponent( Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(carta.ExibirCarta(), 0, 0, null);
	}

}
