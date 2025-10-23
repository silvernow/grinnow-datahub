package grinnow.com.common.amazon;

import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import grinnow.com.cmm.service.Globals;

@Component("mongoDbClient")
public class MongoDbClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbClient.class);

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @PostConstruct
    public void init() {
        try {
            String cluster = Globals.MONGO_CLUSTER;
            String dbName = Globals.MONGO_DB;
            String user = Globals.MONGO_USER;
            String pw = Globals.MONGO_PW;
            String ssl = Globals.MONGO_SSL;

            String uri = String.format(
                "mongodb+srv://%s:%s@%s/%s?retryWrites=true&w=majority&ssl=%s",
                user, pw, cluster, dbName, ssl
            );

            ConnectionString connectionString = new ConnectionString(uri);
            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(dbName);
            collection = database.getCollection("tree_price");

            LOGGER.info("[MongoDB] Connection established successfully.");

        } catch (Exception e) {
            LOGGER.error("[MongoDB] Connection failed: {}", e.getMessage());
        }
    }

    /**
     * MongoDB 연결 상태 확인용 ping 메서드
     */
    public void ping() {
        if (database == null) {
            throw new IllegalStateException("MongoDB not initialized.");
        }

        Document pingCommand = new Document("ping", 1);
        Document result = database.runCommand(pingCommand);
        LOGGER.info("[MongoDB] Ping result: {}", result.toJson());
    }

    /**
     * Map 형태의 데이터를 MongoDB에 저장
     */
    public void saveTreePrice(Map<String, Object> map) {
        try {
            if (collection == null) {
                throw new IllegalStateException("MongoDB collection is not initialized.");
            }
            Document doc = new Document(map);
            doc.put("sync_time", LocalDateTime.now().toString());
            collection.insertOne(doc);
            LOGGER.info("[MongoDB] Document inserted successfully: {}", doc);
        } catch (Exception e) {
            LOGGER.error("[MongoDB] Insert failed: {}", e.getMessage());
        }
    }
}