import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Fin2003p3 
{
	// the perimeter of each shape is the sum of all the left edges
	// plus the sum of all the top edges, doubled.
	public static void main(String[]args) throws IOException
	{
		Scanner in = new Scanner (new FileInputStream("Fin2003p3DATA31.txt"));
		int m=in.nextInt();
		int n=in.nextInt();
		char field[][]=new char[m+1][n+1];
		int i,j;
		// read the data, and add an entire row of "-" (at the top)
		// and add an entire column of "-" at the left
		Arrays.fill(field[0], '-');
		in.nextLine();
		for(i=1; i<=m; i++)
			field[i]=('-'+in.nextLine()).toCharArray();
		int counta[]=new int[5];
		// scan the entire field for the left and top edges of the letter
		// left edges are characterized by: "-A", or "-B", etc.
		// top egdes are characterized by: "-" above each letter
		// count the total number of left and top edges
		for(i=0;i<m;i++)
			for(j=0;j<n;j++)
			{
				if(field[i][j]=='-' )
				{
					int c = field[i+1][j] - 'A';
					if(c>=0 && c<=4)
						counta[c]++;
					c = field[i][j+1] - 'A';
					if(c>=0 && c<=4)
						counta[c]++;
				}
			}
		for(i=0;i<5;i++)
			System.out.println( (char)(i+'A')+" has a perimeter of "+counta[i]*2);
	}
}
