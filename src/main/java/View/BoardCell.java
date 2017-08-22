package View;

import Model.Color;
import Model.Direction;
import Model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class BoardCell extends JComponent implements Observer {

    BufferedImage img = null;
    Piece piece;
    Rectangle boundingBox;
    //whether piece is on the main grid or not
    private boolean onGrid;

    int x;
    int y;
    int finalX;
    int finalY;
    int width;
    int height;
    
    BoardCell(Piece piece, int x, int y, int width, int height){

        this.piece = piece;
        boundingBox = new Rectangle(x, y, width, height);
        onGrid = true;
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;

        this.x = x + 5;
        this.y = y + 5;
        this.width = width - 5;
        this.height = height - 5;
        this.finalX = this.x + this.width - 5;
        this.finalY = this.y + this.height - 5;
    }


    public boolean getOnGrid(){
        return this.onGrid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(5));

        int ovalSize = (int)(this.width * 0.9);

        if(piece != null){

            if(piece.getColor() == Color.GREEN){
                g2d.setColor(java.awt.Color.GREEN);
                g2d.fillOval(this.x, this.y, ovalSize, ovalSize);
            }else if(piece.getColor() == Color.YELLOW){
                g2d.setColor(java.awt.Color.ORANGE);
                g2d.fillOval(this.x, this.y,ovalSize, ovalSize);
            }else{
                g2d.setColor(java.awt.Color.GRAY);
                g2d.fillRect(this.x - 5, this.y -5, width + 5, height + 5);
                if(piece.getName().equals("yellowface")){
                    g2d.drawImage(ImgResources.YELLOWFACE.img, (int)boundingBox.getX(), (int)boundingBox.getY(), (int)boundingBox.getWidth(), (int)boundingBox.getHeight(), null);
                }else if(piece.getName().equals("greenface")){
                    g2d.drawImage(ImgResources.GREENFACE.img, (int)boundingBox.getX(), (int)boundingBox.getY(), (int)boundingBox.getWidth(), (int)boundingBox.getHeight(), null);
                }
            }

            g2d.setColor(java.awt.Color.RED);

            if(piece.getTop() == Piece.Type.SWORD){
                g2d.drawLine(this.x + this.width/2, this.y + this.height/2, this.x + this.width/2,  this.y + 2);
            }else if (piece.getTop() == Piece.Type.SHIELD){
                g2d.drawLine(this.x, this.y, this.finalX, this.y);
            }

            if(piece.getBottom() == Piece.Type.SWORD){
                g2d.drawLine(this.x + this.width/2, this.y + this.height/2, this.x + this.width/2,  this.y + this.height - 2);
            }else if (piece.getBottom() == Piece.Type.SHIELD){
                g2d.drawLine(this.x, this.finalY, this.finalX, this.finalY);
            }

            if(piece.getLeft() == Piece.Type.SWORD){
                g2d.drawLine(this.x + this.width/2, this.y + this.height/2, this.x + 2,  this.y + this.height/2);
            }else if (piece.getLeft() == Piece.Type.SHIELD){
                g2d.drawLine(this.x, this.y, this.x, this.finalY);
            }

            if(piece.getRight() == Piece.Type.SWORD){
                g2d.drawLine(this.x + this.width/2, this.y + this.height/2, this.finalX - 2,  this.y + this.height/2);
            }else if (piece.getRight() == Piece.Type.SHIELD){
                g2d.drawLine(this.finalX, this.y, this.finalX, this.finalY);
            }

        }

        g2d.setColor(java.awt.Color.BLACK);

//        g2d.drawImage(View.ImgResources.SWORD.img,0, 0, this);
    }


    public Direction getDirectionClicked(Point point){
        int size = (int) boundingBox.getHeight();
        if(point.getX() > boundingBox.getX() + 0.2*size && point.getX() < boundingBox.getX() + size - 0.2*size){
            if(point.getY() > boundingBox.getY() && point.getY() < boundingBox.getY() + size*0.2){
                return Direction.UP;
            }
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {

    }


}
