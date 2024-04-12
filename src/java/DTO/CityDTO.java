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
public class CityDTO {
    private String cityID;
    private String cityName;

    public CityDTO() {
    }

    public CityDTO(String cityID, String cityName) {
        this.cityID = cityID;
        this.cityName = cityName;
    }

    public String getCityID() {
        return cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    
    
    
}
