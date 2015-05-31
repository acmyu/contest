import java.io.*;
import java.util.Scanner;

// Longest Chain

public class Fin2005p1 
{
	static char[][]matrix; // rectangle of letters
	static int n,m; // length and width of the matrix
	static char character; // the character with longest chain 
	static int maxLen; // the length of the longest chain so far
	static Scanner in;
	
	public static void main(String[]args) throws IOException
	{
		in=new Scanner (new FileInputStream("Fin2005p1DATA11.txt"));
		for( int i=0; i<5; i++ )
		{
			fillMatrix();
			findLongestChain();
			System.out.printf( "%c has a chain of %d characters\n", character, maxLen);
		}
		
	}
	
	// read the next rectangle of letters
	public static void fillMatrix()
	{
		n=in.nextInt();
		m=in.nextInt();
		matrix = new char[n][m];
		in.nextLine();
		for(int y=0;y<n;y++)
			matrix[y] = in.nextLine().toCharArray();
	}
	
	public static void findLongestChain()
	{
		maxLen = 0;
		for(int y=0; y<n; y++)
			for(int x=0; x<m; x++)
				findNextAdjacentLetter(matrix[y][x], x, y, 1, "");
	}
	
	// recursively find the adjacent letters at (x, y)
	public static void findNextAdjacentLetter(char letter, int x, int y, int len, String history)
	{
		//history contains a set of "where"=x,y values in the form of 2 chars
		//every set of x,y must be unique in the chain 
		String where= ""+ (char) (x+'A') + (char) (y+'A');	
		if( history.indexOf(where) == -1 )
		{
			//use recursion to increase length 
		    //only if (x,y) value just found is unique
			if( len > maxLen )
			{
	        	maxLen = len;
	        	character = letter;
			}
			int[]dx={1,0,-1,0};
			int[]dy={0,1,0,-1}; // four directions
			for( int k=0; k<4; k++ )
			{
				int x2 = x+dx[k], y2 = y+dy[k];
				if( y2>=0&&y2<n && x2>=0&&x2<m && matrix[y2][x2]==letter) // test for boundaries
					findNextAdjacentLetter (letter, x2, y2, len + 1, history + "." + where);
			}
		}
	}
}
