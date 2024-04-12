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
public class BookingDetailDTO {
    
    private String bookingStudioServiceID;
    private String bookingID;
    private String serviceStudioID;
    private String serviceID;
    private String serviceName;
    private String serviceDetailID;
    private String serviceDetailName;
    private String serviceSizeID;
    private String serviceSizeName;
    private int price;
    private int amount;
    private String artistID;
    private String artistName;

    public BookingDetailDTO() {
    }

    public BookingDetailDTO(String bookingStudioServiceID, String bookingID, String serviceStudioID, String serviceID, String serviceName, String serviceDetailID, String serviceDetailName, String serviceSizeID, String serviceSizeName, int price, int amount, String artistID, String artistName) {
        this.bookingStudioServiceID = bookingStudioServiceID;
        this.bookingID = bookingID;
        this.serviceStudioID = serviceStudioID;
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceDetailID = serviceDetailID;
        this.serviceDetailName = serviceDetailName;
        this.serviceSizeID = serviceSizeID;
        this.serviceSizeName = serviceSizeName;
        this.price = price;
        this.amount = amount;
        this.artistID = artistID;
        this.artistName = artistName;
    }

    public String getBookingStudioServiceID() {
        return bookingStudioServiceID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getServiceStudioID() {
        return serviceStudioID;
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

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getArtistID() {
        return artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setBookingStudioServiceID(String bookingStudioServiceID) {
        this.bookingStudioServiceID = bookingStudioServiceID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setServiceStudioID(String serviceStudioID) {
        this.serviceStudioID = serviceStudioID;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    

   

    
    

}
