package co.haptik.tabbedchat.models;

import com.google.gson.annotations.Expose;

/**
 * Created by vijayagnihotri on 18/08/16.
 */
public class Commit {
    @Expose public Author author;
    @Expose public String message;
}
