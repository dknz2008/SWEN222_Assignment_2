package View;

import Controller.Controller;
import Model.Board;
import Model.Color;
import Model.Direction;
import Model.Piece;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JComponent implements MouseMotionListener, MouseListener, Observer {

    Model.Model model;
    Controller controller;
    Piece selectedPiece = null;
    Rectangle selectedPiecesBoundingBox;
//    BoardCell[][] grid;

    protected BoardView(Model.Model m, Controller controller){

        this.model = m;
        this.controller = controller;

//        this.addKeyListener(controller);
//        this.setFocusable(true);

        addMouseListener(this);

//        this.grid = new BoardCell[10][10];
//
//        Board board = model.getBoard();
//        int size = Math.min(getWidth(),getHeight())/10;
//
//        for(int y = 0; y < 10; y++){
//            for(int x = 0; x < 10; x++){
//                Piece piece = board.getGrid()[y][x];
//                grid[y][x]  = new BoardCell(piece,y*size, x*size, size, size);
//            }
//        }

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("boardview repaint");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        int size = Math.min(getWidth(),getHeight())/10;

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
                }


            }

        }

    }




    @Override
    public void mouseClicked(MouseEvent e) {

        if(selectedPiece != null){
            System.out.println("here1");
            Direction directionClicked = getDirectionClicked(e.getPoint(), selectedPiecesBoundingBox);
            if(directionClicked != null){
                System.out.println("here0");
                controller.pieceMovement(selectedPiece, directionClicked);
            }
        }

        int size = Math.min(getWidth(), getHeight())/10;
        boolean selected = false;

        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                Rectangle rect = new Rectangle(y*size, x*size, size, size);
                if(e.getX() > rect.getX() && e.getX() < rect.getX() + rect.getWidth()){
                    if(e.getY() > rect.getY() && e.getY() < rect.getY() + rect.getHeight()){
                        selectedPiece = model.getBoard().getGrid()[y][x];
                        controller.pieceClicked(selectedPiece);
                        selectedPiecesBoundingBox = rect;
                        selected = true;
                        System.out.println("hey!");
                    }
                }
            }
        }


        if(!selected){
            selectedPiece = null;
        }

        //Direction d = getDirectionClicked(e.getPoint(), );

        System.out.println("x: " + e.getPoint().getX());
        System.out.println("y: " + e.getPoint().getY());
        //System.out.println(d);
        repaint();
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

//        if((point.getX() > boundingBox.getX() + 0.2*size) && (point.getX() < boundingBox.getX() + width)){
//            if((point.getY() > boundingBox.getY()) && (point.getY() < boundingBox.getY() + height)){
//                System.out.println("UP!!!!!!");
//                return Direction.UP;
//            }
//        }
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
