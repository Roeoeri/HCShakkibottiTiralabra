package chess.heuristics;

import chess.rules.Piece;

public class ChessHeuristic {

	private final int min = 0;
	private final int max = 8;

	
	/** Palauttaa numeroarvion siitä, kummalla pelaajalla on parametrina annetulla laudalla parempi asema. Positiivinen arvo tarkoittaa valkoista, negatiivinen mustaa.
	 * @param board Lauta jota halutaan arvioida.
	 * @return int Numeroarvio pelaajien asemasta.
	 */
	public int evaluateBoard(Piece[][] board) {

		int whiteEvaluation = evaluationWhitePerspective(board);
		int blackEvaluation = evaluationBlackPerspective(board);
		
		
		
		return blackEvaluation + whiteEvaluation;	

	}

	
	/** Arvioi valkoisen asemaa hänellä olevien nappuloiden perusteella.
	 * @param board Pelitilanne jota halutaan arvioida.
	 * @return int Palauttaa summan nappuloista kerrottuna niiden arvolla.
	 */
	private int evaluationWhitePerspective(Piece[][] board) {
		int sum = 0;

		for (int j = 0; j < max; j++) {
			for (int i = 0; i < max; i++) {

				Piece currentSquare = board[j][i];
				switch (currentSquare) {

				case WPAWN:
					sum += 100;
					break;

				case WROOK:
					sum += 500;
					break;

				case WBISHOP:
					sum += 330;
					break;

				case WQUEEN:
					sum += 900;
					break;

				case WKING:
					sum += 20000;
					break;

				case WKNIGHT:
					sum += 320;
					break;

				}

			}
		}
		return sum;
	}

	
	/** Arvioi mustan asemaa hänella olevien nappuloiden perusteella.
	 * @param board Pelitilanne jota halutaan arvioida.
	 * @return int Palauttaa summan nappuloista kerrottuna niiden arvolla.
	 */
	private int evaluationBlackPerspective(Piece[][] board) {
		int sum = 0;

		for (int j = 0; j < max; j++) {
			for (int i = 0; i < max; i++) {

				Piece currentSquare = board[j][i];
				switch (currentSquare) {

				case BPAWN:
					sum -= 100;
					break;

				case BROOK:
					sum -= 500;
					break;

				case BBISHOP:
					sum -= 330;
					break;

				case BQUEEN:
					sum -= 900;
					break;

				case BKING:
					sum -= 20000;
					break;

				case BKNIGHT:
					sum -= 320;
					break;

				}

			}
		}
		return sum;
	}



}
