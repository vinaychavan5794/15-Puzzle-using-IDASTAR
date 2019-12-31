import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FifteenPuzzle {

	private int[][] tiles=new int[4][4];
	public int xeroX,xeroY;
	public String move="";
	public final static int[][] correctPuzzle=fillCorrectMatrix();
	HashSet<FifteenPuzzle> visited = new HashSet<FifteenPuzzle>();
	LinkedList<FifteenPuzzle> path = new LinkedList<FifteenPuzzle>();
	int pathcost=0;
	int cnt=0;
	int Fvalue;
	
	/*(Method Description)
	 * This function loads the 2D array with goal state.*/
	private static int [][] fillCorrectMatrix() {
		int [][] data=new int[4][4];
		int flag=1;
		for(int i=0;i<4;++i) {
			for(int j=0;j<4;++j) {
				data[i][j]=flag;
				flag++;
			}
		}
		data[3][3]=0;
		return data;
	}
		
	/*(Method Description)
	 * This function asks user to load the puzzle(2D array) with initial state.*/
	private  int [][] fillPuzzleMatrix() {
		Scanner input= new Scanner(System.in);
		System.out.println("Please Enter the Numbers in the Puzzle");
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				tiles[i][j]=input.nextInt();
				if(tiles[i][j] == 0){
					xeroX=i;
					xeroY=j;
	               }
			}
		}
		//input.close();
		return tiles;
	}

	/*(Method Description)
	 * This function creates new node depending on the action chosen:right,left,up,down*/
	public static int[][] createNewNode(int currentMatrix [][],String nextMove,int xeroX,int xeroY){
		int newNode [][] = new int[4][4];
		for(int j=0;j<4;j++) {
			int[] dimension=currentMatrix[j];
			int length =dimension.length;
			newNode[j]=new int[length];
			System.arraycopy(dimension, 0, newNode[j], 0, length);
		}
		switch(nextMove) {
		case "LEFT":
			newNode [xeroX][xeroY] = newNode[xeroX][xeroY-1];
			newNode [xeroX][xeroY-1]=0;
			return newNode;
		case "RIGHT":
			newNode [xeroX][xeroY] = newNode[xeroX][xeroY+1];
			newNode [xeroX][xeroY+1]=0;
			return newNode;
		case "DOWN":
			newNode [xeroX][xeroY] = newNode[xeroX+1][xeroY];
			newNode [xeroX+1][xeroY]=0;
			return newNode;
		case "UP":
			newNode [xeroX][xeroY] = newNode[xeroX-1][xeroY];
			newNode [xeroX-1][xeroY]=0;
			return newNode;
		}
		return newNode;
	}
	
	/*(Method Description)
	 * This function calls createNewNode function to create new node for respective moves
	 *  if right,left,up,down moves are possible.*/
	public List<FifteenPuzzle> findNodesUsingManhattan() {
		ArrayList<FifteenPuzzle> out = new ArrayList<FifteenPuzzle>();
		if(xeroX>0) {
			FifteenPuzzle newPuzzle=new FifteenPuzzle();
			newPuzzle.xeroY=xeroY;
			newPuzzle.xeroX=xeroX-1;
			newPuzzle.move="U";
			newPuzzle.tiles=createNewNode(tiles, "UP", xeroX, xeroY);
			newPuzzle.pathcost=pathcost+1;
			newPuzzle.Fvalue=estimateManhattan()+pathcostEstimation();
			out.add(newPuzzle);
		}
		if(xeroX<3) {
			FifteenPuzzle newPuzzle=new FifteenPuzzle();
			newPuzzle.xeroY=xeroY;
			newPuzzle.xeroX=xeroX+1;
			newPuzzle.move="D";
			newPuzzle.tiles=createNewNode(tiles, "DOWN", xeroX, xeroY);
			newPuzzle.pathcost=pathcost+1;
			newPuzzle.Fvalue=estimateManhattan()+pathcostEstimation();
			out.add(newPuzzle);
		}
		if(xeroY>0) {
			FifteenPuzzle newPuzzle=new FifteenPuzzle();
			newPuzzle.xeroY=xeroY-1;
			newPuzzle.xeroX=xeroX;
			newPuzzle.move="L";
			newPuzzle.tiles=createNewNode(tiles, "LEFT", xeroX, xeroY);
			newPuzzle.pathcost=pathcost+1;
			newPuzzle.Fvalue=estimateManhattan()+pathcostEstimation();	
			out.add(newPuzzle);
		}
		if(xeroY<3) {
			FifteenPuzzle newPuzzle=new FifteenPuzzle();
			newPuzzle.xeroY=xeroY+1;	
			newPuzzle.xeroX=xeroX;
			newPuzzle.move="R";
			newPuzzle.tiles=createNewNode(tiles, "RIGHT", xeroX, xeroY);
			newPuzzle.pathcost=pathcost+1;
			newPuzzle.Fvalue=estimateManhattan()+pathcostEstimation();
			out.add(newPuzzle);
		}
		return out;
	}
	
	/*(Method Description)
	 * This function calls createNewNode function to create new node for respective moves
	 *  if right,left,up,down moves are possible.*/
	public List<FifteenPuzzle> findNodesUsingMisplaced() {
		ArrayList<FifteenPuzzle> out = new ArrayList<FifteenPuzzle>();
		if(xeroX>0) {
			FifteenPuzzle newPuzzle=new FifteenPuzzle();
			newPuzzle.xeroY=xeroY;
			newPuzzle.xeroX=xeroX-1;
			newPuzzle.move="U";
			newPuzzle.tiles=createNewNode(tiles, "UP", xeroX, xeroY);
			newPuzzle.pathcost=pathcost+1;
			newPuzzle.Fvalue=estimateMisplacedTiles()+pathcostEstimation();	
			out.add(newPuzzle);
		}
		if(xeroX<3) {
			FifteenPuzzle newPuzzle=new FifteenPuzzle();
			newPuzzle.xeroY=xeroY;
			newPuzzle.xeroX=xeroX+1;
			newPuzzle.move="D";
			newPuzzle.tiles=createNewNode(tiles, "DOWN", xeroX, xeroY);
			newPuzzle.pathcost=pathcost+1;
			newPuzzle.Fvalue=estimateMisplacedTiles()+pathcostEstimation();
			out.add(newPuzzle);
		}
		if(xeroY>0) {
			FifteenPuzzle newPuzzle=new FifteenPuzzle();
			newPuzzle.xeroY=xeroY-1;
			newPuzzle.xeroX=xeroX;
			newPuzzle.move="L";
			newPuzzle.tiles=createNewNode(tiles, "LEFT", xeroX, xeroY);
			newPuzzle.pathcost=pathcost+1;
			newPuzzle.Fvalue=estimateMisplacedTiles()+pathcostEstimation();
			out.add(newPuzzle);
		}
		if(xeroY<3) {
			FifteenPuzzle newPuzzle=new FifteenPuzzle();
			newPuzzle.xeroY=xeroY+1;	
			newPuzzle.xeroX=xeroX;
			newPuzzle.move="R";
			newPuzzle.tiles=createNewNode(tiles, "RIGHT", xeroX, xeroY);
			newPuzzle.pathcost=pathcost+1;
			newPuzzle.Fvalue=estimateMisplacedTiles()+pathcostEstimation();
			out.add(newPuzzle);
		}
		return out;
	}
	
	
	/*(Method Description)
	 * This function calculates the Number of Misplaced Tiles for a given instance of Puzzle*/
	public int numberMisplacedTiles() {
		int wrong=0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if( (tiles[i][j] >0) && ( tiles[i][j] != correctPuzzle[i][j] ) ){
					wrong++;
				}
			}
		}
		return wrong;
	}
	
	/*(Method Description)
	 * This function is used to check if the goal state is reached.*/
	public boolean goalReached() {		
	        int puzzleSolved [][]  = fillCorrectMatrix();
	        for(int j=0;j<tiles.length;++j) {
				for(int i=0;i<tiles[j].length;++i) {
					if(tiles[j][i]!=puzzleSolved[j][i]) {
						return false;
					}
				}
			}
			return true;   
	}
	
	/*(Method Description)
	 * This function is used to calculate Manhattan Distance for the given Puzzle instance.*/
	public int manhattanDistance() {
		int sum=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				int value=tiles[i][j];
				if(value!=0) {
					int correctX=(value-1)/4;
					int correctY=(value-1)%4;
					int dx=i-correctX;
					int dy=j-correctY;
					sum+=Math.abs(dx)+Math.abs(dy);
				}
			}
		}
		return sum;
	}

	public int pathcostEstimation() {
		return this.pathcost;
	}
	
	public int estimateMisplacedTiles() {
		return this.numberMisplacedTiles();
		
	}
	public int estimateManhattan() {
		return this.manhattanDistance();
	}
	
	/*(Method Description)
	 * This function solves the puzzle (2D array) according to Iterative Deepening A star logic using Manhattan Heuristic by calling
	 * iterativeDFSUsingManhattan method*/
	public  void aStarSolverUsingManhattan() {
		visited.clear();
		cnt=0;
		for (int initialFvalue=Fvalue; initialFvalue < 200; initialFvalue++) {
			visited.clear();
			path.clear();
			FifteenPuzzle nextNode = iterativeDfsUsingManhattan(this, initialFvalue, visited);
			if (nextNode != null && nextNode.goalReached()) {
				String moves="";
				System.out.println("Number of Nodes Expanded:" + cnt);
				while(!path.isEmpty()) {
					FifteenPuzzle trackMoves;
					trackMoves=path.removeFirst();
					moves=moves+trackMoves.move;
				}
				System.out.println("Moves Taken:" + moves);
				break;
			}
		}	
	}
	
	/*(Method Description)
	 * This function solves the puzzle (2D array) according to Iterative Deepening A star logic using Number of Misplaced tiles Heuristic by calling
	 * iterativeDFSUsingMisplaced method*/
	public  void aStarSolverUsingMisplaced() {
		visited.clear();
		cnt=0;
		for (int initialFvalue=Fvalue; initialFvalue < 200; initialFvalue++) {
			visited.clear();
			path.clear();
			FifteenPuzzle nextNode = iterativeDfsUsingMisplaced(this, initialFvalue, visited);
			if (nextNode != null && nextNode.goalReached()) {
				String moves="";
				System.out.println("Number of Nodes Expanded:" + cnt);
				while(!path.isEmpty()) {
					FifteenPuzzle trackMoves;
					trackMoves=path.removeFirst();
					moves=moves+trackMoves.move;
				}
				System.out.println("Moves Taken:" + moves);
				break;
			}
		}	
	}
	
	public FifteenPuzzle iterativeDfsUsingManhattan(FifteenPuzzle node, int fValueMin,
			HashSet<FifteenPuzzle> visited) {
		cnt++;
		if (node != null) {
			
			visited.add(node);
			path.add(node);
		}
		
		if (node != null && node.goalReached()) {
			return node;
		}
        
		if (node.Fvalue > fValueMin) {
			return null;
		}
		FifteenPuzzle test = null;
		for(FifteenPuzzle fp: node.findNodesUsingManhattan()) {
				if( !visited.contains(fp) ) {
					test=iterativeDfsUsingManhattan(fp,fValueMin,visited);
					if(test!=null) {
						return test;
					}
					path.removeLast();	
				}
				
			}

		return null;
	}
	
	public FifteenPuzzle iterativeDfsUsingMisplaced(FifteenPuzzle node, int fValueMin,
			HashSet<FifteenPuzzle> visited) {
		cnt++;
		if (node != null) {
			
			visited.add(node);
			path.add(node);
		}
		
		if (node != null && node.goalReached()) {
			return node;
		}
        
		if (node.Fvalue > fValueMin) {
			return null;
		}
		FifteenPuzzle test = null;
		for(FifteenPuzzle fp: node.findNodesUsingMisplaced()) {
				if( !visited.contains(fp) ) {
					test=iterativeDfsUsingMisplaced(fp,fValueMin,visited);
					if(test!=null) {
						return test;
					}
					path.removeLast();	
				}
				
			}

		return null;
	}
	
	/*(Method Description)
	 * This function prints the state of the puzzle in 2D matrix.*/
	private static void printMatrix(int [][] matrixToPrint,int row,int col) {
		for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                System.out.print(matrixToPrint[i][j]+"\t");
            }             
            System.out.println();
        }	
	}
	
	public static void main(String[] args) {
		FifteenPuzzle p = new FifteenPuzzle();
		int [][] initialmatrix=p.fillPuzzleMatrix();
		p.Fvalue=p.pathcost+p.manhattanDistance();	
		System.out.println("Entered Initial Matrix:");
		printMatrix(initialmatrix, 4, 4);
		boolean exit=true;
		Scanner inu=new Scanner(System.in);
		do {
			System.out.println("\nPlease select one of the following:");
			System.out.println("\n1: Manhattan \n2: Number of Misplaced Tiles \n3: Exit");
			int option=inu.nextInt();
			switch (option) {
				case 1:
					System.out.println("\nSolving using A* with Manhattan as Heuristic:");
					Long memUsedBefore= Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
			   		Long starttime=System.currentTimeMillis();
			   		try {
			   		p.aStarSolverUsingManhattan();
					Long endtime=System.currentTimeMillis();
			   		Long memUsedAfter= Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
			   		Long memoryUsedActual=memUsedAfter-memUsedBefore;
			   		System.out.println("Time taken:"+(endtime-starttime+"ms"));
					System.out.println("Memory Used:"+memoryUsedActual/1024+"KB");
					}
			   		catch (OutOfMemoryError e) {
			   			System.out.println("\nError: No Solution found as A star Algorithm ran out of Memory!\n");
					}
			   		break;
				case 2:
					System.out.println("\nSolving using A* with Number of Misplaced Tiles as Heuristic:");
					memUsedBefore= Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
			   		starttime=System.currentTimeMillis();
			   		try {
			   		p.aStarSolverUsingMisplaced();
					Long endtime1=System.currentTimeMillis();
			   		long memUsedAfter1= Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
			   		Long memoryUsedActual1=memUsedAfter1-memUsedBefore;
			   		System.out.println("Time taken:"+(endtime1-starttime+"ms"));
					System.out.println("Memory Used:"+memoryUsedActual1/1024+"KB");
					}
			   		catch (OutOfMemoryError e) {
			   			System.out.println("\nError: No Solution found as A star Algorithm ran out of Memory!\n");
					}
			   		break;
				case 3:
					exit=false;
					inu.close();
					break;
				default:
					System.out.println("Wrong Choice");
					break;		
			}
		}while(exit);	
		 			
	}

}