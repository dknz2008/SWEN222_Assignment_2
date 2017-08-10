import java.util.ArrayList;
import java.util.List;

/**
 * Created by TML_TEST on 10/08/2017.
 */
public class ReactionManager {


    private static Rule[] rules = new Rule[]{new SwordNothingRule(), new SwordShieldRule(), new SwordSwordRule()};

    ReactionManager(){

    }

    public List<Reaction> workOutReactions(int x, int y, Board board){



        List<Reaction> reactionList = new ArrayList<>();


        for (Rule rule : rules) {

            Piece current = board.getGrid()[y][x];

            if (board.inBounds(x, y - 1)) {
                if (board.getGrid()[y - 1][x] != null) {
                    Piece against = board.getGrid()[y - 1][x];
                    reactionList.addAll(rule.collide(current, against, current.getTop(), against.getBottom(), Orientation.BOTTOM));
                }
            }

            if (board.inBounds(x - 1, y)) {
                if (board.getGrid()[y][x - 1] != null) {
                    Piece against = board.getGrid()[y][x - 1];
                    reactionList.addAll(rule.collide(current, against, current.getLeft(), against.getRight(), Orientation.RIGHT));
                }
            }

            if (board.inBounds(x + 1, y)) {
                if (board.getGrid()[y][x + 1] != null) {
                    Piece against = board.getGrid()[y][x + 1];
                    reactionList.addAll(rule.collide(current, against, current.getRight(), against.getLeft(), Orientation.LEFT));
                }
            }

            if (board.inBounds(x, y + 1)) {
                if (board.getGrid()[y + 1][x] != null) {
                    Piece against = board.getGrid()[y + 1][x];
                    reactionList.addAll(rule.collide(current, against, current.getBottom(), against.getTop(), Orientation.TOP));
                }
            }
        }

        return reactionList;
    }

    public String printReactionInformation(Reaction reaction){
       return ("Reaction between " + reaction.getCurrent().getName() + " and "
                + reaction.getReactive().getName() + ": " + reaction.getReactiveType().toString());
    }


}
