package apjp2017.hw1;

import java.io.FileWriter;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;
import apjp2017.hw1.HW12.EightQueenPosition;
import static apjp2017.hw1.HW12.EightQueenPosition.*;

public class HW12 {
	public enum EightQueenPosition	{
		P00, P01, P02, P03, P04, P05, P06, P07,
		P10, P11, P12, P13, P14, P15, P16, P17,
		P20, P21, P22, P23, P24, P25, P26, P27,
		P30, P31, P32, P33, P34, P35, P36, P37,
		P40, P41, P42, P43, P44, P45, P46, P47,
		P50, P51, P52, P53, P54, P55, P56, P57,
		P60, P61, P62, P63, P64, P65, P66, P67,
		P70, P71, P72, P73, P74, P75, P76, P77;
		
		//Every position is indexed by its row and column value.  
		int row, col ;	
		String position=" "; 
		static { // use static initializer to fill in (row, col) for every literal PXX.
			EightQueenPosition[] all = EightQueenPosition.values() ;
			int k = 0 ;
			for(int r =0; r < 8; r++ ){
				for(int c = 0; c < 8; c++){
					all[k].row = r;
					all[k].col = c;
					k++ ;
				}
			}		  
		} 
		/** TODO-method:
		 *  Given a set of board positions, return a string displaying
		 *  the layout and content of the board.
		 */
		public static String displayQBoard(EnumSet<EightQueenPosition> queens) {
			// replace following code with your implement
			String[][] cheesBoard = new String[8][8];
			String str = "";
			for(EightQueenPosition e:queens) {
				cheesBoard[e.row][e.col] = e.position;
			}
			for(int r = 0; r < 8; r++ ){
				for(int c = 0; c < 8; c++){
		   			str = str + "|" + cheesBoard[r][c] ;
		   		}
		   		str = str + "|\n";
		   	}
			return str;
		}
		/**TODO-method: 
		 * Given a set of board positions, determine if it is a solution of the 8-Queen problem.
		 *  @param board  a set of positions
		 *  @return true if it is a solution of 8 queens.
		 */
		public static boolean isSolution(EnumSet<EightQueenPosition> queens ){
			// replace following code with your implementation
			String[][] chessBoard = new String [8][8];
			EightQueenPosition[] testAnswer = new EightQueenPosition[8];
			for(EightQueenPosition e:queens) {
				e.position = "X";
			}
			EnumSet<EightQueenPosition> Display=EnumSet.allOf(EightQueenPosition.class);
			int i = 0;
			System.out.println(displayQBoard(Display));
			for(EightQueenPosition e:Display) {
				chessBoard[e.row][e.col] = e.position;
				if(e.position.equals("X")) {
					testAnswer[i++] = e;
				}
			}
			String error = "";
			//以下檢查皇后
			for(EightQueenPosition e:testAnswer) { 
				for(int j = 0;j < 8;j++) {//檢查欄
					if(chessBoard[e.row][j].equals("X")) {
						if(e.col == j) {
							continue;
						}
						System.out.println(e.name() + " error");
					}
				}
			for(int r = 0;r < 8;r++) {//檢查列
				if(chessBoard[r][e.col].equals("X")) {
					if(e.row == r) {
						continue;
					}
					System.out.println(e.name() + " error");
				}
			}
			//以下檢查右斜上
			int conutRightUp = e.row;
			if(conutRightUp > 7 - e.col) {
				conutRightUp = 7 - e.col;
			}
			for(int ru = 1 ; ru <= conutRightUp; ru++) { 
				if(chessBoard[e.row-ru][e.col+ru].equals("X")) {
					if(e.row == e.row-ru && e.col == e.col + ru) {
						continue;
					}
					System.out.println(e.name() + " error");
				}
			}
			//以下檢查右斜下
			int conutRightDown = 7 - e.row;
			if(conutRightDown > 7 - e.col) {
				conutRightDown = 7 - e.col;
				
			}
			for(int rd = 1 ; rd <= conutRightDown; rd++) { 

				if(chessBoard[e.row+rd][e.col+rd].equals("X")) {
					if(e.row == e.row + rd && e.col == e.col + rd) {
						continue;
					}
					System.out.println(e.name() + " error");
				}
			}
			//以下檢查左斜上
			int conutLeftUp = e.row;
			if(conutLeftUp > e.col) {
				conutLeftUp = e.col;
			}
			for(int lu = 1 ; lu <= conutLeftUp; lu++) { 

				if(chessBoard[e.row-lu][e.col-lu].equals("X")) {
					if(e.row == e.row - lu && e.col == e.col - lu) {
						continue;
					}
					System.out.println(e.name() + " error");
				}
			}	
			//以下檢查左斜下
			int conutLeftDown = 7 - e.row;
			if(conutLeftDown > e.col) {
				conutLeftDown = e.col;	
			}
			for(int ld = 1 ; ld <= conutLeftDown; ld++) { 
				if(chessBoard[e.row+ld][e.col-ld].equals("X")) {
					if(e.row == e.row + ld && e.col == e.col - ld) {
						continue;
					}
					System.out.println(e.name() + " error");
				}
			}
		}
		if(error.equals("")) {
			return true;
		}
			return false;
	}
	
	
	/** TODO-method: 
	 * Given a set of board positions, determine if we can get a solution of 8 Queens problem by adding additional positions
	 *  to the input board. Return any such extension of the input board if it is possible and return an empty set if
	 *  there is no such solution.  
	 *  @param  board  a set of positions . 
	 *  @return all solution boards which are extensions of the inpout board, or Emptyset.none() 
	 *          if there is no solution.
	 */
	 
