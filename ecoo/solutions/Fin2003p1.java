import java.io.*;
import java.util.Scanner;


public class Fin2003p1 
{
	static int modulus, mult, n, lineNum;
	
	public static void main(String[]args)throws IOException
	{
		Scanner in=new Scanner(new FileInputStream("Fin2003p1DATA11.txt"));
		for (int i=0; i<5; i++)
		{
		    modulus=in.nextInt();
	    	int codeTable[]=new int[modulus-1];
	    	int decodeTable[]=new int[modulus-1];
		    codeTable[0]=in.nextInt();
		    mult=in.nextInt();
		    n=in.nextInt();
			in.nextLine();
		    String s="";
		    int j,k;
		    for(j=0; j<n; j++)
		    	s+=in.nextLine();
			decodeTable[codeTable[0]-1]=0;
		    for(j=1; j<modulus-1; j++)
		    {
				codeTable[j]=(codeTable[j-1]*mult)%modulus;
				decodeTable[codeTable[j]-1]=j;
		    }
		    
	    	
	    	String ans="";
		    for(j=0; j<s.length(); j+=modulus-1)
		    {
		    	char block[]=s.substring(j,j+modulus-1).toCharArray();
		    	for(k=0; k<modulus-1; k++)
		    		if(block[k]=='^')
		    			block[k]='\n';
		    	ans+=decodeBlock(block, decodeTable);
		    }		    
		    System.out.println(ans.substring(0,ans.lastIndexOf('\n')));
		    System.out.println();
		}
	}
	
	public static String decodeBlock(char block[], int decodeTable[])
	{
		char a[]=new char[modulus-1];
		for(int i=0; i<modulus-1; i++)
			a[i]=block[decodeTable[i]];
		return new String(a);
	}
	

}


