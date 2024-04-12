/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ACER
 */
public class DistrictDTO {
    private String districtID;
    private String cityID;
    private String districtName;

    public DistrictDTO(String districtID, String cityID, String districtName) {
        this.districtID = districtID;
        this.cityID = cityID;
        this.districtName = districtName;
    }

    public DistrictDTO() {
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    
}
