import java.io.*;
import java.util.*;

class Customer
{
	String name;
	int tStart;
	int tSpend;
	int tWait;
}

public class Fin1998p4 
{
	static Customer ca[]; // ca: customers
	static int N; // number of customers
	static int ih; // next customer to come
	static Customer qa[][]; // qa[i] is the i-th of 3 queues 
	static int ha[],ta[],na[]; // head, tail and length of each queue
	static int njumps, totServed, totTime;
	static String cJumped[];
	
	public static void main (String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin1998p4DATA41.txt"));
		ca=new Customer[50];
		while(in.hasNext())
		{
			ca[N]=new Customer();
			ca[N].name=in.nextLine();
			String s=in.nextLine();
			ca[N].tStart=Integer.parseInt(s.substring(0,2))*60 + Integer.parseInt(s.substring(3));
			ca[N].tSpend=in.nextInt();
			ca[N].tWait=in.nextInt();
			in.nextLine();
			N++;
		}

		qa=new Customer[3][N];
		ha=new int[3];
		ta=new int[3];
		na=new int[3];
		cJumped=new String[50];

		sortCustomers();
		int t;
		for(t=0; na[0]>0||na[1]>0||na[2]>0 || ih<N; t++)
		{
			placeCustomersInLine(t);
			serveCustomers();
			jumpLine(t);
			changeLine();
		}
		System.out.printf("Total Customers Serviced                   %d\n",totServed);
		System.out.printf("Total time to service all the customers    %d minutes\n",totTime);
		System.out.printf("total number of times a line was jumped    %d\n",njumps);
		System.out.printf("Customers who jumped the line	           %s\n",cJumped[0]);
		for(int i=1; i<njumps; i++)
			System.out.printf("                                           %s\n",cJumped[i]);
		System.out.printf("                                           ....\n");
		System.out.printf("The last customer left at	               %02d:%02d\n", t/60, t%60);
	}
	
	public static void sortCustomers()
	{
		// bubble sort
		for(int i=0; i<N; i++)
			for(int j=0; j<N-i-1; j++)
				if(ca[j].tStart > ca[j+1].tStart)
				{
					Customer c=ca[j];
					ca[j]=ca[j+1];
					ca[j+1]=c;
				}
	}
	
	public static void placeCustomersInLine(int t)
	{
		while(ih<N && ca[ih].tStart==t) // customers coming at time t
		{
			int iS=0; // index of the shortest queue
			for(int i=1; i<3; i++)
				if(na[i]<na[iS])
					iS=i;
			qa[iS][ta[iS]]=ca[ih];  // append the customer to the tail of the shortest queue
			ta[iS]++; na[iS]++;
			ih++;
		}
	}
	
	public static void serveCustomers()
	{
		for(int i=0; i<3; i++)
		{
			if( na[i]>0 ) // if the queue is not empty
			{
				Customer c = qa[i][ha[i]];  // head of the queue, the customer currently being serviced
				if(c.tSpend>0)
				{
					c.tSpend--;
					totTime++;
					if(c.tSpend==0)
					{
						ha[i]++; na[i]--;
						totServed++;
					}
				}
			}
		}
	}
	
	public static void jumpLine(int t)
	{
		for(int i=0; i<3; i++)
		{
			for(int j=ha[i]+1; j<ta[i]; j++)
			{
				Customer c = qa[i][j]; // each customer waiting in line
				if(t - c.tStart >= c.tWait) // remove from the queue if waiting too long
				{
					for(int k=j; k<ta[i]-1; k++) // other customers move ahead
						qa[i][k]=qa[i][k+1];
					ta[i]--; na[i]--;
					cJumped[njumps++] = c.name;
				}
			}
		}
	}

	public static void changeLine()
	{
		// one customer changes line in each iteration, in case the longest queue
		// no longer being the longest after one customer left 
		while( true )
		{
			int iL=0, iS=0; // index of the longest/shortest queue
			for(int i=1; i<3; i++)
			{
				if(na[i]>na[iL])
					iL=i;
				if(na[i]<na[iS])
					iS=i;
			}
			if(na[iL]-na[iS]>1) // 2 or more customers
			{
				Customer c = qa[iL][ta[iL]-1]; // remove the customer at the tail of the longest queue
				ta[iL]--; na[iL]--;
				qa[iS][ta[iS]] = c; // append the customer to the tail of the shortest queue
				ta[iS]++; na[iS]++;
				;  // continue to see if more customers would change lines
			}
			else
				break; // until no customer would change lines
		}
	}
}
