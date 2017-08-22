package View;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JComponent implements Observer {

    Model.Model model;

    View(Model.Model m) {
        this.model = m;
        Menu menu = new Menu(m);
    }

    private void createBoard(){

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("painting");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


}
