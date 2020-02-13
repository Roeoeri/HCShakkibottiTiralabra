package chess.heuristics;

import chess.rules.Piece;

public class ChessHeuristic {

	private final int min = 0;
	private final int max = 8;

	public int evaluateBoard(Piece[][] board, boolean isWhite) {

		int whiteEvaluation = evaluationWhitePerspective(board);
		int blackEvaluation = evaluationBlackPerspective(board);
		
		
		if(isWhite) {
			return whiteEvaluation - blackEvaluation;
		}
		
		return blackEvaluation - whiteEvaluation;	

	}

	private int evaluationWhitePerspective(Piece[][] board) {
		int sum = 0;

		for (int j = 0; j < max; j++) {
			for (int i = 0; i < max; i++) {

				Piece currentSquare = board[j][i];
				switch (currentSquare) {

				case WPAWN:
					sum += valueOfPawnAtPosition(i, j, true);
					break;

				case WROOK:
					sum += valueOfRookAtPosition(i, j, true);
					break;

				case WBISHOP:
					sum += valueOfBishopAtPosition(i, j, true);
					break;

				case WQUEEN:
					sum += valueOfQueenAtPosition(i, j, true);
					break;

				case WKING:
					sum += valueOfKingAtPosition(i, j, true);
					break;

				case WKNIGHT:
					sum += valueOfKnightAtPosition(i, j, true);
					break;
				default:
					break;

				}

			}
		}
		return sum;
	}

	private int evaluationBlackPerspective(Piece[][] board) {
		int sum = 0;

		for (int j = 0; j < max; j++) {
			for (int i = 0; i < max; i++) {

				Piece currentSquare = board[j][i];
				switch (currentSquare) {

				case BPAWN:
					sum += valueOfPawnAtPosition(i, j, false);
					break;

				case BROOK:
					sum += valueOfRookAtPosition(i, j, false);
					break;

				case BBISHOP:
					sum += valueOfBishopAtPosition(i, j, false);
					break;

				case BQUEEN:
					sum += valueOfQueenAtPosition(i, j, false);
					break;

				case BKING:
					sum += valueOfKingAtPosition(i, j, false);
					break;

				case BKNIGHT:
					sum += valueOfKnightAtPosition(i, j, false);
					break;
				default:
					break;

				}

			}
		}
		return sum;
	}

	private int valueOfPawnAtPosition(int x, int y, boolean isWhite) {

		int[][] whiteTable = new int[][] { { 100, 100, 100, 100, 100, 100, 100, 100 },
				{ 105, 110, 110, 80, 80, 110, 110, 105 }, { 105, 95, 90, 100, 100, 90, 95, 105 },
				{ 100, 100, 100, 120, 120, 100, 100, 100 }, { 105, 105, 110, 125, 125, 110, 105, 105 },
				{ 110, 110, 120, 130, 130, 120, 110, 110 }, { 150, 150, 150, 150, 150, 150, 150, 150 },
				{ 100, 100, 100, 100, 100, 100, 100, 100 } };

		int[][] blackTable = new int[][] { { 100, 100, 100, 100, 100, 100, 100, 100 },
				{ 150, 150, 150, 150, 150, 150, 150, 150 }, { 110, 110, 120, 130, 130, 120, 110, 110 },
				{ 105, 105, 110, 125, 125, 110, 105, 105 }, { 100, 100, 100, 120, 120, 100, 100, 100 },
				{ 105, 95, 90, 100, 100, 90, 95, 105 }, { 105, 110, 110, 80, 80, 110, 110, 105 },
				{ 100, 100, 100, 100, 100, 100, 100, 100 } };

		if (isWhite) {
			return (whiteTable[y][x]);
		}
		return (blackTable[y][x]);
	}

	private int valueOfKnightAtPosition(int x, int y, boolean isWhite) {

		int[][] whiteTable = new int[][] { { 270, 280, 290, 290, 290, 290, 280, 270 },
				{ 280, 300, 320, 325, 325, 320, 300, 280 }, { 290, 325, 330, 335, 335, 330, 325, 290 },
				{ 290, 320, 335, 340, 340, 335, 320, 290 }, { 290, 325, 335, 340, 340, 335, 325, 290 },
				{ 290, 320, 330, 335, 335, 330, 320, 290 }, { 280, 300, 320, 320, 320, 320, 300, 280 },
				{ 270, 280, 290, 290, 290, 290, 280, 270 } };

		int[][] blackTable = new int[][] { { 270, 280, 290, 290, 290, 290, 280, 270 },
				{ 280, 300, 320, 320, 320, 320, 300, 280 }, { 290, 320, 330, 335, 335, 330, 320, 290 },
				{ 290, 325, 335, 340, 340, 335, 325, 290 }, { 290, 320, 335, 340, 340, 335, 320, 290 },
				{ 290, 325, 330, 335, 335, 330, 325, 290 }, { 280, 300, 320, 325, 325, 320, 300, 280 },
				{ 270, 280, 290, 290, 290, 290, 280, 270 }, };

		if (isWhite) {
			return (whiteTable[y][x]);
		}
		return (blackTable[y][x]);
	}

