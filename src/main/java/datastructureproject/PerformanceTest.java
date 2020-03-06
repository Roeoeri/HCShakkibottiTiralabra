package datastructureproject;

import chess.bot.ChessBot;
import chess.bot.TiraBot;
import chess.datastructures.ChessList;
import chess.engine.GameState;
import chess.rules.MovementChecker;
import chess.rules.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Ticker;

/**
 * Use this class to write performance tests for your bot.
 * 
 */
public class PerformanceTest {

    private TiraBot bot;
    private List<GameState> gsList = new ArrayList();
    private Random rng;
    private Piece[] pieceCollection; 

    public PerformanceTest(int searchDepth){
        this.rng = new Random();
        bot = new TiraBot(searchDepth);
        pieceCollection = new Piece[] {Piece.BQUEEN,Piece.WQUEEN,
            Piece.BBISHOP,Piece.WBISHOP,Piece.BKNIGHT,Piece.WKNIGHT,
            Piece.BROOK,Piece.WROOK,Piece.BPAWN,Piece.WPAWN};
    }

    public void setGsList(List<GameState> gsList) {
        this.gsList = gsList;
    }

    public void printboard(Piece[][] board){
        for(int j = 0; j<8; j++){
            for(int i = 0; i< 8; i++){
                System.out.print(" " +  board[j][i]);
            }
            System.out.println("");
        }
    }

    public double averageTimeOfNPlays(int games){

        double totalTime = 0;
        for(int i = 0; i< games+1; i++) {
            System.out.println("Round " + i + "out of " + games);

            Piece[][] testBoard = generateRandomTestBoard();
            
			long startTime = System.nanoTime();
			bot.getBestBlackMove(testBoard);
            long endTime = System.nanoTime();
            double durationBlack = (double) (endTime - startTime)/1000000000;
            System.out.println("Black took " + durationBlack);
            if(durationBlack > 10){
                printboard(testBoard);
            }

            startTime = System.nanoTime();
            bot.getBestWhiteMove(generateRandomTestBoard());
            endTime = System.nanoTime();
            double durationWhite = (double) (endTime - startTime)/1000000000;
            System.out.println("White took " + durationWhite);
            if(durationWhite > 10){
                printboard(testBoard);
            }

            System.out.println("--------");
            
            if(i == 0){
                continue;
            }
            totalTime += durationBlack;
            totalTime += durationWhite;
        }
        double avg = totalTime/(games*2);
        return avg;
    }


    public Piece[][] generateRandomTestBoard() {
		
		Piece[][] testBoard = new Piece[][]{
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
            {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},};

            int whiteKingX = rng.nextInt(8);
            int blackKingX = rng.nextInt(8);

            while(whiteKingX == blackKingX){
                whiteKingX = rng.nextInt(8);
                blackKingX = rng.nextInt(8);
            }


            int whiteKingY = rng.nextInt(8);
            int blackKingY = rng.nextInt(8);

            while(whiteKingX == blackKingX){
                whiteKingY = rng.nextInt(8);
                blackKingY = rng.nextInt(8);
            }

            testBoard[whiteKingY][whiteKingX] = Piece.WKING;
            testBoard[blackKingY][blackKingX] = Piece.BKING;
 		
			int amountOfPieces = rng.nextInt(14);
			for(int i = 0; i< amountOfPieces; i++) {
				int x = rng.nextInt(8);
				int y = rng.nextInt(8);
                Piece randomPiece = pieceCollection[rng.nextInt(pieceCollection.length)];
                if(testBoard[y][x] != Piece.EMPTY){
                    i--;
                    continue;
                }
				testBoard[y][x] = randomPiece;
			}
			
			return testBoard;
	}


    public static void main(String[] args) {
        PerformanceTest test = new PerformanceTest(3);
        System.out.println("Average time to calculate a move during 10 random board positions" + test.averageTimeOfNPlays(10));
        System.out.println("Average time to calculate a move during 100 random board positions" + test.averageTimeOfNPlays(100));
        System.out.println("Average time to calculate a move during 1000 random board positions" + test.averageTimeOfNPlays(1000));
        /*
        Set your bot and tests here.
        */
    }

}
