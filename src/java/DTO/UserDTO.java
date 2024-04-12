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
public class UserDTO {
    private String id;
    private String userAccount;
    private String password;
    private String roleId;
    private String fullname;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String email;
    private String city;
    private String district;
    private boolean status;
    private String studioId;

    public UserDTO() {
    }

    public UserDTO(String id, String userAccount, String password, String roleId, String fullname, String birthday, String gender, String phoneNumber, String email, String city, String district, boolean status, String studioId) {
        this.id = id;
        this.userAccount = userAccount;
        this.password = password;
        this.roleId = roleId;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.district = district;
        this.status = status;
        this.studioId = studioId;
    }

    public String getId() {
        return id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getFullname() {
        return fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public boolean isStatus() {
        return status;
    }

    public String getStudioId() {
        return studioId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setStudioId(String studioId) {
        this.studioId = studioId;
    }

   
}
