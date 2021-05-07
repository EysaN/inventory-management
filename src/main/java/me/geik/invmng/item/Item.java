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
    private String name;
    private Double cartId;
    private Double price;
    private Integer qty;
    private boolean available;
    private String createDate;
    private String updateDate;

}
