package View;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainScreen extends JComponent implements Observer {

    public MainScreen(){
        JFrame horizontalFrame = new JFrame("Sword and Shield Game");
        horizontalFrame.setSize(1600, 800);
        horizontalFrame.setVisible(true);
        horizontalFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        horizontalFrame.setLayout(new GroupLayout(mainPanel));

//        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        JSplitPane boardAndContent = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane splitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane splitPane4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane cemeteryPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        JToolBar toolBar = new JToolBar("Still draggable");
        //toolbar buttons
        JButton undoBtn = new JButton("Undo");
        JButton passBtn = new JButton("pass");
        JButton surrenderBtn = new JButton("Surrender");

        toolBar.add(undoBtn);
        toolBar.add(passBtn);
        toolBar.add(surrenderBtn);

        mainPanel.add(toolBar);

//        splitPane.setTopComponent(toolBar);
//        splitPane.setPreferredSize(new Dimension(800, 800));
//        splitPane.setBottomComponent(boardAndContent);


        boardAndContent.setTopComponent(splitPane3);

        splitPane3.setTopComponent(splitPane4);
        splitPane3.setBottomComponent(cemeteryPane);

        //horizontalFrame.add(splitPane, BorderLayout.CENTER);
        horizontalFrame.setSize(150, 150);
        horizontalFrame.setVisible(true);

        splitPane3.setDividerLocation(0.5);
        boardAndContent.setDividerLocation(0.5);


        boardAndContent.setBottomComponent(new BoardView());
    }

    @Override
    public void update(Observable o, Object arg) {
        super.repaint();
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1600, 800);
    }
}
