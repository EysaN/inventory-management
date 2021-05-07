package me.geik.invmng.config;

import com.mongodb.client.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Connection {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        try (MongoClient client = conn.getMongoClient()) {
            List<Document> databases = client.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> System.out.println(db.toJson()));
        }
    }

}
