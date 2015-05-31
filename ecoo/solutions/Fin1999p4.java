import java.io.*;
import java.util.Scanner;

public class Fin1999p4 
{
	
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin1999p4DATA41.txt"));
		for(int i=0; i<5; i++)
		{
			int j;			
			StringBuffer s=new StringBuffer(in.nextLine().toUpperCase());			
			int N=s.length();
			for(j=0; j<N; j++)
			{
				char a=s.charAt(j);
				if(a=='I')
					s.setCharAt(j, 'J');
				if(a<'A'||a>'Z')
				{
					s.deleteCharAt(j);
					N--;
				}
			}
			for(j=0; j<N-1; j+=2)
				if(s.charAt(j)==s.charAt(j+1))
				{
					s.insert(j+1, 'X');
					N++;
				}
			if(N%2==1)
			{
				s.append('X');
				N++;
			}
			String s2="";
			for(j=0; j<N; j+=2)
				s2 += encodePair(s.charAt(j), s.charAt(j+1));
			for(j=0; j<s2.length(); j++)
			{
				System.out.print( s2.charAt(j) );
				if( (j+1)%5==0)
					System.out.print(" ");
			}
			System.out.println("\n");
		}
	}
	
	public static String encodePair(char a, char b)
	{
		char sq[][]={{'A', 'G', 'T', 'H', 'C'},
			     {'R', 'J', 'S', 'E', 'B'},
			     {'D', 'F', 'K', 'L', 'M'},
			     {'N', 'O', 'P', 'Q', 'U'},
			     {'V', 'W', 'X', 'Y', 'Z'}};

		int m1 = 0, n1 = 0, m2 = 0, n2 = 0;
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++)
			{
				if(sq[i][j]==a)
				{
					m1=i; n1=j;
				}
				else if(sq[i][j]==b)
				{
					m2=i; n2=j;
				}
			}
		String t="";
		if(m1==m2)//same row
		{
			int n=(n1+1)%5;
			t+=sq[m1][n];
			n=(n2+1)%5;
			t+=sq[m1][n];
		}
		else if(n1==n2)//same column
		{
			int n=(m1+1)%5;
			t+=sq[n][n1];
			n=(m2+1)%5;
			t+=sq[n][n1];
		}
		else
		{
			t+=sq[m1][n2];
			t+=sq[m2][n1];
		}
		return t;
	}
}
