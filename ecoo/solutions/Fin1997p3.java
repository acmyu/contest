import java.io.*;
import java.util.Scanner;


public class Fin1997p3 
{
	public static void main(String[] args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin1997p3DATA31.txt"));
		char phrase[]=(in.nextLine()+" ").toCharArray(); // append a space at the end, see countWords()
		boolean revealed[]=new boolean[phrase.length];
		int sumA=0, sumB=0;
		int nCorrect=0;
		boolean isATurn=true;
		while(true)
		{
			char g=in.nextLine().charAt(0);
			if(g=='*')
				break;
		
			int v=in.nextInt(); in.nextLine();
			int nfound = findLetter(phrase, revealed, g);
			nCorrect += nfound;
			int val = (g=='A'||g=='E'||g=='I'||g=='O'||g=='U')? -200 : v*nfound;
			if(isATurn)
				sumA += val;
			else
				sumB += val;
				
			if(nfound==0)
				isATurn=!isATurn;
		}
		int nWords=countWords(phrase, revealed);
		System.out.printf("%c\n%d\n%d\n%d\n",
				isATurn?'A':'B', isATurn?sumA:sumB, nCorrect, nWords);
		
		for(int i=0; i<revealed.length; i++)
			if(phrase[i]!=' ')
				System.out.print(revealed[i]? phrase[i] : '?');
	}
	
	public static int countWords(char phrase[], boolean revealed[])
	{
		int i, iStart=0, nWord=0;
		char c0=' ';
		for(i=0; i<phrase.length; i++)
		{
			char c = phrase[i];
			if(c0==' '&&c!=' ' )
				iStart=i;
			else if(c0!=' '&& c==' ')
				// a space has already been appended to the end, otherwise need to check i==phrase.length-1
				if(allRevealed(revealed, iStart, i))
					nWord++;
			c0=c;
		}
		return nWord;
	}
	
	public static boolean allRevealed(boolean revealed[], int i1, int i2)
	{
		for(int i=i1; i<i2; i++)
			if(!revealed[i])
				return false;
		return true;
	}
	
	public static int findLetter(char phrase[], boolean revealed[], char g)
	{
		int n=0;
		for(int i=0; i<phrase.length; i++)
		{
			if(phrase[i]==g)
			{
				n++;
				revealed[i]=true;
			}
		}
		return n;
	}

}
