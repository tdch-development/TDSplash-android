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
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import ca.tdchristian.tdsplash.R;

public class NewsFragment extends Fragment {

    Document doc;
    TextView newsTitleTest;
    TextView newsBodyText;

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

        //Attempt to get HTML document
        try {
            doc = new retrieveHTML().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //parses document for all titles/descriptions
        //stores them to an ArrayList of Elements, declared using JSoup built in formatting
        //contains them like <title>blah<\blah>
        Elements titlesHTML = doc.select("title");
        Elements descriptionsHTML = doc.select("description");

        //convert them to usable strings
        ArrayList<String> titles = new ArrayList<>();
        for(int i = 1; i < titlesHTML.size(); i++){ //ignore first one, because that's website title
            titles.add(titlesHTML.get(i).text()); //get text
        }
        ArrayList<String> descriptions = new ArrayList<>();
        for(int i = 1; i < descriptionsHTML.size(); i++){ //ignore first one, because that's website title
            descriptions.add(descriptionsHTML.get(i).text()); //get text
        }

        //Currently have two ArrayLists (titles and descriptions) that contain all news articles on the page

        newsTitleTest.setText(titles.get(0));
        newsBodyText.setText(descriptions.get(0));

        return v;
    }
}

class retrieveHTML extends AsyncTask<Void, Void, Document> {
    @Override
    protected Document doInBackground(Void... Void){
        try {
            //Get HTML document from URL
            return Jsoup.connect("http://tdnewstest.blogspot.com/feeds/posts/default?alt=rss").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("ERROR:", "IOException e, couldn't connect");
        return null;
    }
    @Override
    protected void onPostExecute(Document doc) {
        //Return the retrieved document
        super.onPostExecute(doc);
    }
}
