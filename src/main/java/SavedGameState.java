import java.util.List;

/**
 * Created by TML_TEST on 11/08/2017.
 */
public class SavedGameState {

    private Board savedBoard;
    private Player savedgreenPlayer;
    private Player savedyellowPlayer;
    private List<Piece> savedMovedPieces;

    SavedGameState(Board board, Player greenPlayer, Player yellowPlayer, List<Piece> movedPieces){
        this.savedBoard = savedBoard;
        this.savedgreenPlayer = greenPlayer;
        this.savedyellowPlayer = yellowPlayer;
        this.savedMovedPieces = movedPieces;
    }

    public void UndoGame(Board board, Player greenPlayer, Player yellowPlayer, List<Piece> movedPieces){
        board = savedBoard;
        greenPlayer = savedgreenPlayer;
        yellowPlayer = savedyellowPlayer;
        movedPieces = savedMovedPieces;
    }


}
