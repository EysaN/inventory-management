package me.geik.invmng.item;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends MongoRepository<Item, ObjectId> {

    List<Item> findAll();

    Item findItemById(@Param("id") ObjectId id);

    List<Item> findItemsByCartId(@Param("cartId") Double cartId);
}
