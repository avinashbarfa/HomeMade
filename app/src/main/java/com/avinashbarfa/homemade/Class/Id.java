package com.avinashbarfa.homemade.Class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Avinash Barfa on 3/20/2018.
 */

public class Id {
    @SerializedName("$oid")
    private  String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
