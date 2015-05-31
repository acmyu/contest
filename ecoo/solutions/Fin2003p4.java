import java.io.*;
import java.util.Scanner;


public class Fin2003p4 
{
	static int ba[];//array of the three sizes of bottles #1, #2 and #3
	static int result;//the amount of wine the customer wants to buy
	static String minhist; //the past moves and liters of wine in each bottle
	static int minnm;// the minimum steps to get result
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream ("Fin2003p4DATA41.txt"));
		for(int i=0; i<5; i++)
		{
			ba=new int[3];
			for(int j=0; j<3; j++)
				ba[j]=in.nextInt();
			result=in.nextInt();
			minnm=8;
			int currb[]={ba[0],0,0};
			findNextBottle("", currb, 0);
			if(minnm>7)
				System.out.println("the problem is impossible to solve\n");
			else
				System.out.print(minhist);
		}
	}
	
	public static void findNextBottle(String hist, int currb[], int nm)
	{
		//if a shorter solution has been found
		if( nm<minnm && (currb[0]==result || currb[1]==result || currb[2]==result) )
		{
			minnm=nm;
			minhist=hist;
			for(int i=0; i<3; i++)
				if(currb[i]==result)
				{
					minhist=minhist+"present bottle #"+(i+1)+"\n\n";
					break;
				}
			return;
		}
		if(nm<7)
		{//try all new possible next steps
			pour(hist, currb, nm, 0, 1);
			pour(hist, currb, nm, 1, 0);
			pour(hist, currb, nm, 0, 2);
			pour(hist, currb, nm, 2, 0);
			pour(hist, currb, nm, 1, 2);
			pour(hist, currb, nm, 2, 1);
		}
	}
	
	//pours bottle a into b and tests if it was found in hist, if not then call recursion
	//currb is the array containing the current amounts in the bottles
	//hist is a string containing the past pairs of a,b and the current amounts in the bottles
	public static void pour(String hist, int currb[], int nm, int a, int b)
	{
		int c = 3-a-b;//the 3rd bottle
		int nm2=nm+1;
		int next[]=new int[3];
		if(currb[a]==0 || currb[b]==ba[b])
			return;
		int t = Math.min(currb[a], ba[b]-currb[b]);
		next[a] = currb[a]-t;
		next[b] = currb[b]+t;
		next[c] = currb[c];
		String s=String.format("%3d%3d%3d\n", next[0], next[1], next[2]);
		if(hist.indexOf(s)==-1)
		{
			hist=hist+"pour bottle #"+(a+1)+" into bottle #"+(b+1)+" to get: "+s;
			findNextBottle(hist, next, nm2);
		}
	}
}
