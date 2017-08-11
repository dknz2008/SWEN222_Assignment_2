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

        assertYellowPieceAt(2,2,"E", Color.YELLOW);

    }


    @Test
    public void testExampleTwo() throws InterruptedException {
        init();

        game.parseCreatePiece(new Scanner("create a 90\n"), game.yellowPlayer);
        game.parseStageTwo(new Scanner("a\nmove right\n"), game.yellowPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("pass\n"), game.yellowPlayer, new ArrayList<>());

        assertYellowPieceAt(3,2,"a", Color.YELLOW);


        game.parseCreatePiece(new Scanner("create w 180\n"), game.greenPlayer);
        game.parseStageTwo(new Scanner("w\nmove down\n"), game.greenPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("pass\n"), game.greenPlayer, new ArrayList<>());

        assertGreenPieceAt(7,8,"w", Color.GREEN);

        game.parseCreatePiece(new Scanner("create f 0\n"), game.yellowPlayer);
        game.parseStageTwo(new Scanner("f\nmove right\n"), game.yellowPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("a\nmove down\n"), game.yellowPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("pass\n"), game.yellowPlayer, new ArrayList<>());

        assertYellowPieceAt(5,3,"a", Color.YELLOW);
        assertYellowPieceAt(3,2,"f", Color.YELLOW);

        game.drawGrid(game.board.getGrid());

        game.parseCreatePiece(new Scanner("create C 0\n"), game.greenPlayer);
        game.parseStageTwo(new Scanner("e\nmove up\n"), game.greenPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("w\nmove down\n"), game.greenPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("c\nmove up\n"), game.greenPlayer, new ArrayList<>() );
        game.parseStageTwo(new Scanner("pass\n"), game.greenPlayer, new ArrayList<>());

        game.drawGrid(game.board.getGrid());

         assertYellowPieceAt(7,5,'C', YELLOW);
  assertPieceAt(7,8,'W', YELLOW);
        assertPieceAt(7,9,'E', YELLOW);


//        runCommand("create C 0");
//        runCommand("move E up");
//        runCommand("move W down");
//        runCommand("move C up");


//        assertPieceAt(7,5,'C', YELLOW);
//        assertPieceAt(7,8,'W', YELLOW);
//        assertPieceAt(7,9,'E', YELLOW);
    }


//    @Test
//    public void testExampleTwo() throws InterruptedException {
//        init();
//
//        createPiece(5,3,'M',180, YELLOW);
//        createPiece(4,4,'F',0, GREEN);
//        createPiece(6,4,'B',0, GREEN);
//        createPiece(5,5,'A',0, GREEN);
//        game.setCurrentPlayer(findPlayer(GREEN));
//        handler.addActionToSupply(Direction.RIGHT);
//        handler.addActionToSupply(Direction.UP);
//        handler.runCommand("move F right");
//        assertPieceAt(7,4,'B',PieceColor.GREEN);
//        assertPieceAt(5,5,'A',PieceColor.GREEN);
//    }
//    @Test(expected = InfiniteActionException.class)
//    public void testExampleThree() throws InterruptedException, InfiniteActionException {
//        init();
//
//        createPiece(5,1,'C',0, GREEN);
//        createPiece(5,3,'F',0, GREEN);
//        createPiece(5,5,'A',270, GREEN);
//        Piece toMove = createPiece(6,4,'M',180, YELLOW);
//        game.setCurrentPlayer(findPlayer(YELLOW));
//        new Move(game,gui,toMove,Direction.LEFT,new HashSet<>(),new ArrayList<>()).commit();
//    }
//    @Test
//    public void testExampleFour() throws InterruptedException {
//        init();
//        createPiece(2,0,'H',90, YELLOW);
//        createPiece(5,1,'C',0, GREEN);
//        createPiece(5,5,'A',270, GREEN);
//        game.setCurrentPlayer(findPlayer(GREEN));
//        handler.runCommand("create F 0");
//        handler.runCommand("move F up");
//        assertEquals(TurnStatus.WIN,game.getStatus());
//    }
//    private Piece createPiece(int x, int y, char c, int rotation, PieceColor color) {
//        Piece p = findPlayer(color).spawnPiece(c,rotation);
//        p.setLocation(new Location(x,y));
//        game.getPieces().add(p);
//        return p;
//    }
//    private void assertPieceAt(int x, int y, char c, PieceColor color) {
//
//        assertEquals(new Location(x,y),findPlayer(color).getPieceOnBoard(c).getLocation());
//    }
//    private Player findPlayer(PieceColor color) {
//        return game.getPlayers().stream().filter(player -> player.getColor()==color).findFirst().get();
//    }
}
