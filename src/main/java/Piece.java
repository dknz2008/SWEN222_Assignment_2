import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by TML_TEST on 25/07/2017.
 */
public class Piece {

    private Type left;
    private Type right;
    private Type top;
    private Type bottom;
    private Color color;
    private String name;
    private Player player;

    private Integer x, y; //x, y coordinate for where it is placed on board (null if not placed anywhere)

    public enum Type {
        SWORD, SHIELD, NOTHING
    }

    public enum Rotation{
        CLOCKWISE, ANTICLOCKWISE
    }

    Piece(Type left, Type right, Type top, Type bottom, Color color, String name, Player player){
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.color = color;
        this.name = name;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Piece rotate(int rotation){
        int r = rotation/90;

        //Rotating piece
        while(r != 0){
            rotatePiece(Piece.Rotation.CLOCKWISE);
            r--;
        }

        return this;
    }



    public boolean rotatePiece(Piece.Rotation rot) {
        Piece.Type l = this.getLeft();
        Piece.Type r = this.getRight();
        Piece.Type t = this.getTop();
        Piece.Type b = this.getBottom();

        if (rot == Piece.Rotation.CLOCKWISE) {
            this.setRight(t);
            this.setBottom(r);
            this.setLeft(b);
            this.setTop(l);
        } else if (rot == Piece.Rotation.ANTICLOCKWISE) {
            this.setRight(b);
            this.setBottom(l);
            this.setLeft(t);
            this.setTop(r);
        }

        return true;
    }

    /**
     * Moves piece to new position and sets old position to empty tile
     * @param direction
     * @param board
     */

    //TODO make it so it works with reactions and shifting other pieces too
    public void move(String direction, Board board){
        if(direction.equalsIgnoreCase("up")){
            if(!board.inBounds(x, y - 1)){
                board.getGrid()[y][x] = null;
            }else if(board.getGrid()[this.y -1][this.x] == null){
                //moving piece
                board.getGrid()[this.y-1][this.x] = this;
                //setting old position to empty
                board.getGrid()[this.y][this.x] = null;
                this.y = this.y - 1;
            }
        }else if(direction.equalsIgnoreCase("down")){
            if(!board.inBounds(x, y + 1)){
                board.getGrid()[y][x] = null;
            }else if(board.getGrid()[this.y+1][this.x] == null){
                //moving piece
                board.getGrid()[this.y+1][this.x] = this;
                //setting old position to empty
                board.getGrid()[this.y][this.x] = null;
                this.y = this.y + 1;
                System.out.println("applying reactions now");
            }

        }else if(direction.equalsIgnoreCase("left")){
            if(!board.inBounds(x-1, y)){
                board.getGrid()[y][x] = null;
            }else if(board.getGrid()[this.y][this.x-1] == null){
                //moving piece
                board.getGrid()[this.y][this.x-1] = this;
                //setting old position to empty
                board.getGrid()[this.y][this.x] = null;
                this.x = this.x - 1;
            }

        }else if(direction.equalsIgnoreCase("right")){
            if(!board.inBounds(x+1, y)){
                board.getGrid()[y][x] = null;
            }else if(board.getGrid()[this.y][this.x+1] == null){
                //moving piece
                board.getGrid()[this.y][this.x+1] = this;
                //setting old position to empty
                board.getGrid()[this.y][this.x] = null;
                this.x = this.x + 1;
            }
        }
    }

//    public void SwordNothingRule(Board board, int x1, int y1, int x2, int y2, Piece p1, Piece p2, Piece.Type t1, Piece.Type t2){
//        if(t1 == Piece.Type.SWORD && t2 == Piece.Type.NOTHING){
//            //move piece to graveyard and make position in grid empty
//            board.getGrid()[x2][y2] = null;
//            if(p1.getColor() == Color.GREEN){
//                board.getGreenCemetery().add(p2);
//            }else if(p1.getColor() == Color.YELLOW){
//                board.getYellowCemetery().add(p2);
//            }
//        }
//
//        if(t2 == Piece.Type.SWORD && t1 == Piece.Type.NOTHING){
//            //move piece to graveyard and make position in grid empty
//            board.getGrid()[x1][y1] = null;
//            if(p2.getColor() == Color.GREEN){
//                board.getGreenCemetery().add(p1); //HMMMMM check this (might need to be p2)
//            }else if(p2.getColor() == Color.YELLOW){
//                board.getYellowCemetery().add(p1);
//            }
//        }
//    }

//    public void SwordSwordRule(Board board, int x1, int y1, int x2, int y2, Piece p1, Piece p2, Piece.Type t1, Piece.Type t2){
//        if(t1 == Piece.Type.SWORD && t2 == Piece.Type.SWORD){
//            removeFromBoard(board, x1, y1, p1);
//            removeFromBoard(board, x1, y1, p2);
//
//            if(p2.getColor() == Color.GREEN){
//                board.getGreenCemetery().add(p2);
//                board.getYellowCemetery().add(p1);
//            }else if(p2.getColor() == Color.YELLOW){
//                board.getYellowCemetery().add(p2);
//                board.getGreenCemetery().add(p1);
//            }
//        }
//    }

//
//    public void SwordShieldRule(Board board, int x1, int y1, int x2, int y2, Piece p1, Piece p2, Piece.Type t1, Piece.Type t2, Orientation pointOfContact){
//        if(t1 == Piece.Type.SWORD && t2 == Piece.Type.SHIELD){
//            //TODO in this case we have to call the reactions again after things have moved!!!
//            System.out.println("hopefully this doesn't happen");
////            shiftPieces(board, x1, y1, p1, pointOfContact);
//        }else if(t2 == Piece.Type.SWORD && t1 == Piece.Type.SHIELD){
//            System.out.println("shifting pieces now");
//            shiftPieces(board, x2, y2, p2, pointOfContact);
//        }
//    }


    /**
     *
     * @param board, the board
     * @param x,
     * @param  y,
     * @param pointOfContact, the spot that the second piece makes contact with the first piece (second pieces top, bottom, left or right)
     */

    public void shiftPieces(Board board, int x, int y, Piece piece, Orientation pointOfContact){
        if(pointOfContact == Orientation.TOP){
            System.out.println("In here");
//            if(board.inBounds(x2+2, y2))
//            if(board.getGrid()[y2+2][x2] != null){
//                System.out.println("in the not null check ");
//                Piece p3 = board.getGrid()[y2+2][x2];
//                applyReactions(board, );
//            }
            //may need an else
            piece.move("down", board);
//            applyReactions(board, x, y);
//            p1.move("down", board, player);

        }else if(pointOfContact == Orientation.BOTTOM){
            System.out.println("didn't work");
        } else if(pointOfContact == Orientation.LEFT){
            System.out.println("didn't work");
        } else if(pointOfContact == Orientation.RIGHT){
            System.out.println("didn't work");
        }
    }



    public void removeFromBoard(Board board, int x, int y, Piece piece){
        board.getGrid()[y][x] = null;
        piece.x = null;
        piece.y = null;
    }


    public Type getRight() {
        return right;
    }

    public Type getLeft() {
        return left;
    }

    public Type getTop() {
        return top;
    }

    public Type getBottom() {
        return bottom;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setLeft(Type left) {
        this.left = left;
    }

    public void setRight(Type right) {
        this.right = right;
    }

    public void setTop(Type top) {
        this.top = top;
    }

    public void setBottom(Type bottom) {
        this.bottom = bottom;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

}
