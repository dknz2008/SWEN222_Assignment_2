package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class MainScreen extends JComponent implements Observer {

    Model.Model model;
    Controller controller;
    JFrame horizontalFrame;

    public MainScreen(Model.Model m, Controller controller){

        this.model = m;
        model.addObserver(this);
        this.controller = controller;

        //setting up frame for main screen
        horizontalFrame = new JFrame("Sword and Shield Game");

        horizontalFrame.addKeyListener(controller);
        horizontalFrame.setFocusable(true);

        horizontalFrame.setVisible(true);
        horizontalFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Creating Panes
        //JSplitPane
        JSplitPane yellowContentAndEverythingElse = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane boardAndGreenContent = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane inventoriesGreen = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane inventoriesYellow = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        //Creating Toolbar
        JToolBar toolBar = new JToolBar("Still draggable");
        //toolbar buttons
        JButton undoBtn = new JButton("Undo");
        JButton passBtn = new JButton("pass");
        JButton surrenderBtn = new JButton("Surrender");


        undoBtn.addActionListener((e) -> model.Undo());
        passBtn.addActionListener((e) -> {model.pass();});
        surrenderBtn.addActionListener((e) -> {
            Menu menu = new Menu(model, controller);
            horizontalFrame.dispose();
            JOptionPane.showMessageDialog(menu, "Game surrendered by: " + model.getCurrentTurn());
        });

        //Adding Buttons to Toolbar
        toolBar.add(undoBtn);
        toolBar.add(passBtn);
        toolBar.add(surrenderBtn);

        //setting up main frame
        horizontalFrame.setLayout(new BorderLayout());
        horizontalFrame.add(toolBar, BorderLayout.NORTH);
        horizontalFrame.add(yellowContentAndEverythingElse, BorderLayout.CENTER);

        yellowContentAndEverythingElse.setTopComponent(boardAndGreenContent);
        yellowContentAndEverythingElse.setBottomComponent(inventoriesYellow);

        boardAndGreenContent.setTopComponent(inventoriesGreen);

        inventoriesGreen.setTopComponent(new BarracksView(model, model.getGreenPlayer(), controller));
        inventoriesGreen.setBottomComponent(new CemeteryView(model, model.getGreenPlayer()));

        inventoriesYellow.setTopComponent(new BarracksView(model, model.getYellowPlayer(), controller));
        inventoriesYellow.setBottomComponent(new CemeteryView(model, model.getYellowPlayer()));

        boardAndGreenContent.setSize(700, 800);
        inventoriesGreen.setSize(150, 400);
        inventoriesYellow.setSize(150, 400);


        yellowContentAndEverythingElse.setDividerLocation(0.4);
        boardAndGreenContent.setDividerLocation(0.5);
        inventoriesGreen.setDividerLocation(0.5);
        inventoriesYellow.setDividerLocation(0.5);

        boardAndGreenContent.setBottomComponent(new BoardView(model, controller, horizontalFrame));

        //horizontalFrame.setVisible(true);
        horizontalFrame.setSize(1600, 800);
    }

    @Override
    public void update(Observable o, Object arg) {
        super.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1600, 800);
    }
}
