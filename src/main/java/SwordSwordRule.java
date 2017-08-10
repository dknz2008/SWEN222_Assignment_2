import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by TML_TEST on 10/08/2017.
 */
public class SwordSwordRule implements Rule {

    @Override
    public List<Reaction> collide(Piece current, Piece reactive, Piece.Type currentType, Piece.Type reactiveType, Orientation pointOfContact) {

        if(currentType == Piece.Type.SWORD && reactiveType == Piece.Type.SWORD){

            return Arrays.asList(new Reaction(current, reactive, currentType, reactiveType, pointOfContact, Reaction.Rules.SWORDSWORD));
        }

        return Collections.emptyList();
    }
}
