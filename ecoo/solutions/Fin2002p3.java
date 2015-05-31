import java.io.*;
import java.util.Scanner;

class Individual
{
	static int N;//total number of individuals
	static Individual list[];//list of individuals

	int id;
	String name;
	boolean male;
	int idad;
	int imom;
	String birth;

	int nc;//number of children of this individual
	Individual children[];

	Individual()//individual not found
	{
		id=-1;
		name="unknown                 ";
		birth="unknown";
	}
	Individual(String s) 
	{
		id=Integer.parseInt(s.substring(0,5));
		name=s.substring(5,29);
		male=s.charAt(29)=='m';
		idad=Integer.parseInt(s.substring(30,35));
		imom=Integer.parseInt(s.substring(35,40));
		birth=s.substring(41);
	}

	public static void readRecord( Scanner in )
	{
		N=in.nextInt();
		in.nextLine();
		list=new Individual[N+1];
		list[0]=new Individual();
		for(int i=1; i<=Individual.N; i++)
			list[i] = new Individual(in.nextLine());
	}

	public static Individual findIndividual(int id)//find individual from list with id
	{
		for(int i=1; i<=N; i++)
			if(list[i].id==id)
				return list[i];
		return list[0];
	}

	public void findChildren()//find all children of this individual
	{
		int i;
		nc=0;
		children=new Individual[N];
		for(i=1; i<=N; i++)
			if(list[i].idad==id || list[i].imom==id)
				children[nc++]=list[i];
		
		// bubble sort children 
		for(i=0; i<nc-1; i++)
			for(int j=0; j<nc-1-i; j++)
			{
				if(children[j].birth.compareTo(children[j+1].birth)>0)
				{
					Individual t=children[j];
					children[j]=children[j+1];
					children[j+1]=t;
				}
			}
	}

	public String getDetailsString()
	{
		Individual d=findIndividual(idad);
		Individual m=findIndividual(imom);
		String str;
		str = String.format("subject:  %s born: %s     %s\n", name, birth, male? "male" : "female");
		str+= "----------------------------------------------\n";
		str+= String.format("father:   %s born: %s\nmother:   %s born: %s\n",
				d.name, d.birth, m.name, m.birth);
		
		findChildren();
		if(nc!=0)
		{
			int idspouse=male? children[0].imom :  children[0].idad;
			Individual s=findIndividual(idspouse);
			str+= String.format("%s%s born: %s\n", s.male? "husband:  " : "wife:     ", s.name, s.birth);
			str+= "children : \n";
			for(int j=0; j<nc; j++)
			{
				Individual c = children[j];
				str+= String.format("          %s born: %s     %s\n", c.name, c.birth, c.male? "male" : "female");
			}
		}
		else
			str+= "no children\n";
		str+="\n";
		return str;
	}
}

public class Fin2002p3 
{
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin2002p3DATA31.txt"));
		Individual.readRecord(in);

		for(int i=0; i<5; i++)
		{
			int id = in.nextInt();
			Individual p = Individual.findIndividual(id);
			System.out.print( p.getDetailsString() );
		}
	}
}
