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
public class BookingStudioService {

    private String bookingID;
    private String studioServiceID;
    private String artistID;
    private int amount;

    public BookingStudioService() {
    }

    public BookingStudioService(String bookingID, String studioServiceID, String artistID, int amount) {
        this.bookingID = bookingID;
        this.studioServiceID = studioServiceID;
        this.artistID = artistID;
        this.amount = amount;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getStudioServiceID() {
        return studioServiceID;
    }

    public String getArtistID() {
        return artistID;
    }

    public int getAmount() {
        return amount;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setStudioServiceID(String studioServiceID) {
        this.studioServiceID = studioServiceID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
