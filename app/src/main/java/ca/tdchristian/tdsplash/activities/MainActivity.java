package ca.tdchristian.tdsplash.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import ca.tdchristian.tdsplash.R;
import ca.tdchristian.tdsplash.fragments.CalendarFragment;
import ca.tdchristian.tdsplash.fragments.InfoBoardFragment;
import ca.tdchristian.tdsplash.fragments.MainFragment;
import ca.tdchristian.tdsplash.fragments.NewsFragment;
import ca.tdchristian.tdsplash.objects.InfoBoard;
import ca.tdchristian.tdsplash.tasks.RetrieveInfoBoard;

import static ca.tdchristian.tdsplash.activities.MainActivity.FragmentType.*;

public class MainActivity extends Activity {

    public InfoBoard infoboard;

    private ProgressBar spinner;


    public enum FragmentType {
        INFOBOARD, CALENDAR, NEWS, PARENTS
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar)findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);

        // This will grab the current InfoBoard from the internet
        retrieveInfoBoard();

        // Create a fragment manager
        FragmentManager fm = getFragmentManager();
        // Put the main fragment in the main container
        MainFragment mainFragment = (MainFragment)fm.findFragmentById(R.id.container_main);


        // If the mainFragment hasn't been started yet, create a new instance and put it in the container
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            fm.beginTransaction().add(R.id.container_main, mainFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        // Return to main fragment if back button is pressed
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void loadFragment(FragmentType fragmentType) {

        // Replace the mainFragment with the specified fragment
        if (fragmentType == INFOBOARD) {
            getFragmentManager().beginTransaction().replace(R.id.container_main, new InfoBoardFragment()).addToBackStack(null).commit();
        } else if (fragmentType == CALENDAR) {
            getFragmentManager().beginTransaction().replace(R.id.container_main, new CalendarFragment()).addToBackStack(null).commit();
        } else if (fragmentType == NEWS) {
            getFragmentManager().beginTransaction().replace(R.id.container_main, new NewsFragment()).addToBackStack(null).commit();
        }
    }


    public void retrieveInfoBoard() {

        // Create a connectivity manager
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        // Grab current network info
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        // Grab infoboard only if there is an active network connected
        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {
            try {
                RetrieveInfoBoard retrieveInfoBoard = new RetrieveInfoBoard();
                infoboard = retrieveInfoBoard.execute().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

