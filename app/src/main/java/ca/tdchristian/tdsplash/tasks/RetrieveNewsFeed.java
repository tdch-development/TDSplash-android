package ca.tdchristian.tdsplash.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class RetrieveNewsFeed extends AsyncTask<Void, Void, Document> {
    @Override
    protected Document doInBackground(Void... Void){
        try {
            //Get HTML document from URL
            return Jsoup.connect("http://tdsplashnews.blogspot.com/feeds/posts/default?alt=rss").get();
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
