package ca.tdchristian.tdsplash.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.tdchristian.tdsplash.R;


public class InfoBoardFragment extends Fragment {

    public InfoBoardFragment() {
        // Required empty public constructor
    }

    public static InfoBoardFragment newInstance() {
        return new InfoBoardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_board, container, false);
    }

}
