package ca.tdchristian.tdsplash.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import ca.tdchristian.tdsplash.R;
import ca.tdchristian.tdsplash.fragments.MainFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        MainFragment mainFragment = (MainFragment)fm.findFragmentById(R.id.container_main);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            fm.beginTransaction().add(R.id.container_main, mainFragment).commit();
        }
    }
}
