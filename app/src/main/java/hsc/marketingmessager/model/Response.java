package hsc.marketingmessager.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hoang Ha on 8/3/2017.
 */

public class Response {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
}
