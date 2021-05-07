package me.geik.invmng.item;

import lombok.Data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Item {

    @Id
    private ObjectId id = new ObjectId();
    private Double cartId, price;
    private Integer qty;
    private boolean available;
    private String createDat, updateDate;

    public Item(Double cartId, Double price, Integer qty, boolean available, String createDat, String updateDate) {
        this.cartId = cartId;
        this.price = price;
        this.qty = qty;
        this.available = available;
        this.createDat = createDat;
        this.updateDate = updateDate;
    }
}
