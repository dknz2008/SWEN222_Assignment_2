import org.junit.Assert.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests to check validity of entire game.
 */
public class Tests {



    @Test
    public void testBasicReactions(){
        Game game = new Game();
    }


    /**
     * Test recursively shifting pieces down the board (in the case where sword meets shield
     */
    @Test
    public void testSwordMeetsShieldReaction1(){
        Game game = new Game();
        Piece p1 = game.greenPlayer.makePiece("C", 0); //Shield top and bottom
        Piece p2 =  game.greenPlayer.makePiece("D", 0); //Sword top, shield bottom
        Piece p3 =  game.greenPlayer.makePiece("B", 0); //Sword top, sword bottom
        game.board.addToGrid(4, 1, p1);
        game.board.addToGrid(4, 3, p2);
//        game.board.addToGrid(4, 5, p3);
        game.drawGrid(game.board.getGrid());
        p1.move("down", game.board);
        game.drawGrid(game.board.getGrid());
        assertTrue(game.board.getGrid()[2][4] == p1);
        assertTrue(game.board.getGrid()[4][4] == p2);
        game.drawGrid(game.board.getGrid());
//        System.out.println("x: " + p3.getX() + "y: " + p3.getY());
//        assertTrue(game.board.getGrid()[4][6] == p3);

    }


    /**
     * Testing a 0 degree rotation
     */
    @Test
    public void testRotate0() {
        Game game = new Game();
        Piece p = game.greenPlayer.findPiece("A");
        p.rotate(0);
        assert p.getRight() == Piece.Type.SHIELD;
    }

    /**
     * Testing a 90 degree rotation
     */
    @Test
    public void testRotate90() {
        Game game = new Game();
        Piece p = game.greenPlayer.findPiece("A");
        p.rotate(90);
        assert p.getRight() == Piece.Type.SWORD;
    }

    /**
     * Testing a 180 degree rotation
     */
    @Test
    public void testRotate180() {
        Game game = new Game();
        Piece p = game.greenPlayer.findPiece("A");
        p.rotate(180);
        assert p.getBottom() == Piece.Type.SWORD;
    }

    /**
     * Testing a 270 degree rotation
     */
    @Test
    public void testRotate270() {
        Game game = new Game();
        Piece p = game.greenPlayer.findPiece("A");
        p.rotate(270);
        assert p.getBottom() == Piece.Type.SWORD;
    }


    /**
     * Finding valid piece
     */
    @Test
    public void testFindValidPiece() {
        Game game = new Game();
        assert game.greenPlayer.findPiece("A") != null;
    }


    /**
     * finding invalid piece
     */
    @Test
    public void testFindInvalidPiece() {
        Game game = new Game();
        assert game.greenPlayer.findPiece("FAKEPIECE") == null;
    }


    /**
     * If it has a piece the piece will not be null at that position
     */
    @Test
    public void testValidPlacePiece_1() {
        Game game = new Game();
        game.board.addToGrid(game.greenPlayer.creationTileX, game.greenPlayer.creationTileY, game.greenPlayer.makePiece("A", 0));
        assert game.board.getGrid()[game.greenPlayer.creationTileY][game.greenPlayer.creationTileX] != null;
    }


    /**
     * Empty position - no piece placed yet
     */
    @Test
    public void testEmptyPosition_1() {
        Game game = new Game();
        assert game.board.getGrid()[game.greenPlayer.creationTileY][game.greenPlayer.creationTileX] == null;
    }


    /**
     * Testing invalid placement positions for yellow
     */
    @Test
    public void testInvalidPlacePiece_1() {
        Game game = new Game();
        Piece p1 = game.yellowPlayer.makePiece("A", 0);
        Piece p2 = game.yellowPlayer.makePiece("B", 0);
        Piece p3 = game.yellowPlayer.makePiece("C", 0);
        Piece p4 = game.yellowPlayer.makePiece("D", 0);
        game.board.addToGrid(0, 0, p1);
        game.board.addToGrid(1, 0, p2);
        game.board.addToGrid(0, 1, p3);
        game.board.addToGrid(1, 1, p4);
        game.board.addToGrid(3, 3, p4);
        assert game.board.getGrid()[0][0] != p1;
        assert game.board.getGrid()[0][1] != p2;
        assert game.board.getGrid()[1][0] != p3;
        assert game.board.getGrid()[1][1] != p4;
        assert game.board.getGrid()[3][3] == p4;
    }


    /**
     * Testing invalid placement positions for Green
     */
    @Test
    public void testInvalidPlacePiece_2() {
        Game game = new Game();
        Piece p1 = game.greenPlayer.makePiece("A", 0);
        Piece p2 = game.greenPlayer.makePiece("B", 0);
        Piece p3 = game.greenPlayer.makePiece("C", 0);
        Piece p4 = game.greenPlayer.makePiece("D", 0);
        game.board.addToGrid(9, 9, p1);
        game.board.addToGrid(9, 8, p2);
        game.board.addToGrid(8, 9, p3);
        game.board.addToGrid(8, 8, p4);
        assert game.board.getGrid()[9][9] != p1;
        assert game.board.getGrid()[8][9] != p2;
        assert game.board.getGrid()[9][8] != p3;
        assert game.board.getGrid()[8][8] != p4;
    }

//    @Test
//    public void testInvalidPlacePiece_2() {
//        Game game = new Game();
//        Piece p = game.greenPlayer.makePiece("A", 0);
//        game.board.addToGrid(1, 0, p);
//        assert game.board.getGrid()[0][0] != p;
//    }


}