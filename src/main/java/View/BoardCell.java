package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class BoardCell extends JComponent implements Observer {

    BufferedImage img = null;

     BoardCell(){

    }

    @Override
    protected void paintComponent(Graphics g) {


        g.drawImage(ImgResources.SWORD.img,0, 0, this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
