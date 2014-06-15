/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appwithmongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

/**
 *
 * @author techsupport
 */
public class QueryDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        DB dB=(new MongoClient("localhost",27017)).getDB("testdatabase");
        DBCollection dBCollection=dB.getCollection("newcol");
//        BasicDBObject basicDBObject=new BasicDBObject();
//        basicDBObject.put("name", "Chamath");
        DBCursor dBCursor=dBCollection.find();
        while (dBCursor.hasNext()) {            
            System.out.println(dBCursor.next());
        }
    }
    
}
