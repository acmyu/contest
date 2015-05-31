import java.io.*;
import java.util.Scanner;


public class Fin2005p2 
{
	public static void main(String[]args) throws IOException
	{
		Scanner in = new Scanner (new FileInputStream ("Fin2005p2DATA21.txt"));
		for(int i=0; i<5; i++)
		{
			char[] code=in.nextLine().toCharArray();
			int[] numline = step8(code);
			numline = step7(numline);
			int[] base27= step6(numline);
			base27=step5(base27);
			numline=step4(base27);
			numline=step3(numline);
			code=step2(numline);
			System.out.println(new String(code));
		}
	}
		
	//reverse step 8 to step 7
	public static int[] step8(char[]a)
	{
		int[] b=new int[a.length];
		for(int i=0;i<a.length;i++)
			b[i] = a[i]==' '? 0 : a[i]-'A'+1; 
		return b;
	}
	
	public static int[] step7 (int[] a)
	{
		int n=a.length;
		int[] b=new int[n];
		b[0]=a[0];
		for(int i=1;i<n;i++)
			b[i]=(a[i]+27-a[i-1])%27;
		return b;
	}
	
	public static int[] step6(int[] a)
	{
		int n=a.length;
		int[]b=new int[n/5];
		for(int i=0; i<n; i+=5)
			for(int j=0;j<5;j++)
				b[i/5] = b[i/5]*27 + a[i+j];
		return b;
	}
	
	public static int[] step5 (int[]a)
	{
		int n=a.length;
		int[]b=new int[n];
		for(int i=0;i<n;i++)
			b[i]=a[i]*14%14348891;
		return b;
	}
	
	public static int[] step4 (int[] a)
	{
		int n=a.length;
		int[] b=new int[n*5];
		for(int i=0; i<n; i++)
		{
			int j=i*5;
			b[j]=a[i]/(27*27*27*27);
			a[i]=a[i]%(27*27*27*27);
			b[j+1]=a[i]/(27*27*27);
			a[i]=a[i]%(27*27*27);
			b[j+2]=a[i]/(27*27);
			a[i]=a[i]%(27*27);
			b[j+3]=a[i]/27;
			a[i]=a[i]%27;
			b[j+4]=a[i];
		}
		return b;
	}
	
	public static int[] step3 (int[] a)
	{
		int n=a.length;
		int[]b=new int[n];
		b[0]=a[0];
		for(int i=1;i<n;i++)
			b[i]=(a[i]+b[i-1]) % 27;
		return b;
	}
	
	public static char[] step2 (int[] a)
	{
		int n=a.length;
		char[]b=new char[n];
		for(int i=0;i<n;i++)
			b[i] = a[i]==0? ' ' : (char)(a[i]+'A'-1);
		return b;
	}

}
