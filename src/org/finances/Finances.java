/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.finances;

import java.util.Date;

/**
 *
 * @author vasil
 */
public class Finances {
    private int id;
    private double amount;
    private String Source;
    private String Type;
    private Date date;
    private String notes;

    public Finances(int id, double amount, String Source, String Type, Date date, String notes) {
        this.id = id;
        this.amount = amount;
        this.Source = Source;
        this.Type = Type;
        this.date = date;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getSource() {
        return Source;
    }

    public String getType() {
        return Type;
    }

    public Date getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }
    
    
    
    
}
