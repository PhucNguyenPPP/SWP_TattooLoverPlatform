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
public class ImgDTO {

    private String imgID;
    private String imgName;
    private String imgLink;
    private String role;
    private String studioID;
    private String userID;
    private String serviceID;
    private String serviceDetailID;

    public ImgDTO() {
    }

    public ImgDTO(String imgID, String imgName, String imgLink, String role, String studioID, String userID, String serviceID, String serviceDetailID) {
        this.imgID = imgID;
        this.imgName = imgName;
        this.imgLink = imgLink;
        this.role = role;
        this.studioID = studioID;
        this.userID = userID;
        this.serviceID = serviceID;
        this.serviceDetailID = serviceDetailID;
    }

    public String getImgID() {
        return imgID;
    }

    public String getImgName() {
        return imgName;
    }

    public String getImgLink() {
        return imgLink;
    }

    public String getRole() {
        return role;
    }

    public String getStudioID() {
        return studioID;
    }

    public String getUserID() {
        return userID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getServiceDetailID() {
        return serviceDetailID;
    }

    public void setImgID(String imgID) {
        this.imgID = imgID;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStudioID(String studioID) {
        this.studioID = studioID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public void setServiceDetailID(String serviceDetailID) {
        this.serviceDetailID = serviceDetailID;
    }
    
    

}
