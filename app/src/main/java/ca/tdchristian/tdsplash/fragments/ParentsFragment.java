package ca.tdchristian.tdsplash.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.tdchristian.tdsplash.R;

public class ParentsFragment extends Fragment {

    public ParentsFragment() {
        // Required empty public constructor
    }

    public static ParentsFragment newInstance() {
        return new ParentsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parents, container, false);
    }

}
