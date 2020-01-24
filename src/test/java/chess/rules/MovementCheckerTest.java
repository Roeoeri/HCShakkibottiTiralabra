/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author htomi
 */
public class MovementCheckerTest {
    
    MovementChecker checker;
    
    public MovementCheckerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        checker = new MovementChecker();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:testBoard
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    public void whitePawnMovesCorrectlyWhenThereIsRoomToMove(){
        Piece[][] testBoard = new Piece[][]{
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.EMPTY,Piece.EMPTY,Piece.WPAWN,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
            {Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY,Piece.EMPTY},
        };
        
        ArrayList<Piece[][]> result = checker.getLegalMoves(testBoard, true);
        
        Piece[][] resultBoard = result.get(0);
     
        assertTrue(resultBoard[3][2] == Piece.EMPTY && resultBoard[4][2] == Piece.WPAWN);
        
        
    }
}
