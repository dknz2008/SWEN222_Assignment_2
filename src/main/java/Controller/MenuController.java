package Controller;

import View.MainScreen;
import View.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class MenuController implements ActionListener {

    Menu menu;

    public MenuController(Menu menu){
        this.menu = menu;
//
//        menu.getBeginGameButton().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                MainScreen screen = new MainScreen(m);
//                frame.dispose();
//            }
//        });
//        infoButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(frame, "Sword and Shield game implementation by Dylan Kumar.");
//            }
//        });
//        quitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.dispose();
//            }
//        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menu.getInfoButton()){
            JOptionPane.showMessageDialog(menu.getFrame(), "Sword and Shield game implementation by Dylan Kumar.");
        }
    }
}
