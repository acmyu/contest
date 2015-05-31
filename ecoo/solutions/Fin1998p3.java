import java.io.*;
import java.util.Scanner;


public class Fin1998p3 
{
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin1998p3DATA31.txt"));
		for(int testcase=0; testcase<5; testcase++)
		{
			String s=in.nextLine();
			s=findBrakes(s);
			while (true)
			{				
				int itag=s.indexOf('<');
				if(itag==-1)
					break;
				int jtag=s.indexOf('>');
				String tag=s.substring(itag+1, jtag);
				if(itag>0&&s.charAt(itag-1)!=' '&&s.charAt(itag-1)!='\n' && jtag<s.length()-1&&s.charAt(jtag+1)!=' '&&s.charAt(jtag+1)!='\n')
					s=s.substring(0,jtag+1)+' '+s.substring(jtag+1);
				
				int iend=s.indexOf("</"+tag+">");
				int jend=iend+2+tag.length();
				String text=s.substring(jtag+1, iend);
				if(iend>0&&s.charAt(iend-1)!=' '&&s.charAt(iend-1)!='\n' && jend<s.length()-1&&s.charAt(jend+1)!=' '&&s.charAt(jend+1)!='\n')
					s=s.substring(0,jend+1)+' '+s.substring(jend+1);

				
				if(tag.equals("lower")||tag.equals("LOWER"))
					text=text.toLowerCase();
				else if(tag.equals("upper")||tag.equals("UPPER"))
					text=text.toUpperCase();
				else
					text=makeProper(text);
				
				String s2=s.substring(0,itag)+text;
				s=s.substring(iend+2);
				s=s.substring(s.indexOf('>')+1);
				s=s2+s;
			}
			s=addOrRemoveSpaces(s);
			System.out.println(s+"\n");
		}
	}
	
	public static String addOrRemoveSpaces(String s)
	{
		int len=s.length();
		for(int i=0; i<len-2; i++)
		{
			if(s.charAt(i)=='\n'&&s.charAt(i+1)==' ')
			{
				s=s.substring(0,i+1)+s.substring(i+2);
				len--;
			}
			if((s.charAt(i)<'A'||s.charAt(i)>'z')&&s.charAt(i)!=' '&&s.charAt(i)!='\n' && s.charAt(i+1)!=' ')
			{
				s=s.substring(0,i+1)+' '+s.substring(i+1);
				len++;
			}
		}
		return s;
	}
	
	public static String makeProper(String text)
	{
		text=text.toLowerCase();
		char t[]=text.toCharArray();
		if(t[0]>='a'&&t[0]<='z')
			t[0]=(char) (t[0]-'a'+'A');
		else
			t[1]=(char) (t[1]-'a'+'A');
		for(int i=1; i<t.length-1; i++)
		{
			if(t[i]==' ')
				t[i+1]=(char) (t[i+1]-'a'+'A');
		}
		return new String(t);
	}
	
	public static String findBrakes(String s)
	{
		String t=s.toLowerCase();
		while(true)
		{
			int ibr=t.indexOf("<br>");
			if(ibr==-1)
				return s;
			s=s.substring(0,ibr)+"\n"+s.substring(ibr+4);
			t=t.substring(0,ibr)+"\n"+t.substring(ibr+4);
		}
	}
}
