import java.io.*;
import java.util.Scanner;

public class Fin2004p3
{
	static int nd; //length of da
	static String maxChain;//array of the longest chain of indexes of da

	public static void main (String[]args) throws IOException
	{
		Scanner in = new Scanner (new FileInputStream ("D:\\Fin2004p3DATA31.txt"));
		for(int n=0; n<5; n++)
		{
			String s=in.nextLine();
			nd=s.length();
			
			maxChain = new String(s.substring(0,1));
			String chain = new String(s.substring(0,1));
			String source = new String( s.substring(1) );
			add( chain, source ); 

			for(int i=0; i<maxChain.length(); i++)
			{
				int d = maxChain.charAt(i) - '0';
				System.out.printf("-%01d%01d", d/3, d%3);
			}
			System.out.println("- has length "+maxChain.length());
		}
	}

	//chain: the chain which needs an extra domino.
	//if source has such a domino, take it, and delete it from source. then call itself again.
	public static void add(String chain, String source)
	{
		if(chain.length() > maxChain.length())
			maxChain = chain;
		char lastd = chain.charAt(chain.length()-1);
		char c = (char) ('0'+ (lastd-'0')%3*3);
		for(int k=0; k<3; k++, c++)
		{
			int i = source.indexOf(c);
			if( i>=0 )
				add( chain+c, source.substring(0,i)+source.substring(i+1) );
		}
	}
}

