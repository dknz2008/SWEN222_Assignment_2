package Model;

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

    public Color getColor() {
        return color;
    }

    public enum Type {
        SWORD, SHIELD, NOTHING
    }

    public enum Rotation{
        CLOCKWISE, ANTICLOCKWISE
    }

    public Piece(Type left, Type right, Type top, Type bottom, Color color, String name, Player player){
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

    /**
     * Rotates piece by rotation angle clockwise
     * @param rotation the angle of rotation
     * @return Model.Piece the piece that was rotated
     */
    public Piece rotate(int rotation){
        int r = rotation/90;

        //Rotating piece
        while(r != 0){
            rotatePiece(Piece.Rotation.CLOCKWISE);
            r--;
        }

        return this;
    }


    /**
     * Rotates piece either clockwise or anti-clockwise
     * @param rot the direction or rotation (CLOCKWISE or ANTICLOCKWISE)
     *
     */
    public void rotatePiece(Piece.Rotation rot) {
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
    }

    /**
     * Moves piece to new position and sets old position to empty tile.
     * Makes sure piece is sent to cemetery if piece goes out of bounds.
     * @param direction direction that you want to move
     * @param board the board object
     */

    public void move(String direction, Board board){
        if(direction.equalsIgnoreCase("up")){
            if(!board.inBounds(x, y - 1)){
                player.sendToCemetery(this, board);
            }else if(board.getGrid()[this.y - 1][this.x] == null){
                //moving piece
                board.getGrid()[this.y-1][this.x] = this;
                //setting old position to empty
                board.getGrid()[this.y][this.x] = null;
                this.y = this.y - 1;
            }else{
                //moving other piece first
                board.getGrid()[this.y -1][this.x].move("up", board);
                this.move("up", board);
            }
        }else if(direction.equalsIgnoreCase("down")){
            if(!board.inBounds(x, y + 1)){
                player.sendToCemetery(this, board);
            }else if(board.getGrid()[this.y+1][this.x] == null){
                //moving piece
                board.getGrid()[this.y+1][this.x] = this;
                //setting old position to empty
                board.getGrid()[this.y][this.x] = null;
                this.y = this.y + 1;
            }else{
                //moving other piece first
                board.getGrid()[this.y + 1][this.x].move("down", board);
                this.move("down", board);
            }

        }else if(direction.equalsIgnoreCase("left")){
            if(!board.inBounds(x-1, y)){
                player.sendToCemetery(this, board);
            }else if(board.getGrid()[this.y][this.x-1] == null){
                //moving piece
                board.getGrid()[this.y][this.x-1] = this;
                //setting old position to empty
                board.getGrid()[this.y][this.x] = null;
                this.x = this.x - 1;
            }else{
                //moving other piece first
                board.getGrid()[this.y][this.x - 1].move("left", board);
                this.move("left", board);
            }

        }else if(direction.equalsIgnoreCase("right")){
            if(!board.inBounds(x+1, y)){
                player.sendToCemetery(this, board);
            }else if(board.getGrid()[this.y][this.x+1] == null){
                //moving piece
                board.getGrid()[this.y][this.x+1] = this;
                //setting old position to empty
                board.getGrid()[this.y][this.x] = null;
                this.x = this.x + 1;
            }else{
                //moving other piece first
                board.getGrid()[this.y][this.x + 1].move("right", board);
                this.move("right", board);
            }
        }
    }


    public Type getRight() {
        return right;
    }

    public Type getLeft() { return left; }

    public Type getTop() {
        return top;
    }

    public Type getBottom() {
        return bottom;
    }

    public String getName() { return name; }

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

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {return x; }

    public Integer getY() { return y;}

}
