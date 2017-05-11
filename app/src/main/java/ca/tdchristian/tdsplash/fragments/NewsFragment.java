package ca.tdchristian.tdsplash.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ca.tdchristian.tdsplash.R;
import ca.tdchristian.tdsplash.objects.NewsPost;
import ca.tdchristian.tdsplash.tasks.RetrieveNewsFeed;

public class NewsFragment extends Fragment {

    Document doc;
    TextView newsTitleTest;
    TextView newsBodyText;
    TextView newsDateTest;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        newsTitleTest = (TextView)v.findViewById(R.id.newsTitleTest);
        newsBodyText = (TextView)v.findViewById(R.id.newsBodyTest);
        newsDateTest = (TextView)v.findViewById(R.id.newsDateTest);

        // Create an instance of RetrieveNewsFeed
        RetrieveNewsFeed retrieveNewsFeed = new RetrieveNewsFeed();

        //Attempt to get HTML document
        try {
            doc = retrieveNewsFeed.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //parses document for all titles/descriptions
        //stores them to an ArrayList of Elements, declared using JSoup built in formatting
        //contains them like <title>blah<\blah>
        int posts = doc.select("item").size();
        Elements titlesHTML = doc.select("title");
        Elements datesHTML = doc.select("pubDate");
        Elements descriptionsHTML = doc.select("description");

        //newsTitleTest.setText(Integer.toString(posts));



        NewsPost[] newsPosts = new NewsPost[posts]; // create an array that holds all the news posts

        // Fill the array with news posts
        for(int i = 0; i < posts; i++) {
            newsPosts[i] = new NewsPost(titlesHTML.get(i+1).text(), datesHTML.get(i).text(), descriptionsHTML.get(i+1).text());
        }

        String[] dateFormatted = new String[posts];
        for(int i = 0; i < posts; i++){
            String temp = newsPosts[i].getDate().substring(0, newsPosts[i].getDate().length()-9); // remove secs, time zone
            String time = temp.substring(temp.length()-5, temp.length());
            int hour = Integer.parseInt(time.substring(0,2)) % 12; //get hours, mod 12
            dateFormatted[i] = temp.substring(0,temp.length() - 5) + hour + time.substring(2);
        }

        newsTitleTest.setText(newsPosts[0].getTitle());
        newsDateTest.setText(dateFormatted[0]); //get rid of seconds and time zone
        newsBodyText.setText(newsPosts[0].getBody());


        // Return the inflated view
        return v;
    }
}
