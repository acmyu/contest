import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JFrame;

public class Fin1998p1 extends JFrame
{
	static char map[][];
	static int age[][];
	static int maxAge;
	static int NW;
	static char screen[][];
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream("Fin1998p1DATA11.txt"));
		map=new char[15][15];
		age=new int[15][15];
		screen=new char[15*5][15];
		for(int testcase=0; testcase<5; testcase++)
		{
			for(int i=0; i<15; i++)
				for(int j=0; j<15; j++)
				{
					map[i][j]=' ';
					age[i][j]=0;
				}
			int n=in.nextInt()-1;
			int m=15-in.nextInt();
			age[m][n]=in.nextInt();
			n=in.nextInt()-1;
			m=15-in.nextInt();
			age[m][n]=in.nextInt();
			maxAge=in.nextInt();
			NW=in.nextInt();
			
			for(int i=0; i<NW; i++)
			{
				ageOneWeek();
				updateMap();
				haveChildren();
			}
			
			for(int i=testcase*15, j=0; i<testcase*15+15 && j<15; i++, j++)
				screen[i]=map[j].clone();
		}
		new Fin1998p1();
	}
	
	Fin1998p1()
	{
		setSize (600, 1000);
		setVisible (true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint (Graphics g)
	{
		super.paint (g);
		for(int i=0; i<15*5; i++)
			for(int j=0; j<15; j++)
			{
				switch(screen[i][j])
				{
					case 'c':
						g.setColor (Color.yellow);
						break;
					case 'm':
						g.setColor (Color.blue);
						break;
					case 'f':
						g.setColor (Color.red);
						break;
					case 's':
						g.setColor (Color.green);
						break;
				}
				if(screen[i][j]!=' ')
					g.fillOval(j*10+10, i*10+30, 10, 10);
			}		
	}
	
	public static void haveChildren()
	{
		for(int i=0; i<15; i++)
			for(int j=0; j<15; j++)
			{
				if(map[i][j]==' ')
				{
					int di[]={ 0, 1, 0,-1, 1, 1,-1,-1};
					int dj[]={ 1, 0,-1, 0, 1,-1,-1, 1};
					boolean female=false, male=false;
					for(int k=0; k<8; k++)
					{
						int i2=i+di[k];
						int j2=j+dj[k];
						if(i2>=0&&i2<15 && j2>=0&&j2<15)
						{
							if(map[i2][j2]=='f')
								female=true;
							else if(map[i2][j2]=='m')
								male=true;
						}
					}
					if(female&&male)
					{
						map[i][j]='c';
						age[i][j]=1;
					}
				}
			}

	}
	
	public static void ageOneWeek()
	{
		for(int i=0; i<15; i++)
			for(int j=0; j<15; j++)
			{
				if(age[i][j]!=0)
					age[i][j]++;
			}
	}
	
	public static void updateMap()
	{
		for(int i=0; i<15; i++)
			for(int j=0; j<15; j++)
			{
				if(age[i][j]>=1 && age[i][j]<2)
					map[i][j]='c';
				if(age[i][j]>=2 && age[i][j]<4)
					map[i][j]='m';
				if(age[i][j]>=4 && age[i][j]<6)
					map[i][j]='f';
				if(age[i][j]>=6 && age[i][j]<maxAge)
					map[i][j]='s';
				if(age[i][j]>maxAge)
					map[i][j]=' ';
			}
	}
}
