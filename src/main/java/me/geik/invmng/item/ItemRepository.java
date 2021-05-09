package me.geik.invmng.item;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// No need for implementation, using this interface only gives us CRUD operations due to Spring Data
@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends MongoRepository<Item, ObjectId> {

    /**
     * The following functions allows us to make database transactions
     * like find, insert, delete, update
     * */

    List<Item> findAll();

    Item findItemById(@Param("id") ObjectId id);

    List<Item> findItemsByCartId(@Param("cartId") Double cartId);

    List<Item> findItemsByName(@Param("name") String Name);

    void deleteById(@Param("id") ObjectId id);

    void deleteAllByCartId(@Param("cartId") Double cartId);

    void deleteAllByName(@Param("name") String name);

}
