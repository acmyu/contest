import java.io.*;
import java.util.Scanner;


public class Fin2000p2 
{
	static StringBuffer order;
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner( new FileInputStream("Fin2000p2DATA21.txt"));
		for(int c=0; c<5; c++)
		{
			int i, j;
			int N=in.nextInt(); // number of tasks
			int nclues=in.nextInt();
			in.nextLine();
			order=new StringBuffer(N);
			for(i=0; i<N; i++)
				order.append((char)(i+'A'));
			
			String clues[]=new String[nclues];
			for(i=0; i<nclues; i++)
				clues[i]=in.nextLine();
			
			int nSwap = 1;
			while( nSwap>0 )
			{
				nSwap = 0;
				for(j=0; j<nclues; j++)
					nSwap += doSort(clues[j]);
			}
			System.out.println(order);
		}
	}
	
	public static int doSort(String clue)
	{
		int i, ib, ia;
		int nSwap = 0;
		ib=order.indexOf(""+clue.charAt(0));
		for(i=2; i<clue.length(); i++)
		{
			ia = order.indexOf( ""+clue.charAt(i) );
			if(ia>ib)
			{
				char t=order.charAt(ia);
				order.setCharAt(ia, order.charAt(ib));
				order.setCharAt(ib, t);
				ib=ia;
				nSwap++;
			}
		}
		return nSwap;
	}
}
