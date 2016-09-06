package co.haptik.tabbedchat.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import co.haptik.tabbedchat.models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

/**
 * Created by vijayagnihotri on 18/08/16.
 */
public class GitNet {
    public static APIInterface API;
    public static RestAdapter adapter;
    public static Gson gson;

    public static String LOG_TAG = "NETWORK CALL LOG";

    public void initialize(String baseUrl, Context context) {
        API = initialize(baseUrl, context, APIInterface.class);
    }

    protected static <T> T initialize(String baseUrl, Context context, Class<T> restInterface) {
        if(adapter == null) {
            gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            adapter = new RestAdapter.Builder().setEndpoint(baseUrl)
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog(LOG_TAG))
                    .build();
        }
        return adapter.create(restInterface);

    }

    public interface APIInterface {
        @GET("/repos/rails/rails/commits")
        void fetchCommits(Callback<List<User>> callback);
    }
}
