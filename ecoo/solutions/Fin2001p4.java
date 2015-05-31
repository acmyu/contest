import java.io.*;
import java.util.Scanner;


public class Fin2001p4 
{
	static int x,y;// current position using unit coordinates for accuracy
				   // x=1 represents 3/2, y=1 represents (sqrt 3)/2
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin2001p4DATA41.txt"));
		for(int i=0; i<5; i++)
		{
			char s[]=in.nextLine().toCharArray();
			int a[]={0,1,2,3,4};//current status of each face of tetrahedron
			x=0; y=0;
			int N=s.length;
			for( int j=0; j<N; j++)
				a = move( a, s[j]-'0' );
			double X = 3/2.0 * x;
			double Y = Math.sqrt(3)/2 * y;
			System.out.printf("after %d moves, the distance = %.2f meters\n", N, Math.sqrt(X*X+Y*Y));
		}
	}

	/*
	a[0]=0:  
			    /\	  
			   / .\			
	 		  /  . \			
			 /   .  \          
			/ a4 .a3 \        
		   /  .    .  \      
		  / .   a2   . \     
		 /.____________.\   

	a[0]=1:
		 ._______________.
		 \ .           . /
		  \  .   a2  .  /
		   \   .   .   /
			\ a3 . a4 /
			 \   .   /
			  \  .  /
			   \ . /
			    \./
		     
	*/
	public static int[] move (int a[], int b)
	{
		int a2[]=new int[5];
		if(a[0]==0)
		{
			a2[0]=1; a2[1]=b;
			if(a[2]==b)
			{
				a2[2]= a[1];
				a2[3]= a[4];
				a2[4]= a[3];
				y-=2;
			}
			else if(a[3]==b)
			{
				a2[2]= a[4];
				a2[3]= a[1];
				a2[4]= a[2];
				x+=1;  y+=1;
			}
			else if(a[4]==b)
			{
				a2[2]= a[3];
				a2[3]= a[2];
				a2[4]= a[1];
				x-=1; y+=1;
			}
		}
		else
		{
			a2[0]=0; a2[1]=b;
			if(a[2]==b)
			{
				a2[2]= a[1];
				a2[3]= a[4];
				a2[4]= a[3];
				y+=2;
			}
			else if(a[3]==b)
			{
				a2[2]= a[4];
				a2[3]= a[1];
				a2[4]= a[2];
				x-=1;  y-=1;
			}
			else if(a[4]==b)
			{
				a2[2]= a[3];
				a2[3]= a[2];
				a2[4]= a[1];
				x+=1; y-=1;
			}
		}
		return a2;
	}
}
