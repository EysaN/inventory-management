package me.geik.invmng.item;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ItemController {

    private final ItemRepository items;

    public ItemController(ItemRepository items){
        this.items = items;
    }

    @GetMapping("/items")
    public @ResponseBody Items findAllItems()  throws DataAccessException {
        Items items = new Items();
        items.getItemsList().addAll(this.items.findAll());
        return items;
    }

}
