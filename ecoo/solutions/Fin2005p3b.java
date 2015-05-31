import java.io.*;
import java.util.Scanner;


public class Fin2005p3b 
{
	static int[][] dist; // contains the number of hours it takes for a beetle to travel from tree x to tree y
	static int[] tx, ty; // ordered pairs for trees
	static int[] doom; 	// contains the current number of hours the first beetle will reach that tree
	static int t; // current time t
	static int n; // number of trees n
	static Scanner in;
	
	public static void main(String[]args) throws IOException
	{
		in=new Scanner (new FileInputStream ("Fin2005p3DATA31.txt"));
		for(int i=0; i<5; i++)
		{
			n=in.nextInt();
			dist=new int[n][n];
			tx=new int[n]; ty=new int[n];
			doom=new int[n];
			fill();
			do
				findNext();
		    while(!done());
		    System.out.println("The whole park is infected after "+ t/24+" day(s) and "+ t%24+ " hour(s)"); 
		}
	}

	public static void fill()
	{
	    // fill tree arrays
	    for (int i =0; i<n; i++)
	    {
	        tx[i]=in.nextInt();
	        ty[i]=in.nextInt();
	    }
	    // fill the dist array
	    int deltax, deltay;
	    for (int i=0; i<n; i++)
	        for(int j=0; j<n; j++)
	        {
	            deltax = (tx[i] - tx[j]);
	            deltay = (ty[i] - ty[j]);
	            dist [i][j] = deltax * deltax + deltay * deltay;
	        }
	    // fill the dooms array:
	    for (int i=0; i<n; i++)
	        doom[i] = dist[0][i];
	    t = 0;
	}
	
	public static void findNext()
	{
	    // find the next tree to fall and update doom list  and update t
	    int minim=Integer.MAX_VALUE;
	    int tree=0;
	    for(int i=0; i<n; i++)
	    {
	        if (doom[i] > 0 && doom[i] < minim)
	        {
	            minim = doom[i];
	            tree = i;
	        }
	    }
  	    if (tree > 0)
	    {
	        for (int i=0; i<n; i++)
	        {
	            doom[i] -= minim;
	            if (doom[i] > dist[tree][i])
	                doom[i] = dist[tree][i];
	        }
	        t += minim;
	    }
	}

	public static boolean done()
	{
	    for (int i=0; i<n; i++)
	        if (doom[i] > 0)
	            return false;
	    return true;
	}
}
