package me.geik.invmng.item;

import org.bson.types.ObjectId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the REST APIs and their functionalities
 * We are implementing the mongodb operations declared in our repository
 * And processing the requests to return the data to the user
 * */

@RestController
public class ItemController {

    // using logger to insert events into console
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ItemRepository item;

    // custom constructor for the repository object
    public ItemController(ItemRepository item){
        this.item = item;
    }

    @GetMapping("/items/all")
    @ResponseBody
    public List<Item> findAllItems() throws DataAccessException {
        logger.info("Getting all items");
        return new ArrayList<>(this.item.findAll());
    }

    @GetMapping("/items/{id}")
    @ResponseBody
    public Item findItemById(@PathVariable String id) throws DataAccessException {
        logger.info("Getting an item by id: " + id);
        return this.item.findItemById(id);
    }

    @GetMapping("/items/findByCart/{cartId}")
    @ResponseBody
    public List<Item> findItemByCartId(@PathVariable Double cartId) throws DataAccessException {
        logger.info("Getting an item by CartId: " + cartId);
        return this.item.findItemsByCartId(cartId);
    }

    @GetMapping("/items/findByName/{name}")
    @ResponseBody
    public List<Item> findItemByName(@PathVariable String name) throws DataAccessException {
        logger.info("Getting an item by Name: " + name);
        return this.item.findItemsByName(name);
    }

    @GetMapping("/item/save")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void saveItem(
            @RequestParam(name="id", required = false, defaultValue = "0") String id,
            @RequestParam(name="name", required = false, defaultValue = "item") String name,
            @RequestParam(name="cartId", required = false, defaultValue = "0") String cartId,
            @RequestParam(name="price", required = false, defaultValue = "0") Double price,
            @RequestParam(name="qty", required = false, defaultValue = "0") Integer qty,
            @RequestParam(name="available", required = false, defaultValue = "false") boolean available
    ) throws DataAccessException {
        Item item;
        // if no id is provided, it will insert new item with new auto generated id
        if(id.equals("0")){
            item = new Item(name, cartId, price, qty, available, getDateNow(), getDateNow());
        } else {
            // update the item with provided id, or add it of not id not found
            item = new Item(id, name, cartId, price, qty, available, getDateNow());
        }
        // this save is not declared in the repository interface
        // but we can use directly here, it acts like an upsert operation
        this.item.save(item);
        logger.info("Adding/Updating item with id: " + item.getId());
    }

    @GetMapping("/item/delete/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id) throws DataAccessException {
        logger.info("Deleting an item by id: " + id);
        this.item.deleteById(id);
    }

    @GetMapping("/items/deleteByCart/{cartId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByCartId(@PathVariable Double cartId) throws DataAccessException {
        logger.info("Deleting all items by CartId: " + cartId);
        this.item.deleteAllByCartId(cartId);
    }

    @GetMapping("/items/deleteByName/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByName(@PathVariable String name) throws DataAccessException {
        logger.info("Deleting all items by Name: " + name);
        this.item.deleteAllByName(name);
    }

    // helper function to get the current date instance
    public String getDateNow() {
        Instant instant = Instant.now();
        return instant.toString();
    }

    // create new cart if id is not found

}
