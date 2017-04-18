package ca.tdchristian.tdsplash.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.tdchristian.tdsplash.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // Variable Declaration and Initialization
    public Button edsbyButton;
    public Button splashButton;
    public Button busButton;

    public MainFragment() {
        // Required empty public constructor
    }

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

        // Button Initialization
        edsbyButton = (Button)v.findViewById(R.id.edsbyButton);
        splashButton = (Button)v.findViewById(R.id.splashButton);
        busButton = (Button)v.findViewById(R.id.busButton);

        // Action Preformed for Edsby Button
        edsbyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Sets URL and then runs method
                String url = "https://tdchristian.edsby.com/";
                openInternet(url);

            }
        });

        // Action Preformed for Splash Button
        splashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Sets URL and then runs method
                String url = "http://splash.tdchristian.ca/";
                openInternet(url);

            }
        });

        // Action Preformed for Webmail Button
        busButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Sets URL and then runs method
                String url = "https://tdch.mybusplanner.ca/StudentLogin.aspx";
                openInternet(url);

            }
        });
        return v;
    }

    // Method which opens up the site in default browser
    public void openInternet(String url){

        // Changes string to URI
        Uri.parse(url);

        // Creates a new Intent
        Intent openBrowser = new Intent(Intent.ACTION_VIEW);

        // Sets information, which is the site URL
        openBrowser.setData(Uri.parse(url));

        // Opens up the site
        startActivity(openBrowser);

    }

}
