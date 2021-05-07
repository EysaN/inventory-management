package me.geik.invmng.item;

import java.util.ArrayList;
import java.util.List;

public class Items {

    private List<Item> items;

    public List<Item> getItemsList(){
        if (items == null){
            items = new ArrayList<>();
        }
        return items;
    }
}
