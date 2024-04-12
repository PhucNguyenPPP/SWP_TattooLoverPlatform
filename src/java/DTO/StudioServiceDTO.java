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
public class StudioServiceDTO {

    private String studioServiceID;
    private String studioID;
    private String serviceID;
    private String serviceName;
    private String serviceDetailID;
    private String serviceDetailName;
    private String serviceSizeID;
    private String serviceSizeName;
    private String studioStaffID;
    private int price;
    private int amount;
    private boolean status;

    public StudioServiceDTO() {
    }

    public StudioServiceDTO(String studioServiceID, String studioID, String serviceID, String serviceName, String serviceDetailID, String serviceDetailName, String serviceSizeID, String serviceSizeName, String studioStaffID, int price, int amount, boolean status) {
        this.studioServiceID = studioServiceID;
        this.studioID = studioID;
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceDetailID = serviceDetailID;
        this.serviceDetailName = serviceDetailName;
        this.serviceSizeID = serviceSizeID;
        this.serviceSizeName = serviceSizeName;
        this.studioStaffID = studioStaffID;
        this.price = price;
        this.amount = amount;
        this.status = status;
    }

    public String getStudioServiceID() {
        return studioServiceID;
    }

    public String getStudioID() {
        return studioID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDetailID() {
        return serviceDetailID;
    }

    public String getServiceDetailName() {
        return serviceDetailName;
    }

    public String getServiceSizeID() {
        return serviceSizeID;
    }

    public String getServiceSizeName() {
        return serviceSizeName;
    }

    public String getStudioStaffID() {
        return studioStaffID;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStudioServiceID(String studioServiceID) {
        this.studioServiceID = studioServiceID;
    }

    public void setStudioID(String studioID) {
        this.studioID = studioID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceDetailID(String serviceDetailID) {
        this.serviceDetailID = serviceDetailID;
    }

    public void setServiceDetailName(String serviceDetailName) {
        this.serviceDetailName = serviceDetailName;
    }

    public void setServiceSizeID(String serviceSizeID) {
        this.serviceSizeID = serviceSizeID;
    }

    public void setServiceSizeName(String serviceSizeName) {
        this.serviceSizeName = serviceSizeName;
    }

    public void setStudioStaffID(String studioStaffID) {
        this.studioStaffID = studioStaffID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    

   
    

   

}
