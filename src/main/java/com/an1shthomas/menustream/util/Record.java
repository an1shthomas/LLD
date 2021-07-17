package com.an1shthomas.menustream.util;

import com.an1shthomas.menustream.menu.MenuItemType;

import java.util.HashSet;
import java.util.Set;

public class Record {
    private final int id;
    private final MenuItemType itemType;
    private final String name;
    private final double price;
    private final Set<Integer> items;

    public Record(int id, MenuItemType itemType, String name, double price, Set<Integer> items) {
        this.id = id;
        this.itemType = itemType;
        this.name = name;
        this.price = price;
        this.items = new HashSet<>(items);
    }

    public int getId() {
        return id;
    }

    public MenuItemType getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Set<Integer> getItems() {
        return items;
    }

    public static class RecordBuilder {
        private int id;
        private MenuItemType itemType;
        private String name;
        private double price = 0.0d;
        private Set<Integer> items = new HashSet<>();

        public RecordBuilder setId(String id) {
            this.id = Integer.parseInt(id);
            return this;
        }

        public RecordBuilder setItemType(String itemType) {
            this.itemType = MenuItemType.valueOf(itemType);
            return this;
        }

        public RecordBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RecordBuilder setPrice(String price) {
            this.price = Double.parseDouble(price);
            return this;
        }

        public RecordBuilder addItems(String id) {
            this.items.add(Integer.parseInt(id));
            return this;
        }

        public Record build() {
            return new Record(id, itemType, name, price, items);
        }


    }
}
