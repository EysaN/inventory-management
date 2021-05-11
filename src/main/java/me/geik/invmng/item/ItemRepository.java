package me.geik.invmng.item;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// No need for implementation, using this interface only gives us CRUD operations due to Spring Data
@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends MongoRepository<Item, String> {

    /**
     * The following functions allows us to make database transactions
     * like find, insert, delete, update
     * We do not have to declare them explicitly but I put them here for more clarity of code
     * */

    List<Item> findAll();

    Item findItemById(@Param("id") String id);

    List<Item> findItemsByCartId(@Param("cartId") Double cartId);

    List<Item> findItemsByName(@Param("name") String Name);

    void deleteById(@Param("id") String id);

    void deleteAllByCartId(@Param("cartId") Double cartId);

    void deleteAllByName(@Param("name") String name);

}
