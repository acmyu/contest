import java.io.*;
import java.util.Scanner;


public class Fin1999p2 
{
	static char mat[][];
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin1999p2DATA21.txt"));
		for(int i=0; i<5; i++)
		{
			int j;
			char word[][]=new char[3][];
			for(j=0; j<3; j++)
				word[j]=in.nextLine().toUpperCase().toCharArray();
			if(  match(word[0], word[1], word[2])			
			  || match(word[1], word[2], word[0])
			  || match(word[2], word[1], word[0]) )
			{
				for(j=0; j<mat.length; j++)
					System.out.println(mat[j]);
			}
			System.out.println();
		}
	}
	
	public static boolean match(char a[], char b[], char c[])
	{		
		int ib, jb, ic, jc;
		for(ib=0; ib<a.length; ib++)
			for(jb=0; jb<b.length; jb++)
			{
				if(a[ib]==b[jb])
				{
					for(ic=0; ic<a.length; ic++)
						for(jc=0; jc<c.length; jc++)
						{
							if(a[ic]==c[jc] && Math.abs(ib-ic)>1)
							{
								makeMat(ib, jb, ic, jc, a,b,c);
								return true;
							}
						}
				}
			}
		return false;
	}
	
	public static void makeMat(int ib, int jb, int ic, int jc, char a[], char b[], char c[])
	{
		mat=new char[a.length][Math.max(jb, jc)+Math.max(b.length-jb, c.length-jc)];

		int i,j;
		for(i=0; i<mat.length; i++)
			for(j=0; j<mat[0].length; j++)
				mat[i][j]=' ';
		
		j=Math.max(jb, jc);
		for(i=0; i<a.length; i++)
			mat[i][j]=a[i];
		
		if(jb>jc)
		{
			int n=jb-jc;
			for(j=0; j<b.length; j++)
				mat[ib][j]=b[j];
			for(j=0; j<c.length; j++)
				mat[ic][j+n]=c[j];
		}
		else
		{
			int n=jc-jb;
			for(j=0; j<b.length; j++)
				mat[ib][j+n]=b[j];
			for(j=0; j<c.length; j++)
				mat[ic][j]=c[j];
		}
	}
}
