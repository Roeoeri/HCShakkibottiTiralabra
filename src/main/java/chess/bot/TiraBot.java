/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.bot;

import chess.datastructures.ChessList;
import chess.engine.GameState;
import chess.heuristics.ChessHeuristic;
import chess.model.Side;
import chess.rules.MovementChecker;
import chess.rules.Piece;

/**
 *
 * @author htomi
 */
public class TiraBot implements ChessBot {

    private Piece[][] currentboard;
    private MovementChecker checker;
    private ChessHeuristic heuristic;
    private int maxDepth;
    private final int minEvalValue = -3000000;
	private final int maxEvalValue =  3000000;
	private final int convertNumberCharToInt = 49;
	private final int convertAlphabetCharToInt = 97;
   

    public TiraBot(int maxDepth) {
		this.maxDepth = maxDepth;

        currentboard = new Piece[][]{
            {Piece.WROOK, Piece.WKNIGHT, Piece.WBISHOP, Piece.WQUEEN, Piece.WKING, Piece.WBISHOP, Piece.WKNIGHT, Piece.WROOK},
            {Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN},
            {Piece.BROOK, Piece.BKNIGHT, Piece.BBISHOP, Piece.BQUEEN, Piece.BKING, Piece.BBISHOP, Piece.BKNIGHT, Piece.BROOK}
        };
        
        checker = new MovementChecker();
        heuristic = new ChessHeuristic();
    }

    
	/** 
	 * @param gs Pelimoottorin ymmärtämä GameState-olio
	 * @return String Siirto UCI Formaatissa
	 */
	@Override
    public String nextMove(GameState gs) {
    	if(gs.getMoveCount() > 0) {
    		updateBoard(gs.getLatestMove());
    	}
    	
    	Piece[][] play = null;
    	if(gs.playing == Side.BLACK) {
    		play = getBestBlackMove(currentboard);
    	}else {
    		play = getBestWhiteMove(currentboard);
    	}
    	
    	String move = parseMove(currentboard,play);
    	currentboard = play;
        return move;
    }
    
    
	/** 
	 * Parsii kahden pelitilanteen välisen erotuksen merkkijonomuotoiseksi UCI siirroksi.
	 * @param startBoard Pelitilanne alussa.
	 * @param endBoard Pelitilanne lopussa.
	 * @return String Pelitilanteiden välinen erotus UCI-siirtona
	 */
	public String parseMove(Piece[][] startBoard, Piece[][] endBoard) {
    	String endPoint = "";
    	String startPoint = "";
    	for(int i = 0; i< 8; i++) {
    		for(int j = 0; j<8; j++) {
    		   	Piece startPiece = startBoard[i][j];
    		   	Piece endPiece = endBoard[i][j];
    		   	
    			String row = String.valueOf((char) (i + convertNumberCharToInt));
    		    String column = String.valueOf((char) (j + convertAlphabetCharToInt));
    			if(startPiece != endPiece) {
    				if(endPiece == Piece.EMPTY) {
    					startPoint = column + row;
    				}else {
    					endPoint = column+row;
    				}
    			}
    			
    		}
    	}
    	return startPoint + endPoint;
    }
    
    
    
