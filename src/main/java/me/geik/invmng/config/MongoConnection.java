package me.geik.invmng.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnection {

    Resource resource = new ClassPathResource("/application.properties");
    Properties props = PropertiesLoaderUtils.loadProperties(resource);
    // retrieving mongodb uri value from properties
    private final String mongodbUri = props.getProperty("spring.data.mongodb.uri");

    private final MongoClient client;
    // initializing new mongodb client to handle requests
    public MongoClient getMongoClient() {
        return client;
    }

    // This constructor will return new client whenever an object from this class is created
    public MongoConnection() throws IOException {
        ConsoleDecoration.printSection("INITIALIZATION");
        this.client = createMongoClient();
    }

    // This is the method used to create new mongodb client
    private MongoClient createMongoClient() {
        // create connection string instance
        ConnectionString connectionString = new ConnectionString(mongodbUri);
        // handle the translation to and from BSON for this POJO
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        // add the default codec registry, which contains all the default codecs
        // They can handle all the major types in Java-like Boolean, Double, String, BigDecimal
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        // wrap all settings together
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        return MongoClients.create(clientSettings);
    }
}
