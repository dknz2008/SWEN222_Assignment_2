package Controller;

import Model.Direction;
import Model.Piece;
import Model.Player;
import View.BoardCell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Controller extends Observable implements KeyListener {

    Model.Model myModel;
    Piece pieceSelected;
    Player playerWon;

    public Player getPlayerWon() {
        return playerWon;
    }

    public Controller(Model.Model model){
        this.myModel = model;
    }

    public List<Piece> getMovedPieces() {
        return myModel.movedPieces;
    }

    public boolean addPiece(Player player, Piece piece){
        return myModel.addPiece(player, piece);
    }

    //moving piece?
    public void pieceMovement(Piece piece, Direction direction){
        myModel.movePiece(piece, direction);

        winCondition();
        pieceSelected = null;
    }

    public Player winCondition(){
        this.playerWon = myModel.hasWon();
        //System.out.println(playerWon.toString());
        return playerWon;
    }

    public void pieceClicked(Piece piece){
        pieceSelected = piece;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(pieceSelected !=null){
            switch (e.getKeyChar()){
                case 'w':
                    pieceMovement(pieceSelected, Direction.UP);
                    break;
                case 'a':
                    pieceMovement(pieceSelected, Direction.LEFT);
                    break;
                case 's':
                    pieceMovement(pieceSelected, Direction.DOWN);
                    break;
                case 'd':
                    pieceMovement(pieceSelected, Direction.RIGHT);
                    break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
