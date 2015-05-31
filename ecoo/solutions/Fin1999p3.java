import java.io.*;
import java.util.Scanner;


public class Fin1999p3 
{
	static int N, maxCal;
	static String maxPath;
	static int ma[],na[],ca[];
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream ("Fin1999p3DATA31.txt"));
		for(int i=0; i<5; i++)
		{
			N=in.nextInt();
			int j;
			ma=new int[N];
			na=new int[N];
			ca=new int[N];
			for(j=0; j<N; j++)
			{
				ma[j]=in.nextInt();
				na[j]=in.nextInt();
				ca[j]=in.nextInt();
			}
			maxCal=0;
			findNextPacket(0, 0, "", -1, 0);
			System.out.printf("%s yields %d calories\n\n", maxPath, maxCal);
		}
	}
	
	public static void findNextPacket(int cal, int dist, String path, int curr, int np)
	{
		if(np==N)
		{
			if(cal>maxCal)
			{
				maxCal=cal;
				maxPath=path;
			}
			return;
		}
		
		for(int i=0; i<N; i++)
		{
			if(path.indexOf((char)(i+'A'))==-1)
			{
				int c, d;
				if(curr==-1)
					d=ma[i]+na[i];
				else
					d=Math.abs(ma[curr]-ma[i])+Math.abs(na[curr]-na[i]);
				c=ca[i]/d;
				findNextPacket(cal+c, d, path+(char)(i+'A'), i, np+1);
			}
		}
	}
}
