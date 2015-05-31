import java.io.*;
import java.util.Scanner;


class Spider
{
	double x; // x-value of its position
	double y; // y-value of its position
 	double s; // speed centimeters/second
	double d; // distance to next spider
}

public class Fin2003p2 
{
	static Spider sp[];
	static int count;// number of seconds until the chase ends, used in function move
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream ("Fin2003p2DATA21.txt"));
		for(int n=0;n<5;n++)
		{
			sp=new Spider[3];
			for(int i=0;i<3;i++)
			{
				sp[i]=new Spider();
				sp[i].x=in.nextDouble();
				sp[i].y=in.nextDouble();
				sp[i].s=in.nextDouble();
			}
			System.out.println("spider #"+ move()+ " made the catch after "+ count+ " seconds\n");
		}
	}
	
	public static int move()
	{
		count=0;
	    Spider p;
		while(true)
		{
		    sp[0].d = distance (sp[0], sp[1]);
		    sp[1].d = distance (sp[1], sp[2]);
		    sp[2].d = distance (sp[2], sp[0]);
		    if (sp[0].d < sp[0].s)
		        return 1;
		    if (sp[1].d < sp[1].s)
		        return 2;
		    if (sp[2].d < sp[2].s)
		        return 3;
		    count ++;
		    // since spider 2 moves towards the original position of spider 0
		    // therefore, p is used, since sp[0] has changed
		    p = sp[0];
		    sp[0] = newPosition (sp[0], sp[1]);
		    sp[1] = newPosition (sp[1], sp[2]);
		    sp[2] = newPosition (sp[2], p);
		
		}
	}
	
	// returns the distance between spiders p and q
	public static double distance (Spider p, Spider q)
	{
	    return Math.sqrt( (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y));
	}
	
	// finds the new values, r for spider p, after moving towards spider q in one second
	public static Spider newPosition (Spider p, Spider q)
	{
	    double dist = distance (p, q);
	    Spider r = new Spider();
	    r.x = (dist * p.x - p.s * p.x + p.s * q.x) / dist;
	    r.y = (dist * p.y - p.s * p.y + p.s * q.y) / dist;
	    r.s = p.s;
	    r.d = p.d - p.s;
	    return r;
	}
}
