import java.io.*;
import java.util.Scanner;

class Triple
{
	int x, y, z;
	Triple( int x0, int y0, int z0)
	{
		x=x0; y=y0; z=z0;
	}
	Triple Add( Triple a )
	{
		return new Triple( x+a.x, y+a.y, z+a.z);
	}
	boolean isAdjacent( Triple a ) // true if this triple is touched by triple a
	{
		int dx=Math.abs(a.x-x);
		int dy=Math.abs(a.y-y);
		int dz=Math.abs(a.z-z);
		return dx+dy+dz==1;
	}
	boolean inTriples(Triple a[], int len)
	{
		for(int i=0;i<len;i++)
			if(x==a[i].x && y==a[i].y && z==a[i].z)
				return true;
		return false;
	}
}
public class Fin2002p4 
{
	static Triple tunnel[];
	static int N, nWalls;
	
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream ("Fin2002p4DATA41.txt"));
		Triple dir[] = new Triple[6];  // directions corresponding to moves code
		dir[0] = new Triple( 1,0,0 );
		dir[1] = new Triple( 0,0,1 );
		dir[2] = new Triple( 0,-1,0 );
		dir[3] = new Triple( -1,0,0 );
		dir[4] = new Triple( 0,0,-1 );
		dir[5] = new Triple( 0,1,0 );
		
		for(int i=0; i<5; i++)
		{
			N=in.nextInt();
			int j, nT;
			tunnel = new Triple[N+1];
			tunnel[0]=new Triple(0,0,0);
			Triple current = tunnel[0];
			for(nT=1, j=1; j<=N; j++)
			{
				int d=in.nextInt()-1;
				current = current.Add( dir[d] );
				if(!current.inTriples(tunnel, nT) )
					tunnel[nT++] = current; // add to tunnel only when this triple is not already in tunnel
			}
			
			nWalls=(nT)*6;
			for(j=0; j<nT; j++)
				for(int k=0; k<nT; k++)
					if(tunnel[j].isAdjacent(tunnel[k]))
						nWalls--;
			System.out.printf("after %d moves, the tunnel has %d walls.\n", N, nWalls);
			in.nextLine();
				
		}
	}
	
}
