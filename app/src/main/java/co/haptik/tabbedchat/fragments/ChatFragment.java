package co.haptik.tabbedchat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.haptik.tabbedchat.R;
import co.haptik.tabbedchat.models.MessageListObject;
import co.haptik.tabbedchat.network.NetworkUtility;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class ChatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cView = inflater.inflate(R.layout.chat_fragment_layout, container, false);
        NetworkUtility.API.fetchChats(new Callback<MessageListObject>() {
            @Override
            public void success(MessageListObject messageListObject, Response response) {
                Log.d("Network log","Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Network log","Failure");
            }
        });
        return cView;
    }
}
