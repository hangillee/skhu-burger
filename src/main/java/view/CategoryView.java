package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CategoryView extends JFrame {
    public void printFrame() {
        setTitle("SKHU BURGER");
        setSize(540, 810);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        add(backgroundPanel);
        setVisible(true);
    }

    static class BackgroundPanel extends JPanel {
        public BackgroundPanel() {
            setBackground(Color.decode("#FFB347"));
            setLayout(null);
            MainPanel mainPanel = new MainPanel();
            mainPanel.setBounds(30, 30, 480, 720);
            add(mainPanel);
        }
    }

    static class MainPanel extends JPanel {
        public MainPanel() {
            setPreferredSize(new Dimension(480, 720));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BorderLayout());

            LabelPanel labelPanel = new LabelPanel();
            add(labelPanel, BorderLayout.NORTH);

            ButtonPanel buttonPanel = new ButtonPanel();
            add(buttonPanel, BorderLayout.CENTER);
        }
    }

    static class LabelPanel extends JPanel {
        public LabelPanel() {
            setPreferredSize(new Dimension(480, 120));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BorderLayout());

            JLabel text = new JLabel("주문할 메뉴를 선택해주세요");
            text.setFont(new Font("Gothic", Font.BOLD, 36));
            text.setHorizontalAlignment(JLabel.CENTER);

            add(text, BorderLayout.CENTER);
        }
    }

    static class ButtonPanel extends JPanel {
        public ButtonPanel() {
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            JButton setMenu = createButton("src/main/resources/category/Set.png");
            JButton burger = createButton("src/main/resources/category/Burger.png");
            JButton drink = createButton("src/main/resources/category/Drink.png");
            JButton side = createButton("src/main/resources/category/Side.png");

            add(setMenu);
            add(burger);
            add(drink);
            add(side);
        }

        private JButton createButton(String imagePath) {
            ImageIcon toGoIcon = new ImageIcon(imagePath);
            JButton button = new JButton(toGoIcon);
            button.setPreferredSize(new Dimension(200, 220));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
//            button.addActionListener(e -> {
//                CategoryView categoryView = new CategoryView();
//                categoryView.printFrame();
//                setVisible(false);
//            });
            return button;
        }
    }
}
