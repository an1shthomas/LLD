package com.an1shthomas.menustream.menu;

import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class MenuItem {
    private final int id;
    private String name;
    private double price;
    private MenuItemType type;
    private final Set<Integer> linkedItems;
    private Set<MenuItem> linkedMenuItems;

    public MenuItem(int id) {
        this.id = id;
        linkedItems = new HashSet<>();
        linkedMenuItems = new HashSet<>();
    }

    public void addLinkedItems(Set<Integer> linkedItems) {
        this.linkedItems.addAll(linkedItems);
    }

    public void addLinkedMenuItem(MenuItem menuItem) {
        this.linkedMenuItems.add(menuItem);
    }
}
