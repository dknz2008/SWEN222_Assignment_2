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
        game.gameLoop();
    }



    public void assertPieceAt(int x, int y, String name, Color color){
        assertTrue(game.yellowPlayer.findPiece(name) != null);
        assertTrue(game.yellowPlayer.findPiece(name).getX() == x);
        assertTrue(game.yellowPlayer.findPiece(name).getY() == y);
    }

    @Test
    public void testExampleOne() throws InterruptedException {
        init();
//        ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());

//        game.parseTurn(game.yellowPlayer, new Scanner("y\ncreate e 0\ne\nmove down\npass\n"));
        game.parseCreatePiece(new Scanner("create a 0"), game.yellowPlayer);
        game.parseStageTwo(new Scanner("pass"), game.yellowPlayer, new ArrayList<>() );
//        game.parseTurn(game.yellowPlayer, new Scanner("y\ncreate e 0\npass\n"));

        assertPieceAt(2,2,"E", Color.YELLOW);

//        runCommand("create A 90");
//        runCommand("move A right");
//        runCommand("pass");
//
//        runCommand("create W 180");
//        runCommand("move W down");
//        runCommand("pass");
//
//        runCommand("create F 0");
//        runCommand("move F right");
//        runCommand("move A down");
//        runCommand("pass");
//
//        runCommand("create C 0");
//        runCommand("move E up");
//        runCommand("move W down");
//        runCommand("move C up");

//        assertPieceAt(3,2,'F', GREEN);
//        assertPieceAt(5,3,'A', GREEN);
//
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
