package ca.tdchristian.tdsplash.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;

import ca.tdchristian.tdsplash.R;
import ca.tdchristian.tdsplash.fragments.CalendarFragment;
import ca.tdchristian.tdsplash.fragments.InfoBoardFragment;
import ca.tdchristian.tdsplash.fragments.MainFragment;
import ca.tdchristian.tdsplash.fragments.NewsFragment;
import ca.tdchristian.tdsplash.objects.InfoBoard;
import ca.tdchristian.tdsplash.objects.Period;
import ca.tdchristian.tdsplash.objects.Schedule;
import ca.tdchristian.tdsplash.tasks.RetrieveInfoBoard;

import static ca.tdchristian.tdsplash.activities.MainActivity.FragmentType.*;

public class MainActivity extends Activity {

    public InfoBoard infoboard;

    public enum FragmentType {
        INFOBOARD, CALENDAR, NEWS, PARENTS
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveInfoBoard();

        FragmentManager fm = getFragmentManager();
        MainFragment mainFragment = (MainFragment)fm.findFragmentById(R.id.container_main);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            fm.beginTransaction().add(R.id.container_main, mainFragment).commit();
        }


    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void loadFragment(FragmentType fragmentType) {
        if (fragmentType == INFOBOARD) {
            getFragmentManager().beginTransaction().replace(R.id.container_main, new InfoBoardFragment()).addToBackStack(null).commit();
        } else if (fragmentType == CALENDAR) {
            getFragmentManager().beginTransaction().replace(R.id.container_main, new CalendarFragment()).addToBackStack(null).commit();
        } else if (fragmentType == NEWS) {
            getFragmentManager().beginTransaction().replace(R.id.container_main, new NewsFragment()).addToBackStack(null).commit();
        }
    }

    public void retrieveInfoBoard() {
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {
            try {
                infoboard = new RetrieveInfoBoard().execute().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

