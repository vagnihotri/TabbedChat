package co.haptik.tabbedchat.models;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class MessageListObject {
    @Expose public Integer count;
    @Expose public List<MessageObject> messages;

    public MessageListObject(){

    }
}
