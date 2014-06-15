/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appwithmongo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.List;

/**
 *
 * @author techsupport
 */
public class NewDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        DB db=(new MongoClient("localhost",27017)).getDB("shopData");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        boolean flag=false;
        while (!flag) {            
            flag=authentication(db, bufferedReader);
        }
        
    }
    private static boolean authentication(DB db,BufferedReader bufferedReader)throws IOException{
        boolean flag=true;
        System.out.println("User :");//username eranda
        String user=bufferedReader.readLine();
        System.out.println("Password :"); // password 123era
        String password=bufferedReader.readLine();
        
        if(db.authenticate(user, password.toCharArray())){
            DBCollection dBCollection=db.getCollection("items");
            String command=null;
            while (true) {                
                System.out.println("What do you want to do ?");
                command=bufferedReader.readLine();
                if (command.equals("exit")) {
                    break;
                }else if (command.equals("findAll")) {
                    findAll(dBCollection);
                }else if(command.equals("insertData")){
                    insertData(bufferedReader,dBCollection);
                }else if (command.equals("insert")) {
                    insert(bufferedReader,dBCollection);
                }else if (command.equals("update")) {
                    update(bufferedReader,dBCollection);
                }else if (command.equals("delete")) {
                    delete(bufferedReader,dBCollection);
                }else if(command.equals("join")){
                    join(bufferedReader, dBCollection);
                }
            }
        }else{
            System.out.println("Invalide Username/Password");
            flag=false;
        }
        
        return flag;
    }
    private static void findAll(DBCollection dBCollection){
        DBCursor dBCursor=dBCollection.find();
        while (dBCursor.hasNext()) {            
            System.out.println(dBCursor.next());
//            System.out.println(dBCursor.next().get("manufacturerName"));

            
        }
    }
    private static void insertData(BufferedReader bufferedReader,DBCollection dBCollection)throws IOException{
        System.out.println("JASON Script Here :");
        dBCollection.insert((DBObject)JSON.parse(bufferedReader.readLine()));
        
    }
     private static void insert(BufferedReader bufferedReader,DBCollection dBCollection) throws IOException{
         System.out.print("Channel Name :");
         String Name= bufferedReader.readLine();
         DBObject dBObject=new BasicDBObject();
         dBObject.put("name", Name);
         System.out.println(" ");
         DBObject adddBObject=new BasicDBObject();
         System.out.print("Add Channel Sub :");
         String subscriptions=bufferedReader.readLine();           
         adddBObject.put("subscriptions", Integer.parseInt(subscriptions));
         System.out.println(" ");
         System.out.print("Channel Tutorials :");
         String tutorials=bufferedReader.readLine();
         String[] temp=tutorials.split(",");
         int i=0;
         BasicDBList basicDBOList=new BasicDBList();
         DBObject newDBObject=null;
         while(i<temp.length){
             newDBObject=new BasicDBObject();
             newDBObject.put("name",temp [i++]);
             basicDBOList.add(newDBObject);
         }
         dBObject.put("tutorials", basicDBOList.toArray());
         dBCollection.insert(dBObject);
     }
     private static void delete(BufferedReader bufferedReader,DBCollection dBCollection) throws IOException{
         System.out.println("What should I delete?");
         DBObject dBObject=new BasicDBObject();
         dBObject.put("_id", bufferedReader.readLine());
         dBCollection.remove(dBObject);
     }
     private static void update(BufferedReader bufferedReader,DBCollection dBCollection) throws IOException{
         System.out.print("Update From Name :");
         DBObject dBObject=new BasicDBObject();
         dBObject.put("name", bufferedReader.readLine());
         System.out.println(" ");
         System.out.print("Update to Name :");
         DBObject todbBObject=new BasicDBObject();
         todbBObject.put("name", bufferedReader.readLine());
         DBObject updateDBObject=new BasicDBObject();
         updateDBObject.put("$set", todbBObject);
         dBCollection.update(dBObject, updateDBObject);
     }
     private static void join(BufferedReader bufferedReader,DBCollection dBCollection){
         DBObject dBObject=new BasicDBObject("$match",new BasicDBObject("itemCode","11"));
         System.out.println(dBObject);
     }
}
