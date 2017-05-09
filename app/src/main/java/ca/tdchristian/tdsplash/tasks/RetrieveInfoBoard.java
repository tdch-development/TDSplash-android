package ca.tdchristian.tdsplash.tasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;

import ca.tdchristian.tdsplash.objects.InfoBoard;
import ca.tdchristian.tdsplash.objects.Period;
import ca.tdchristian.tdsplash.objects.Schedule;

public class RetrieveInfoBoard extends AsyncTask<Void, Void, InfoBoard> {

    int progress_status;

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        //Toast.makeText(Main.get
        //Main.getApp().txt_percentage.setText("downloading 0%");App(),"Invoke on PreExecute()", Toast.LENGTH_SHORT).show();
        progress_status = 0 ;
    }

    @Override
    protected InfoBoard doInBackground(Void... strings) {

        Schedule schedule = getSchedule();
        String message1 = getMessage1();
        String message2 = getMessage2();
        Drawable image1 = getImage1();
        Drawable image2 = getImage2();
        Period currentPeriod = getCurrentPeriod();

        return new InfoBoard(schedule, message1, message2, image1, image2, currentPeriod);
    }

    @Override
    protected void onPostExecute(InfoBoard infoboard) {
        super.onPostExecute(infoboard);
    }

    private Schedule getSchedule() {
        Schedule schedule = new Schedule();
        Document doc;

        try {
            doc = Jsoup.connect("http://splash.tdchristian.ca/apps/infoboard/blocks/block3.php").get();
            Elements spanElements = doc.select("span");

            for (int i = 0; i<spanElements.size();i+=3) {
                schedule.add(new Period(spanElements.get(i).text(), spanElements.get(i+1).text(), spanElements.get(i+2).text()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedule;
    }

    private String getMessage1() {
        Document doc;
        String message = "";
        try {
            doc = Jsoup.connect("http://splash.tdchristian.ca/apps/infoboard/blocks/block2.php").get();
            message = doc.getElementsByClass("event").text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private String getMessage2() {
        Document doc;
        String message = "";
        try {
            doc = Jsoup.connect("http://splash.tdchristian.ca/apps/infoboard/blocks/block6.php").get();
            message = doc.getElementById("bottommessage").text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private Drawable getImage1() {
        Document doc;
        String imageURL;
        try {
            doc = Jsoup.connect("http://splash.tdchristian.ca/apps/infoboard/blocks/block4.php").get();
            Element imageSrc = doc.select("img").first();
            imageURL = imageSrc.absUrl("src").replace("blocks/", "");
            InputStream is = (InputStream) new URL(imageURL).getContent();
            return Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private Drawable getImage2() {
        Document doc;
        String imageURL;
        try {
            doc = Jsoup.connect("http://splash.tdchristian.ca/apps/infoboard/blocks/block5.php").get();
            Element imageSrc = doc.select("img").first();
            imageURL = imageSrc.absUrl("src").replace("blocks/", "");
            InputStream is = (InputStream) new URL(imageURL).getContent();
            return Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private Period getCurrentPeriod() {
        Document doc;
        Period period = null;
        try {
            doc = Jsoup.connect("http://splash.tdchristian.ca/apps/infoboard/blocks/block3.php").get();
            period = new Period(doc.getElementsByClass("periodname_current").text(),
                    doc.getElementsByClass("periodstart_current").text(),
                    doc.getElementsByClass("periodend_current").text());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return period;
    }

}


