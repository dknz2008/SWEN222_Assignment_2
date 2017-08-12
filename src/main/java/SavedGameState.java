import com.rits.cloning.Cloner;

import java.util.List;

/**
 * Created by TML_TEST on 11/08/2017.
 */
public class SavedGameState {

    private final boolean wasPass;
    private final Player currentPlayer;
    private Board savedBoard;
    private Player savedgreenPlayer;
    private Player savedyellowPlayer;
    private List<Piece> savedMovedPieces;
    private boolean wasCreation;

    SavedGameState(Board board, Player greenPlayer, Player yellowPlayer, List<Piece> movedPieces, Player currentPlayer, boolean wasCreating, boolean wasPass){
        this.savedBoard = board;
        this.savedgreenPlayer = greenPlayer;
        this.savedyellowPlayer = yellowPlayer;
        this.savedMovedPieces = movedPieces;
        this.wasCreation = wasCreating;
        this.wasPass = wasPass;
        this.currentPlayer = currentPlayer;
    }

    public void UndoGame(Game game){
        game.board = savedBoard;
        game.currentTurn = currentPlayer;
        game.greenPlayer = savedgreenPlayer;
        game.yellowPlayer = savedyellowPlayer;
        game.movedPieces = savedMovedPieces;
    }

    public boolean isWasCreation() {
        return wasCreation;
    }

    public boolean isWasPass() {
        return wasPass;
    }
}
