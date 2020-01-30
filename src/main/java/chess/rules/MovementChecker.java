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
    private final int size = 8;
    private final int min = 0;

    public ArrayList<Piece[][]> getLegalMoves(Piece[][] board, boolean isWhite) {
        legalMoves = new ArrayList();
        if (isWhite) {
            getLegalWhiteMoves(board);
        }
        return legalMoves;
    }

    private void getLegalWhiteMoves(Piece[][] board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Piece currentSquare = board[i][j];
                switch (currentSquare) {

                    case WPAWN:
                        getLegalPawnMoves(board, j, i, Piece.BLACK);
                        break;

                }

            }
        }

    }
    
    private void getLegalBlackMoves(Piece[][] board ){
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                Piece currentSquare = board[i][j];
                switch(currentSquare){
                    
                    case BPAWN:
                        getLegalPawnMoves(board,j,i-2, Piece.WHITE);
                        break;
                }
            }
        }
    }

    private void getLegalPawnMoves(Piece[][] board, int x, int y, Piece oppositeColor) {

        int yUp = y + 1;
        if (yUp < size) {
            Piece contentOfTile = board[yUp][x];
            if (contentOfTile == Piece.EMPTY) {
                Piece[][] moveUp = moveCharacter(board, x, y, x, yUp);
                legalMoves.add(moveUp);
            }
            int xRight = x + 1;
            if (xRight < size) {
                contentOfTile = board[yUp][xRight];
                {
                    if (getColor(contentOfTile) == oppositeColor) {
                        Piece[][] attackRight = moveCharacter(board, x, y, xRight, yUp);
                        legalMoves.add(attackRight);
                    }
                }
            }
            int xLeft = x - 1;
            if (xLeft >= min) {
                contentOfTile = board[yUp][xLeft];
                {
                    if (getColor(contentOfTile) == oppositeColor) {
                        Piece[][] attackLeft = moveCharacter(board, x, y, xLeft, yUp);
                        legalMoves.add(attackLeft);
                    }
                }

            }
        }

    }
    
 

    private Piece[][] moveCharacter(Piece[][] board, int x1, int y1, int x2, int y2) {

        Piece[][] boardToBeReturned = new Piece[8][8];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardToBeReturned[i][j] = board[i][j];
            }
        }

        Piece pieceAtCurrentLocation = board[y1][x1];
        boardToBeReturned[y2][x2] = pieceAtCurrentLocation;
        boardToBeReturned[y1][x1] = Piece.EMPTY;

        return boardToBeReturned;
    }

    public Piece getColor(Piece piece) {
        if (piece.toString().charAt(0) == 'B') {
            return Piece.BLACK;
        }
        return Piece.WHITE;

    }

}
