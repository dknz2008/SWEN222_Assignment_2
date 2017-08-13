import java.util.ArrayList;
import java.util.List;

/**
 * Created by TML_TEST on 25/07/2017.
 */
public class Player {

    List<Piece> barracks;
    List<Piece> inPlay;
    List<Piece> cemetery;
    Color color;
    int creationTileX;
    int creationTileY;

    public Player(int creationTileX, int creationTileY, Color color) {
        this.creationTileX = creationTileX;
        this.creationTileY = creationTileY;
        this.color = color;
        barracks = new ArrayList<>();
        inPlay = new ArrayList<>();
        cemetery = new ArrayList<>();
    }


    /**
     * Whether the piece has been placed on the board or not.
     * Returns the piece that is currently in play, or null
     * if piece does not exist.
     * @return Piece
     */

    public Piece pieceCurrentlyPlayed(String name){
        for(Piece p: inPlay){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Sends selected piece to cemetery
     * @param p the piece being sent to cemetery
     * @param board the board the piece comes from
     */
    public void sendToCemetery(Piece p, Board board){
        inPlay.remove(p);
        cemetery.add(p);
        board.getGrid()[p.getY()][p.getX()] = null;
        p.setX(null);
        p.setY(null);
    }

    /**
     * Finds piece in players barracks
     * @param name the name of the piece
     * @return Piece
     */

    public Piece findPiece(String name){
        for(Piece p: barracks){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }

    public List<Piece> getBarracks() {
        return barracks;
    }

    /**
     * Checks if the creation tile is free for the player
     * @param board the game board
     * @return boolean
     */
    public boolean isCreationTileFree(Board board){
        return board.checkIfPositionIsEmpty(creationTileX, creationTileY);
    }

    /**
     * Returns piece with correct orientation
     * @param name name of piece
     * @param rotation amount the piece should be rotated by
     * @return Piece
     */
    public Piece makePiece(String name, int rotation){
        Piece p = findPiece(name.toUpperCase());
        if(p == null){
            return null;
        }

        return p.rotate(rotation);
    }

    /**
     * Puts piece onto creation tile
     * @param board the Board
     * @param p the piece you're creating
     *
     */
    public void createPieceOnBoard(Board board, Piece p){
        createPiece(board, p, creationTileX, creationTileY);
    }

    /**
     * Creates the piece and adds it to the specified x and y coordinates
     * as well as puts it in play and removes from barracks.
     * @param board the Board
     * @param p the Piece
     * @param x x coordinate
     * @param y y coordinate
     */
    public void createPiece(Board board, Piece p, int x, int y){
        board.getGrid()[y][x] = p;
        p.setX(x);
        p.setY(y);
        barracks.remove(p);
        inPlay.add(p);
    }

    public List<Piece> getInPlay() {
        return inPlay;
    }


    /**
     * Populates list of pieces with the 24 possible pieces
     */
    public void populatePieceList(){
        char name;

        //setting characters to be capital for yellow player and non capital for green 
        if(this.color == Color.YELLOW){
            name = 'A';
        }else{
            name = 'a';
        }

        barracks.add(new Piece(Piece.Type.SWORD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, Character.toString((name++)), this));
        barracks.add(new Piece(Piece.Type.SWORD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, Character.toString(name++) , this));
        barracks.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SHIELD, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, Character.toString(name++) , this));
        barracks.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this ));
        barracks.add(new Piece(Piece.Type.SWORD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, Character.toString(name++), this ));
        barracks.add(new Piece(Piece.Type.SWORD, Piece.Type.SWORD, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SWORD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SHIELD, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SWORD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SWORD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, Character.toString(name++), this));
        barracks.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SHIELD, Color.YELLOW, Character.toString(name++), this));
    }

}
