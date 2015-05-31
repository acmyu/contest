import java.io.*;
import java.util.*;


public class Fin2004p1 
{
	static int h, w;
	static char[][]m;
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner(new FileInputStream("Fin2004p1DATA11.txt"));
		h=in.nextInt();
		w=in.nextInt();
		in.nextLine();
		m=new char[h][w];
		for(int i=0; i<h; i++)
			m[i]=(in.nextLine()).toCharArray();
		
		for( char letter='A'; letter<='E'; letter++)
		{
			//find and print out the centre of gravity
			int vCenterX2 = getVertCentreX2 (letter);
			int hCenterX2 = getHoriCentreX2 (letter);
			String svCenter = String.format( vCenterX2%2==0?"%.0f":"%.1f", vCenterX2/2.+1 ); 
			String shCenter = String.format( hCenterX2%2==0?"%.0f":"%.1f", hCenterX2/2.+1 ); 
			System.out.println("The centre of gravity of shape " +letter+ " is: (" + shCenter + "," + svCenter + ")");
			
		    if( isVertSymmetric(letter, hCenterX2) )
		    	System.out.println("  It is symmetric about the vertical line ");         
		    else
		    	System.out.println("  It is not symmetric vertically");

		    if( isHoriSymmetric(letter, vCenterX2) )
		    	System.out.println("  It is symmetric about the horizontal line ");         
		    else
		    	System.out.println("  It is not symmetric horizontally");
		    System.out.println();
		}	
	}
	
	// find the vertical centre line (times 2), based on the number of "c"'s on either side
	public static int getVertCentreX2(char c)
	{
	    int tt = total (c);
	    int count = 0;
	    for (int i=0; i<h; i++)
	    {
	        for (int j=0; j<w; j++)
	            if (m[i][j] == c)
	                count++;
	        if(count*2 >= tt)
		        return (count*2 == tt) ? i*2 + 1 : i*2;
	    }
	    return 0;
	}

	//find the horizontal centre line (times 2), based on number of "c"'s on either side
	public static int getHoriCentreX2 (char c)
	{
	    int tt = total (c);
	    int count = 0;
	    for (int j=0; j<w; j++)
	    {
	        for (int i=0; i<h; i++)
	            if (m[i][j] == c)
	                count++;
	        if(count*2 >= tt)
		        return (count*2 == tt) ? j*2 + 1 : j*2;
	    }
	    return 0;
	}

	//check if the shape is symmetric about the vertical line
	public static boolean isVertSymmetric( char c, int hCenterX2)
	{
		for (int i=0; i<h; i++)
	        if (! isSymmetric( m[i], c, hCenterX2))
	            return false;
		return true;
	}

    //check if the shape is symmetric about the horizontal line
	public static boolean isHoriSymmetric( char c, int vCenterX2)
	{
		for (int j=0; j<w; j++)
	        if (! isSymmetric( getColumn(j), c, vCenterX2) )
	            return false;
		return true;
	}
	
	//convert the ith column of m to a char[]
	public static char[] getColumn( int j)
	{
	    char []s = new char[h];
	    for (int i=0; i<h; i++)
	        s[i] = m[i][j];
	    return s;
	}
	
	//find the total number of letters of a given value (A,B,...,E)
	public static int total (char c)
	{
	    int count = 0;
	    for (int i=0; i<h; i++)
	        for (int j=0; j<w; j++)
	            if (m[i][j] == c) 
	                count++;
	    return count;
	}
	// check if the set of "c" characters in string "s" are symmetric about "mid2/2"
	public static boolean isSymmetric( char []s, char c, int mid2)
	{
	    int n = s.length;
	    int i, j;
	    if( mid2<n ) // mid2/2 is the centre or to the left of the middle of s
	    {
		    for(i=mid2/2+1; i<n; i++)
		    {
		    	j=mid2-i;
		    	if( s[i]==c&& (j<0 || s[j]!=c)
		    	 || s[i]!=c&& (j>=0&&s[j]==c) )
		    		return false;
		    }
	    }
	    else
	    {
	    	for(i=mid2/2-1; i>=0; i--)
		    {
		    	j=mid2-i;
		    	if( s[i]==c&& (j>=n || s[j]!=c)
		    	 || s[i]!=c&& (j<n&&s[j]==c) )
		    		return false;
		    }
	    }
	    return true;
	}
}
