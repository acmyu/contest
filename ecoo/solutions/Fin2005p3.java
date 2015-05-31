import java.io.*;
import java.util.Scanner;


public class Fin2005p3 
{
	static Scanner in;
	static int n; // number of trees n
	static int[] tx, ty; // ordered pairs for trees
	static int[] doom; 	// contains the current number of hours the first beetle will reach that tree
	
	public static void main(String[]args) throws IOException
	{
		in=new Scanner (new FileInputStream ("Fin2005p3DATA31.txt"));
		for(int i=0; i<5; i++)
		{
			n=in.nextInt();
			tx=new int[n]; ty=new int[n];
			doom=new int[n];
			int t = findDoom();
		    System.out.println("The whole park is infected after "+ t/24+" day(s) and "+ t%24+ " hour(s)"); 
		}
	}
	
	public static int findDoom()
	{
	    // fill tree arrays
	    for (int i =0; i<n; i++)
	    {
	        tx[i]=in.nextInt();
	        ty[i]=in.nextInt();
	    }

	    doom[0] = 0;
		for(int i=1; i<n; i++)
			doom[i]=Integer.MAX_VALUE;
		
		spread(0);


		int t=0;
		for(int i=0; i<n; i++)
		{
			if(doom[i]>t)
				t=doom[i];
		}
		return t;
	}
	
	public static void spread(int istart)
	{
		for(int i=0; i<n; i++)
		{
			int deltax = (tx[istart] - tx[i]);
            int deltay = (ty[istart] - ty[i]);
			int d = doom[istart] + deltax * deltax + deltay * deltay;
			if(d<doom[i])
			{
				doom[i] = d;
				spread(i);
			}
		}
	}
}
