package GUI;

import sun.applet.Main;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class MainView implements Observer {

    private JButton undoButton;
    private JButton passButton;
    private JButton surrenderButton;
    private JPanel MainPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;

    @Override
    public void update(Observable o, Object arg) {

    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("menu");
        frame.setContentPane(new MainView().MainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setSize(1600, 800);
        frame.setVisible(true);
        MainView view = new MainView();

    }

}
