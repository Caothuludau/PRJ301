/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author CaoThuLuDau
 */
public class Tag {
    private int tagID;
    private String tagName;
    private int status;

    // For Update
    public Tag(int tagID, String tagName, int status) {
        this.tagID = tagID;
        this.tagName = tagName;
        this.status = status;
    }
    
    //For Insert
    public Tag(String tagName, int status) {
        this.tagName = tagName;
        this.status = status;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
    
}
