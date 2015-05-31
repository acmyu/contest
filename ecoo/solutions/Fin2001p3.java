import java.io.*;
import java.util.Scanner;

public class Fin2001p3
{
	static char[][]word;
	static int[] one, two;
	static int vlen, hlen;
	static char[][]mat;
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin2001p3DATA31.txt"));
		word=new char[4][];
		one=new int[4];
		two=new int[4];
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<4;j++)
				word[j]=in.nextLine().toCharArray();
			// note: if (0 1 2 3) works, then (1 0 2 3), (2 3 0 1) or the like also work
			// so, we do not have to test all 24 permutations, instead, 3 is sufficient:
			if(  !findCross(word[0], word[1], word[2], word[3])
			  && !findCross(word[0], word[2], word[1], word[3])
			  && !findCross(word[0], word[3], word[1], word[2]) )
						System.out.println("Not found");
		}
	}
	
	/*
	    ja jc jd      jb
	    |  |  |       |
	id--|--|--d       |
	    |  |  d       |
	ib--|--|--d-------b
	    |  |  d       b
	ia--aaaaaaxaaaaaaaxaaaaaa
	       |  d       b
	       |  d       b
	       |  d       b
	ic-----cccxcccccccxcccccccc
	          d       b
	          d
	*/
	public static boolean findCross( char[] a,char[] c, char[] b, char[] d )
	{
		int na=a.length, nb=b.length, nc=c.length, nd=d.length;
		int ia, ja, ib, jb, ic, jc, id, jd; // position of each string in the resulting 2d array
		int di; // relative position of b and d
		int dj; // relative position of a and c
		
		for(dj=-(nc-3); dj<=na-3; dj++)
		{
			if(dj<0) { ja=-dj; jc=0; }
			else { ja=0; jc=dj; }
			for(di=-(nd-3); di<=nb-3; di++)
			{
				if(di<0) { ib=-di; id=0; }
				else { ib=0; id=di; }
				// coordinates of the rectangle formed by the crossed words:
				int jLeft  = Math.max(ja,jc);
				int jRight = Math.min(ja+na-1, jc+nc-1);
				int iTop   = Math.max(id, ib);
				int iBottom= Math.min(id+nd-1, ib+nb-1);
				for(jd=jLeft; jd<=jRight; jd++)
					for(jb=jLeft; jb<=jRight; jb++)
						for(ia=iTop; ia<=iBottom; ia++)
							for(ic=iTop; ic<=iBottom; ic++)
							{
								if( Math.abs(jd-jb)>1 && Math.abs(ia-ic)>1
								&& a[jd-ja]==d[ia-id]
								&& a[jb-ja]==b[ia-ib]
								&& c[jd-jc]==d[ic-id]
								&& c[jb-jc]==b[ic-ib])
								{
									int m, M = Math.max(id+nd, ib+nb);
									int n, N = Math.max(ja+na, jc+nc);
									mat=new char[M][N];
									for( m=0; m<M; m++)
										for( n=0; n<N; n++)
											mat[m][n]=' ';
									
									for(m=0; m<na; m++)
										mat[ia][ja+m] = a[m];
									for(m=0; m<nb; m++)
										mat[ib+m][jb] = b[m];
									for(m=0; m<nc; m++)
										mat[ic][jc+m] = c[m];
									for(m=0; m<nd; m++)
										mat[id+m][jd] = d[m];
									
									for(m=0; m<M; m++)
										System.out.println(new String(mat[m]));
									System.out.println("\npress any key to continue...");
									// System.in.read() ;
									// no clear screen method in java
									return true;
								}
							}
			}
		}
		return false;
	}
}