	/** 
	 * Tarkistaa, onko jossakin annetussa pelitilanteessa peli ohitse, eli toinen kuninkaista on kaatunut.
	 * @param board Pelitilanne jota halutaan tarkastella.
	 * @return boolean Palauttaa true, jos jompi kumpi kuninkaista on kaatunut, muutoin false.
	 */
	private boolean gameOver(Piece[][] board) {
    	boolean bKingAlive = false;
    	boolean wKingAlive = false;
    	int size = board.length;
    	
    	for(int i = 0; i< size; i++) {
    		for(int j = 0; j<size; j++) {
    			if(board[i][j] == Piece.BKING) {
    				bKingAlive = true;
    			}
    			if(board[i][j] == Piece.WKING) {
    				wKingAlive = true;
    			}
    		}
    	}
    	
    	if(bKingAlive && wKingAlive) {
			return false;
		}
		
		return true;
    }
    
    
	/** Minimax-algoritmin max-osuus.
	 * @param board Pelitilanne, jota halutaan tarkastella.
	 * @param depth Syvyys, johon tähän mennessä ollaan haettu.
	 * @param alpha Alpha-arvo alphabeta pruningia varten.
	 * @param beta Beta-arvo alphabeta pruningia varten.
	 * @return int Numeerinen arvio sille, miten peli tulee jatkumaan kunkin valkoisen siirron jälkeen. Positiivinen jos peli
	 * on valkoisen eduksi, muuten negatiivinen.
	 */
	private int maxValue(Piece[][] board, int depth, int alpha, int beta){
		if(depth == maxDepth || gameOver(board)){
			return heuristic.evaluateBoard(board);
		}
    	ChessList<Piece[][]> children = checker.getLegalMoves(board, true);
    	if(children.size() == 0) {
    		return heuristic.evaluateBoard(board);
    	}
    	
    	int v = Integer.MIN_VALUE;
    	for(Piece[][] child : children) {
    		v= Math.max(v, minValue(child, depth+1, alpha,beta));
    		alpha = Math.max(alpha, v);
    		if(alpha >= beta) {
    			return v;
    		}
    	}
    	return v;    	
    }
    
    
	/** 
	 * Minimax algoritmin min-osuus.
	 * @param board Pelitilanne, jota halutaan tarkastella.
	 * @param depth Syvyys, johon tähän mennessä ollaan haettu.
	 * @param alpha Alpha-arvo alphabeta pruningia varten.
	 * @param beta Beta-arvo alphabeta pruningia varten.
	 * @return int Numeerinen arvio sille, miten peli tulee jatkumaan kunkin mustan siirron jälkeen. Positiivinen jos peli
	 * on valkoisen eduksi, muuten negatiivinen.
	 */
	private int minValue(Piece[][] board, int depth, int alpha, int beta){
		if(depth == maxDepth || gameOver(board)){
			return heuristic.evaluateBoard(board);
		}

		ChessList<Piece[][]> children = checker.getLegalMoves(board, false);
    	
    	if(children.size() == 0) {
    		return heuristic.evaluateBoard(board);
    	}
    	
    	int v = Integer.MAX_VALUE;
    	for(Piece[][] child : children) {
    		v= Math.min(v, maxValue(child, depth+1,alpha,beta));
    		beta = Math.min(beta, v);
    		if(alpha >= beta) {
    			return v;
    		}
    	}
    	return v;    
    }
    
    
    
	/** Metodi, joka palauttaa parametrina annetun pelitilanteen lapsista sen jolla on suurin arvo (= Paras siirto valkoiselle).
	 * @param board Tarkasteltava pelitilanne.
	 * @return Piece[][] Pelitilanne joka seuraa parasta valkoista siirtoa.
	 */
	public Piece[][] getBestWhiteMove(Piece[][] board){
    	ChessList<Piece[][]> children = checker.getLegalMoves(board, true);
    	
    	Piece[][] bestNode = null;
    	int bestValue = minEvalValue;
    	for(Piece[][] child : children) {
    		int score = minValue(child,1,minEvalValue,maxEvalValue);
    		if(score > bestValue) {
    			bestValue = score;
    			bestNode = child;
    		}
    	}
    	return bestNode;	
    }
    
    
	/** Metodi, joka palauttaa parametrina annetun pelitilanteen lapsista sen jolla on pienin arvo (= Paras siirto mustalle).
	 * @param board Tarkasteltava pelitilanne.
	 * @return Piece[][] Piece[][] Pelitilanne joka seuraa parasta mustaa siirtoa.
	 */
	public Piece[][] getBestBlackMove(Piece[][] board){
    	ChessList<Piece[][]> children = checker.getLegalMoves(board, false);
    	
    	Piece[][] bestNode = null;
    	int bestValue = maxEvalValue;
    	for(Piece[][] child : children) {
    		int score = maxValue(child,1,minEvalValue,maxEvalValue);
    		if(score < bestValue) {
    			bestValue = score;
    			bestNode = child;
    		}
    	}
    	return bestNode;	
    }
    
    
    
	/** Päivittää tirabotin sisäistä pelilautaa parametrina annetun UCI-merkkijonon mukaisesti.
	 * @param move UCI-siirto.
	 */
	public void updateBoard(String move) {

        int x1 = move.charAt(0) - convertAlphabetCharToInt;
        int y1 = move.charAt(1) - convertNumberCharToInt;

        int x2 = move.charAt(2) - convertAlphabetCharToInt;
        int y2 = move.charAt(3) - convertNumberCharToInt;
        
        
        Piece PieceToBeMoved = currentboard[y1][x1];
        
        if(move.length() > 4) {
        	if(checker.getColor(PieceToBeMoved) == Piece.WHITE) {
        		PieceToBeMoved = Piece.WQUEEN;
        	}else {
        		PieceToBeMoved = Piece.BQUEEN;
        	}
        }

        currentboard[y1][x1] = Piece.EMPTY;
        currentboard[y2][x2] = PieceToBeMoved;
    }

}
