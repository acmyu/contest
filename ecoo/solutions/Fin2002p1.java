import java.io.*;
import java.util.Scanner;


public class Fin2002p1 
{
	static int ageGroup[];
	static int currentTot;
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin2002p1DATA11.txt"));
		System.out.println("Life expectancy      number of months    number of rabbits");
		for(int i=0; i<5; i++)
		{
			int age=in.nextInt();
			int month=in.nextInt();
			ageGroup=new int[age];
			fibonacci(age, month);
			System.out.printf("%13d%21d%21d\n", age, month, currentTot);
		}
	}
	
	public static void fibonacci(int age, int month)
	{
	    ageGroup[0] = 1;
	    int i;
	    for(i=1; i<age; i++)
	        ageGroup[i] = 0;
	    for(i=0; i<month; i++)
	        oneYear(age);
	}
	
	public static void oneYear(int age)
	{
		int newBorn = 0;
		int i;
		for(i=1; i<age; i++)
		    newBorn += ageGroup[i];
		for(i=age-1; i>=1; i--)
		    ageGroup[i] = ageGroup [i - 1];
		ageGroup[0] = newBorn;
		currentTot = 0;
		for(i=0; i<age; i++)
		    currentTot += ageGroup[i];
	}
}
