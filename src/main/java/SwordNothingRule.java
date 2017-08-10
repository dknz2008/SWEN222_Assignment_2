import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by TML_TEST on 10/08/2017.
 */
public class SwordNothingRule implements Rule {

    @Override
    public List<Reaction> collide(Piece current, Piece reactive, Piece.Type currentType, Piece.Type reactiveType, Orientation pointOfContact) {
        if (currentType == Piece.Type.SWORD && reactiveType == Piece.Type.NOTHING) {
            return Arrays.asList(new Reaction(current, reactive, currentType, reactiveType, pointOfContact, Reaction.Rules.SWORDNOTHING));
        }

        if (currentType == Piece.Type.NOTHING && reactiveType == Piece.Type.SWORD) {
            //putting it in in opposite direction
            return Arrays.asList(new Reaction(reactive, current, reactiveType, currentType, Orientation.getOppositeDirection(pointOfContact), Reaction.Rules.SWORDNOTHING));
        }

        return Collections.emptyList();
    }
}
