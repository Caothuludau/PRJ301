/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author CaoThuLuDau
 */
public class Category {
    private int cateID;
    private String cateName, description;
    private int status;

    public Category() {
    }
    
    //For Update
    public Category(int cateID, String cateName, String description, int status) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.description = description;
        this.status = status;
    }

    
    //For Insert
    public Category(String cateName, String description, int status) {
        this.cateName = cateName;
        this.description = description;
        this.status = status;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
