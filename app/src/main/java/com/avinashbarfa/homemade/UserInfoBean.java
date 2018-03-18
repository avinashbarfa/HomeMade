package com.avinashbarfa.homemade;

/**
 * Created by Avinash Barfa on 3/18/2018.
 */

public class UserInfoBean {

    private String userName, emailID,userID;

    public UserInfoBean(String userID, String userName, String emailID) {
        this.userName = userName;
        this.emailID = emailID;
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUserID() {
        return userID;
    }
}
