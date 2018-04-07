package com.avinashbarfa.homemade.Data;

/**
 * Created by Avinash Barfa on 3/23/2018.
 */

public class Links {

    private String dataBaseIPAddress = "http://192.168.0.105";

    private String retriveCategoriesURL = "https://api.myjson.com/bins/sulcf";

    private String retriveProductsURL = "https://api.myjson.com/bins/v543n";

    private String postFeedbackURL = "";


    public String getDataBaseIPAddress() {
        return dataBaseIPAddress;
    }

    public String getRetriveCategoriesURL() {
        return retriveCategoriesURL;
    }

    public String getRetriveProductsURL() {
        return retriveProductsURL;
    }

    public String getPostFeedbackURL() {
        return postFeedbackURL;
    }
}
