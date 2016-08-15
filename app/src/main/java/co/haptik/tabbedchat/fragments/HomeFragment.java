package co.haptik.tabbedchat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.haptik.tabbedchat.R;
import co.haptik.tabbedchat.adapters.TabbedViewPagerAdapter;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class HomeFragment extends Fragment {

    public static final String CHAT_FRAGMENT = "CHAT";
    public static final String FAVORITE_FRAGMENT = "FAVORITES";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabbedViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cView = inflater.inflate(R.layout.home_fragment_layout, container, false);
        viewPager = (ViewPager) cView.findViewById(R.id.fragment_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) cView.findViewById(R.id.sliding_tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        return cView;
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new TabbedViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ChatFragment(), CHAT_FRAGMENT);
        adapter.addFragment(new FavoriteFragment(), FAVORITE_FRAGMENT);
        viewPager.setAdapter(adapter);
    }
}
