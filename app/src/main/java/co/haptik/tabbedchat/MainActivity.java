package co.haptik.tabbedchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import co.haptik.tabbedchat.fragments.HomeFragment;
import co.haptik.tabbedchat.models.User;
import co.haptik.tabbedchat.network.GitNet;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    public static final String HOME_FRAGMENT = "HOME";
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().getFragment(savedInstanceState, HOME_FRAGMENT);
        }else {
            homeFragment = new HomeFragment();
            if (!homeFragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment, HOME_FRAGMENT).commit();
            }
        }*/
        GitNet.API.fetchCommits(new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                Log.d("success","success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("success","failure");
            }
        });
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
