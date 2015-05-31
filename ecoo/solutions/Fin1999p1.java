import java.io.*;
import java.util.Scanner;


public class Fin1999p1 
{
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner(new FileInputStream ("Fin1999p1DATA11.txt"));
		for(int testcase=0; testcase<5; testcase++)
		{
			char[]dir=in.nextLine().toCharArray();
			String sn="";
			char die[]={'1','5','6','2','4','3'}; //n,e,s,w,t,b
			for(int i=0; i<dir.length; i++)
			{
				die=doNextTurn(die, dir[i]);
				sn+=die[5]+" ";
			}
			System.out.println(sn);
		}
	}
	
	public static char[] doNextTurn(char die[], char cdir)
	{
		int iaa[][] = { 
				{ 4,1,5,3,2,0},//turn north
				{0,4,2,5,3,1},//turn east
				{5,1,4,3,0,2},//turn south
				{0,5,2,4,1,3}//turn west
				};

		String sdir = "NESW";
		int idir = sdir.indexOf(cdir);
		char die2[]=new char [6];
		for(int i=0; i<6; i++)
		{
			int i0 = iaa[idir][i];
			die2[i]=die[i0];
		}
		return die2;
	}
}
