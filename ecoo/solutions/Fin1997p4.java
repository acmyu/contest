import java.io.*;
import java.util.Scanner;


public class Fin1997p4 
{
	static int Q, Y, Z, T, W, ns, pensUnlocked, tripAtAlarm, doorAtAlarm;
	static boolean ga[];
	public static void main (String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin1997p4DATA41.txt"));
		Q=in.nextInt();
		Y=in.nextInt();
		Z=in.nextInt();
		W=in.nextInt();
		int X=in.nextInt();
		T=X/10000 * 3600;
		X%=10000;
		T+=X/100 * 60;
		X%=100;
		T+=X;
		ga=new boolean[Q];
		for(int i=0; i<Q; i++)
			ga[i]=true;
		
		lockOrUnlockGates();
		System.out.printf("%02d:%02d:%02d\n", ns/3600, ns%3600/60, ns%60);
		int nLocked=0;
		for(int i=0; i<Q; i++)
			if(ga[i])
				nLocked++;
		System.out.printf("%d\n%d\n%d\n%d\n", nLocked, pensUnlocked,tripAtAlarm,doorAtAlarm+1);
	}
	
	public static void lockOrUnlockGates()
	{
		ns=0; 
		boolean alarmSounded=false;
		for(int round=1; round<=W; round++)
		{
			for(int i=0; i<Q; i+=round)
			{
				ns+=Y;
				if(T<=ns && !alarmSounded)
				{
					if(T==ns)
						ga[i]=!ga[i];
					for(int j=0; j<Q; j++)
							if(!ga[j])
								pensUnlocked++;
					if(T<ns)
						ga[i]=!ga[i];
					tripAtAlarm=round;
					doorAtAlarm = i;
					alarmSounded=true;
				}
				else
					ga[i]=!ga[i];
				ns+=Z;				
			}
			
		}
	}
}
