/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;

import java.util.ArrayList;

/**
 *
 * @author htomi
 */
public class MovementChecker {

    private ArrayList<Piece[][]> legalMoves;

    public ArrayList<Piece[][]> getLegalMoves(Piece[][] board, boolean isWhite) {
        legalMoves = new ArrayList();
        if(isWhite){
            getLegalWhiteMoves(board);
        }
        return legalMoves;
    }

    public void getLegalWhiteMoves(Piece[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                Piece currentSquare = board[i][j];
                switch (currentSquare) {

                    case WPAWN:
                        getLegalWhitePawnMoves(board,j,i);
                        break;
                    

                }

            }
        }

    }

    public void getLegalWhitePawnMoves(Piece[][] board, int x, int y) {
        
        if(y +1 < board.length){
            Piece contentOfTile = board[y+1][x];
            if(contentOfTile == Piece.EMPTY){
                Piece[][] moveUp = moveCharacter(board,x,y,x,y+1);
                legalMoves.add(moveUp);
            }
        }

    }
    
    
    public Piece[][] moveCharacter(Piece[][] board, int x1, int y1, int x2, int y2){
        
        
        Piece[][] boardToBeReturned = new Piece[8][8];
        
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                boardToBeReturned[i][j] = board[i][j];
            }
        }
        
        Piece pieceAtCurrentLocation = board[y1][x1];
        boardToBeReturned[y2][x2] = pieceAtCurrentLocation;
        boardToBeReturned[y1][x1] = Piece.EMPTY;
        
        return boardToBeReturned;
    }

}
