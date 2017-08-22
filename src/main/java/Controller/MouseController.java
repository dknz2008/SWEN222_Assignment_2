package Controller;

import Model.Board;
import Model.Direction;
import View.BoardCell;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseMotionListener, MouseListener {

    BoardCell[][] grid;
    BoardCell[] greenBarracks;
    BoardCell[] yellowBarracks;

    public MouseController(BoardCell[][] grid, BoardCell[] greenBarracks, BoardCell[] yellowBarracks){
        this.grid = grid;
        this.greenBarracks = greenBarracks;
        this.yellowBarracks = yellowBarracks;
    }

    @Override
    public void mouseClicked(MouseEvent e) {



        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                grid[y][x].getDirectionClicked(e.getPoint());
            }
        }

        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                grid[y][x].getDirectionClicked(e.getPoint());
            }
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

}
