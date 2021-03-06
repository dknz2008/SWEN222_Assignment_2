package View;

import Controller.Controller;
import Model.*;
import com.rits.cloning.Cloner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

public class BarracksView extends JComponent implements MouseMotionListener, MouseListener, Observer {

    Model model;
    Player player;
    Controller controller;
    Piece selectedPiece;

    protected BarracksView(Model m, Player player, Controller controller){
        this.model = m;
        this.player = player;
        this.controller = controller;
        model.addObserver(this);
        addMouseListener(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        if(selectedPiece == null){
            int size = Math.min(getWidth()/3,getHeight()/8);

            for(int y = 0; y < 8; y++){
                for(int x = 0; x < 3; x++) {
                    g.drawRect(x*size, y*size, size, size);
                }
            }

            int count = 0;
            for(int y = 0; y < 8; y++){
                for(int x = 0; x < 3; x++){
                    Piece piece;
                    if(count >= player.getBarracks().size()){
                        piece = null;
                    }else{
                        piece = player.getBarracks().get(count++);
                    }

                    if(piece != null){
                        BoardCell boardCell = new BoardCell(piece,x*size, y*size, size, size);
                        boardCell.paintComponent(g);
                    }
                }
            }
        }else {
            for (int x = 0; x < 4; x++) {
                int size = Math.min(getWidth() / 3, getHeight() / 8);
                g.drawRect(x * size, 0, size, size);
            }

            Piece[] orientations =  model.getOrientations(selectedPiece);

            for (int x = 0; x < 4; x++) {
                int size = Math.min(getWidth() / 3, getHeight() / 8);
                BoardCell boardCell = new BoardCell(orientations[x],x*size, 0, size, size);
                boardCell.paintComponent(g);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(model.getState() == Model.GameState.CREATION){

            boolean set = false;
            int size = Math.min(getWidth()/3, getHeight()/8);
            int count = 0;

            //if piece is currently not selected
            if(selectedPiece == null &&  player == model.getCurrentTurn() && model.getCurrentTurn().isCreationTileFree(model.getBoard())) {
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 3; x++) {
                        Rectangle rect = new Rectangle(x * size, y * size, size, size);
                        if (e.getX() > rect.getX() && e.getX() < rect.getX() + rect.getWidth()) {
                            if (e.getY() > rect.getY() && e.getY() < rect.getY() + rect.getHeight()) {

                                Piece piece;
                                if (count >= player.getBarracks().size()) {
                                    piece = null;
                                } else {
                                    piece = player.getBarracks().get(count);
                                }

                                selectedPiece = piece;
                                set = true;
                            }
                        }
                        count++;
                    }
                }
            }else{
                for (int x = 0; x < 4; x++) {
                    size = Math.min(getWidth() / 3, getHeight() / 8);
                    Rectangle rect = new Rectangle(x * size, 0, size, size);
                    if (e.getX() > rect.getX() && e.getX() < rect.getX() + rect.getWidth()) {
                        if (e.getY() > rect.getY() && e.getY() < rect.getY() + rect.getHeight()) {
                            Piece[] orientations =  model.getOrientations(selectedPiece);
                            controller.addPiece(player, orientations[0].rotate(x*90));
                            set = false;
                        }
                    }
                }
            }

            if (!set) {
                selectedPiece = null;
            }

            repaint();
            //Direction d = getDirectionClicked(e.getPoint(), );

            System.out.println("x: " + e.getPoint().getX());
            System.out.println("y: " + e.getPoint().getY());
        }else{
            selectedPiece = null;
        }


    }


//    public boolean checkIfInBounds(Rectangle rect){
//
//    }

    public Direction getDirectionClicked(Point point, Rectangle boundingBox){
        int size = (int) boundingBox.getHeight();
        if(point.getX() > boundingBox.getX() + 0.2*size && point.getX() < boundingBox.getX() + size - 0.2*size){
            if(point.getY() > boundingBox.getY() && point.getY() < boundingBox.getY() + size*0.2){
                return Direction.UP;
            }
        }
        return null;
    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

}
