package datastructureproject;

import chess.bot.TiraBot;
import chess.engine.GameState;
import chess.rules.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Use this class to write performance tests for your bot.
 *
 */
public class PerformanceTest {

    private TiraBot bot;
    private List<GameState> gsList = new ArrayList();
    private Random rng;
    private Piece[] pieceCollection;

    public PerformanceTest(int searchDepth) {
        this.rng = new Random();
        bot = new TiraBot(searchDepth);
        pieceCollection = new Piece[]{Piece.BQUEEN, Piece.WQUEEN,
            Piece.BBISHOP, Piece.WBISHOP, Piece.BKNIGHT, Piece.WKNIGHT,
            Piece.BROOK, Piece.WROOK, Piece.BPAWN, Piece.WPAWN};
    }

    public void setGsList(List<GameState> gsList) {
        this.gsList = gsList;
    }

    public void printboard(Piece[][] board) {
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                System.out.print(" " + board[j][i]);
            }
            System.out.println("");
        }
    }

    public double averageTimeOfNPlays(int games) {

        double totalTime = 0;
        for (int i = 0; i < games + 1; i++) {

            Piece[][] testBoard = generateRandomTestBoard();

            long startTime = System.nanoTime();
            bot.getBestBlackMove(testBoard);
            long endTime = System.nanoTime();
            double durationBlack = (double) (endTime - startTime) / 1000000000;

            startTime = System.nanoTime();
            bot.getBestWhiteMove(generateRandomTestBoard());
            endTime = System.nanoTime();
            double durationWhite = (double) (endTime - startTime) / 1000000000;

            if (i == 0) {
                continue;
            }
            totalTime += durationBlack;
            totalTime += durationWhite;
        }
        double avg = totalTime / (games * 2);
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

        while (whiteKingX == blackKingX) {
            whiteKingX = rng.nextInt(8);
            blackKingX = rng.nextInt(8);
        }

        int whiteKingY = rng.nextInt(8);
        int blackKingY = rng.nextInt(8);

        while (whiteKingX == blackKingX) {
            whiteKingY = rng.nextInt(8);
            blackKingY = rng.nextInt(8);
        }

        testBoard[whiteKingY][whiteKingX] = Piece.WKING;
        testBoard[blackKingY][blackKingX] = Piece.BKING;

        int amountOfPieces = rng.nextInt(14);
        for (int i = 0; i < amountOfPieces; i++) {
            int x = rng.nextInt(8);
            int y = rng.nextInt(8);
            Piece randomPiece = pieceCollection[rng.nextInt(pieceCollection.length)];
            if (testBoard[y][x] != Piece.EMPTY) {
                i--;
                continue;
            }
            testBoard[y][x] = randomPiece;
        }

        return testBoard;
    }

    public static void main(String[] args) {

        for (int i = 1; i < 6; i++) {
            printResultsAtDepth(i);
        }

    }

    public static void printResultsAtDepth(int depth) {
        PerformanceTest test = new PerformanceTest(depth);
        System.out.println("Average times at search depth: " + depth);
        System.out.println("Average time to calculate a move during 10 random board positions " + test.averageTimeOfNPlays(10)
                + " seconds");
        System.out.println("Average time to calculate a move during 100 random board positions " + test.averageTimeOfNPlays(100)
                + " seconds");
        System.out.println("Average time to calculate a move during 1000 random board positions " + test.averageTimeOfNPlays(1000)
                + " seconds");
        System.out.println("----------------------");
    }

}
