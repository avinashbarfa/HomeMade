package com.avinashbarfa.homemade.Data;

/**
 * Created by Avinash Barfa on 3/23/2018.
 */

public class Links {

    private String dataBaseIPAddress = "http://192.168.0.105";

    private String retrieveCategoriesURL = getDataBaseIPAddress()+"/home-made/retrieve-categories.php";

    private String retrieveProductsURL = getDataBaseIPAddress()+"/home-made/retrieve-products.php";

    private String postFeedbackURL = "";

    private String addProductURL = getDataBaseIPAddress()+"/home-made/add-product.php";


    public String getDataBaseIPAddress() {
        return dataBaseIPAddress;
    }

    public String getRetrieveCategoriesURL() {
        return retrieveCategoriesURL;
    }

    public String getRetrieveProductsURL() {
        return retrieveProductsURL;
    }

    public String getPostFeedbackURL() {
        return postFeedbackURL;
    }

    public String getAddProductURL() {
        return addProductURL;
    }
}
