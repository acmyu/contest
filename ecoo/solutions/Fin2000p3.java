import java.io.*;
import java.util.Scanner;


public class Fin2000p3 
{
	static int ca[][];
	static int minSum;
	static String minPath;
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream ("Fin2000p3DATA31.txt"));
		ca=new int[5][7];
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<7; j++)
			{
				ca[i][j]=in.nextInt();
				System.out.printf("%-5d", ca[i][j]);
			}
			System.out.println();
		}
		minSum=Integer.MAX_VALUE;
		goToNextCity( String.format("\n  start in A, motel cost: $%3d\n",ca[4][0]), 4, 0, ca[4][0], 1);

		System.out.printf(minPath);
		System.out.printf("                           ---\n");
		System.out.printf("              total cost: $%3d", minSum);
	}
	
	public static void goToNextCity(String path, int m, int n, int sum, int nc)
	{
		if(m==0&&n==6)
		{
			if(sum<minSum)
			{
				minSum=sum;
				minPath=path;
			}
			return;
		}

		int dm[] = {-1,  0, -1};
		int dn[] = { 0,  1,  1};
		int x2[] = { 1,  1,  2};
		String sd[] = { "North,", "East,", "NE," };
		for(int i=0; i<3; i++)
		{
			int m2=m+dm[i], n2=n+dn[i];
			if(m2>=0&&m2<5 && n2>=0&&n2<7)
			{
				int s2 = sum + ca[m2][n2]*x2[i];
				String p2 = path + String.format("  move %-7smotel cost: $ %d\n", sd[i], ca[m2][n2]*x2[i]);
				goToNextCity(p2, m2, n2, s2, nc+1);
			}
		}
	}
}
