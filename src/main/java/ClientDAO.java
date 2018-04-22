import java.util.ArrayList;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.google.gson.Gson;


public class ClientDAO implements DAO{
    MongoClient mongoClient;
    DB db;
    DBCollection coll;

    public ClientDAO() {
        this.mongoClient = new MongoClient("localhost", 27017);
        this.db = mongoClient.getDB( "TourAgencydb" );
        this.coll = db.getCollection("ClientCollection");
    }

    public ClientDAO (String db_name, String collection) {
        this.mongoClient = new MongoClient("localhost", 27017);
        this.db = mongoClient.getDB( db_name );
        this.coll = db.getCollection(collection);
    }

    @Override
    public ArrayList<Client> getAll() {
        DBCursor cursor = coll.find();
        ArrayList<Client> Clients = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                DBObject dbobj = cursor.next();
                Client emp = (new Gson()).fromJson(dbobj.toString(), Client.class);
                Clients.add(emp);
            }
        } finally {
            cursor.close();
        }
        return Clients;
    }

    @Override
    public Object getEntityByID(String id) {
        BasicDBObject query = new BasicDBObject("id", id);

        DBCursor cursor = coll.find(query);
        Client Client = null;
        try {
            while(cursor.hasNext()) {
                DBObject dbobj = cursor.next();
                Client emp = (new Gson()).fromJson(dbobj.toString(), Client.class);
                Client = emp;
            }
        } finally {
            cursor.close();
        }
        return Client;
    }

    @Override
    public Object update(Object entity) {
        Client Client = (Client)entity;
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject()
                .append("fullName", Client.getFullName())
                .append("Address", Client.getAddress())
                .append("age", Client.getAge()));

        BasicDBObject searchQuery = new BasicDBObject().append("id", Client.getId());

        coll.update(searchQuery, newDocument);
        return true;
    }

    @Override
    public boolean delete(String id) {
        BasicDBObject query = new BasicDBObject("id", id);

        DBObject cursor = coll.findOne(query);
        coll.remove(cursor);
        return true;
    }

    @Override
    public boolean create(Object entity) {
        try {
            Client Client = (Client)entity;
            BasicDBObject doc = new BasicDBObject()
                    .append("id", Client.getId())
                    .append("fullName", Client.getFullName())
                    .append("Address", Client.getAddress())
                    .append("age", Client.getAge());



            coll.insert(doc);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}

