package View;

import Model.Piece;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BarracksView extends JComponent implements Observer {

    Model.Model model;
    Player player;

    protected BarracksView(Model.Model m, Player player){
        this.model = m;
        this.player = player;
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {

        int size = Math.min(getWidth()/3,getHeight()/8);
        int count = 0;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 3; x++) {
                g.drawRect(x*size, y*size, size, size);
            }
        }

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
    }

}
