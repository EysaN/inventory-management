package me.geik.invmng.cart;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Cart {
    @Id
    private ObjectId id = new ObjectId();
    private boolean closed;
    private Integer amount;
    private String description;
    private String createDate;
    private String updateDate;
}
