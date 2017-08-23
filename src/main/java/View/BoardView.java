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
//    BoardCell[][] grid;

    protected BoardView(Model.Model m, Controller controller){

        this.model = m;
        this.controller = controller;
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
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int size = Math.min(getWidth(),getHeight())/10;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        Board board = model.getBoard();

        //draw grid
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawRect(i * size, j * size, size, size);
            }
        }

        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                Piece piece = board.getGrid()[y][x];
                if(piece == selectedPiece && selectedPiece != null){
                    g.setColor(java.awt.Color.RED);
                    g.drawRect(x * size, y * size, size, size);
                    g.setColor(java.awt.Color.black);
                }
                BoardCell boardCell = new BoardCell(piece,x*size, y*size, size, size);
                boardCell.paintComponent(g);
            }
        }


//        for(int y = 0; y < 10; y++){
//            for(int x = 0; x < 10; x++){
//                BoardCell b = grid[y][x];
//                System.out.println(b.getPiece() !=null);
//                b.paintComponent(g);
//            }
//
//        }

    }




    @Override
    public void mouseClicked(MouseEvent e) {

        int size = Math.min(getWidth(), getHeight())/10;
        boolean selected = false;
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                Rectangle rect = new Rectangle(y*size, x*size, size, size);
                if(e.getX() > rect.getX() && e.getX() < rect.getX() + rect.getWidth()){
                    if(e.getY() > rect.getY() && e.getY() < rect.getY() + rect.getHeight()){
                        selectedPiece = model.getBoard().getGrid()[y][x];
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


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}
