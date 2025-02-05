package br.com.fiap.fiappi.core.restaurant.enums;

public enum KitchenTypeEnum {

    TRADITIONAL("Traditional kitchen with a fixed menu or à la carte"),
    INDUSTRIAL("Industrial kitchen for large-scale meal production"),
    PRODUCTION("Production kitchen for distribution to other units"),
    ASSEMBLY("Assembly kitchen that finishes pre-prepared dishes"),
    FINE_DINING("Fine dining kitchen with refined techniques"),
    FAST_FOOD("Fast food kitchen with quick and standardized production"),
    CATERING("Catering and buffet kitchen for events"),
    DARK_KITCHEN("Dark kitchen with no in-person service"),
    SUSTAINABLE("Sustainable kitchen focusing on organic ingredients and zero waste");

    private final String description;

    KitchenTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
