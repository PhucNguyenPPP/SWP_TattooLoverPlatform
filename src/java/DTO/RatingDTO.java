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
public class RatingDTO {
    private String studioID;
    private float rating;

    public RatingDTO() {
    }

    public RatingDTO(String studioID, float rating) {
        this.studioID = studioID;
        this.rating = rating;
    }

    public String getStudioID() {
        return studioID;
    }

    public float getRating() {
        return rating;
    }

    public void setStudioID(String studioID) {
        this.studioID = studioID;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

       
    
    
    
}
