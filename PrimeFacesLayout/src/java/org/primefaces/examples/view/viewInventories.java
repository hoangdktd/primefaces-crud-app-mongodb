/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.examples.view;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author techsupport
 */
@ManagedBean
@RequestScoped
public class viewInventories {

    /**
     * Creates a new instance of viewInventories
     */
    public List<Inventory> inventoryList = new ArrayList<Inventory>();
    private Inventory inventory;
    private Inventory inventories[];
    private List<Inventory> filteredInventory;

    public List<Inventory> getFilteredInventory() {
        return filteredInventory;
    }

    public void setFilteredInventory(List<Inventory> filteredInventory) {
        this.filteredInventory = filteredInventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory[] getInventories() {
        return inventories;
    }

    public void setInventories(Inventory[] inventories) {
        this.inventories = inventories;
    }

//    public void getDatatoTable() throws UnknownHostException {
//       
//    }
    public  List<Inventory> getInventoryList() throws UnknownHostException {
        DB db = (new MongoClient("localhost", 27017).getDB("shopData"));
        DBCollection dBCollection = db.getCollection("Item");
        DBCollection dBCollection1 = db.getCollection("ItemDetails");
        DBCursor dBCursor = dBCollection.find();
        DBCursor dBCursor1=dBCollection1.find();
        while (dBCursor.hasNext() && dBCursor1.hasNext()) {
            
             DBObject ob= dBCursor.next();
             DBObject object= dBCursor1.next();
             
             if (ob.get("itemCode").toString() == object.get("itemCode".toString())) {
                Inventory inventory = new Inventory();
                 inventory.setItemCode(ob.get("itemCode").toString());
                 inventory.setItemName(ob.get("itemName").toString());
                 inventory.setProducerName( ob.get("manufacturerName").toString());
                 String x=object.get("Quantity").toString();
                 int i=Integer.parseInt(x);
                 inventory.setQty(i);
                 inventoryList.add(inventory);
            }
                 
                 
   
        }  
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
