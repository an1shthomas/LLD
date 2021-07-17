package com.an1shthomas.menustream;

import com.an1shthomas.menustream.menu.Menu;
import com.an1shthomas.menustream.menu.MenuBuilder;
import com.an1shthomas.menustream.menu.MenuItemType;
import com.an1shthomas.menustream.stream.MenuStream;
import com.an1shthomas.menustream.stream.MenuStreamImpl;
import com.an1shthomas.menustream.util.Record;
import org.json.JSONObject;

public class Demo {

    public static void main(String[] args) {

        final MenuBuilder menuBuilder = new MenuBuilder();
        MenuStream stream = new MenuStreamImpl();
        String nextLine = stream.nextLine();
        while (nextLine != null) {
            if (nextLine == "") {
                nextLine = stream.nextLine();
                continue;
            }
            Record.RecordBuilder record = new Record.RecordBuilder();
            record.setId(nextLine);
            String type = stream.nextLine();
            record.setItemType(type);
            record.setName(stream.nextLine());
            if (MenuItemType.valueOf(type) != MenuItemType.CATEGORY) {
                record.setPrice(stream.nextLine());
            }
            nextLine = stream.nextLine();
            while (nextLine != null && nextLine != "") {
                record.addItems(nextLine);
                nextLine = stream.nextLine();
            }
            menuBuilder.handle(record.build());
        }

        Menu menu = menuBuilder.build();
        System.out.println("Menu by category: ");
        System.out.println(new JSONObject(menu.getMenuByCategory()).toString(2));
    }
}
