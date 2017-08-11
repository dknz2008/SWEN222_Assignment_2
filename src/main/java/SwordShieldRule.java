import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by TML_TEST on 10/08/2017.
 */
public class SwordShieldRule implements Rule {

    @Override
    public List<Reaction> collide(Piece current, Piece reactive, Piece.Type currentType, Piece.Type reactiveType, Orientation pointOfContact) {

        if (currentType == Piece.Type.SHIELD && reactiveType == Piece.Type.SWORD) {
            return Arrays.asList(new Reaction(current, reactive, currentType, reactiveType, pointOfContact, Reaction.Rules.SWORDSHIELD));
        }

        if (currentType == Piece.Type.SWORD && reactiveType == Piece.Type.SHIELD) {
            //putting it in in opposite direction
            return Arrays.asList(new Reaction(reactive, current, reactiveType, currentType, Orientation.getOppositeDirection(pointOfContact), Reaction.Rules.SWORDSHIELD));
        }

        return Collections.emptyList();
    }
}
