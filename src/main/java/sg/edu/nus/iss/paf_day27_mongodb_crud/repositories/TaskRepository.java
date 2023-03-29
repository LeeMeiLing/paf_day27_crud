package sg.edu.nus.iss.paf_day27_mongodb_crud.repositories;

import java.sql.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import jakarta.json.JsonObject;

@Repository
public class TaskRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    /*
     * db.activities.insertOne({  task: 'abc', priority: 1, dueDate: ISODate('yyyy-MM-dd') })
     */
    public Document insertTask(JsonObject task){

        Document toInsert = Document.parse(task.toString());

        toInsert.put("dueDate", Date.valueOf(toInsert.getString("dueDate")));
		
        Document inserted = mongoTemplate.insert(toInsert,"activities");// return the inserted object
        return inserted;
        
    }

    /*
     * db.activities.find({ 
     *  task: { $exists:false} 
     * })
     */
    public void findWithoutTask(){

        Criteria criteria = Criteria.where("task").exists(false);

        Query query = Query.query(criteria);

        List<Document> results = mongoTemplate.find(query, Document.class,"activities");

        System.out.println(">>>>results:" + results);
    }


    /*
     * db.activities.deleteMany({
     *  task : { $exists : false }
     * })
     */
    public void deleteActivitiesWithoutTask(){

        Criteria criteria = Criteria.where("task").exists(false);

        Query query = Query.query(criteria);

        DeleteResult deleted = mongoTemplate.remove(query,"activities");

        System.out.printf( ">>> delete count: %d\n", deleted.getDeletedCount());
        System.out.printf( ">>> acknowledged: %b\n", deleted.wasAcknowledged());

    }


    /*
     * db.activities.updateMany({
     *  { priority : { $gte : 7 } },
     *  { $set : { important : true } }
     * )
     */
    public void updateActivity(){

        Criteria criteria = Criteria.where("priority").gte(7);

        Query query = Query.query(criteria);

        Update updateOps = new Update().set("important", true);

        UpdateResult result = mongoTemplate.updateMulti(query, updateOps, Document.class, "activities");

        System.out.printf( ">>> matched count: %d\n", result.getMatchedCount());
        System.out.printf( ">>> modified count: %d\n", result.getModifiedCount());
        System.out.printf( ">>> acknowledged: %b\n", result.wasAcknowledged());
        
    }
}
