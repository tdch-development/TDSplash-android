package ca.tdchristian.tdsplash.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import ca.tdchristian.tdsplash.R;
import ca.tdchristian.tdsplash.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    // Variable Declaration and Initialization
    public ImageButton edsbyButton;
    public Button splashButton;
    public Button busButton;
    public Button infoboardButton;

    MainActivity mainActivity = (MainActivity)getActivity();

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
        edsbyButton = (ImageButton)v.findViewById(R.id.edsbyButton);
        splashButton = (Button)v.findViewById(R.id.splashButton);
        busButton = (Button)v.findViewById(R.id.busButton);
        infoboardButton = (Button)v.findViewById(R.id.infoboardButton);

        infoboardButton.setOnClickListener(this);
        edsbyButton.setOnClickListener(this);
        splashButton.setOnClickListener(this);
        busButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == busButton) {
            openURL("https://tdch.mybusplanner.ca/StudentLogin.aspx");
        } else if (v == splashButton) {
            openURL("http://splash.tdchristian.ca/");
        } else if (v == edsbyButton) {
            openURL("https://tdchristian.edsby.com/");
        } else if (v == infoboardButton) {
            mainActivity.loadInfoBoard();
        }
    }

    // Method which opens up the site in default browser
    public void openURL(String url){
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));

    }

}
