import java.io.*;
import java.util.Scanner;


public class Fin2005p4 
{
	static int N=8;
	static int iking, jking;
	static char[][] board;
	static int[][] nma; // numbers of moves to all squares starting from the rook
	public static void main(String[]args) throws IOException
	{
		Scanner in=new Scanner (new FileInputStream ("Fin2005p4DATA41.txt"));
		for(int n=0;n<5;n++)
		{
			// read the chess board data
			board=new char[N][N];
			for(int i=0;i<N;i++)
				board[i]=in.nextLine().toCharArray();
				
			// find the positions of Rook and King
			int irook=0, jrook=0;
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
				{
					if(board[i][j]=='K')
					{
						iking=i; jking=j;
					}
					else if(board[i][j]=='R')
					{
						irook=i; jrook=j;
					}

				}
			}

			nma=new int[N][N];
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++)
					nma[i][j]=Integer.MAX_VALUE;
			moveRook(irook,jrook,0);
			System.out.println("The Rook will take the King in "+nma[iking][jking]+" move(s)");
		}
	}

	public static void moveRook(int i, int j, int nm)
	{
		// i, j: current position
		// nm: number of moves so far
		nma[i][j]=nm;
		if( i==iking && j==jking )
			return;
		int []di = {-1, 0, 1, 0};
		int []dj = { 0,-1, 0, 1};
		int i2, j2, d;
		for( d=0; d<4; d++ )  // four directions
		{
			for( i2=i+di[d],j2=j+dj[d]; i2>=0&&i2<N&&j2>=0&&j2<N
					&& board[i2][j2]!='X'; i2+=di[d],j2+=dj[d] )
			{
				// continue if i2,j2 are within bounds and the square is not 'X'
				// if we have not been there before or need less number of moves to there
				int nm2 = nm+1;
				if(nma[i2][j2]>nm2 && nma[iking][jking]>nm2)
					moveRook(i2, j2, nm2);
			}
		}
	}
}
