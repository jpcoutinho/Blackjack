package Baralho;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;

public class Baralho {
	
	private final int TotalNaipes = 4;
	private final int TotalCartas = 52;
	
	ArrayList<Carta> baralho = new ArrayList<Carta>();
	String cartaValorAux;
	
	public Baralho()
	{
		CriarBaralho();
	}
	
	private void CriarBaralho() {
		
		for (int i = 0; i < TotalNaipes; i++)
		{
				String naipe;
				
				if (i == 0)
					naipe = "c";
				
				else if (i == 1)
					naipe = "d";
				
				else if (i == 2)
					naipe = "s";
				
				else
					naipe = "h";
				
			for (int j = 1; j <= (TotalCartas/TotalNaipes); j++)
			{
				if (j == 1)
					cartaValorAux = "a";
				
				else if (j == 10)
					cartaValorAux = "t";
				
				else if (j == 11)
					cartaValorAux = "j";
				
				else if (j == 12)
					cartaValorAux = "q";
				
				else if (j == 13)
					cartaValorAux = "k";
				
				else
					cartaValorAux = String.valueOf(j);
				
				String filename = "imagens/" + cartaValorAux + naipe +".gif";
				//System.out.println(filename);
				
				baralho.add(new Carta(filename, j));
				
			}
		}
		
		embaralhar();
	}
	
	private void embaralhar()
	{
		Collections.shuffle(baralho);
		
		/*for(Carta carta : baralho) {
            System.out.println(carta.filename);
        }*/
	}
	
	public Carta PegarCarta()
	{
		Random gerador = new Random();
		int indiceRand = gerador.nextInt(baralho.size());
		
		//System.out.println("Tamanho baralho antes: " + baralho.size());
		Carta aux = baralho.get(indiceRand);
		
		baralho.remove(indiceRand);
	
		//System.out.println("Tamanho baralho: " + baralho.size() + " cartas");
		
		return aux;
		
	}
	
	public int qntCartas()
	{
		return baralho.size();
	}
}
