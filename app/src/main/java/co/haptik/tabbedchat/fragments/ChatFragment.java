package co.haptik.tabbedchat.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import co.haptik.tabbedchat.R;
import co.haptik.tabbedchat.adapters.ChatListAdapter;
import co.haptik.tabbedchat.firebase.FirebaseSingleton;
import co.haptik.tabbedchat.models.MessageListObject;
import co.haptik.tabbedchat.network.NetworkUtility;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class ChatFragment extends Fragment {

    private ChatListAdapter adapter;
    private RecyclerView chatListView;
    private DatabaseReference messagesRef;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cView = inflater.inflate(R.layout.chat_fragment_layout, container, false);

        init(cView);
        loadMessages();

        return cView;
    }

    private void init(View cView) {
        chatListView = (RecyclerView)cView.findViewById(R.id.chat_list_layout);
        messagesRef = FirebaseSingleton.getInstance().getDatabaseRef();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
    }

    private void loadMessages() {
        showLoader();
        messagesRef.child(FirebaseSingleton.MESSAGE_REF_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MessageListObject messageListObject = dataSnapshot.getValue(MessageListObject.class);
                if (messageListObject == null || messageListObject.count == 0) {
                    fetchMessages();
                } else {
                    setListView(messageListObject);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                fetchMessages();
            }
        });
    }

    private void fetchMessages() {
        NetworkUtility.API.fetchChats(new Callback<MessageListObject>() {
            @Override
            public void success(MessageListObject messageListObject, Response response) {
                setListView(messageListObject);
            }

            @Override
            public void failure(RetrofitError error) {
                chatListView.setVisibility(View.GONE);
                hideLoader();
            }
        });
    }

    private void setListView(MessageListObject messageListObject) {
        adapter = new ChatListAdapter(messageListObject, getActivity());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        chatListView.setLayoutManager(llm);
        chatListView.setAdapter(adapter);
        hideLoader();
        FirebaseSingleton.getInstance().setValue(messageListObject);
    }

    private void showLoader() {
        progressDialog.show();
    }

    private void hideLoader() {
        progressDialog.dismiss();
    }
}
