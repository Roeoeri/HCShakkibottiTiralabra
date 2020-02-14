/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.bot;

import java.util.ArrayList;

import chess.engine.GameState;
import chess.heuristics.ChessHeuristic;
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
    private final int maxDepth = 5;
    private final int minEvalValue = -3000000;
   

    public TiraBot() {

        currentboard = new Piece[][]{
            {Piece.WROOK, Piece.WKNIGHT, Piece.WBISHOP, Piece.WQUEEN, Piece.WKING, Piece.WBISHOP, Piece.WKNIGHT, Piece.WROOK},
            {Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN},
            {Piece.BROOK, Piece.BKNIGHT, Piece.BBISHOP, Piece.BQUEEN, Piece.BKING, Piece.BBISHOP, Piece.BKNIGHT, Piece.BROOK}
        };
        
        checker = new MovementChecker();
        heuristic = new ChessHeuristic();
    }

    @Override
    public String nextMove(GameState gs) {
        return "e1e2";
    }
    
    
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
    
    private int maxValue(Piece[][] board, int depth){
    	ArrayList<Piece[][]> children = checker.getLegalMoves(board, true);
    	
    	if(depth == maxDepth || children.size() == 0 || gameOver(board)) {
    		return heuristic.evaluateBoard(board);
    	}
    	
    	int v = Integer.MIN_VALUE;
    	for(Piece[][] child : children) {
    		v= Math.max(v, minValue(child, depth+1));
    	}
    	return v;    	
    }
    
    private int minValue(Piece[][] board, int depth){
	ArrayList<Piece[][]> children = checker.getLegalMoves(board, false);
    	
    	if(depth == maxDepth || children.size() == 0 || gameOver(board)) {
    		return heuristic.evaluateBoard(board);
    	}
    	
    	int v = Integer.MAX_VALUE;
    	for(Piece[][] child : children) {
    		v= Math.min(v, maxValue(child, depth+1));
    	}
    	return v;    
    }
    
    
    public Piece[][] getBestWhiteMove(Piece[][] board){
    	ArrayList<Piece[][]> children = checker.getLegalMoves(board, true);
    	
    	Piece[][] bestNode = null;
    	int bestValue = minEvalValue;
    	for(Piece[][] child : children) {
    		int score = minValue(child,0);
    		if(score > bestValue) {
    			bestValue = score;
    			bestNode = child;
    		}
    	}
    	
    	return bestNode;
    		
    }
    

    
	public void printBoard(Piece[][] board) {
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				System.out.print(board[i][j] +" ");
			}
			System.out.println();
		}
	}
    
    
    

    public void updateBoard(String move) {

        int x1 = move.charAt(0) - 97;
        int y1 = move.charAt(1) - 49;

        int x2 = move.charAt(2) - 97;
        int y2 = move.charAt(3) - 49;

        Piece PieceToBeMoved = currentboard[y1][x1];

        currentboard[y1][x1] = Piece.EMPTY;
        currentboard[y2][x2] = PieceToBeMoved;

    }

}
