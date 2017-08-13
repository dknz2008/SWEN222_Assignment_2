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

    public Piece getGreenface() {
        return greenface;
    }

    public Piece getYellowface() {
        return yellowface;
    }

    public Piece getEmptytile() {
        return emptytile;
    }

    public Board() {
        grid = new Piece[10][10];
        grid[0][0] = emptytile;
        grid[0][1] = emptytile;
        grid[1][0] = emptytile;
        grid[1][1] = greenface;

        grid[9][9] = emptytile;
        grid[8][9] = emptytile;
        grid[9][8] = emptytile;
        grid[8][8] = yellowface;

    }

    public Piece[][] getGrid() {
        return grid;
    }

    /**
     * Checks if the grid at specified x and y coordinate is empty
     * @param x the x coordinate
     * @param y the y coordinate
     * @return boolean
     */
    public boolean checkIfPositionIsEmpty(int x, int y) {
        if (!inBounds(x, y)) {
            return false;
        }
        return (grid[y][x] == null);
    }

    /**
     * Check if the yellow face is being attacked by player (sword facing it)
     * @param player the player you are checking against
     * @return boolean
     */
    public boolean attackingYellowFace(Player player) {
        for (Piece p : player.getInPlay()) {
            if (p.getX() == 7 && p.getY() == 8) {
                if (p.getRight() == Piece.Type.SWORD) return true;
            }

            if (p.getX() == 8 && p.getY() == 7) {
                if (p.getBottom() == Piece.Type.SWORD) return true;
            }
        }
        return false;
    }

    /**
     * Check if the green face is being attacked by player (sword facing it)
     * @param player the player you are checking against
     * @return boolean
     */
    public boolean attackingGreenFace(Player player) {
        for (Piece p : player.getInPlay()) {
            if (p.getX() == 2 && p.getY() == 1) {
                if (p.getLeft() == Piece.Type.SWORD) return true;
            }

            if (p.getX() == 1 && p.getY() == 2) {
                if (p.getTop() == Piece.Type.SWORD) return true;
            }
        }
        return false;
    }


    /**
     * Check if piece is in bounds of the board or not
     * @param x x coordinate you are checking
     * @param y y coordinate you are checking
     * @return boolean
     */
    public boolean inBounds(int x, int y) {
        return !(x < 2 && y < 2 || x > 7 && y > 7 || x < 0 || y < 0 || x >= 10 || y >= 10);
    }


    /**
     * If there is an empty space add piece to grid at specified position
     *
     * @param x x coordinate of piece you want to add at
     * @param y y coordinate of the piece you want to add at
     * @param p the piece you want to add
     */

    public boolean addToGrid(int x, int y, Piece p) {
        if (inBounds(x, y)) {
            if (grid[y][x] == null) {
                grid[y][x] = p;
                p.setX(x);
                p.setY(y);
                return true;
            }
        }
        return false;
    }
}