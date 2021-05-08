package me.geik.invmng.config;

import me.geik.invmng.cart.Cart;
import me.geik.invmng.cart.CartRepository;
import me.geik.invmng.item.Item;
import me.geik.invmng.item.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {ItemRepository.class, CartRepository.class})
@Configuration
public class MongoDBConfig {
    /*
    * This class will initialize new empty collections if not found when the program starts
    * */
    @Bean
    CommandLineRunner commandLineRunner(ItemRepository itemRepository, CartRepository cartRepository) {
        return strings -> {
            itemRepository.save(new Item());
            cartRepository.save(new Cart());
        };
    }

}
