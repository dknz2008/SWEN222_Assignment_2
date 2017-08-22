package View;

import Model.Board;
import Model.Direction;
import Model.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JComponent implements MouseMotionListener, MouseListener, Observer {

    Model.Model model;
//    BoardCell[][] grid;

    protected BoardView(Model.Model m){

        this.model = m;

//        grid = new BoardCell[10][10];
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
                BoardCell boardCell = new BoardCell(piece,y*size, x*size, size, size);
                addMouseListener(boardCell);
                boardCell.paintComponent(g);
            }
        }


//        for(int y = 0; y < 10; y++){
//            for(int x = 0; x < 10; x++){
//                grid[y][x].paintComponent(g);
//            }
//        }

    }




    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("hey!");
        if(onGrid){
            Direction d = getDirectionClicked(e.getPoint());
            System.out.println("x: " + e.getPoint().getX());
            System.out.println("y: " + e.getPoint().getY());
            System.out.println(d);
        }else{
            //change/return something to the barracks views to show the difference pieces  (may need to get pass in barracks view or grid
            //in order so that the board cell knows where it came from.
        }

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
