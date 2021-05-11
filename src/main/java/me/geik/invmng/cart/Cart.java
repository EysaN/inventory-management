package me.geik.invmng.cart;

import lombok.Data;

import me.geik.invmng.item.Item;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "cart")
public class Cart {
    @Id
    private String id = new ObjectId().toHexString();
    private boolean closed;
    private Double amount;
    private String description;
    private String createDate;
    private String updateDate;
    private List<Item> items;

    public Cart(){}

    public Cart(boolean closed, Double amount, String description, String createDate, String updateDate) {
        this.closed = closed;
        this.amount = amount;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Cart(String id, boolean closed, Double amount, String description, String updateDate, List<Item> items) {
        this.id = id;
        this.closed = closed;
        this.amount = amount;
        this.description = description;
        this.updateDate = updateDate;
        this.items = items;
    }
}
