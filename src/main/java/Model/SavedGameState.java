package Model;

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

    public void UndoGame(Model model){
        model.board = savedBoard;
        model.currentTurn = currentPlayer;
        model.greenPlayer = savedgreenPlayer;
        model.yellowPlayer = savedyellowPlayer;
        model.movedPieces = savedMovedPieces;
    }

    public Board getSavedBoard() {
        return savedBoard;
    }

    public boolean isWasCreation() {
        return wasCreation;
    }

    public boolean isWasPass() {
        return wasPass;
    }
}
