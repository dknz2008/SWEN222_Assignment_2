/**
 * Created by TML_TEST on 10/08/2017.
 */
public enum Orientation{
    LEFT, RIGHT, TOP, BOTTOM;


    public static Orientation getOppositeDirection(Orientation orientation){
        if(orientation == Orientation.BOTTOM){
            return Orientation.TOP;
        }
        if(orientation == Orientation.TOP){
            return Orientation.BOTTOM;
        }
        if(orientation == Orientation.LEFT){
            return Orientation.RIGHT;
        }
        if(orientation == Orientation.RIGHT){
            return Orientation.LEFT;
        }

        return null;
    }
}