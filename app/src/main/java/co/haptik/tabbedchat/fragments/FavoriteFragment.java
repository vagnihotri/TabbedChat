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
import co.haptik.tabbedchat.adapters.FavoriteListAdapter;
import co.haptik.tabbedchat.firebase.FirebaseSingleton;
import co.haptik.tabbedchat.models.MessageListObject;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class FavoriteFragment extends Fragment {

    private FavoriteListAdapter adapter;
    private RecyclerView favListView;
    private DatabaseReference messagesRef;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cView = inflater.inflate(R.layout.favorite_fragment_layout, container, false);
        init(cView);
        loadFavorites();
        return cView;
    }

    private void init(View cView) {
        favListView = (RecyclerView) cView.findViewById(R.id.fav_list_layout);
        messagesRef = FirebaseSingleton.getInstance().getDatabaseRef();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
    }

    private void loadFavorites() {
        showLoader();
        messagesRef.child(FirebaseSingleton.MESSAGE_REF_KEY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MessageListObject messageListObject = dataSnapshot.getValue(MessageListObject.class);
                if (messageListObject != null && messageListObject.count > 0) {
                    setListView(messageListObject);
                } else {
                    hideLoader();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideLoader();
            }
        });
    }

    private void setListView(MessageListObject messageListObject) {
        adapter = new FavoriteListAdapter(messageListObject, getActivity());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        favListView.setLayoutManager(llm);
        favListView.setAdapter(adapter);
        hideLoader();
    }

    private void showLoader() {
        progressDialog.show();
    }

    private void hideLoader() {
        progressDialog.dismiss();
    }
}
