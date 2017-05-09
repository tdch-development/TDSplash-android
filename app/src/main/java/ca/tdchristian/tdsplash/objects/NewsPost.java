package ca.tdchristian.tdsplash.objects;

/**
 * Created by ethrv on 2017-05-09.
 */

public class NewsPost {

    private String title;
    private String date;
    private String body;

    public NewsPost(String title, String date, String body) {
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }
}
