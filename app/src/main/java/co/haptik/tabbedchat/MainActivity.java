package co.haptik.tabbedchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.haptik.tabbedchat.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    public static final String HOME_FRAGMENT = "HOME";
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().getFragment(savedInstanceState, HOME_FRAGMENT);
        }else {
            homeFragment = new HomeFragment();
            if (!homeFragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment, HOME_FRAGMENT).commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, HOME_FRAGMENT, homeFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
