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
public class BookingDTO {

    private String bookingID;
    private String userID;
    private String userFullName;
    private String fullName;
    private String phoneNumber;
    private String studioID;
    private String studioName;
    private String studioStaffID;
    private String slotID;
    private String slotTimeStart;
    private String slotTimeEnd;
    private int totalPrice;
    private String description;
    private String currentDate;
    private String bookingDate;
    private int rating;
    private String commentation;
    private String status;

    public BookingDTO() {
    }

    public BookingDTO(String bookingID, String userID, String userFullName, String fullName, String phoneNumber, String studioID, String studioName, String studioStaffID, String slotID, String slotTimeStart, String slotTimeEnd, int totalPrice, String description, String currentDate, String bookingDate, int rating, String commentation, String status) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.userFullName = userFullName;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.studioID = studioID;
        this.studioName = studioName;
        this.studioStaffID = studioStaffID;
        this.slotID = slotID;
        this.slotTimeStart = slotTimeStart;
        this.slotTimeEnd = slotTimeEnd;
        this.totalPrice = totalPrice;
        this.description = description;
        this.currentDate = currentDate;
        this.bookingDate = bookingDate;
        this.rating = rating;
        this.commentation = commentation;
        this.status = status;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStudioID() {
        return studioID;
    }

    public String getStudioName() {
        return studioName;
    }

    public String getStudioStaffID() {
        return studioStaffID;
    }

    public String getSlotID() {
        return slotID;
    }

    public String getSlotTimeStart() {
        return slotTimeStart;
    }

    public String getSlotTimeEnd() {
        return slotTimeEnd;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public int getRating() {
        return rating;
    }

    public String getCommentation() {
        return commentation;
    }

    public String getStatus() {
        return status;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStudioID(String studioID) {
        this.studioID = studioID;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public void setStudioStaffID(String studioStaffID) {
        this.studioStaffID = studioStaffID;
    }

    public void setSlotID(String slotID) {
        this.slotID = slotID;
    }

    public void setSlotTimeStart(String slotTimeStart) {
        this.slotTimeStart = slotTimeStart;
    }

    public void setSlotTimeEnd(String slotTimeEnd) {
        this.slotTimeEnd = slotTimeEnd;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCommentation(String commentation) {
        this.commentation = commentation;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
   
}
