
package me.geik.invmng.config;

import com.mongodb.client.MongoClient;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * This class is only used to test the connectivity of the mongodb URI
 */

public class Connection {
    // Click the green run button to start
    public static void main(String[] args) throws IOException {
        // new connection instance is initialize from MongoConnection class
        MongoConnection conn = new MongoConnection();
        // connection client is created to handle the request to mongodb
        try (MongoClient client = conn.getMongoClient()) {
            // connecting to mongodb and listing all exiting databases
            List<Document> databases = client.listDatabases().into(new ArrayList<>());
            // printing each database on the console
            databases.forEach(db -> System.out.println(db.toJson()));
        }
    }

}
