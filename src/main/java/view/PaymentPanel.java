package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public class PaymentPanel extends JPanel {
    private static final Map<String, Integer> orderMenus = MenuPanel.getOrderMenus();

    public PaymentPanel() {
        MainFrame.getPanels().add(new ResultPanel(), "ResultPanel");
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

            MenuInfoLabelPanel menuInfoPanel = new MenuInfoLabelPanel();
            add(menuInfoPanel);

            PaymentSelectPanel paymentSelectPanel = new PaymentSelectPanel();
            add(paymentSelectPanel);

            ButtonPanel buttonPanel = new ButtonPanel();
            add(buttonPanel);
        }
    }

    static class MenuInfoLabelPanel extends JPanel {
        public MenuInfoLabelPanel() {
            setPreferredSize(new Dimension(480, 310));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(null);

            JLabel menuInfo = new JLabel("주문 내역");
            menuInfo.setPreferredSize(new Dimension(480, 65));
            menuInfo.setFont(new Font("Gothic", Font.BOLD, 24));
            menuInfo.setHorizontalAlignment(JLabel.CENTER);
            menuInfo.setBounds(0, 0, 480, 65);
            add(menuInfo);

            JTextArea menuList = new JTextArea();
            menuList.setPreferredSize(new Dimension(360, 200));
            menuList.setColumns(20);
            menuList.setFont(new Font("Gothic", Font.PLAIN, 20));
            orderMenus.forEach((menuName, menuCount) -> menuList.append(menuName + " " + menuCount + "개\n"));
            menuList.setEditable(false);

            JScrollPane menuScroll = new JScrollPane(menuList);
            menuScroll.setPreferredSize(new Dimension(360, 200));
            Border mainBorder = new SoftBevelBorder(SoftBevelBorder.LOWERED);
            Border paddingBorder = new EmptyBorder(5, 5, 5, 0);
            menuScroll.setBorder(new CompoundBorder(mainBorder, paddingBorder));
            menuScroll.setBounds(20, 65, 440, 200);
            add(menuScroll);

            DecimalFormat decimalFormat = new DecimalFormat("###,###");
            JLabel priceInfo = new JLabel("총 결제 금액 " + decimalFormat.format(MenuPanel.getTotalPrice()) + "원");
            priceInfo.setPreferredSize(new Dimension(480, 30));
            priceInfo.setFont(new Font("Gothic", Font.BOLD, 24));
            priceInfo.setHorizontalAlignment(JLabel.CENTER);
            priceInfo.setBounds(0, 280, 480, 30);
            add(priceInfo);
        }
    }

    static class PaymentSelectPanel extends JPanel {
        public PaymentSelectPanel() {
            setPreferredSize(new Dimension(480, 310));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(null);

            JLabel menuInfo = new JLabel("결제 방법을 선택해주세요.");
            menuInfo.setPreferredSize(new Dimension(480, 65));
            menuInfo.setFont(new Font("Gothic", Font.BOLD, 24));
            menuInfo.setHorizontalAlignment(JLabel.CENTER);
            menuInfo.setBounds(0, 0, 480, 65);
            add(menuInfo);

            URL cashUrl = getClass().getClassLoader().getResource("images/payment/Cash.png");
            ImageIcon cashIcon = new ImageIcon(cashUrl);
            JButton cashButton = new JButton(cashIcon);
            cashButton.setBorderPainted(false);
            cashButton.setFocusPainted(false);
            cashButton.setContentAreaFilled(false);
            cashButton.setPreferredSize(new Dimension(210, 200));
            cashButton.setBounds(20, 65, 210, 200);
            cashButton.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "CashPanel");
            });
            add(cashButton);

            URL cardUrl = getClass().getClassLoader().getResource("images/payment/Card.png");
            ImageIcon cardIcon = new ImageIcon(cardUrl);
            JButton cardButton = new JButton(cardIcon);
            cardButton.setBorderPainted(false);
            cardButton.setFocusPainted(false);
            cardButton.setContentAreaFilled(false);
            cardButton.setPreferredSize(new Dimension(210, 200));
            cardButton.setBounds(250, 65, 210, 200);
            cardButton.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "CardPanel");
            });
            add(cardButton);
        }
    }

    static class ButtonPanel extends JPanel {
        public ButtonPanel() {
            setPreferredSize(new Dimension(480, 100));
            setBackground(Color.decode("#FFFFFF"));
            setLayout(new BorderLayout());

            URL url = getClass().getClassLoader().getResource("images/Cancel.png");
            ImageIcon icon = new ImageIcon(url);
            JButton cancelButton = new JButton(icon);
            cancelButton.setBorderPainted(false);
            cancelButton.setFocusPainted(false);
            cancelButton.setContentAreaFilled(false);
            cancelButton.setPreferredSize(new Dimension(158, 65));
            cancelButton.setBounds(0, 0, 158, 65);
            cancelButton.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                cardLayout.show(MainFrame.getPanels(), "MenuPanel");
            });
            add(cancelButton);
        }
    }
}
