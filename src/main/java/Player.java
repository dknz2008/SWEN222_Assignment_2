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
     * Whether the piece has been placed on the board or not
     * @return
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

    public void sendToCemetery(Piece p, Board board){
        inPlay.remove(p);
        cemetery.add(p);
        board.getGrid()[p.getY()][p.getX()] = null;
        p.setX(null);
        p.setY(null);
    }

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

    public boolean isCreationTileFree(Board board){
        return board.checkIfPositionIsEmpty(creationTileX, creationTileY);
    }

    /**
     * Returns piece with correct orientation
     * @param name
     * @param rotation
     * @return
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
     * @param board
     * @param p
     * @return
     */
    public boolean createPieceOnBoard(Board board, Piece p){
        createPiece(board, p, creationTileX, creationTileY);
        return true;
    }

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
