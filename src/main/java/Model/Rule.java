package Model;

import java.util.List;

public interface Rule {


    /**
     * Returns a list of the reactions between
     * two colliding pieces
     */
    public List<Reaction> collide(Piece current, Piece reactive, Piece.Type currentType, Piece.Type reactiveType, Orientation pointOfContact);
}
