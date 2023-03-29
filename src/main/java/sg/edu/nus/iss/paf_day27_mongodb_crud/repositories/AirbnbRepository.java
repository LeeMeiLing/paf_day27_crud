package sg.edu.nus.iss.paf_day27_mongodb_crud.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;

@Repository
public class AirbnbRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String C_AIRBNB= "airbnb";


    // Creating Text Index for Text Search
    /*
     * db.airbnb.createIndex({ description: "text" })
     */
    public void addTextIndex(){

        //Retrieving the collection on which you want to create the index
        MongoCollection<Document> collection = mongoTemplate.getDb().getCollection(C_AIRBNB);
        //Creating an index
        collection.createIndex(Indexes.text("description"));

        System.out.println(">>>>>added text index ");
    }

    /*
     * db.airbnb.find(
        {
            $text:{ $search: "windy" }
        },
        {
            score:{ $meta: "textScore" }
            
        })
        .sort({score:1})
     */
    public List<Document> findByTextSearch(){

        TextCriteria textCriteria= TextCriteria.forDefaultLanguage()
                .matchingPhrase("windy");

        TextQuery query= TextQuery.queryText(textCriteria);

        List<Document> results = mongoTemplate.find(query,Document.class, C_AIRBNB);

        System.out.println(">>>> result is " + results);

        return results;
    }

}
