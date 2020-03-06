package chess.bot;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import chess.rules.Piece;
import datastructureproject.PerformanceTest;

public class TiraBotTest {
	
	private TiraBot bot;
	private PerformanceTest test;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bot = new TiraBot(3);
		test = new PerformanceTest(3);
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
	
	@Test
	public void botParsesMovesCorrectly() {
		
		Piece[][] startBoard = new Piece[][]{
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.WPAWN, Piece.WKING, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.BKING, Piece.EMPTY, Piece.BPAWN, Piece.BPAWN, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},};
            
        Piece[][] endBoard = new Piece[][]{
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.WPAWN, Piece.WKING, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.BPAWN, Piece.BPAWN, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.BKING, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},};
                
        
        String UCIMove = bot.parseMove(startBoard, endBoard);
        
        assertEquals(UCIMove,"b5b6");	
	}
	
	@Test
	public void botGeneratesPlayInAReasonableAmountOfTime() {

		assertTrue(test.averageTimeOfNPlays(100) < 1);
		
	}		
}
