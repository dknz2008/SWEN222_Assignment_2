package View;

import Model.Model;

import javax.swing.*;

public class Main {

    public static void main(String[] s) {
        SwingUtilities.invokeLater(()->new View(new Model()));
    }

}
