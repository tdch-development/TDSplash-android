package ca.tdchristian.tdsplash.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ca.tdchristian.tdsplash.R;
import ca.tdchristian.tdsplash.activities.MainActivity;
import ca.tdchristian.tdsplash.objects.InfoBoard;
import ca.tdchristian.tdsplash.tasks.RetrieveInfoBoard;


public class InfoBoardFragment extends Fragment implements View.OnClickListener {

    private TextView period1Name;
    private TextView period1Time;
    private TextView period2Name;
    private TextView period2Time;
    private TextView period3Name;
    private TextView period3Time;
    private TextView period4Name;
    private TextView period4Time;
    private TextView period5Name;
    private TextView period5Time;
    private TextView period6Name;
    private TextView period6Time;
    private TextView period7Name;
    private TextView period7Time;

    private LinearLayout period1;
    private LinearLayout period2;
    private LinearLayout period3;
    private LinearLayout period4;
    private LinearLayout period5;
    private LinearLayout period6;
    private LinearLayout period7;

    private TextView message1;
    private TextView message2;

    private ImageView image1;
    private ImageView image2;




    public InfoBoardFragment() {
    }

    public static InfoBoardFragment newInstance() {
        return new InfoBoardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_info_board, container, false);

        Log.d("debugging", "Started onCreateView");

        period1Name = (TextView) v.findViewById(R.id.period1Name);
        period1Time = (TextView) v.findViewById(R.id.period1Time);
        period2Name = (TextView) v.findViewById(R.id.period2Name);
        period2Time = (TextView) v.findViewById(R.id.period2Time);
        period3Name = (TextView) v.findViewById(R.id.period3Name);
        period3Time = (TextView) v.findViewById(R.id.period3Time);
        period4Name = (TextView) v.findViewById(R.id.period4Name);
        period4Time = (TextView) v.findViewById(R.id.period4Time);
        period5Name = (TextView) v.findViewById(R.id.period5Name);
        period5Time = (TextView) v.findViewById(R.id.period5Time);
        period6Name = (TextView) v.findViewById(R.id.period6Name);
        period6Time = (TextView) v.findViewById(R.id.period6Time);
        period7Name = (TextView) v.findViewById(R.id.period7Name);
        period7Time = (TextView) v.findViewById(R.id.period7Time);
        period1 = (LinearLayout) v.findViewById(R.id.period1);
        period2 = (LinearLayout) v.findViewById(R.id.period2);
        period3 = (LinearLayout) v.findViewById(R.id.period3);
        period4 = (LinearLayout) v.findViewById(R.id.period4);
        period5 = (LinearLayout) v.findViewById(R.id.period5);
        period6 = (LinearLayout) v.findViewById(R.id.period6);
        period7 = (LinearLayout) v.findViewById(R.id.period7);
        message1 = (TextView) v.findViewById(R.id.message1);
        message2 = (TextView) v.findViewById(R.id.message2);
        image1 = (ImageView) v.findViewById(R.id.imageView1);
        image2 = (ImageView) v.findViewById(R.id.imageView2);

        Log.d("debugging", "Views initialized");

        refreshSchedule();

