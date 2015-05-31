import java.io.*;
import java.util.Scanner;

public class Fin2004p3c
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
			int source[] = new int[9];
			for( int i=1; i<nd; i++ )
				source[s.charAt(i)-'0']++;

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
	//if source has such a domino, take it, and decrease the number by 1.
	public static void add(String chain, int source[])
	{
		if(chain.length() > maxChain.length())
			maxChain = chain;
		char lastd = chain.charAt(chain.length()-1);
		int  nextd = (lastd-'0')%3*3;
		for(int k=0; k<3; k++, nextd++)
		{
			if( source[nextd]>0 )
			{
				int s2[] = source.clone();
				s2[nextd]--;
				add( chain+(char)(nextd+'0'), s2);
			}
		}
	}
}

