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
    private Double cartId;

    public Item(Double cartId) {
        this.cartId = cartId;
    }


}
