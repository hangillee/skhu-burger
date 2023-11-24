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

public class CouponPanel extends JPanel {
    public CouponPanel() {
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

            ImagePanel imagePanel = new ImagePanel();
            add(imagePanel, BorderLayout.CENTER);

            ButtonPanel buttonPanel = new ButtonPanel();
            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    static class LabelPanel extends JPanel {
        public LabelPanel() {
            setPreferredSize(new Dimension(480, 100));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BorderLayout());

            JLabel text = new JLabel("쿠폰의 바코드를 스캔해주세요.");
            text.setFont(new Font("Gothic", Font.BOLD, 36));
            text.setHorizontalAlignment(JLabel.CENTER);

            add(text, BorderLayout.CENTER);
        }
    }

    static class ImagePanel extends JPanel {
        public ImagePanel() {
            setPreferredSize(new Dimension(480, 400));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BorderLayout());

            JLabel image = new JLabel();
            image.setPreferredSize(new Dimension(480, 400));
            image.setHorizontalAlignment(JLabel.CENTER);
            image.setIcon(new ImageIcon("src/main/resources/coupon/Info.png"));

            add(image, BorderLayout.CENTER);
        }
    }

    static class ButtonPanel extends JPanel {
        public ButtonPanel() {
            setPreferredSize(new Dimension(480, 120));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton cancel = createButton("src/main/resources/Cancel.png");
            cancel.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "MainPanel");
            });

            add(cancel);
        }

        private JButton createButton(String imagePath) {
            ImageIcon toGoIcon = new ImageIcon(imagePath);
            JButton button = new JButton(toGoIcon);
            button.setPreferredSize(new Dimension(158, 65));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.next(MainFrame.getPanels());
            });
            return button;
        }
    }
}