	private int valueOfBishopAtPosition(int x, int y, boolean isWhite) {

		int[][] whiteTable = new int[][] { { 310, 320, 320, 320, 320, 320, 320, 310 },
				{ 320, 335, 330, 330, 330, 330, 335, 320 }, { 320, 340, 340, 340, 340, 340, 340, 320 },
				{ 320, 330, 340, 340, 340, 340, 330, 320 }, { 320, 335, 335, 340, 340, 335, 335, 320 },
				{ 320, 330, 325, 340, 340, 335, 330, 320 }, { 320, 330, 330, 330, 330, 330, 330, 320 },
				{ 310, 320, 320, 320, 320, 320, 320, 310 } };

		int[][] blackTable = new int[][] { { 310, 320, 320, 320, 320, 320, 320, 310 },
				{ 320, 330, 330, 330, 330, 330, 330, 320 }, { 320, 330, 325, 340, 340, 335, 330, 320 },
				{ 320, 335, 335, 340, 340, 335, 335, 320 }, { 320, 330, 340, 340, 340, 340, 330, 320 },
				{ 320, 340, 340, 340, 340, 340, 340, 320 }, { 320, 335, 330, 330, 330, 330, 335, 320 },
				{ 310, 320, 320, 320, 320, 320, 320, 310 }, };

		if (isWhite) {
			return (whiteTable[y][x]);
		}
		return (blackTable[y][x]);
	}

	private int valueOfRookAtPosition(int x, int y, boolean isWhite) {

		int[][] whiteTable = new int[][] { { 500, 500, 500, 505, 505, 500, 500, 500 },
				{ 495, 500, 500, 500, 500, 500, 500, 495, }, { 495, 500, 500, 500, 500, 500, 500, 495 },
				{ 495, 500, 500, 500, 500, 500, 500, 495 }, { 495, 500, 500, 500, 500, 500, 500, 495 },
				{ 495, 500, 500, 500, 500, 500, 500, 495 }, { 505, 510, 510, 510, 510, 510, 510, 505 },
				{ 500, 500, 500, 500, 500, 500, 500, 500 },

		};
		int[][] blackTable = new int[][] { { 500, 500, 500, 500, 500, 500, 500, 500 },
				{ 505, 510, 510, 510, 510, 510, 510, 505 }, { 495, 500, 500, 500, 500, 500, 500, 495 },
				{ 495, 500, 500, 500, 500, 500, 500, 495 }, { 495, 500, 500, 500, 500, 500, 500, 495 },
				{ 495, 500, 500, 500, 500, 500, 500, 495 }, { 495, 500, 500, 500, 500, 500, 500, 495, },
				{ 500, 500, 500, 505, 505, 500, 500, 500 } };

		if (isWhite) {
			return (whiteTable[y][x]);
		}
		return (blackTable[y][x]);
	}

	private int valueOfQueenAtPosition(int x, int y, boolean isWhite) {

		int[][] whiteTable = new int[][] { { 880, 890, 890, 895, 895, 890, 890, 880 },
				{ 890, 900, 905, 900, 900, 900, 900, 890 }, { 890, 905, 905, 905, 905, 905, 900, 890 },
				{ 900, 900, 905, 905, 905, 905, 900, 895 }, { 895, 900, 905, 905, 905, 905, 900, 895 },
				{ 890, 900, 905, 905, 905, 905, 900, 890 }, { 890, 900, 900, 900, 900, 900, 900, 890 },
				{ 880, 890, 890, 895, 895, 890, 890, 880 } };
		int[][] blackTable = new int[][] { { 880, 890, 890, 895, 895, 890, 890, 880 },
				{ 890, 900, 900, 900, 900, 900, 900, 890 }, { 890, 900, 905, 905, 905, 905, 900, 890 },
				{ 895, 900, 905, 905, 905, 905, 900, 895 }, { 900, 900, 905, 905, 905, 905, 900, 895 },
				{ 890, 905, 905, 905, 905, 905, 900, 890 }, { 890, 900, 905, 900, 900, 900, 900, 890 },
				{ 880, 890, 890, 895, 895, 890, 890, 880 } };

		if (isWhite) {
			return (whiteTable[y][x]);
		}
		return (blackTable[y][x]);
	}

	private int valueOfKingAtPosition(int x, int y, boolean isWhite) {

		int[][] whiteTable = new int[][] { { 19950, 19970, 19970, 19970, 19970, 19970, 19970, 19950 },
				{ 19970, 19970, 20000, 20000, 20000, 20000, 19970, 19970 },
				{ 19970, 19990, 20020, 20030, 20030, 20020, 19990, 19970 },
				{ 19970, 19990, 20030, 20040, 20040, 20030, 19990, 19970 },
				{ 19970, 19990, 20030, 20040, 20040, 20030, 19990, 19970 },
				{ 19970, 19990, 20020, 20030, 20030, 20020, 19990, 19970 },
				{ 19970, 19980, 19910, 20000, 20000, 19990, 19980, 19970 },
				{ 19950, 19960, 19970, 11980, 19980, 19970, 19960, 19950 } };

		int[][] blackTable = new int[][] { { 19950, 19960, 19970, 11980, 19980, 19970, 19960, 19950 },
				{ 19970, 19980, 19910, 20000, 20000, 19990, 19980, 19970 },
				{ 19970, 19990, 20020, 20030, 20030, 20020, 19990, 19970 },
				{ 19970, 19990, 20030, 20040, 20040, 20030, 19990, 19970 },
				{ 19970, 19990, 20030, 20040, 20040, 20030, 19990, 19970 },
				{ 19970, 19990, 20020, 20030, 20030, 20020, 19990, 19970 },
				{ 19970, 19970, 20000, 20000, 20000, 20000, 19970, 19970 },
				{ 19950, 19970, 19970, 19970, 19970, 19970, 19970, 19950 } };

		if (isWhite) {
			return (whiteTable[y][x]);
		}
		return (blackTable[y][x]);
	}

}