        return v;
    }

    public void promptRefresh() {
        int g = View.GONE;
        period1.setVisibility(g);
        period2.setVisibility(g);
        period3.setVisibility(g);
        period4.setVisibility(g);
        period5.setVisibility(g);
        period6.setVisibility(g);
        period7.setVisibility(g);
        message1.setVisibility(g);
        image2.setVisibility(g);
        message2.setText("Please connect to the internet, then tap here to refresh");
    }

    public void makeAllViewsVisible() {

        int v = View.VISIBLE;
        period1.setVisibility(v);
        period2.setVisibility(v);
        period3.setVisibility(v);
        period4.setVisibility(v);
        period5.setVisibility(v);
        period6.setVisibility(v);
        period7.setVisibility(v);
        message1.setVisibility(v);
        message2.setVisibility(v);
        image1.setVisibility(v);
        image2.setVisibility(v);
    }

    public void refreshSchedule() {

        makeAllViewsVisible();
        InfoBoard infoboard;
        try {

            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.retrieveInfoBoard();
            infoboard = mainActivity.infoboard;
            int periods = infoboard.getSchedule().size();
            setPeriodVisibility(periods);

            int i = 0;

            if (i < periods) {
                period1Name.setText(infoboard.getSchedule().get(0).getName());
                period1Time.setText(infoboard.getSchedule().get(0).getStart() + " - " + infoboard.getSchedule().get(0).getEnd());
                i++;
            }
            if (i < periods) {
                period2Name.setText(infoboard.getSchedule().get(1).getName());
                period2Time.setText(infoboard.getSchedule().get(1).getStart() + " - " + infoboard.getSchedule().get(1).getEnd());
                i++;
            }
            if (i < periods) {
                period3Name.setText(infoboard.getSchedule().get(2).getName());
                period3Time.setText(infoboard.getSchedule().get(2).getStart() + " - " + infoboard.getSchedule().get(2).getEnd());
                i++;
            }
            if (i < periods) {
                period4Name.setText(infoboard.getSchedule().get(3).getName());
                period4Time.setText(infoboard.getSchedule().get(3).getStart() + " - " + infoboard.getSchedule().get(3).getEnd());
                i++;
            }
            if (i < periods) {
                period5Name.setText(infoboard.getSchedule().get(4).getName());
                period5Time.setText(infoboard.getSchedule().get(4).getStart() + " - " + infoboard.getSchedule().get(4).getEnd());
                i++;
            }
            if (i < periods) {
                period6Name.setText(infoboard.getSchedule().get(5).getName());
                period6Time.setText(infoboard.getSchedule().get(5).getStart() + " - " + infoboard.getSchedule().get(5).getEnd());
                i++;
            }
            if (i < periods) {
                period7Name.setText(infoboard.getSchedule().get(6).getName());
                period7Time.setText(infoboard.getSchedule().get(6).getStart() + " - " + infoboard.getSchedule().get(6).getEnd());
            }

            message1.setText(infoboard.getMessage1());
            message2.setText(infoboard.getMessage2());
            image1.setImageDrawable(infoboard.getImage1());
            image2.setImageDrawable(infoboard.getImage2());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPeriodVisibility(int p) {
        switch (p) {
            case 0:
                period1.setVisibility(View.GONE);
                period2.setVisibility(View.GONE);
                period3.setVisibility(View.GONE);
                period4.setVisibility(View.GONE);
                period5.setVisibility(View.GONE);
                period6.setVisibility(View.GONE);
                period7.setVisibility(View.GONE);
                break;
            case 1:
                period1.setVisibility(View.VISIBLE);
                period2.setVisibility(View.GONE);
                period3.setVisibility(View.GONE);
                period4.setVisibility(View.GONE);
                period5.setVisibility(View.GONE);
                period6.setVisibility(View.GONE);
                period7.setVisibility(View.GONE);
                break;
            case 2:
                period1.setVisibility(View.VISIBLE);
                period2.setVisibility(View.VISIBLE);
                period3.setVisibility(View.GONE);
                period4.setVisibility(View.GONE);
                period5.setVisibility(View.GONE);
                period6.setVisibility(View.GONE);
                period7.setVisibility(View.GONE);
                break;
            case 3:
                period1.setVisibility(View.VISIBLE);
                period2.setVisibility(View.VISIBLE);
                period3.setVisibility(View.VISIBLE);
                period4.setVisibility(View.GONE);
                period5.setVisibility(View.GONE);
                period6.setVisibility(View.GONE);
                period7.setVisibility(View.GONE);
                break;
            case 4:
                period1.setVisibility(View.VISIBLE);
                period2.setVisibility(View.VISIBLE);
                period3.setVisibility(View.VISIBLE);
                period4.setVisibility(View.VISIBLE);
                period5.setVisibility(View.GONE);
                period6.setVisibility(View.GONE);
                period7.setVisibility(View.GONE);
                break;
            case 5:
                period1.setVisibility(View.VISIBLE);
                period2.setVisibility(View.VISIBLE);
                period3.setVisibility(View.VISIBLE);
                period4.setVisibility(View.VISIBLE);
                period5.setVisibility(View.VISIBLE);
                period6.setVisibility(View.GONE);
                period7.setVisibility(View.GONE);
                break;
            case 6:
                period1.setVisibility(View.VISIBLE);
                period2.setVisibility(View.VISIBLE);
                period3.setVisibility(View.VISIBLE);
                period4.setVisibility(View.VISIBLE);
                period5.setVisibility(View.VISIBLE);
                period6.setVisibility(View.VISIBLE);
                period7.setVisibility(View.GONE);
                break;
            case 7:
                period1.setVisibility(View.VISIBLE);
                period2.setVisibility(View.VISIBLE);
                period3.setVisibility(View.VISIBLE);
                period4.setVisibility(View.VISIBLE);
                period5.setVisibility(View.VISIBLE);
                period6.setVisibility(View.VISIBLE);
                period7.setVisibility(View.VISIBLE);
                break;
            default:
                period1.setVisibility(View.VISIBLE);
                period2.setVisibility(View.VISIBLE);
                period3.setVisibility(View.VISIBLE);
                period4.setVisibility(View.VISIBLE);
                period5.setVisibility(View.VISIBLE);
                period6.setVisibility(View.VISIBLE);
                period7.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View view) {

        if (view == message2) {

            ConnectivityManager cm =
                    (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            if (activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting()) {
                refreshSchedule();
            }
        }
    }

}
