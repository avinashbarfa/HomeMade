package com.avinashbarfa.homemade.Data;

/**
 * Created by Avinash Barfa on 3/23/2018.
 */

public class Links {

    private String dataBaseIPAddress = "http://192.168.43.72";

    private String retriveCategoriesURL = getDataBaseIPAddress()+"/home-made/retrieve-categories.php";

    private String retriveProductsURL = getDataBaseIPAddress()+"/home-made/retrieve-products.php";

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
