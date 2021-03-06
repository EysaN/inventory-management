package me.geik.invmng.cart;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cart", path = "cart")
public interface CartRepository extends MongoRepository<Cart, String> {
    List<Cart> findAll();

    Cart findCartById(@Param("id") String id);
}
