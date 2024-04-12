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
public class SlotDTO {
    private String slotID;
    private String studioID;
    private String timeStart;
    private String timeEnd;
    private boolean status;

    public SlotDTO() {
    }

    public SlotDTO(String slotID, String studioID, String timeStart, String timeEnd, boolean status) {
        this.slotID = slotID;
        this.studioID = studioID;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.status = status;
    }

    public String getSlotID() {
        return slotID;
    }

    public String getStudioID() {
        return studioID;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public boolean isStatus() {
        return status;
    }

    public void setSlotID(String slotID) {
        this.slotID = slotID;
    }

    public void setStudioID(String studioID) {
        this.studioID = studioID;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

  

    
    
}
