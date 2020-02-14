package chess.bot;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import chess.rules.Piece;

public class TiraBotTest {
	
	private TiraBot bot;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bot = new TiraBot();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void minimaxReturnsCorrectPlayInObviousSituation() {
        Piece[][] testBoard = new Piece[][]{
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.WPAWN, Piece.WKING, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.BKING, Piece.EMPTY, Piece.BPAWN, Piece.BPAWN, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},};
            
       Piece[][] move  = bot.getBestWhiteMove(testBoard);
       Boolean bKingGetsEaten = true;
       for(int i = 0; i<8; i++) {
    	   for(int j = 0; j<8; j++) {
    		   if(move[i][j] == Piece.BKING) {
    			   bKingGetsEaten = false;
    		   }
    	   }
       }
       assertEquals(bKingGetsEaten,true);
            
	}
}
