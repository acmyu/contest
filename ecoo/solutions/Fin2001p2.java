import java.io.*;
import java.util.Scanner;


public class Fin2001p2 
{
	static int value[]={200, 100, 25, 10, 5, 1};
	static int minNc;
	static int ans[];
	static boolean found;
	static int r;
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin2001p2DATA21.txt"));
		String coinName[]={"twoonies", "loonies", "quarters", "dimes", "nickels", "pennies"};
		for(int i=0; i<5; i++)
		{
			r=0;
			found=false;
			double money=in.nextDouble();
			int m=(int)(money*100);
			int coins[]=new int[6];
			findNextCoin(m, 0, coins, 0);
			if(found)
			{
				System.out.printf("$%.2f = ", money);
				for(int j=0; j<6; j++)
					if(ans[j]!=0)
						System.out.printf("%d %s ", ans[j], coinName[j]);
				System.out.println();
			}
			else
				System.out.printf("$%.2f cannot be expressed in 13 coins\n", money);
		}
	}
	
	//total: amount of cents not yet converted
	//ncoin: number of coins used so far
	//coins[]: the amount of each type of coin used
	//ic: current coin being use to convert total
	public static void findNextCoin(int total, int ncoin, int coins[], int ic)
	{
		if( found )
			return;
		if(ncoin==13 && total==0)
		{
			ans=coins.clone();
			found=true;
			return;
		}
		if( ncoin>=13 || total<=0 || ic>=6 )
			return;
		for(int n=total/value[ic]; n>=0; n--)
		{
			int nca2[]=coins.clone();
			nca2[ic] += n;
			findNextCoin(total-value[ic]*n, ncoin+n, nca2, ic+1);
		}
	}
}
