package chess.datastructures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.rules.Piece;

public class ChessListTest {
	
	private ChessList<Piece[][]> list;
	private Piece[][] testBoard; 

	@Before
	public void setUp() throws Exception {
		list = new ChessList();
		
		
		testBoard = new Piece[][]{
            {Piece.WROOK, Piece.WKNIGHT, Piece.WBISHOP, Piece.WQUEEN, Piece.WKING, Piece.WBISHOP, Piece.WKNIGHT, Piece.WROOK},
            {Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN, Piece.WPAWN},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN, Piece.BPAWN},
            {Piece.BROOK, Piece.BKNIGHT, Piece.BBISHOP, Piece.BQUEEN, Piece.BKING, Piece.BBISHOP, Piece.BKNIGHT, Piece.BROOK}
        };
	}

	@Test
	public void chessBoardCanBeAddedToList() {
        
        list.add(testBoard);
        assertTrue(list.size() == 1);
	}
	
	@Test
	public void multipleChessBoardCanBeAddedToList() {
		for(int i = 0; i< 2000; i++) {
			list.add(testBoard);
		}
		assertTrue(list.size() == 2000);
	}

}
