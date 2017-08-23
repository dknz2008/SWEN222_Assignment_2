package Controller;

import Model.Direction;
import Model.Piece;
import Model.Player;
import View.BoardCell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    Model.Model myModel;


    Piece pieceSelected;
    List<Piece> movedPieces;

    public Controller(Model.Model model){
        this.myModel = model;
        movedPieces = new ArrayList<>();
    }

    public boolean addPiece(Player player, Piece piece){
        if(currentTurn.isCreationTileFree(board)){
            player.createPieceOnBoard(myModel.getBoard(), piece);
            parseReactions(s, piece);
        }

    }

    //moving piece?
    public void pieceMovement(Piece piece, BoardCell boardCell, Direction direction){
        pieceClicked(piece, boardCell);
        piece.move(direction.toString(), myModel.getBoard());
        movedPieces.add(piece);
    }

    public void pieceClicked(Piece piece, BoardCell boardCell){
        pieceSelected = piece;
    }


}
