package controller;

import view.CategoryPanel;
import view.CouponPanel;
import view.MainFrame;
import view.MainPanel;
import view.MenuPanel;

public class SkhuBurgerController {
    private final MainFrame mainFrame = new MainFrame();

    public void createPanels() {
        MainFrame.getPanels().add(new MainPanel(), "MainPanel");
        MainFrame.getPanels().add(new CategoryPanel(), "CategoryPanel");
        MainFrame.getPanels().add(new CouponPanel(), "CouponPanel");
        MainFrame.getPanels().add(new MenuPanel(), "MenuPanel");
    }

    public void printMainFrame() {
        createPanels();
        mainFrame.add(MainFrame.getPanels());
        mainFrame.setVisible(true);
    }
}
