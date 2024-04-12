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
public class ServiceSizeDTO {
    private String serviceSizeID;
    private String staffID;
    private String name;
    private boolean status;

    public ServiceSizeDTO() {
    }

    public ServiceSizeDTO(String serviceSizeID, String staffID, String name, boolean status) {
        this.serviceSizeID = serviceSizeID;
        this.staffID = staffID;
        this.name = name;
        this.status = status;
    }

    public String getServiceSizeID() {
        return serviceSizeID;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setServiceSizeID(String serviceSizeID) {
        this.serviceSizeID = serviceSizeID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
