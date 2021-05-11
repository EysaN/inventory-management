package me.geik.invmng.cart;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cart")
public class Cart {
    @Id
    private ObjectId id = new ObjectId();
    private boolean closed;
    private Double amount;
    private String description;
    private String createDate;
    private String updateDate;

    public Cart(){}

    public Cart(boolean closed, Double amount, String description, String createDate, String updateDate) {
        this.closed = closed;
        this.amount = amount;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
