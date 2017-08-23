package View;

import Controller.Controller;
import Controller.MenuController;
import Model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JComponent {

    private JPanel panelMain;
    private JButton infoButton;
    private JButton beginGameButton;
    private JButton quitButton;
    private JFrame frame;

    public Menu(Model m, Controller controller) {

        frame = new JFrame("menu");
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

//        this.addKeyListener(new MenuController(this));

        beginGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainScreen screen = new MainScreen(m, controller);
                frame.dispose();
            }
        });
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Sword and Shield game implementation by Dylan Kumar.");
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public JButton getBeginGameButton() {
        return beginGameButton;
    }

    public JButton getInfoButton() {
        return infoButton;
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public JFrame getFrame() {
        return frame;
    }
}
