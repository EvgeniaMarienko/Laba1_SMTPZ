import com.mongodb.MongoClient;
import com.mongodb.DB;

import java.util.ArrayList;
import junit.framework.TestCase;



public class TourDAOTests extends TestCase{

    DAO<Tour> dao = new TourDAO("test_mydb", "testCollection");


    public void testInsertIntoDb() {
        Tour t1 = new Tour("odin","Africa, safari, animals", "Africa", "nice chill", 2000,"imge" );

        assertEquals(true, dao.create(t1));
    }


    public void testGetFromDb() {
        Tour t1 = new Tour("odin","Africa, safari, animals", "Africa", "nice chill", 2000,"imge" );
        dao.create(t1);
        Tour get = (Tour) dao.getEntityByID(t1.getId());
        assertEquals(t1.getName(), get.getName());
        assertEquals(t1.getKeys(), get.getKeys());
        assertEquals(t1.getImgUri(), get.getImgUri());
    }


    public void testGetAllFromDb() {
        Tour t1 = new Tour("odin","Africa, safari, animals", "Africa", "nice chill", 2000,"imge");
        Tour t2 = new Tour("dva","Africa, safari, animals", "Africa", "nice chill", 2000,"imge");
        dao.create(t1);
        dao.create(t2);
        ArrayList<Tour> list = dao.getAll();
        assertEquals(list.contains(t2), true );
        assertEquals(list.contains(t1), true );
    }


    public void testUpdateDb() {
        Tour t1 = new Tour("odin","Africa, safari, animals", "Africa", "nice chill", 2000,"imge");
        dao.create(t1);
        t1.setImgUri("SUPER_URL");
        assertEquals(dao.update(t1), true );
        Tour a = (Tour)dao.getEntityByID("odin");
        assertEquals(a.getImgUri(), "SUPER_URL");
    }


    public void testDeleteDb() {
        Tour t1 = new Tour("odins","Africa, safari, animals", "Africa", "nice chill", 2000,"imge");
        dao.create(t1);
        dao.delete(t1.getId());
        Tour a = (Tour)dao.getEntityByID("odins");
        assertEquals(a, null);
    }

    @Override
    protected void tearDown() throws Exception {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB( "test_mydb" );
        db.getCollection("testCollection").drop();
        super.tearDown();
    }

}