import java.io.*;
import java.util.Scanner;

public class Fin2004p2 
{
	static char[][]word;
	static int[] one, two;
	static int vlen, hlen;
	static char[][]mat;
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin2004p2DATA21.txt"));
		word=new char[4][];
		one=new int[4];
		two=new int[4];
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<4;j++)
				word[j]=in.nextLine().toCharArray();
			if(  !findCross(word[0], word[1], word[2], word[3])
			  && !findCross(word[0], word[1], word[3], word[2])
			  && !findCross(word[0], word[2], word[1], word[3])
			  && !findCross(word[0], word[2], word[3], word[1])
			  && !findCross(word[0], word[3], word[2], word[1])
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
	public static boolean findCross( char[] a,char[] b, char[] c, char[] d )
	{
		int na=a.length, nb=b.length, nc=c.length, nd=d.length;
		int ia, ja, ib, jb, ic, jc, id, jd; // position of each string in the resulting 2d array
		int di; // relative position of b and d
		int dj; // relative position of a and c
		
		for(dj=-(nc-2); dj<=na-2; dj++)
		{
			if(dj<0) { ja=-dj; jc=0; }
			else { ja=0; jc=dj; }
			for(di=-(nd-2); di<=nb-2; di++)
			{
				if(di<0) { ib=-di; id=0; }
				else { ib=0; id=di; }
				
				for(jd=Math.max(ja,jc); jd<=Math.min(ja+na-2, jc+nc-2); jd++)
					for(jb=jd+1; jb<=Math.min(ja+na-1, jc+nc-1); jb++)
						for(ia=Math.max(id, ib); ia<=Math.min(id+nd-2, ib+nb-2); ia++)
							for(ic=ia+1; ic<=Math.min(id+nd-1, ib+nb-1); ic++)
							{
								if(a[jd-ja]==d[nd-1-(ia-id)]
								&& a[jb-ja]==b[ia-ib]
								&& c[nc-1-(jd-jc)]==d[nd-1-(ic-id)]
								&& c[nc-1-(jb-jc)]==b[ic-ib])
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
										mat[ic][jc+m] = c[nc-1-m];
									for(m=0; m<nd; m++)
										mat[id+m][jd] = d[nd-1-m];

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
