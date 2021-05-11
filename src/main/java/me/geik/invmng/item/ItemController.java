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
    public Item findItemById(@PathVariable ObjectId id) throws DataAccessException {
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

    @GetMapping("/item/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void saveItem(
            @RequestParam(name="name", required = false, defaultValue = "item") String name,
            @RequestParam(name="cartId", required = false, defaultValue = "0") Double cartId,
            @RequestParam(name="price", required = false, defaultValue = "0") Double price,
            @RequestParam(name="qty", required = false, defaultValue = "0") Integer qty,
            @RequestParam(name="available", required = false, defaultValue = "false") boolean available
    ) throws DataAccessException {
        // add new item with default values for the date fields
        Item item = new Item(name, cartId, price, qty, available, getDateNow(), getDateNow());
        this.item.insert(item);
        logger.info("Adding new item with id: " + item.getId());
    }

    @GetMapping("/item/update/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(
            @RequestParam(name="name", required = false, defaultValue = "item") String name,
            @RequestParam(name="cartId", required = false, defaultValue = "0") Double cartId,
            @RequestParam(name="price", required = false, defaultValue = "0") Double price,
            @RequestParam(name="qty", required = false, defaultValue = "0") Integer qty,
            @RequestParam(name="available", required = false, defaultValue = "false") boolean available
    ) throws DataAccessException {
        // update selected item and set update date to now
        Item item = new Item(name, cartId, price, qty, available, getDateNow());
        this.item.insert(item);
        logger.info("Adding new item with id: " + item.getId());
    }

    @GetMapping("/item/delete/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable ObjectId id) throws DataAccessException {
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


}
