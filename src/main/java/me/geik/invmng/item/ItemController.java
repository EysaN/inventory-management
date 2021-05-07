package me.geik.invmng.item;

import org.bson.types.ObjectId;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ItemController {

    private final ItemRepository item;

    public ItemController(ItemRepository item){
        this.item = item;
    }

    @GetMapping("/items")
    public @ResponseBody List<Item> findAllItems() throws DataAccessException {
        return new ArrayList<>(this.item.findAll());
    }

    @GetMapping("/item/{id}")
    public @ResponseBody Item findItemById(@PathVariable ObjectId id) throws DataAccessException {
        return this.item.findItemById(id);
    }

    @GetMapping("/items/{cartId}")
    public @ResponseBody List<Item> findItemByCartId(@PathVariable Double cartId) throws DataAccessException {
        return this.item.findItemsByCartId(cartId);
    }

}
