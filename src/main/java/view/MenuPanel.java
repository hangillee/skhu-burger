package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        setBackground(Color.decode("#FFB347"));
        setLayout(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(30, 30, 480, 720);
        JScrollPane scrollPane = new JScrollPane(backgroundPanel);
        scrollPane.setBounds(20, 30, 500, 400);

        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.setBounds(20, 425, 500, 320);

        add(scrollPane);
        add(shoppingBasket);
    }

    static class BackgroundPanel extends JPanel {
        public BackgroundPanel() {
            setPreferredSize(new Dimension(480, 720));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new GridLayout(3, 2));

            for (int i = 1; i <= 6; i++) {
                add(new MenuButton(i));
            }
        }
    }

    static class MenuButton extends JButton {
        public MenuButton(int index) {
            super(null, new ImageIcon("src/main/resources/setmenu/" + index + ".png"));
            setPreferredSize(new Dimension(240, 240));
            setBorderPainted(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "MainPanel");
            });
        }
    }

    static class ShoppingBasket extends JPanel {
        public ShoppingBasket() {
            setPreferredSize(new Dimension(500, 360));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new FlowLayout(FlowLayout.CENTER));
            add(new ButtonPanel());
        }
    }

    static class ButtonPanel extends JPanel {
        public ButtonPanel() {
            setPreferredSize(new Dimension(500, 60));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new GridLayout(1, 2));
            JButton otherMenu = createButton("src/main/resources/OtherMenu.png");
            otherMenu.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "CategoryPanel");
            });
            JButton purchase = createButton("src/main/resources/Purchase.png");
            add(otherMenu);
            add(purchase);
        }

        private JButton createButton(String imagePath) {
            ImageIcon icon = new ImageIcon(imagePath);
            JButton button = new JButton(icon);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            return button;
        }
    }
}
