package domain;

public enum Category {
    BURGER,
    SIDE,
    DRINK,
    SET_MENU;

    public static Category getCategory(String category) {
        return switch (category) {
            case "burger" -> BURGER;
            case "side" -> SIDE;
            case "drink" -> DRINK;
            case "setmenu" -> SET_MENU;
            default -> null;
        };
    }
}
