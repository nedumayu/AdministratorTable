package ConnectionTest;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {

        ConnectionDB connect = new ConnectionDB("jdbc:postgresql://localhost:5432/letipay", "postgres", "123");

        connect.init();

        JFrame frame = new JFrame("Administrator table");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        PanelFrame panel = new PanelFrame(connect);
        panel.init();
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.pack();
    }
}