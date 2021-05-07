package me.geik.invmng.item;

import org.bson.Document;
import org.bson.types.ObjectId;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    private final ItemRepository item;

    public ItemController(ItemRepository item){
        this.item = item;
    }

    @GetMapping("/items")
    @ResponseBody
    public List<Item> findAllItems() throws DataAccessException {
        return new ArrayList<>(this.item.findAll());
    }

    @GetMapping("/item/{id}")
    @ResponseBody
    public Item findItemById(@PathVariable ObjectId id) throws DataAccessException {
        return this.item.findItemById(id);
    }

    @GetMapping("/itemsByCart/{cartId}")
    @ResponseBody
    public List<Item> findItemByCartId(@PathVariable Double cartId) throws DataAccessException {
        return this.item.findItemsByCartId(cartId);
    }

    @GetMapping("/itemsByName/{name}")
    @ResponseBody
    public List<Item> findItemByName(@PathVariable String name) throws DataAccessException {
        return this.item.findItemsByName(name);
    }

    @GetMapping("/item/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void saveItem(
            @RequestParam(name="name", required = false, defaultValue = "item") String name,
            @RequestParam(name="cartId", required = false, defaultValue = "0") Double cartId,
            @RequestParam(name="price", required = false, defaultValue = "0") Double price,
            @RequestParam(name="qty", required = false, defaultValue = "0") Integer qty,
            @RequestParam(name="available", required = false, defaultValue = "false") boolean available,
            @RequestParam(name="createDate", required = false, defaultValue = "0") String createDate,
            @RequestParam(name="updateDate", required = false, defaultValue = "0") String updateDate
    ) throws DataAccessException {

        // add default values for the date fields
        if (createDate.equals("0")){
            createDate = getDateNow();
        }
        if (updateDate.equals("0")){
            updateDate = getDateNow();
        }

        this.item.insert(new Item(name, cartId, price, qty, available, createDate, updateDate));
    }

    @GetMapping("/item/delete/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable ObjectId id) throws DataAccessException {
        this.item.deleteById(id);
    }

    @GetMapping("/itemsDeleteByCart/{cartId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByCartId(@PathVariable Double cartId) throws DataAccessException {
        this.item.deleteAllByCartId(cartId);
    }

    @GetMapping("/itemsDeleteByName/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByName(@PathVariable String name) throws DataAccessException {
        this.item.deleteAllByName(name);
    }


    public String getDateNow() {
        Instant instant = Instant.now();
        return instant.toString();
    }


}
