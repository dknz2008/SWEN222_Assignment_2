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
        int size = Math.min(getWidth(),getHeight())/10;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                g.drawRect(i*size, j*size, size, size);
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}
