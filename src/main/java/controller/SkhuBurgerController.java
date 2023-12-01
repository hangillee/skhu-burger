package controller;

import view.CardPanel;
import view.CashPanel;
import view.CategoryPanel;
import view.CouponPanel;
import view.MainFrame;
import view.MainPanel;

public class SkhuBurgerController {
    private final MainFrame mainFrame = new MainFrame();

    public void createPanels() {
        MainFrame.getPanels().add(new MainPanel(), "MainPanel");
        MainFrame.getPanels().add(new CategoryPanel(), "CategoryPanel");
        MainFrame.getPanels().add(new CouponPanel(), "CouponPanel");
        MainFrame.getPanels().add(new CardPanel(), "CardPanel");
        MainFrame.getPanels().add(new CashPanel(), "CashPanel");
    }

    public void printMainFrame() {
        createPanels();
        mainFrame.add(MainFrame.getPanels());
        mainFrame.setVisible(true);
    }
}
