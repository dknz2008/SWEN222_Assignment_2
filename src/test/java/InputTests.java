import org.junit.Test;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * conversion's of http://ecs.victoria.ac.nz/foswiki/pub/Courses/SWEN222_2017T2/LectureSchedule/AssExamples3.pdf
 * into a Unit Tests
 */
public class InputTests {

    private Game game;
    private void init() {
        game = new Game();
    }

    /**
     * Asserts green piece is at correct position
     * @param x x position,
     * @param y y position,
     * @param name name that is associated with piece,
     * @param color the color that the piece belongs to
     */
    public void assertGreenPieceAt(int x, int y, String name, Color color){
        assertTrue(game.greenPlayer.pieceCurrentlyPlayed(name) != null);
        assertTrue(game.greenPlayer.pieceCurrentlyPlayed(name).getX() == x);
        assertTrue(game.greenPlayer.pieceCurrentlyPlayed(name).getY() == y);
    }

    /**
     * Asserts green piece is at correct position
     * @param x x position,
     * @param y y position,
     * @param name name that is associated with piece,
     * @param color the color that the piece belongs to
     */
    public void assertYellowPieceAt(int x, int y, String name, Color color){
        assertTrue(game.yellowPlayer.pieceCurrentlyPlayed(name) != null);
        assertTrue(game.yellowPlayer.pieceCurrentlyPlayed(name).getX() == x);
        assertTrue(game.yellowPlayer.pieceCurrentlyPlayed(name).getY() == y);
    }

    /**
     * Tests a simple creation phase
     *
     */
    @Test
    public void testExampleOne(){
        init();

        game.parseCreatePiece(new Scanner("create e 0\n"), game.yellowPlayer);
        game.parseStageTwo(new Scanner("pass\n"), game.yellowPlayer, new ArrayList<>() );
        assertYellowPieceAt(7,7,"E", Color.YELLOW);

    }


    /**
     * tests multiple creation phases as well as reactions between two pieces and
     * pieces pushing other pieces (test is based on examples file)
     */
    @Test
    public void testExampleTwo(){
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


    /**
     * tests multiple reactions, supplying the input for what reaction should be done first,
     * one at a time
     *
     */
    @Test
    public void testExampleThree() {
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


    /**
     * Tests the win condition when sword is against face
     */
    @Test
    public void testExampleFour(){
        init();

        game.yellowPlayer.createPiece(game.board, game.yellowPlayer.makePiece("h", 90), 2, 0);
        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("c", 0), 5, 1);
        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("a", 270), 5, 5);

        game.currentTurn = game.greenPlayer;

        game.drawGrid(game.board.getGrid());


        game.parseCreatePiece(new Scanner("create f 0\n"), game.greenPlayer);
        game.drawGrid(game.board.getGrid());
        game.parseStageTwo(new Scanner("f\nmove up\n"), game.greenPlayer, new ArrayList<>() );


        assertEquals(game.hasWon(), game.yellowPlayer);
    }


    /**
     * Test shifting multiple pieces down the board (in the case where sword meets shield)
     */
    @Test
    public void testSwordMeetsShieldReaction1(){
        Game game = new Game();

        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("c", 0), 4, 1); //Shield top and bottom
        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("d", 0), 4, 3);   //Sword top, shield bottom
//        game.greenPlayer.createPiece(game.board, game.greenPlayer.makePiece("b", 0), 4, 5); //Sword top, sword bottom

        game.drawGrid(game.board.getGrid());

        game.currentTurn = game.greenPlayer;

        game.parseStageTwo(new Scanner("c\nmove down\n"), game.greenPlayer, new ArrayList<>() );

        game.drawGrid(game.board.getGrid());

        assertGreenPieceAt(4,2, "c", Color.GREEN);
//        assertGreenPieceAt(4,4, "d", Color.GREEN);


    }


}
