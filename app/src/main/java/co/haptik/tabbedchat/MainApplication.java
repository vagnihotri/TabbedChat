package co.haptik.tabbedchat;

import android.app.Application;

import co.haptik.tabbedchat.network.GitNet;
import co.haptik.tabbedchat.network.NetworkUtility;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new NetworkUtility().initialize("http://haptik.co/android", this);
        new GitNet().initialize("https://api.github.com",this);
    }
}
