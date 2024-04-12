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
public class ServiceDetailDTO {
    private String serviceDetailID;
    private String staffID;
    private String name;
    private boolean status;

    public ServiceDetailDTO() {
    }

    public ServiceDetailDTO(String serviceDetailID, String staffID, String name, boolean status) {
        this.serviceDetailID = serviceDetailID;
        this.staffID = staffID;
        this.name = name;
        this.status = status;
    }

    public String getServiceDetailID() {
        return serviceDetailID;
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

    public void setServiceDetailID(String serviceDetailID) {
        this.serviceDetailID = serviceDetailID;
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
