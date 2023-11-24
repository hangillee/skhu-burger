package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CategoryPanel extends JPanel {
    public CategoryPanel() {
        setBackground(Color.decode("#FFB347"));
        setLayout(null);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(30, 30, 480, 720);
        add(backgroundPanel);
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
        }
    }

    static class LabelPanel extends JPanel {
        public LabelPanel() {
            setPreferredSize(new Dimension(480, 100));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BorderLayout());

            JLabel text = new JLabel("주문할 메뉴를 선택해주세요.");
            text.setFont(new Font("Gothic", Font.BOLD, 36));
            text.setHorizontalAlignment(JLabel.CENTER);

            add(text, BorderLayout.CENTER);
        }
    }

    static class ButtonPanel extends JPanel {
        public ButtonPanel() {
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            JButton setMenu = createButton("src/main/resources/category/Set.png", 200, 220);
            setMenu.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "MenuPanel");
            });
            JButton burger = createButton("src/main/resources/category/Burger.png", 200, 220);
            JButton drink = createButton("src/main/resources/category/Drink.png", 200, 220);
            JButton side = createButton("src/main/resources/category/Side.png", 200, 220);
            JButton coupon = createButton("src/main/resources/category/Coupon.png", 410, 120);
            coupon.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "CouponPanel");
            });

            add(setMenu);
            add(burger);
            add(drink);
            add(side);
            add(coupon);
        }

        private JButton createButton(String imagePath, int width, int height) {
            ImageIcon icon = new ImageIcon(imagePath);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(width, height));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            return button;
        }
    }
}
