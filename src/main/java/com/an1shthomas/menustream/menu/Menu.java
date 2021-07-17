package com.an1shthomas.menustream.menu;

import java.util.*;

public class Menu {
    private final Map<Integer, MenuItem> menuItems;

    public Menu() {
        this.menuItems = new HashMap<>();
    }

    public Map<Integer, MenuItem> getMenuItems() {
        return menuItems;
    }

    public Map<Integer, MenuCategory> getMenuByCategory() {
        final Map<Integer, MenuCategory> categories = new HashMap<>();
        this.menuItems.values().stream()
                .filter(item -> item.getType().equals(MenuItemType.CATEGORY)).forEach(item -> {
            MenuCategory menuCategory = new MenuCategory(item.getId());
            menuCategory.setName(item.getName());
            // Add all the linked menu items
            for (Integer linkedItemId : item.getLinkedItems()) {
                MenuItem menuItem = this.menuItems.get(linkedItemId);
                menuCategory.getMenuItems().add(menuItem);
                // For each linked menu item, add options if necessary
                menuItem.getLinkedItems().stream().forEach(m -> menuItem.addLinkedMenuItem(this.menuItems.get(m)));
            }
            categories.put(item.getId(), menuCategory);
        });
        return categories;
    }
}
