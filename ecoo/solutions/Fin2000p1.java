import java.io.*;
import java.util.Scanner;

public class Fin2000p1 
{
	static int M,N;
	static char ca[][];
	static int flag[][];
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin2000p1DATA11.txt"));
		for(int i=0; i<5; i++)
		{
			int j,k;
			N=in.nextInt();
			M=in.nextInt();
			in.nextLine();
			ca=new char[M][N];
			flag=new int[M][N];
			for(j=0; j<M; j++)
				ca[j]=in.nextLine().toCharArray();
			int nw=in.nextInt();
			in.nextLine();
			char word[]=new char[nw];
			for(j=0; j<nw; j++)
			{
				word=in.nextLine().toCharArray();
				findWord(word);
			}
			
			String ans="";
			for(j=0; j<M; j++)
				for(k=0; k<N; k++)
				{
					if(flag[j][k]==0)
						ans+=ca[j][k];
				}
			System.out.println(ans);
			System.out.println();
		}
	}
	
	public static void findWord(char word[])
	{
		for(int i=0; i<M; i++)
			for(int j=0; j<N; j++)
				for(int k=0; k<8; k++) // 8 directions
					if(findWord1d(i,j,k,word))
						break;
	}

	// find the word in one of 8 directions, set flag[][] to 1 if found
	public static boolean findWord1d(int i, int j, int k, char word[])
	{
		int dm[]={ 1, 1, 0, 0,-1,-1, 1,-1};
		int dn[]={ 1,-1, 1,-1,-1, 1, 0, 0};				

		int i2=i, j2=j;
		for(int m=0; m<word.length; m++)
		{
			if(i2<0||i2>=M || j2<0||j2>=N)
				return false;
			if(ca[i2][j2]!=word[m])
				return false;
			i2+=dm[k]; j2+=dn[k];
		}
		
		i2=i; j2=j;
		for(int m=0; m<word.length; m++)
		{
			flag[i2][j2]=1;
			i2+=dm[k]; j2+=dn[k];
		}
		return true;
	}
}
