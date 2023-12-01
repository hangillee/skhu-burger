package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardPanel extends JPanel {

    public CardPanel() {
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
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            ImagePanel imagePanel = new ImagePanel();
            add(imagePanel);

            LabelPanel labelPanel = new LabelPanel();
            add(labelPanel);

            ButtonPanel buttonPanel = new ButtonPanel();
            add(buttonPanel);
        }
    }

    static class LabelPanel extends JPanel {
        public LabelPanel() {
            setPreferredSize(new Dimension(480, 100));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(null);

            JLabel firstText = new JLabel("IC칩이 위쪽을 향하도록");
            firstText.setFont(new Font("Gothic", Font.BOLD, 36));
            firstText.setHorizontalAlignment(JLabel.CENTER);
            firstText.setBounds(0, 0, 480, 50);

            JLabel secondText = new JLabel("카드를 리더기에 넣어주세요.");
            secondText.setFont(new Font("Gothic", Font.BOLD, 36));
            secondText.setHorizontalAlignment(JLabel.CENTER);
            secondText.setBounds(0, 50, 480, 50);

            add(firstText);
            add(secondText);
        }
    }

    static class ImagePanel extends JPanel {
        public ImagePanel() {
            setPreferredSize(new Dimension(480, 380));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(null);

            JLabel image = new JLabel();
            image.setPreferredSize(new Dimension(480, 380));
            image.setHorizontalAlignment(JLabel.CENTER);
            URL url = getClass().getClassLoader().getResource("images/payment/CreditCard.png");
            image.setIcon(new ImageIcon(url));
            image.setBounds(0, 25, 480, 380);

            add(image);
        }
    }

    static class ButtonPanel extends JPanel {
        public ButtonPanel() {
            setPreferredSize(new Dimension(480, 120));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton cancel = createButton("images/Cancel.png", 158, 64);
            cancel.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "PaymentPanel");
            });

            JButton receipt = createButton("images/payment/Receipt.png", 200, 64);
            receipt.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "ResultPanel");
            });

            add(cancel);
            add(receipt);
        }

        private JButton createButton(String imagePath, int width, int height) {
            URL url = getClass().getClassLoader().getResource(imagePath);
            ImageIcon icon = new ImageIcon(url);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(width, height));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            return button;
        }
    }
}
