package com.avinashbarfa.homemade.Data;

/**
 * Created by Avinash Barfa on 3/29/2018.
 */

public class CategoriesData {

    private String categoryId,categoryName,categorySubTitle,imageUrl;


    public CategoriesData(String categoryId, String categoryName, String categorySubTitle, String imageUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categorySubTitle = categorySubTitle;
        this.imageUrl = imageUrl;
    }


    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorySubTitle() {
        return categorySubTitle;
    }

    public void setCategorySubtiltle(String categorySubTitle) {
        this.categorySubTitle = categorySubTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
