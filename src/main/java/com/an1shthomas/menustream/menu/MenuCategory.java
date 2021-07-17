package com.an1shthomas.menustream.menu;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class MenuCategory {
    private final int id;
    private String name;
    private final List<MenuItem> menuItems;

    public MenuCategory(int id) {
        this.id = id;
        menuItems = new ArrayList<>();
    }
}
