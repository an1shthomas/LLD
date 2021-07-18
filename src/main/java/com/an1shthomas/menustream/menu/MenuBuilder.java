package com.an1shthomas.menustream.menu;

import com.an1shthomas.menustream.util.Record;

public class MenuBuilder {

    private Menu menu;

    public MenuBuilder() {
        this.menu = new Menu();
    }

    public void handle(Record record) {
        MenuItem menuItem = new MenuItem(record.getId());
        menuItem.setPrice(record.getPrice());
        menuItem.setName(record.getName());
        menuItem.setType(record.getItemType());
        menuItem.addLinkedItems(record.getItems());
        menu.addMenuItem(record.getId(), menuItem);
    }

    public Menu build() {
        return this.menu;
    }
}
