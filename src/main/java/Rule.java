import java.util.List;

/**
 * Created by TML_TEST on 9/08/2017.
 */
public interface Rule {
    public List<Reaction> collide(Piece current, Piece reactive, Piece.Type currentType, Piece.Type reactiveType, Orientation pointOfContact);
}
