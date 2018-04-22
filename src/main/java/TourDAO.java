import java.util.ArrayList;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.google.gson.Gson;


public class TourDAO implements DAO{
    MongoClient mongoClient;
    DB db;
    DBCollection coll;

    public TourDAO() {
        this.mongoClient = new MongoClient("localhost", 27017);
        this.db = mongoClient.getDB( "TourAgencydb" );
        this.coll = db.getCollection("TourCollection");
    }

    public TourDAO (String db_name, String collection) {
        this.mongoClient = new MongoClient("localhost", 27017);
        this.db = mongoClient.getDB( db_name );
        this.coll = db.getCollection(collection);
    }

    @Override
    public ArrayList<Tour> getAll() {
        DBCursor cursor = coll.find();
        ArrayList<Tour> Tours = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                DBObject dbobj = cursor.next();
                Tour emp = (new Gson()).fromJson(dbobj.toString(), Tour.class);
                Tours.add(emp);
            }
        } finally {
            cursor.close();
        }
        return Tours;
    }

    @Override
    public Object getEntityByID(String id) {
        BasicDBObject query = new BasicDBObject("id", id);

        DBCursor cursor = coll.find(query);
        Tour Tour = null;
        try {
            while(cursor.hasNext()) {
                DBObject dbobj = cursor.next();
                Tour emp = (new Gson()).fromJson(dbobj.toString(), Tour.class);
                Tour = emp;
            }
        } finally {
            cursor.close();
        }
        return Tour;
    }

    @Override
    public Object update(Object entity) {
        Tour Tour = (Tour)entity;
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("keys", Tour.getKeys())
                                                           .append("name", Tour.getName())
                                                           .append("description", Tour.getDescription())
                                                            .append("price",Tour.getPrice())
                                                            .append("imgUri",Tour.getImgUri()));

        BasicDBObject searchQuery = new BasicDBObject().append("id", Tour.getId());

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
            Tour Tour = (Tour)entity;
            BasicDBObject doc = new BasicDBObject()
                    .append("keys", Tour.getKeys())
                    .append("name", Tour.getName())
                    .append("id", Tour.getId())
                    .append("description", Tour.getDescription())
                    .append("price",Tour.getPrice())
                    .append("imgUri",Tour.getImgUri());


            coll.insert(doc);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
