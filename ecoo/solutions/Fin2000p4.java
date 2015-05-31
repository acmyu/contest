import java.io.*;
import java.util.Scanner;
import java.awt.*;
import javax.swing.JFrame;

public class Fin2000p4 extends JFrame
{
	static int da[], nda;
	static int sa[], nsa;
	static int im, imx, imy;
	public static void main (String[] args) throws IOException, InterruptedException
	{
		Scanner in=new Scanner (new FileInputStream ("Fin2000p4DATA41.txt"));
		sa=new int[10];
		da=new int[10];
		nsa=10;
		for(int i=0; i<10; i++)
			sa[i]=in.nextInt()*5;
		
		(new Fin2000p4()).doAnimate();
	}

	Fin2000p4()
	{
		setSize (400, 250);
		setVisible (true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint (Graphics g)
	{
		super.paint (g);
		g.setColor (Color.green);
		int x=400;
		for(int i=0; i<nsa; i++)
		{
			x = x-sa[i]-5;
			if( i==im )
				g.fillRect (x-imx, 240-sa[i]-imy, sa[i], sa[i]);
			else
				g.fillRect (x,     240-sa[i],     sa[i], sa[i]);
		}
		
		x=5;
		for(int i=0; i<nda; i++)
		{
			g.fillRect (x,     85-da[i],     da[i], da[i]);
			x = x+da[i]+5;
		}

	}

	int getmx() // distance that the square moves to the left 
	{
		int x2=400;
		for(int i=0; i<=im; i++)
			x2 = x2-sa[i]-5;
		int x1=5;
		for(int i=0; i<nda; i++)
			x1 = x1+da[i]+5;
		return x2-x1;
	}

	void doAnimate() throws InterruptedException
	{
		int i, j, delay = 50; //200 ms
		for(i=0; i<10; i++)
		{
			im=0; imx=0; imy=0;
			for(j=1; j<nsa; j++)
				if(sa[j]>sa[im])
					im=j;
			for( ; imy<155; imy+=5) // move up
			{  Thread.sleep(delay); repaint(); }
			
			int nm= getmx();
			for( ; imx<nm; imx+=5)  // move left
			{  Thread.sleep(delay); repaint(); }

			da[nda]=sa[im];
			nda++;

			j = im;
			for( ; sa[j]>0; sa[j]-=2) // close rank
			{  Thread.sleep(delay); repaint(); }

			for( ; j<nsa-1; j++)
				sa[j]=sa[j+1];
			nsa--;
		}
	}

}

