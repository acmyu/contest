import java.io.*;
import java.util.Scanner;

public class Fin2001p1 
{
	static String lines[];
	static int NP; // number of pieces
	static int NL; // number of lines
	static int iaStart[][]; // iaStart[i][j]: start position (index) of the message of i-th piece in j-th line
	static int iaEnd[][]; // iaEnd[i][j]:  end position (index+1) of the message of i-th piece in j-th line
	static int finalOrder[];
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin2001p1DATA11.txt"));
		for(int i=0 ;i<5; i++)
		{		
			NL=in.nextInt();
			in.nextLine();

			lines = new String[NL];
			iaStart = new int[5][NL];
			iaEnd = new int[5][NL];
			int j;
			for(j=0; j<NL; j++)
			{
				lines[j] = in.nextLine()+'=';
				findPiecePosition(j);
			}

			//findRightOrder();
			matchNext( new int[NP], 0, 0 );
			for(j=0; j<NL; j++)
			{
				String s="";
				for(int k=0; k<NP; k++)
				{
					int n = finalOrder[k];
					s += lines[j].substring(iaStart[n][j], iaEnd[n][j]);
				}
				System.out.println(s);
			}
			System.out.println();
		}
	}
	
	// Find the start and end positions (indices) of the message of each piece in n-th line
	public static void findPiecePosition(int n)
	{
		String s = lines[n];
		NP=0;
		char c0='=';
		int i, L = s.length();
		for(i=0; i<L; i++)
		{
			char c=s.charAt(i);
			if(c0=='=' && c!='=')
				iaStart[NP][n] = i;
			if(c0!='='&&c=='=')
				iaEnd[NP++][n] = i;
			c0=c;
		}
	}
	
	// Two pieces match if each line has the same distance between end position of the left piece and
	// start position of the right one 
	public static boolean match(int iL, int iR)
	{
		if(iL==-1) // first piece
		{
			int d=iaStart[iR][0];
			for(int k=1; k<NL; k++)
				if(iaStart[iR][k]!=d)
					return false;
		}
		else
		{
			int d=iaStart[iR][0] - iaEnd[iL][0];
			for(int k=1; k<NL; k++)
				if(iaStart[iR][k] - iaEnd[iL][k] != d)
					return false;			
		}
		return true;
	}

	// Find all possible orders of pieces by generating all permutation of pieces.
	public static void matchNext( int ordera[], int flag, int np)
	{
		if(np==NP)
		{
			finalOrder=ordera.clone();
			return;
		}
		int iL = np==0? -1 : ordera[np-1]; // left piece
		for(int i=0; i<NP; i++)
		{
			if((flag&(1<<i))==0 && match(iL, i))
			{
				ordera[np]=i;
				matchNext(ordera, flag|(1<<i), np+1);
			}
		}
	}

	/* not working for the following case:
	1111==3333===2222=4444
	aaa==bccccd=abbb===ddd
	public static void findRightOrder()
	{
		int i, j;
		finalOrder = new int[NP];
		for( j=0; j<NP; j++ )
			if( match(-1, j) )
			{
				finalOrder[0] = j;
				break;
			}
		for( i=1; i<NP; i++ )
			for( j=0; j<NP; j++ )
				if( match(finalOrder[i-1], j) )
					finalOrder[i] = j;
	}
	*/
	
}