	public static  String getAllSolutions(EnumSet<EightQueenPosition> queens){
		// replace following code with your implementation
		String answers = "";
		for(int i0 = 0; i0 < 8; i0++ ) {
			for(int i1 = 0; i1 < 8; i1++ ) {
				for(int i2 = 0; i2 < 8; i2++ ) {
					for(int i3 = 0; i3 < 8; i3++ ) {
						for(int i4 = 0; i4 < 8; i4++ ) {
							for(int i5 = 0; i5 < 8; i5++ ) {
								for(int i6 = 0; i6 < 8; i6++ ) {
									for(int i7 = 0; i7 < 8; i7++ ) {
										EnumSet<EightQueenPosition> Selection =EnumSet.of(find(0,i0 ,queens),find(1,i1 ,queens),find(2,i2 ,queens),find(3,i3 ,queens),find(4,i4 ,queens),find(5,i5 ,queens));
										Selection.add(find(5,i5 ,queens));
										Selection.add(find(6,i6 ,queens));
										Selection.add(find(7,i7 ,queens));
										//印出所有解
										if(isSolution(Selection) == true) {
											answers = answers + Selection.toString() + "\t";
											try {
												FileWriter file = new FileWriter("allEightQueenSolution.txt");
												file.append(displayQBoard(Selection));											
												file.flush();
												file.close();
											}catch(Exception e){
												
											}
										}
										reset();
									}
								}
							}
						}
					}
				}
			}
		}
		return answers;
	}
	////////////////////////////////////////////////////////////
	////// Add any additional methods/fields  from here      ///
	///////////////////////////////////////////////////////////
	public static EightQueenPosition find(int row, int col,EnumSet<EightQueenPosition> queens) {
		for(EightQueenPosition e: queens) {
			if(e.row == row && e.col == col) {
				return e;
			}
		}
		return null;
	}
	
	//以下重置棋盤
	public static void reset() {
		EnumSet<EightQueenPosition> display = EnumSet.allOf(EightQueenPosition.class);
		for(EightQueenPosition e:display) {
			 e.position=" ";
		 }
	}	
}
	
	
	static int nTests = 0;   // each test may produce multiple errors!
	static int nErrors = 0;
	static int nTestErrors = 0;
	static boolean newTest = true;

	static void error() {
		error("");
	}

	static void error(String s) {
		nErrors++;
		if(newTest){
			nTestErrors ++ ;
			newTest = false;
			System.out.println(">>> new TestError! <<< ");
		}
		System.out.println("*** error " + nErrors + "*** :" + s);
	}
	
	static void error(String s, Object ... args){
		error( String.format(s, args));
	}

	static void test() {
		nTests++;
		newTest = true ;
	}
	
//	static void test(int k) {
//		nTests += k ;
//	}

	static String summary() {

		StringBuilder sb = new StringBuilder();
		sb.append("There are totally " + nTests + " full tests! \n");
		sb.append("There are " + nTestErrors + " test errors! \n");
		sb.append("There are " + nErrors + " detailed errors! \n");
		sb.append(" The passing rate is " + (nTests - nTestErrors) + "/" + nTests + "="
				+ ((nTests - nTestErrors) * 100 / nTests) + "!\n");

		int score = (nTests - nTestErrors) * 50 / nTests + 50 ;

		sb.append("The score is " + score + " provided your source passes compilation!");

		return sb.toString();

	}

	
	public static void mainTest(String... args) {
		
		HW12 hw12 = new HW12();
	
		hw12.testDisplayQBoard();
		hw12.testIsSolution();
		hw12.testGetAllSolutions();
	

	}
	
	/**
	 * 
	 * @param args
	 */
	public void testDisplayQBoard() {
		// write your test code here!
		EnumSet<EightQueenPosition> display = EnumSet.allOf(EightQueenPosition.class);
		System.out.println(displayQBoard(display));
	}

	/**
	 * 
	 * @param args
	 */
	public  void testIsSolution() {
		// write your test code here!
		EnumSet<EightQueenPosition> answer = EnumSet.of(P06,P13,P21,P37,P45,P50,P62,P74);
		if(isSolution(answer) != true) {
			error("testIsSolution() is error !!");
		}
		reset();
		EnumSet<EightQueenPosition> wrongAnswer = EnumSet.of(P01,P02,P16,P23,P45,P55,P66,P72);
		if(isSolution(wrongAnswer) != false) {
			error("testIsSolution() is error !!");
		}
		reset();
	}

	/**
	 * 
	 * @param args
	 */
	public  void testGetAllSolutions() {
		// write your test code here!
		reset();
		EnumSet<EightQueenPosition> Display = EnumSet.allOf(EightQueenPosition.class);
		System.out.println("開始計算全部解 : \n" + getAllSolutions(Display));
		
	}		 
	 
	 public static void main(String... args) {

			// write your own test here!
			System.out.println("=====Begin of Test======");
			// test with tests given by the instructor!
			mainTest(args);
			System.out.println("=====End of Test=========");
			try {
				System.out.println(summary());
			}catch(Exception e) {
				
			}	
		}
}