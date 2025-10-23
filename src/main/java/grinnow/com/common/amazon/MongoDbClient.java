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

/**
 * MongoDB Atlas (cloud.mongodb.com) 클라이언트
 * JDK 1.8 + eGovFramework + SLF4J 로깅
 */
@Component("mongoDbClient")
public class MongoDbClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbClient.class);
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
            
            LOGGER.info("[MongoDB] uri : {}", uri);
            LOGGER.info("[MongoDB] Connecting to cluster: {}", cluster);

            ConnectionString connectionString = new ConnectionString(uri);
            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            collection = database.getCollection("treeprice");

            LOGGER.info("[MongoDB] Connection established successfully (DB: {}, Collection: treeprice)", dbName);

        } catch (Exception e) {
            LOGGER.error("[MongoDB] Connection failed: {}", e.getMessage(), e);
        }
    }

    /**
     * Map 형태 데이터를 MongoDB에 저장
     */
    public void saveTreePrice(Map<String, Object> map) {
        try {
            if (collection == null) {
                LOGGER.warn("[MongoDB] Collection not initialized. Insert skipped.");
                return;
            }

            Document doc = new Document(map);
            doc.put("sync_time", LocalDateTime.now().toString());
            collection.insertOne(doc);

            LOGGER.debug("[MongoDB] Inserted document: {}", doc.toJson());
        } catch (Exception e) {
            LOGGER.error("[MongoDB] Insert failed: {}", e.getMessage(), e);
        }
    }
}