package com.avinashbarfa.homemade.Class;

/**
 * Created by Avinash Barfa on 3/20/2018.
 */

public class Common {

    private static String DB_NAME = "homemade";
    private static String COLLECTION_NAME = "userprofile";
    private static String API_KEY = "hzA8jyCsH4prSCVSfvp5B8Ykhte9GWDi";

    private static String getAddressSingle(UserInfoBean userInfoBean) {
        String baseUrl = String.format("http://api.mlab.com/api/i/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/"+userInfoBean.get_id().getOid()+"?apiKey="+API_KEY);
        return stringBuilder.toString();
    }
    
    public static String getAddressAPI() {
		String baseUrl = String.format("http://api.mlab.com/api/i/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
	}

}
