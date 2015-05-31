import java.io.*;
import java.util.Scanner;


public class Fin1998p2 
{
	static int a[], b[], c[], d[], p[];
	// although a true volume should be divided by 6,
	// to keep them as integers, volumes are not so divided
	public static void main(String[]args)throws IOException
	{
		Scanner in = new Scanner (new FileInputStream("Fin1998p2DATA21.txt"));
		for(int testcase=0; testcase<5; testcase++)
		{
			a=new int[3]; b=new int[3]; c=new int[3]; d=new int[3]; p=new int[3]; 
			a[0]=in.nextInt(); a[1]=in.nextInt(); a[2]=in.nextInt();
			b[0]=in.nextInt(); b[1]=in.nextInt(); b[2]=in.nextInt();
			c[0]=in.nextInt(); c[1]=in.nextInt(); c[2]=in.nextInt();
			d[0]=in.nextInt(); d[1]=in.nextInt(); d[2]=in.nextInt();
			p[0]=in.nextInt(); p[1]=in.nextInt(); p[2]=in.nextInt();
					
	    System.out.printf("the point (%d, %d, %d) is %s the tetrahedron of volume %.2f\n",
	    		p[0], p[1], p[2], isin(), volume(a, b, c, d)/6.0 );
		}
	}
	
	public static String isin ()
	{
		// return "inside" if P is inside the shape, "outside" if P is outside.
		int sum;
		sum = volume (p, a, b, c) + volume (p, b, c, d) + volume (p, a, c, d) + volume (p, a, b, d);
		if(sum==volume (a, b, c, d))
			return "inside";
		else
			return "outside";
	}
	
	public static int simplvolume (int x[], int y[], int z[])
	{
	    // calulates the volume of the tetrahedron, with one vertex the origin
	    int temp;
	    temp = x[0] * y[1] * z[2] + x[1] * y[2] * z[0] + x[2] * y[0] * z[1];
	    temp = temp - x[2] * y[1] * z[0] - x[0] * y[2] * z[1] - x[1] * y[0] * z[2];
	    return Math.abs(temp);
	}

	public static int volume (int w[], int x[], int y[], int z[])
	{
	    // calculates the volume of a tetrahedron with the four input vertices
	    int r[]=new int[3];
	    int s[]=new int[3];
	    int t[]=new int[3];
	    for(int i=0; i<3; i++)
	    {
	        r[i] = x[i] - w[i];
	        s[i] = y[i] - w[i];
	        t[i] = z[i] - w[i];
	    }
	    return simplvolume (r, s, t);
	}
}





