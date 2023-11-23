package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView extends JFrame {
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

            LogoPanel logoPanel = new LogoPanel();
            add(logoPanel, BorderLayout.SOUTH);
        }
    }

    static class LabelPanel extends JPanel {
        public LabelPanel() {
            setPreferredSize(new Dimension(480, 240));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BorderLayout());

            JLabel text = new JLabel("주문 방식을 선택해주세요");
            text.setFont(new Font("Gothic", Font.BOLD, 36));
            text.setHorizontalAlignment(JLabel.CENTER);

            add(text, BorderLayout.CENTER);
        }
    }

    static class ButtonPanel extends JPanel {
        public ButtonPanel() {
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));

            JButton inDoor = createButton("src/main/resources/main/InDoor.png");
            JButton toGo = createButton("src/main/resources/main/ToGo.png");

            add(inDoor);
            add(toGo);
        }

        private JButton createButton(String imagePath) {
            ImageIcon toGoIcon = new ImageIcon(imagePath);
            JButton button = new JButton(toGoIcon);
            button.setPreferredSize(new Dimension(158, 250));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.addActionListener(e -> {
                CategoryView categoryView = new CategoryView();
                categoryView.printFrame();
                setVisible(false);
            });
            return button;
        }
    }

    static class LogoPanel extends JPanel {
        public LogoPanel() {
            setPreferredSize(new Dimension(480, 200));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BorderLayout());

            ImageIcon logoIcon = new ImageIcon("src/main/resources/main/SkhuBurger.png");
            Image logoImage = logoIcon.getImage();
            logoImage.getScaledInstance(120, 170, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(logoImage);
            JLabel logo = new JLabel(logoIcon);
            logo.setHorizontalAlignment(JLabel.CENTER);

            add(logo, BorderLayout.CENTER);
        }
    }
}
