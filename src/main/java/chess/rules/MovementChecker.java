/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;


import chess.datastructures.ChessList;

/**
 *
 * @author htomi
 */
public class MovementChecker {

    private ChessList<Piece[][]> legalMoves;
    private final int size = 8;
    private final int min = 0;

    
    /** Palauttaa listan laillisista siirroista joita on saatavilla annetusta pelitilanteesta.
     * @param board Pelitilanne, jota halutaan tarkastella.
     * @param isWhite true tarkoittaa, että tarkastellaan valkoisia siirtoja, false puolestaan mustia.
     * @return ChessList<Piece[][]> Lista laillisista siirroista.
     */
    public ChessList<Piece[][]> getLegalMoves(Piece[][] board, boolean isWhite) {
        legalMoves = new ChessList<Piece[][]>();
        if (isWhite) {
            getLegalWhiteMoves(board);
        } else {
            getLegalBlackMoves(board);
        }
        return legalMoves;
    }

    
    /** Lisää luokan sisäiseen listaan laillisia valkoisen siirtoja annetusta pelitilanteesta.
     * @param board Pelitilanne jota halutaan tarkastella.
     */
    private void getLegalWhiteMoves(Piece[][] board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Piece currentSquare = board[i][j];
                switch (currentSquare) {

                    case WPAWN:
                        getLegalPawnMoves(board,j,i);
                        break;

                    case WROOK:
                        getLegalRookMoves(board,j,i);
                        break;

                    case WBISHOP:
                        getLegalBishopMoves(board,j,i);
                        break;

                    case WQUEEN:
                        getLegalQueenMoves(board,j,i);
                        break;

                    case WKING:
                        getLegalKingMoves(board,j,i);
                        break;
                    
                    case WKNIGHT:
                    	getLegalKnightMoves(board,j,i);
                    	break;

                }

            }
        }

    }

    
    /** Lisää luokan sisäiseen listaan laillisia mustan siirtoja annetusta pelitilanteesta.
     * @param board Pelitilanne jota halutaan tarkastella.
     */
    private void getLegalBlackMoves(Piece[][] board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Piece currentSquare = board[i][j];
                switch (currentSquare) {

                    case BPAWN:
                        getLegalPawnMoves(board,j,i);
                        break;

                    case BROOK:
                        getLegalRookMoves(board,j,i);
                        break;

                    case BBISHOP:
                        getLegalBishopMoves(board,j,i);
                        break;

                    case BQUEEN:
                        getLegalQueenMoves(board,j,i);
                        break;

                    case BKING:
                        getLegalKingMoves(board,j,i);
                        break;
                     
                    case BKNIGHT:
                    	getLegalKnightMoves(board,j,i);
                    	break;
                }

            }
        }
    }

    
    /**  Tarkistaa onko annetuissa koordinaateissa sotilasta, joka voi liikkua kaksi askelta. Jos on niin lisätään se listaan.
     * @param board Pelitilanne jota halutaan tarkastella.
     * @param x Sotilaan sarake
     * @param y Sotilaan rivi
     */
    private void getTwoSquarePawnMove(Piece[][] board, int x, int y){
        Piece pawn = board[y][x];
     
        if(pawn == Piece.BPAWN && y == 6){
            if(board[y-1][x] == Piece.EMPTY && board[y-2][x] == Piece.EMPTY){
                Piece[][] move = moveCharacter(board, x, y, x, y -2);
                legalMoves.add(move);
            }
        }
        if(pawn == Piece.WPAWN && y == 1){
            if(board[y + 1][x] == Piece.EMPTY && board[y + 2][x] == Piece.EMPTY){
                Piece[][] move = moveCharacter(board, x, y, x, y + 2);
                legalMoves.add(move);
            }
        }

    }

    
    /** Lisää listaan normaalitilanteissa sallittuja sotilaan liikkeitä.
     * @param board Pelitilanne, jota halutaan tarkastella.
     * @param x  Sotilaan sarake
     * @param y  Sotilaan rivi
     */
    private void getReqularPawnMoves(Piece[][] board, int x, int y) {
        Piece pawn = board[y][x];
        Piece oppositeColor = getOppositeColor(pawn);

        int pawnStep = 1;
        if (board[y][x] == Piece.BPAWN) {
            pawnStep = -1;
        }

        int yUp = y + pawnStep;
        if (yUp < size && yUp >= 0) {
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

    
    /** Lisää listaan kaikki sotilaiden lailliset siirrot.
     * @param board Pelitilanne, jota halutaan tarkastella
     * @param x Sotilaan sarake.
     * @param y Sotilaan rivi
     */
    private void getLegalPawnMoves(Piece[][] board , int x, int y){
        getTwoSquarePawnMove(board, x, y);
        getReqularPawnMoves(board, x, y); 
    }

    
    /** Lisää listaan sallitut tornin liikkeet
     * @param board Pelitilanne, jota halutaan tarkastella.
     * @param x Tornin sarake
     * @param y Tornin rivi
     */
    private void getLegalRookMoves(Piece[][] board, int x, int y) {

        Piece ownColor = getColor(board[y][x]);

        traverseUntilEnd(board, x, y, x + 1, y, 1, 0, ownColor);

        traverseUntilEnd(board, x, y, x - 1, y, -1, 0, ownColor);

        traverseUntilEnd(board, x, y, x, y + 1, 0, 1, ownColor);

        traverseUntilEnd(board, x, y, x, y - 1, 0, -1, ownColor);
    }

    
    /**  Lisää listaan sallitut lähetin liikkeet
     * @param board Pelitilanne, jota halutaan tarkastella
     * @param x Lähetin Sarake
     * @param y Lähetin Rivi
     */
    private void getLegalBishopMoves(Piece[][] board, int x, int y) {

        Piece ownColor = getColor(board[y][x]);

        traverseUntilEnd(board, x, y, x - 1, y - 1, -1, -1, ownColor);

        traverseUntilEnd(board, x, y, x + 1, y - 1, 1, -1, ownColor);

        traverseUntilEnd(board, x, y, x - 1, y + 1, -1, 1, ownColor);

        traverseUntilEnd(board, x, y, x + 1, y + 1, 1, 1, ownColor);
    }

    
    /** Lisää listaan sallitut kuningattaren liikkeet
     * @param board Pelitilanne jota halutaan tarkastella.
     * @param x Kuningattaren sarake.
     * @param y Kuningattaren rivi.
     */
    private void getLegalQueenMoves(Piece[][] board, int x, int y) {

        getLegalRookMoves(board, x, y);
        getLegalBishopMoves(board, x, y);
    }

    
    /** Lisää listaan sallitut kuninkaan liikkeet
     * @param board Pelitilanne jota halutaan tarkastella.
     * @param x Kuninkaan sarake
     * @param y Kuninkaan rivi
     */
    private void getLegalKingMoves(Piece[][] board, int x, int y) {

        Piece ownColor = getColor(board[y][x]);

        for (int y2 = y - 1; y2 <= y + 1; y2++) {
            for (int x2 = x - 1; x2 <= x + 1; x2++) {
                if (x2 >= size || x2 < min || y2 >= size || y2 < min) {
                    continue;
                }
                Piece destinationTileColor = getColor(board[y2][x2]);
                if (destinationTileColor == getOppositeColor(ownColor) || destinationTileColor == Piece.EMPTY) {
                    Piece[][] move = moveCharacter(board, x, y, x2, y2);
                    legalMoves.add(move);
                }
            }
        }
    }
    
    
    /**  Lisää listaan sallitut ratsun liikkeet
     * @param board Pelitilanne, jota halutaan tarkastella
     * @param x Ratsun sarake.
     * @param y Ratsun rivi.
     */
    private void getLegalKnightMoves(Piece[][] board, int x, int y) {
    	int[] possibleStepsInYCoordinate = new int[]{2,2,1,1,-1,-1,-2,-2};
    	int[] possibleStepsInXCoordinate = new int[]{-1,1,-2,2,-2,2,-1,1};
    	
    	int possibleFinalCoordinates = possibleStepsInYCoordinate.length;
    	
    	Piece oppositeColor = getOppositeColor(board[y][x]);
    	
    	for(int i = 0; i<possibleFinalCoordinates; i++) {
    		int newX = x + possibleStepsInXCoordinate[i];
    		int newY = y + possibleStepsInYCoordinate[i];
    		
    		if(newX >= size || newX < min || newY >= size || newY < min){
    			continue;
    		}
    		
    		Piece destinationTileColor = getColor(board[newY][newX]);
    		if(destinationTileColor == oppositeColor || destinationTileColor == Piece.EMPTY) {
    			Piece[][] move = moveCharacter(board,x,y,newX,newY);
    			legalMoves.add(move);
     		}
    	}
    	
    }

    
    /** Käy rekursiivisesti läpi ruutuja, jotka ovat vaaka- tai pystysuorassa tai vinossa aloitusruutun nähden
     * @param board Pelitilanne jota halutaan tarkastella.
     * @param startX Aloitussarake
     * @param startY Aloitusrivi
     * @param x uusi sarake
     * @param y uusi rivi
     * @param xSteps kuinka monta askelta otetaan seuraavalle sarakkeelle
     * @param ySteps kuinka mont askelta otetaan seuraavalle riville
     * @param ownColor Aloitusruudussa olleen nappulan väri.
     */
    private void traverseUntilEnd(Piece[][] board, int startX, int startY, int x, int y, int xSteps, int ySteps, Piece ownColor) {
        if (x >= size || x < min || y >= size || y < min) {
            return;
        }

        Piece tileColor = getColor(board[y][x]);

        if (tileColor == ownColor) {
            return;
        }

        if (tileColor == getOppositeColor(ownColor)) {
            Piece[][] attack = moveCharacter(board, startX, startY, x, y);
            legalMoves.add(attack);
            return;
        }

        Piece[][] moveToThisTile = moveCharacter(board, startX, startY, x, y);
        legalMoves.add(moveToThisTile);

        traverseUntilEnd(board, startX, startY, x + xSteps, y + ySteps, xSteps, ySteps, ownColor);
    }
    

    
    /** Luo uuden laudan, jossa nappula on siirretty alkupisteestä loppupisteeseen.
     * @param board Pelilauta
     * @param x1 Alkusarake
     * @param y1 Alkurivi
     * @param x2 Loppusarake
     * @param y2 Loppurivi
     * @return Piece[][] Uusi lauta jossa nappula on siirretty.
     */
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

    
    /** Palauttaa nappulan värin
     * @param piece Nappula
     * @return Piece Nappulan väri
     */
    public Piece getColor(Piece piece) {
        if (piece.toString().charAt(0) == 'B') {
            return Piece.BLACK;
        }
        if (piece.toString().charAt(0) == 'W') {
            return Piece.WHITE;
        }
        return Piece.EMPTY;

    }

    
    /** Palauttaa nappulan vastakkaisen värin
     * @param piece Nappula
     * @return Piece Vastakkainen väri.
     */
    public Piece getOppositeColor(Piece piece) {
        if (getColor(piece) == Piece.WHITE) {
            return Piece.BLACK;
        }
        if (getColor(piece) == Piece.BLACK) {
            return Piece.WHITE;
        }
        return Piece.EMPTY;
    }

}
