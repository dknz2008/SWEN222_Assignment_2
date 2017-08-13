/**
 * Created by TML_TEST on 10/08/2017.
 */

public class Reaction {

    public enum Rules{
        SWORDNOTHING, SWORDSHIELD, SWORDSWORD;
    }
    private Piece current;
    private Piece reactive;
    private Piece.Type currentType;
    private Piece.Type reactiveType;
    private Orientation pointOfContact;
    private Rules rule;

    public Piece getCurrent() {
        return current;
    }

    public Piece getReactive() {
        return reactive;
    }

    public Piece.Type getCurrentType() {
        return currentType;
    }

    public Piece.Type getReactiveType() {
        return reactiveType;
    }


    /**
     *
     * @param current
     * @param reactive
     * @param currentType
     * @param reactiveType
     * @param pointOfContact, the point of contact for the current piece
     */
    public Reaction(Piece current, Piece reactive, Piece.Type currentType, Piece.Type reactiveType, Orientation pointOfContact, Rules rule){
        this.current = current;
        this.reactive = reactive;
        this.reactiveType = reactiveType;
        this.currentType = currentType;
        this.pointOfContact = pointOfContact;
        this.rule = rule;
    }


    /**
     * Execute reaction between two Pieces
     * @param board the board
     */
    public void executeReaction(Board board){
        System.out.println(currentType);
        if(rule == Rules.SWORDNOTHING){
            System.out.println(reactive.getPlayer().getColor());
            System.out.println(reactive.getName());
            reactive.getPlayer().sendToCemetery(reactive, board);
        }else if(rule == Rules.SWORDSWORD){
            reactive.getPlayer().sendToCemetery(reactive, board);
            current.getPlayer().sendToCemetery(current, board);
        }else if(rule == Rules.SWORDSHIELD){
            if(pointOfContact == Orientation.BOTTOM){
                reactive.move("up", board);
            }else if(pointOfContact == Orientation.TOP){
                reactive.move("down", board);
            } else if(pointOfContact == Orientation.LEFT){
                reactive.move("right", board);
            } else if(pointOfContact == Orientation.RIGHT){
                reactive.move("left", board);
            }
        }else{
            System.out.println("NO REACTION -- THIS SHOULD NOT HAPPEN ");
        }
    }

}
