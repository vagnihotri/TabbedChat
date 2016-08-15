package co.haptik.tabbedchat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.haptik.tabbedchat.R;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class FavoriteFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cView = inflater.inflate(R.layout.favorite_fragment_layout, container, false);
        return cView;
    }
}
