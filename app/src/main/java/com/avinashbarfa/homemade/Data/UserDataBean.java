package com.avinashbarfa.homemade.Data;

/**
 * Created by Avinash Barfa on 3/23/2018.
 */

public class UserDataBean {

    private String fullName, _Id , emailId , accountCreatedOn , dateOfBirth;
    private long contactNumber;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String get_Id() {
        return _Id;
    }

    public void set_Id(String _Id) {
        this._Id = _Id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAccountCreatedOn() {
        return accountCreatedOn;
    }

    public void setAccountCreatedOn(String accountCreatedOn) {
        this.accountCreatedOn = accountCreatedOn;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
