package View;

import Controller.Controller;
import Model.*;
import Model.Color;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JComponent implements MouseMotionListener, MouseListener, Observer {

    Model model;
    Controller controller;
    Piece selectedPiece = null;
    Rectangle selectedPiecesBoundingBox;
    Frame frame;

    protected BoardView(Model m, Controller controller, Frame frame){
        this.model = m;
        this.controller = controller;
        model.addObserver(this);
        addMouseListener(this);
        this.frame = frame;
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        int size = Math.min(getWidth(), getHeight()-20)/10;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        Board board = model.getBoard();

        //draw grid and creation tiles
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawRect(i * size, j * size, size, size);

                //green creation tile
                if(i == 2 && j == 2  && board.getGrid()[2][2] == null){
                    g.setColor(java.awt.Color.GREEN);
                    g.fillRect(i*size, j*size, size, size);
                    g.setColor(java.awt.Color.BLACK);
                }

                //yellow creation tile
                if(i == 7 && j == 7 && board.getGrid()[7][7] == null){
                    g.setColor(java.awt.Color.ORANGE);
                    g.fillRect(i*size, j*size, size, size);
                    g.setColor(java.awt.Color.BLACK);
                }

            }
        }

        //drawing pieces on grid
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                Piece piece = board.getGrid()[y][x];

                if(controller.getMovedPieces().contains(piece)){
                    g.setColor(java.awt.Color.BLUE);
                    g.fillRect(x*size, y*size, size, size);
                    g.setColor(java.awt.Color.black);
                }

                BoardCell boardCell = new BoardCell(piece,x*size, y*size, size, size);
                boardCell.paintComponent(g);

                if(piece == selectedPiece && selectedPiece != null){
                    g.setColor(java.awt.Color.BLUE);
                    g.drawRect(x * size, y * size, size, size);
                    g.setColor(java.awt.Color.black);

                    int alpha = 127; // 50% transparent
                    g.setColor(new java.awt.Color(0, 0, 0, alpha));

                    int width = (int)(size - 0.4*size);
                    int height = (int)(0.2*size);

                    g.fillRect((int)(x*size + 0.2*size), (y)*size, width, height);
                    g.fillRect((int)(x*size + 0.2*size), (y*size + size - height), width, height);
                    g.fillRect((int)(x*size), (y*size + height), height, width);
                    g.fillRect((int)(x*size + size - 0.2*size), (y*size + height), height, width);
                    g.setColor(java.awt.Color.black);
                }


            }

        }

        String s = "Current Turn: " + model.getCurrentTurn().toString() + ", Phase: " + model.getState().toString();
        g.drawString(s, getWidth()/2 - s.length(), getHeight() - 15);

        if(controller.winCondition() != null){
            Menu menu = new Menu(model, controller);
            JOptionPane.showMessageDialog(frame, controller.winCondition().toString() + " Wins!!!!");
            frame.dispose();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(model.getState() == Model.GameState.MOVEMENT){

            if(selectedPiece != null){
                System.out.println("here");
                getBoundingBox(e);
                Direction directionClicked = getDirectionClicked(e.getPoint(), selectedPiecesBoundingBox);
                if(directionClicked != null){
                    controller.pieceMovement(selectedPiece, directionClicked);
                    selectedPiece = null;
                }
            }

            if(!getBoundingBox(e)){
                selectedPiece = null;
            }

            System.out.println("x: " + e.getPoint().getX());
            System.out.println("y: " + e.getPoint().getY());
            System.out.println(selectedPiece == null);
            repaint();
        }
    }


    public boolean getBoundingBox(MouseEvent e){
        int size = Math.min(getWidth(), getHeight()-20)/10;

        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                Rectangle rect = new Rectangle(y*size, x*size, size, size);
                if(rect.contains(e.getPoint())){
                    selectedPiece = model.getBoard().getGrid()[y][x];
                    if(selectedPiece != null){
                        System.out.println("__here");
                        if(selectedPiece.getColor() == model.getCurrentTurn().getColor()){
                            System.out.println("ofc");
                            controller.pieceClicked(selectedPiece);
                            selectedPiecesBoundingBox = rect;
                            return true;
                        }
                    }else{
                        System.out.println("its null what???");
                    }
                }
            }
        }

        return false;
    }

    public Direction getDirectionClicked(Point point, Rectangle boundingBox){
        int size = (int) boundingBox.getHeight();

        int x = (int) boundingBox.getX();
        int y = (int) boundingBox.getY();

        int width = (int)(size - 0.4*size);
        int height = (int)(0.2*size);

        Rectangle top = new Rectangle((int)(x + 0.2*size), (y), width, height);
        Rectangle bottom = new Rectangle((int)(x + 0.2*size), (y + size - height), width, height);
        Rectangle left = new Rectangle((int)(x), (y + height), height, width);
        Rectangle right = new Rectangle((int)(x + size - 0.2*size), (y + height), height, width);

        if(top.contains(point)){
            System.out.println("UP!");
            return Direction.UP;
        }

        if(bottom.contains(point)){
            System.out.println("down!");
            return Direction.DOWN;
        }

        if(left.contains(point)){
            System.out.println("left!");
            return Direction.LEFT;
        }

        if(right.contains(point)){
            System.out.println("right!");
            return Direction.RIGHT;
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


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}
