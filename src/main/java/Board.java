import java.util.ArrayList;
import java.util.List;

/**
 * Created by TML_TEST on 25/07/2017.
 */
public class Board {

    private Piece[][] grid;

    private Piece emptytile = new Piece(null, null, null, null, null, "emptytile", null);
    private Piece yellowface = new Piece(null, null, null, null, null, "yellowface", null);
    private Piece greenface = new Piece(null, null, null, null, null, "greenface", null);

    private List<Piece> yellowPieces;
    private List<Piece> yellowCemetery;
    private List<Piece> greenPieces;
    private List<Piece> greenCemetery;

    public Piece getGreenface() {
        return greenface;
    }

    public Piece getYellowface() {
        return yellowface;
    }

    public Piece getEmptytile() {
        return emptytile;
    }

    public Board(){
        grid  = new Piece[10][10];
        grid[0][0] = emptytile;
        grid[0][1] = emptytile;
        grid[1][0] = emptytile;
        grid[1][1] = greenface;

        grid[9][9] = emptytile;
        grid[8][9] = emptytile;
        grid[9][8] = emptytile;
        grid[8][8] = yellowface;

    }
    
    public List<Piece> getGreenCemetery() {
        return greenCemetery;
    }

    public List<Piece> getGreenPieces() {
        return greenPieces;
    }

    public List<Piece> getYellowCemetery() {
        return yellowCemetery;
    }

    public List<Piece> getYellowPieces() {
        return yellowPieces;
    }

    public Piece[][] getGrid() {
        return grid;
    }

    public boolean checkIfPositionIsEmpty(int x, int y){
        if(!inBounds(x, y)){
            return false;
        }
        return(grid[y][x] == null);
    }

    public void removeIfOutOfBounds(int x, int y, Piece p, Player player){
        if(!inBounds(x, y)) {
            player.sendToCemetery(p, this);
            grid[y][x] = null;
        }
    }


    public boolean attackingYellowFace(Player player){
        for(Piece p: player.getInPlay()){
            if(p.getX() == 7 && p.getY() == 8){
                if(p.getRight() == Piece.Type.SWORD) return true;
            }

            if(p.getX() == 8 && p.getY() == 7){
                if(p.getBottom() == Piece.Type.SWORD) return true;
            }
        }
        return false;
    }

    public boolean attackingGreenFace(Player player){
        for(Piece p: player.getInPlay()){
            if(p.getX() == 2 && p.getY() == 1){
                if(p.getLeft() == Piece.Type.SWORD) return true;
            }

            if(p.getX() == 1 && p.getY() == 2){
                if(p.getTop() == Piece.Type.SWORD) return true;
            }
        }
        return false;
    }


    public boolean inBounds(int x, int y){
        return !(x < 2 && y < 2 || x > 7 && y > 7 || x < 0 || y < 0 || x >= 10 || y >= 10);
//        return((x > 0 && x < grid[0].length && y > 0 && y < grid.length) && !((x > 0 && x < 2 && y > 0 && y < 2) || (x > grid[0].length - 2 && x < grid[0].length && y > grid.length - 2 && y < grid.length)));
//        return(!(x < 2 && y < 2) || (x > grid[0].length -2 && y > grid.length - 2));
//        return(((x < grid.length && x >= 2) && (y < grid[0].length - 2 && y >= 2)));
    }


    /**
     * If there is an empty space add piece to grid at position
     * @param x
     * @param y
     * @param p
     */

    public boolean addToGrid(int x, int y, Piece p){
        if(inBounds(x, y)){
            if(grid[y][x] == null){
                grid[y][x] = p;
                p.setX(x);
                p.setY(y);
                return true;
            }
        }
        return false;
    }






//    public void generatePossiblePieces() {
//
//        yellowPieces = new ArrayList<>();
//        yellowCemetery = new ArrayList<>();
//        greenPieces = new ArrayList<>();
//        greenCemetery = new ArrayList<>();
//
//        char name = 'A';
//
//        yellowPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SHIELD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.SWORD, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SHIELD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SWORD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.NOTHING, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SHIELD, Color.YELLOW, name++));
//        yellowPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SHIELD, Color.YELLOW, name++));
//
//        name = 'A';
//
//        greenPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SWORD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SWORD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SHIELD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SHIELD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.NOTHING, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SHIELD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.SWORD, Piece.Type.SWORD, Piece.Type.SWORD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.GREEN, name++));
//
//
//        greenPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.NOTHING, Color.GREEN, name++));
//
//        greenPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SWORD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.NOTHING, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SHIELD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SHIELD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SWORD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SHIELD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.NOTHING, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.NOTHING, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.SWORD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SWORD, Piece.Type.NOTHING, Piece.Type.SWORD, Piece.Type.NOTHING, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.NOTHING, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.NOTHING, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.SWORD, Piece.Type.SHIELD, Color.GREEN, name++));
//        greenPieces.add(new Piece(Piece.Type.SHIELD, Piece.Type.SHIELD, Piece.Type.NOTHING, Piece.Type.SHIELD, Color.YELLOW, name++));
//
//        System.out.println(greenPieces.size());
//        System.out.println(yellowPieces.size());
//
//        for(int i = 0; i < 10; i++){
//            grid[0][i] = yellowPieces.get(i);
//        }
//
//    }

}
