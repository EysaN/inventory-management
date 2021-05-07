package me.geik.invmng.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bson.types.ObjectId;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public @ResponseBody Cart findItemById(@PathVariable ObjectId id) throws DataAccessException {
        logger.info("Getting a cart by id");
        return this.cart.findCartById(id);
    }

}
