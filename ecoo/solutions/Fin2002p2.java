import java.io.*;
import java.util.Scanner;

public class Fin2002p2 
{
	static char square[][];
	static int timea[][];//stores minimum time to each spot
	static int minT;
	static String minPath;
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream ("Fin2002p2DATA21.txt"));
		for(int i=0; i<5; i++)
		{
			int j, k;
			timea=new int[5][5];
			for(j=0; j<5; j++)
				for(k=0; k<5; k++)
					timea[j][k]=Integer.MAX_VALUE;
			timea[0][0]=0;
			square=new char[5][5];
			minT=Integer.MAX_VALUE;
			for(j=0; j<5; j++)
				square[j]=in.nextLine().toCharArray();
			findNextStep(0,0,0,""+square[0][0]);
			System.out.printf("it takes %d minutes to take the path: %s\n", minT, minPath);
			
		}
	}
	
	// m,n = current position
	// t = time so far
	// path = the string of levels passed through on the way to"E"
	public static void findNextStep(int m, int n, int t, String path)
	{
		if(path.length()>11)
			return;
		if(square[m][n]=='E')
		{
			minT=t;
			minPath=path;
			return;
		}
		int dm[]={1, 0,-1, 0};
		int dn[]={0, 1, 0,-1};
		for(int i=0; i<4; i++)
		{
			int m2=m+dm[i];
			int n2=n+dn[i];
			if(m2>=0&&m2<5 && n2>=0&&n2<5)
			{
				int diff=Math.abs(square[m2][n2]-square[m][n]); //difference between next level and the current level
				int t2 = t + ( diff==0? 20 : 60*(diff*2-1) );
				if(t2<timea[m2][n2] && t2<minT)
				{
					timea[m2][n2]=t2;
					String path2=path+square[m2][n2];
					findNextStep(m2,n2,t2,path2);
				}
			}
		}
	}
}
