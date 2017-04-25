package ca.tdchristian.tdsplash.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;

import ca.tdchristian.tdsplash.R;
import ca.tdchristian.tdsplash.activities.MainActivity;
import ca.tdchristian.tdsplash.objects.InfoBoard;
import ca.tdchristian.tdsplash.objects.Period;
import ca.tdchristian.tdsplash.objects.Schedule;

import static ca.tdchristian.tdsplash.activities.MainActivity.FragmentType.*;

public class MainFragment extends Fragment implements View.OnClickListener {

    // Variable Declaration and Initialization
    public ImageButton edsbyButton;
    public Button splashButton;
    public Button busButton;
    public ImageButton infoboardButton;
    public ImageButton calendarButton;
    public ImageButton newsButton;
    public Button parentsButton;
    public TextView currentPeriodName;
    public TextView currentPeriodTime;
    public TextView infoboardMessage;
    public ImageView infoboardMainImage;



    // Required empty public constructor
    public MainFragment(){    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // View Initialization
        edsbyButton = (ImageButton)v.findViewById(R.id.edsbyButton);
        splashButton = (Button)v.findViewById(R.id.splashButton);
        busButton = (Button)v.findViewById(R.id.busButton);
        infoboardButton = (ImageButton)v.findViewById(R.id.infoboardButton);
        calendarButton = (ImageButton)v.findViewById(R.id.calendarButton);
        newsButton = (ImageButton)v.findViewById(R.id.newsButton);
        currentPeriodName = (TextView)v.findViewById(R.id.currentPeriodName);
        currentPeriodTime = (TextView)v.findViewById(R.id.currentPeriodTime);
        infoboardMessage = (TextView)v.findViewById(R.id.infoboardMessage);
        infoboardMainImage = (ImageView)v.findViewById(R.id.infoboardMainImage);

        // Set onClickListeners to this class's onClick function
        infoboardButton.setOnClickListener(this);
        edsbyButton.setOnClickListener(this);
        splashButton.setOnClickListener(this);
        busButton.setOnClickListener(this);
        calendarButton.setOnClickListener(this);
        newsButton.setOnClickListener(this);



        MainActivity mainActivity = (MainActivity)getActivity();


        InfoBoard infoBoard = mainActivity.infoboard;

        try {

            currentPeriodName.setText(infoBoard.getCurrentPeriod().getName());
            currentPeriodTime.setText(infoBoard.getCurrentPeriod().getStart() + " - " + infoBoard.getCurrentPeriod().getEnd());
            infoboardMessage.setText(infoBoard.getMessage2());
            infoboardMainImage.setImageDrawable(infoBoard.getImage1());
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Return the inflated layout
        return v;
    }

    @Override
    public void onClick(View v) {


        MainActivity mainActivity = (MainActivity)getActivity();
        if (v == busButton) {
            openURL("https://tdch.mybusplanner.ca/StudentLogin.aspx");
        } else if (v == splashButton) {
            openURL("http://splash.tdchristian.ca/");
        } else if (v == edsbyButton) {
            openURL("https://tdchristian.edsby.com/");
        } else if (v == infoboardButton) {
            Log.d("debugging", "InfoBoardButton clicked");
            mainActivity.loadFragment(INFOBOARD);
        } else if (v == calendarButton) {
            mainActivity.loadFragment(CALENDAR);
        } else if (v == newsButton) {
            mainActivity.loadFragment(NEWS);
        }
    }

    // Method which opens up the given URL in default browser
    public void openURL(String url){
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
    }

}
