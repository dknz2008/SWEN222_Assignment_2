package View;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JComponent implements Observer {


    protected BoardView(){
        BoardCell test = new BoardCell();
    }
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.drawRect(0, 0, getRootPane().getWidth()/10, getRootPane().getWidth()/10);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}
