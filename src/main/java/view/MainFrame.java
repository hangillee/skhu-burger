package view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private static final JPanel panels = new JPanel(new CardLayout());

    public MainFrame() {
        setTitle("SKHU BURGER");
        setSize(540, 810);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static JPanel getPanels() {
        return panels;
    }
}
