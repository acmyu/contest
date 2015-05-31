import java.io.*;
import java.util.Scanner;


public class Fin2004p4 
{
	static int na[];//array of numbers available
	static int maxList[];//combination with the largest magic total
	static int maxTotal;//largest magic total
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream ("Fin2004p4DATA41.txt"));
		for(int i=0;i<5;i++)
		{
			na=new int[9];
			for(int j=0; j<9; j++)
				na[j]=in.nextInt();
			int list[]=new int[9];
			maxTotal=0;
			maxList=new int [9];
			findNextNumber(list, 0, 0);//recursive function
			System.out.printf("         %3d\n", maxList[0]);
			System.out.printf("      %3d   %3d\n", maxList[8], maxList[1]);
			System.out.printf("   %3d         %3d\n", maxList[7], maxList[2]);
			System.out.printf("%3d   %3d   %3d   %3d\n", maxList[6], maxList[5], maxList[4], maxList[3]);
			System.out.printf("the magic total = %d\n\n", maxTotal);
			//can not clear screen or read key with java
		}
	}
	
	//list: the array which needs an extra number.
	//len: length of list so far 
	//flag: if the i-th bit of flag ==0, then na[i] is not yet used, and put it in list, then call itself again.
	public static void findNextNumber(int list[], int len, int flag)
	{
		if(len==9)
		{
			int total=getTotal(list);
			if(total>maxTotal)
			{
				maxTotal=total;
				maxList=list.clone();
			}
		}
		for(int i=0; i<9; i++)
		{
			if( (flag&(1<<i))==0 )
			{
				list[len]=na[i];
				findNextNumber(list, len+1, flag|(1<<i));
			}
		}
	}

	
	public static int getTotal (int a[])
	{
		//check if the largest vertex is on top and the smallest vertex is bottom left
		if(a[0]<a[3] || a[3]<a[6])
			return 0;
		int m,n,k;
		//check if all sides have equal sum
		k = a[0]+a[1]+a[2]+a[3];
		m = a[3]+a[4]+a[5]+a[6];
		n = a[6]+a[7]+a[8]+a[0];
		return m==n && m==k ? m : 0; // 0: indicates a bad solution
	}
}
