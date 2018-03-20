package com.avinashbarfa.homemade.Class;

/**
 * Created by Avinash Barfa on 3/18/2018.
 */

public class UserInfoBean {

    private String userName, emailID;
    private  Id _id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }
}
