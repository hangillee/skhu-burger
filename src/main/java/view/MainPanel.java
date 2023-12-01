package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    public MainPanel() {
        setBackground(Color.decode("#FFB347"));
        setLayout(null);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(30, 30, 480, 720);
        add(backgroundPanel);
        setVisible(true);
    }

    static class BackgroundPanel extends JPanel {
        public BackgroundPanel() {
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

            JLabel text = new JLabel("주문 방식을 선택해주세요.");
            text.setFont(new Font("Gothic", Font.BOLD, 36));
            text.setHorizontalAlignment(JLabel.CENTER);

            add(text, BorderLayout.CENTER);
        }
    }

    static class ButtonPanel extends JPanel {
        public ButtonPanel() {
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));

            JButton inDoor = createButton("images/main/InDoor.png");
            JButton toGo = createButton("images/main/ToGo.png");

            add(inDoor);
            add(toGo);
        }

        private JButton createButton(String imagePath) {
            URL url = getClass().getClassLoader().getResource(imagePath);
            ImageIcon icon = new ImageIcon(url);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(158, 250));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "CategoryPanel");
            });
            return button;
        }
    }

    static class LogoPanel extends JPanel {
        public LogoPanel() {
            setPreferredSize(new Dimension(480, 200));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BorderLayout());

            URL url = getClass().getClassLoader().getResource("images/main/SkhuBurger.png");
            ImageIcon logoIcon = new ImageIcon(url);
            Image logoImage = logoIcon.getImage();
            logoImage.getScaledInstance(120, 170, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(logoImage);
            JLabel logo = new JLabel(logoIcon);
            logo.setHorizontalAlignment(JLabel.CENTER);

            add(logo, BorderLayout.CENTER);
        }
    }
}
