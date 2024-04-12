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
public class ServiceDTO {
    private String serviceID;
    private String staffID;
    private String name;
    private boolean status;

    public ServiceDTO() {
    }

    public ServiceDTO(String serviceID, String staffID, String name, boolean status) {
        this.serviceID = serviceID;
        this.staffID = staffID;
        this.name = name;
        this.status = status;
    }

    public String getServiceID() {
        return serviceID;
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

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
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
