package Controller;

import Model.Direction;
import Model.Piece;
import Model.Player;
import View.BoardCell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Controller implements KeyListener{

    Model.Model myModel;


    Piece pieceSelected;
    List<Piece> movedPieces;


    public Controller(Model.Model model){
        this.myModel = model;
        movedPieces = new ArrayList<>();
    }

    public boolean addPiece(Player player, Piece piece){
        return myModel.addPiece(player, piece);
    }

    //moving piece?
    public void pieceMovement(Piece piece, Direction direction){
//        pieceClicked(piece, boardCell);
        if(!movedPieces.contains(piece)){
            piece.move(direction.toString(), myModel.getBoard());
            movedPieces.add(piece);
        }else{
            System.out.println("Piece has already been moved in this turn");
        }

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
