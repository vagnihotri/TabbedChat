package co.haptik.tabbedchat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.haptik.tabbedchat.Utility;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class MessageObject implements Comparable<MessageObject> {
    @Expose public String body;

    @SerializedName("Name")
    @Expose public String name;

    @Expose public String username;

    @SerializedName("image-url")
    @Expose public String imageUrl;

    @SerializedName("message-time")
    @Expose public String messageTime;

    public boolean isFav = false;

    public MessageObject() {

    }

    @Override
    public int compareTo(MessageObject another) {
        SimpleDateFormat sdf = new SimpleDateFormat(Utility.DATE_FORMAT_SERVER);
        Date d1,d2;
        try {
            d1 = sdf.parse(this.messageTime);
            d2 = sdf.parse(another.messageTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return (d1.getTime() > d2.getTime() ? 1 : -1);
    }
}
