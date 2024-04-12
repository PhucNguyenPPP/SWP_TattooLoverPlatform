/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class NotificationDTO {
    private String userID;
    private String userFullName;
    private String description;
    private String dateTime;
    private String status;

    public NotificationDTO() {
    }

    public NotificationDTO(String userID, String userFullName, String description, String dateTime, String status) {
        this.userID = userID;
        this.userFullName = userFullName;
        this.description = description;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getDescription() {
        return description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
  
    
    
}
