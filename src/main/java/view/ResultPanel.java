package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {
    public ResultPanel() {
        setBackground(Color.decode("#FFB347"));
        setLayout(null);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(30, 30, 480, 720);
        add(backgroundPanel);
        setVisible(true);
    }

    static class BackgroundPanel extends JPanel {
        public BackgroundPanel() {
            setPreferredSize(new Dimension(480, 440));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            LabelPanel labelPanel = new LabelPanel();
            add(labelPanel);

            ButtonPanel buttonPanel = new ButtonPanel();
            add(buttonPanel);
        }
    }

    static class LabelPanel extends JPanel {
        public LabelPanel() {
            setPreferredSize(new Dimension(480, 300));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(null);

            JLabel firstText = new JLabel("주문이 성공적으로");
            firstText.setFont(new Font("Gothic", Font.BOLD, 36));
            firstText.setHorizontalAlignment(JLabel.CENTER);
            firstText.setBounds(0, 20, 480, 50);

            JLabel secondText = new JLabel("완료되었습니다!");
            secondText.setFont(new Font("Gothic", Font.BOLD, 36));
            secondText.setHorizontalAlignment(JLabel.CENTER);
            secondText.setBounds(0, 70, 480, 50);

            DecimalFormat decimalFormat = new DecimalFormat("0000");
            Long orderNumber = MenuPanel.getOrderSheet().getId();
            JLabel orderNumberText = new JLabel(decimalFormat.format(orderNumber));
            orderNumberText.setFont(new Font("Gothic", Font.BOLD, 72));
            orderNumberText.setHorizontalAlignment(JLabel.CENTER);
            orderNumberText.setBounds(0, 210, 480, 60);

            JLabel thirdText = new JLabel("영수증을 챙기시고");
            thirdText.setFont(new Font("Gothic", Font.BOLD, 24));
            thirdText.setHorizontalAlignment(JLabel.CENTER);
            thirdText.setBounds(0, 360, 480, 40);

            JLabel fourthText = new JLabel("대기 번호를 확인해주세요!");
            fourthText.setFont(new Font("Gothic", Font.BOLD, 24));
            fourthText.setHorizontalAlignment(JLabel.CENTER);
            fourthText.setBounds(0, 400, 480, 40);

            add(firstText);
            add(secondText);
            add(orderNumberText);
            add(thirdText);
            add(fourthText);
        }
    }

    static class ButtonPanel extends JPanel {
        public ButtonPanel() {
            setPreferredSize(new Dimension(480, 100));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(null);

            URL url = getClass().getClassLoader().getResource("images/Ok.png");
            ImageIcon icon = new ImageIcon(url);
            JButton okButton = new JButton(icon);
            okButton.setBorderPainted(false);
            okButton.setFocusPainted(false);
            okButton.setContentAreaFilled(false);
            okButton.setPreferredSize(new Dimension(158, 65));
            okButton.setBounds(160, 0, 158, 100);
            okButton.addActionListener(e -> {
                MenuPanel.repaintBasket();
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "MainPanel");
            });
            add(okButton);
        }
    }
}
