package me.geik.invmng.cart;

import me.geik.invmng.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bson.types.ObjectId;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CartRepository cart;

    public CartController(CartRepository cart){
        this.cart = cart;
    }

    @GetMapping("/carts")
    public @ResponseBody
    List<Cart> findAllItems() throws DataAccessException {
        logger.info("Getting all carts");
        return new ArrayList<>(this.cart.findAll());
    }

    @GetMapping("/cart/{id}")
    public @ResponseBody Cart findItemById(@PathVariable String id) throws DataAccessException {
        logger.info("Getting a cart by id");
        return this.cart.findCartById(id);
    }

    @GetMapping("/cart/save")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void saveCart(
            @RequestParam(name="id", required = false, defaultValue = "0") String id,
            @RequestParam(name="closed", required = false, defaultValue = "false") boolean closed,
            @RequestParam(name="amount", required = false, defaultValue = "0.0") Double amount,
            @RequestParam(name="description", required = false, defaultValue = "new cart") String description,
            @RequestParam(name="items", required = false, defaultValue = "item0,item1") List<Item> items
    ) throws DataAccessException {
        Cart cart;
        // if no id is provided, it will insert new item with new auto generated id
        if(id.equals("0")){
            cart = new Cart(closed, amount, description, getDateNow(), getDateNow());
        } else {
            // add new cart with default values for the date fields
            cart = new Cart(id, closed, amount, description, getDateNow(), items);
        }

        this.cart.save(cart);
        logger.info("Adding/Updating cart with id: " + cart.getId());
    }

    // helper function to get the current date instance
    public String getDateNow() {
        Instant instant = Instant.now();
        return instant.toString();
    }

}
