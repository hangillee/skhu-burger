package view;

import domain.Menu;
import domain.OrderMenu;
import domain.OrderSheet;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import repository.MenuRepository;
import repository.OrderMenuRepository;
import repository.OrderSheetRepository;

public class MenuPanel extends JPanel {
    private static final Map<String, Integer> orderMenus = new HashMap<>();
    private static final MenuRepository menuRepository = new MenuRepository();
    private static final OrderMenuRepository orderMenuRepository = new OrderMenuRepository();
    private static final OrderSheetRepository orderSheetRepository = new OrderSheetRepository();
    private static OrderSheet orderSheet;

    private static JPanel shoppingBasket;
    private static JPanel backgroundPanel;
    private static String category = "setmenu";

    public MenuPanel() {
        int menuCount = getMenuCount();
        setBackground(Color.decode("#FFB347"));
        setLayout(null);

        shoppingBasket = getShoppingBasket();
        JScrollPane basketScroll = new JScrollPane(shoppingBasket);
        basketScroll.setBounds(20, 490, 500, 260);

        JPanel buttonPanel = getButtonPanel(500, 60);
        JButton otherMenu = createButton("images/OtherMenu.png");
        otherMenu.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
            cardLayout.show(MainFrame.getPanels(), "CategoryPanel");
        });
        JButton purchase = createButton("images/Purchase.png");
        purchase.addActionListener(e -> {
            if (orderMenus.isEmpty()) {
                createPopup();
            } else {
                orderSheet = new OrderSheet();
                for (String menuName : orderMenus.keySet()) {
                    Menu menu = menuRepository.findByName(menuName);
                    OrderMenu orderMenu = new OrderMenu();
                    orderMenu.setMenu(menu);
                    orderMenu.setOrderSheet(orderSheet);
                    orderSheetRepository.save(orderSheet);
                    orderMenuRepository.save(orderMenu);
                }
                CardLayout cardLayout = (CardLayout) MainFrame.getPanels().getLayout();
                MainFrame.getPanels().add(new PaymentPanel(), "PaymentPanel");
                cardLayout.show(MainFrame.getPanels(), "PaymentPanel");
            }
        });
        buttonPanel.add(otherMenu);
        buttonPanel.add(purchase);
        buttonPanel.setBounds(20, 430, 500, 60);

        backgroundPanel = getButtonPanel(480, 720);
        int firstIndex = 0;
        if (category.equals("setmenu")) {
            firstIndex = 1;
        } else if (category.equals("burger")) {
            firstIndex = 7;
        }
        for (int i = firstIndex; i <= (menuCount + firstIndex - 1); i++) {
            backgroundPanel.add(new MenuButton(i));
        }
        backgroundPanel.setBounds(20, 30, 480, 720);
        JScrollPane menuScroll = new JScrollPane(backgroundPanel);
        menuScroll.setBounds(20, 30, 500, 400);

        add(menuScroll);
        add(buttonPanel);
        add(basketScroll);
    }

    public static void repaintBasket() {
        orderMenus.clear();
        shoppingBasket.removeAll();
        shoppingBasket.revalidate();
        shoppingBasket.repaint();
    }

    public static void setCategory(String category) {
        MenuPanel.category = category;
    }

    public static void repaintMenuButtons() {
        backgroundPanel.removeAll();
        int menuCount = getMenuCount();
        int firstIndex = 0;
        if (category.equals("setmenu")) {
            firstIndex = 1;
        } else if (category.equals("burger")) {
            firstIndex = 7;
        } else if (category.equals("drink")) {
            firstIndex = 13;
        } else if (category.equals("side")) {
            firstIndex = 18;
        }
        for (int i = firstIndex; i <= (menuCount + firstIndex - 1); i++) {
            backgroundPanel.add(new MenuButton(i));
        }
        backgroundPanel.revalidate();
        backgroundPanel.repaint();
    }

    private void createPopup() {
        JFrame popup = new JFrame();
        popup.setBackground(Color.decode("#FFB347"));
        popup.setSize(350, 200);
        popup.setLocationRelativeTo(null);

        JLabel label = new JLabel("메뉴를 선택해주세요!");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Gothic", Font.BOLD, 20));
        label.setPreferredSize(new Dimension(350, 100));

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#FFB347"));
        panel.add(label);

        JButton okButton = new JButton("확인");
        okButton.setFocusPainted(false);
        okButton.setContentAreaFilled(false);
        okButton.setPreferredSize(new Dimension(100, 50));
        okButton.addActionListener(e -> popup.dispose());
        panel.add(okButton);

        popup.add(panel);
        popup.setVisible(true);
    }

    private JPanel getButtonPanel(int width, int height) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(width, height));
        buttonPanel.setBackground(Color.decode("#FFFFFF"));
        buttonPanel.setLayout(new GridLayout(0, 2));
        return buttonPanel;
    }

    private static JPanel getShoppingBasket() {
        JPanel shoppingBasket = new JPanel();
        shoppingBasket.setPreferredSize(new Dimension(480, 720));
        shoppingBasket.setBackground(Color.decode("#FFFFFF"));
        shoppingBasket.setLayout(new FlowLayout(FlowLayout.CENTER));
        shoppingBasket.setBounds(20, 490, 480, 720);
        return shoppingBasket;
    }

    static class MenuButton extends JButton {
        public MenuButton(int index) {
            super(String.valueOf(index),
                    new ImageIcon(MenuButton.class.getClassLoader()
                            .getResource("images/" + category + "/" + index + ".png")));
            setPreferredSize(new Dimension(240, 240));
            setBorderPainted(false);
            setFocusPainted(false);
            setContentAreaFilled(false);

            JPanel menuListPanel = new JPanel();
            menuListPanel.setPreferredSize(new Dimension(480, 60));
            menuListPanel.setBackground(Color.decode("#FFFFFF"));
            menuListPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#FFB347")));
            menuListPanel.setLayout(null);

            JLabel menuInfo = new JLabel();
            menuInfo.setFont(new Font("Gothic", Font.PLAIN, 20));
            menuInfo.setPreferredSize(new Dimension(380, 60));
            DecimalFormat decimalFormat = new DecimalFormat("###,###");
            menuInfo.setBounds(10, 0, 380, 60);
            menuListPanel.add(menuInfo);

            JButton plusButton = new JButton("+");
            plusButton.setPreferredSize(new Dimension(30, 30));
            plusButton.setBounds(400, 15, 30, 30);
            menuListPanel.add(plusButton);
            plusButton.addActionListener(e -> {
                Menu menu = menuRepository.findById((long) index);
                if (!orderMenus.containsKey(menu.getName())) {
                    orderMenus.put(menu.getName(), 1);
                    menuInfo.setText(menu.getName()
                            + "  "
                            + orderMenus.get(menu.getName())
                            + " 개   "
                            + decimalFormat.format(menu.getPrice()) + "원");
                    shoppingBasket.add(menuListPanel);
                } else {
                    orderMenus.put(menu.getName(), orderMenus.get(menu.getName()) + 1);
                    int totalPrice = orderMenus.get(menu.getName()) * menu.getPrice();
                    menuInfo.setText(menu.getName()
                            + "  "
                            + orderMenus.get(menu.getName())
                            + " 개   "
                            + decimalFormat.format(totalPrice) + "원");
                }
                shoppingBasket.revalidate();
                shoppingBasket.repaint();
            });

            JButton minusButton = new JButton("-");
            minusButton.setPreferredSize(new Dimension(30, 30));
            minusButton.setBounds(440, 15, 30, 30);
            menuListPanel.add(minusButton);
            minusButton.addActionListener(e -> {
                Menu menu = menuRepository.findById((long) index);
                if (orderMenus.containsKey(menu.getName())) {
                    if (orderMenus.get(menu.getName()) == 1) {
                        orderMenus.remove(menu.getName());
                        shoppingBasket.remove(menuListPanel);
                    } else {
                        orderMenus.put(menu.getName(), orderMenus.get(menu.getName()) - 1);
                        int totalPrice = orderMenus.get(menu.getName()) * menu.getPrice();
                        menuInfo.setText(menu.getName()
                                + "  "
                                + orderMenus.get(menu.getName())
                                + " 개   "
                                + decimalFormat.format(totalPrice) + "원");
                    }
                }
                shoppingBasket.revalidate();
                shoppingBasket.repaint();
            });

            addActionListener(e -> {
                Menu menu = menuRepository.findById((long) index);
                if (!orderMenus.containsKey(menu.getName())) {
                    orderMenus.put(menu.getName(), 1);
                    menuInfo.setText(menu.getName()
                            + "  "
                            + orderMenus.get(menu.getName())
                            + " 개   "
                            + decimalFormat.format(menu.getPrice()) + "원");
                    shoppingBasket.add(menuListPanel);
                } else {
                    orderMenus.put(menu.getName(), orderMenus.get(menu.getName()) + 1);
                    int totalPrice = orderMenus.get(menu.getName()) * menu.getPrice();
                    menuInfo.setText(menu.getName()
                            + "  "
                            + orderMenus.get(menu.getName())
                            + " 개   "
                            + decimalFormat.format(totalPrice) + "원");
                }
                shoppingBasket.revalidate();
                shoppingBasket.repaint();
            });
        }
    }

    private static int getMenuCount() {
        List<Menu> menus = menuRepository.findByCategory(category);
        return menus.size();
    }

    private JButton createButton(String imagePath) {
        URL url = getClass().getClassLoader().getResource(imagePath);
        ImageIcon icon = new ImageIcon(url);
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(250, 60));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    public static Map<String, Integer> getOrderMenus() {
        return orderMenus;
    }

    public static int getTotalPrice() {
        int totalPrice = 0;
        for (String menuName : orderMenus.keySet()) {
            Menu menu = menuRepository.findByName(menuName);
            totalPrice += (menu.getPrice() * orderMenus.get(menuName));
        }
        return totalPrice;
    }

    public static OrderSheet getOrderSheet() {
        return orderSheet;
    }
}
