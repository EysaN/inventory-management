package me.geik.invmng.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnection {

    private final MongoClient client;

    public MongoClient getMongoClient() {
        return client;
    }

    public MongoConnection(){
        ConsoleDecoration.printSection("INITIALIZATION");
        this.client = createMongoClient();
    }

    private MongoClient createMongoClient() {
        // create connection string instance
        ConnectionString connectionString = new ConnectionString(System.getProperty("mongodb.uri"));
        // handle the translation to and from BSON for this POJO
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        // add the default codec registry, which contains all the default codecs
        // They can handle all the major types in Java-like Boolean, Double, String, BigDecimal
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        // wrap all my settings together
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        return MongoClients.create(clientSettings);
    }
}
