package me.geik.invmng.item;

import lombok.Data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "item")
public class Item {

    @Id
    private ObjectId id = new ObjectId();
    private String name;
    @Indexed(unique = true)
    private Double cartId;
    private Double price;
    private Integer qty;
    private boolean available;
    private String createDate;
    private String updateDate;

    public Item() {}

    public Item(String name, Double cartId, Double price, Integer qty, boolean available, String createDate, String updateDate) {
        this.name = name;
        this.cartId = cartId;
        this.price = price;
        this.qty = qty;
        this.available = available;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
