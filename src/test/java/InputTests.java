import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A conversion of http://ecs.victoria.ac.nz/foswiki/pub/Courses/SWEN222_2017T2/LectureSchedule/AssExamples3.pdf
 * into a Unit Test
 */
public class InputTests {

    private Game game;
    private void init() {
        game = new Game();
    }

    public void assertGreenPieceAt(int x, int y, String name, Color color){
        assertTrue(game.greenPlayer.pieceCurrentlyPlayed(name) != null);
        assertTrue(game.greenPlayer.pieceCurrentlyPlayed(name).getX() == x);
        assertTrue(game.greenPlayer.pieceCurrentlyPlayed(name).getY() == y);
    }

    public void assertYellowPieceAt(int x, int y, String name, Color color){
        assertTrue(game.yellowPlayer.pieceCurrentlyPlayed(name) != null);
        assertTrue(game.yellowPlayer.pieceCurrentlyPlayed(name).getX() == x);
        assertTrue(game.yellowPlayer.pieceCurrentlyPlayed(name).getY() == y);
    }

    @Test
    public void testExampleOne() throws InterruptedException {
        init();

        game.parseCreatePiece(new Scanner("create e 0\n"), game.yellowPlayer);
        game.parseStageTwo(new Scanner("pass\n"), game.yellowPlayer, new ArrayList<>() );

        assertYellowPieceAt(7,7,"E", Color.YELLOW);

    }


    @Test
    public void testExampleTwo() throws InterruptedException {
        init();

        game.parseCreatePiece(new Scanner("create e 0\n"), game.yellowPlayer);
        game.parseStageTwo(new Scanner("e\nmove down\n"), game.yellowPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("pass\n"), game.yellowPlayer, new ArrayList<>() );

        game.parseCreatePiece(new Scanner("create a 90\n"), game.greenPlayer);
        game.parseStageTwo(new Scanner("a\nmove right\n"), game.greenPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("pass\n"), game.greenPlayer, new ArrayList<>());

        assertGreenPieceAt(3,2,"a", Color.GREEN);

        game.parseCreatePiece(new Scanner("create x 180\n"), game.yellowPlayer);
        game.parseStageTwo(new Scanner("x\nmove down\n"), game.yellowPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("pass\n"), game.yellowPlayer, new ArrayList<>());


        assertYellowPieceAt(7,8,"x", Color.YELLOW);

        game.parseCreatePiece(new Scanner("create f 0\n"), game.greenPlayer);
        game.parseStageTwo(new Scanner("f\nmove right\n"), game.greenPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("a\nmove down\n"), game.greenPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("pass\n"), game.greenPlayer, new ArrayList<>());

        assertGreenPieceAt(5,3,"a", Color.GREEN);
        assertGreenPieceAt(3,2,"f", Color.GREEN);

        game.drawGrid(game.board.getGrid());

        game.parseCreatePiece(new Scanner("create C 0\n"), game.yellowPlayer);
        game.drawGrid(game.board.getGrid());
        game.parseStageTwo(new Scanner("e\nmove up\n"), game.yellowPlayer, new ArrayList<>() );
        game.drawGrid(game.board.getGrid());
        game.parseStageTwo(new Scanner("x\nmove down\n"), game.yellowPlayer, new ArrayList<>() );
        game.drawGrid(game.board.getGrid());
        game.parseStageTwo(new Scanner("c\nmove up\n"), game.yellowPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("pass\n"), game.yellowPlayer, new ArrayList<>());

        game.drawGrid(game.board.getGrid());

        assertYellowPieceAt(7,5, "c", Color.YELLOW);
        assertYellowPieceAt(7,8,"x", Color.YELLOW);
        assertYellowPieceAt(7,9,"e", Color.YELLOW);
    }


    @Test
    public void testExampleThree() throws InterruptedException {
        init();

        game.yellowPlayer.createPiece(game.board, game.yellowPlayer.makePiece("m", 180), 5, 3);
        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("f", 0), 4, 4);
        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("b", 0), 6, 4);
        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("a", 0), 5, 5);

        game.currentTurn = game.greenPlayer;

        game.parseStageTwo(new Scanner("f\nmove right\n0\n1\n"), game.greenPlayer, new ArrayList<>() );

        assertGreenPieceAt(7,4,"b",Color.GREEN);
        assertGreenPieceAt(5,5,"a" ,Color.GREEN);
    }


    @Test
    public void testExampleFour() throws InterruptedException {
        init();

        game.yellowPlayer.createPiece(game.board, game.yellowPlayer.makePiece("h", 90), 2, 0);
        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("c", 0), 5, 1);
        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("a", 270), 5, 5);

        game.currentTurn = game.greenPlayer;

        game.drawGrid(game.board.getGrid());


        game.parseCreatePiece(new Scanner("create F 0\n"), game.greenPlayer);
        game.drawGrid(game.board.getGrid());
        game.parseStageTwo(new Scanner("f\nmove up\n"), game.greenPlayer, new ArrayList<>() );


        assertEquals(game.hasWon(), game.yellowPlayer);
    }



//    private Player findPlayer(PieceColor color) {
//        return game.getPlayers().stream().filter(player -> player.getColor()==color).findFirst().get();
//    }
}
