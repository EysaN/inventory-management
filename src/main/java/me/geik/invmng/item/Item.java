package me.geik.invmng.item;

import lombok.Data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// This annotation is described in README file
@Data
/**
 * This annotation makes spring-boot-mongodb knows that this class represents a mongodb document
 * which is to be stored within the item collection of the database
 * */
@Document(collection = "item")
public class Item {

    // we are defining that the "id" attribute is linked to (_id) field in our document
    @Id
    private String id = new ObjectId().toHexString();
    private String name;
    // Creating an index on attribute cartId to optimize search by cart id
    @Indexed(unique = true)
    private String cartId;
    private Double price;
    private Integer qty;
    private boolean available;
    private String createDate;
    private String updateDate;

    // empty constructor
    public Item() {}

    // customized constructor without id attribute which will be auto generated
    public Item(String name, String cartId, Double price, Integer qty, boolean available, String createDate, String updateDate) {
        this.name = name;
        this.cartId = cartId;
        this.price = price;
        this.qty = qty;
        this.available = available;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // customized constructor without id and createDate attributes
    public Item(String id, String name, String cartId, Double price, Integer qty, boolean available, String updateDate) {
        this.id = id;
        this.name = name;
        this.cartId = cartId;
        this.price = price;
        this.qty = qty;
        this.available = available;
        this.updateDate = updateDate;
    }
}
