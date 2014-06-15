/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.primefaces.examples.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author techsupport
 */
@ManagedBean
@RequestScoped
public class joinCollection {

    /**
     * Creates a new instance of joinCollection
     */
    private String itemCode;
    private String itemDetailCode;
    private int qty; 

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDetailCode() {
        return itemDetailCode;
    }

    public void setItemDetailCode(String itemDetailCode) {
        this.itemDetailCode = itemDetailCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
}
