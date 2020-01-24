/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.bot;

import chess.engine.GameState;
import chess.rules.Piece;

/**
 *
 * @author htomi
 */
public class TiraBot implements ChessBot{
    
    public Piece[][] board;
    public Piece[] blackPawns;
  
    
    public TiraBot(){
        
        board = new Piece[][]{
            {Piece.WROOK,Piece.WKNIGHT,Piece.WBISHOP,Piece.WQUEEN,Piece.WKING,Piece.WBISHOP,Piece.WKNIGHT,Piece.WROOK},
            {Piece.WPAWN,Piece.WPAWN,Piece.WPAWN,Piece.WPAWN,Piece.WPAWN,Piece.WPAWN,Piece.WPAWN,Piece.WPAWN},
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.BPAWN,Piece.BPAWN,Piece.BPAWN,Piece.BPAWN,Piece.BPAWN,Piece.BPAWN,Piece.BPAWN,Piece.BPAWN},
            {Piece.BROOK,Piece.BKNIGHT,Piece.BBISHOP,Piece.BQUEEN,Piece.BKING,Piece.BBISHOP,Piece.BKNIGHT,Piece.BROOK}      
        };  
    }
    
    
    
    
    @Override
    public String nextMove(GameState gs){
        return"e1e2";
    }
    
    
    public void updateBoard(String move){
        
        int x1 = move.charAt(0)-97;
        int y1 = move.charAt(1)-49;
        
        int x2 = move.charAt(2)-97;
        int y2 = move.charAt(3)-49;
        
  
        Piece PieceToBeMoved = board[y1][x1];
        
        
        board[y1][x1] = Piece.EMPTY;
        board[y2][x2] = PieceToBeMoved;
             
    }
    
}
