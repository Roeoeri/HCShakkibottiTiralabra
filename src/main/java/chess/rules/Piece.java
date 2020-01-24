/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;

/**
 *
 * @author htomi
 */
public enum Piece {
    
    BPAWN,
    BROOK,
    BKNIGHT,
    BBISHOP,
    BQUEEN,
    BKING,
    WPAWN,
    WROOK,
    WKNIGHT,
    WBISHOP,
    WQUEEN,
    WKING,
    EMPTY,
    BLACK,
    WHITE;
    
    public Piece getColor(Piece piece){
        if(piece.toString().charAt(0) == 'B'){
            return this.BLACK;
        }
        return this.WHITE;
        
    }
       
}
