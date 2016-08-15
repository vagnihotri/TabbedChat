package co.haptik.tabbedchat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class MessageObject {
    @Expose public String body;

    @SerializedName("Name")
    @Expose public String name;

    @Expose public String username;

    @SerializedName("image-url")
    @Expose public String imageUrl;

    @SerializedName("message-time")
    @Expose public String messageTime;

}
