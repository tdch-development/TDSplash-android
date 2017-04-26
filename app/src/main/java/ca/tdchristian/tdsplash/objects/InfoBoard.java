
package ca.tdchristian.tdsplash.objects;

import android.graphics.drawable.Drawable;

public class InfoBoard {
    private Schedule schedule;
    private String message1;
    private String message2;
    private Drawable image1;
    private Drawable image2;
    private Period currentPeriod;


    public InfoBoard(Schedule schedule, String message1, String message2, Drawable image1, Drawable image2, Period currentPeriod) {
        this.schedule = schedule;
        this.message1 = message1;
        this.message2 = message2;
        this.image1 = image1;
        this.image2 = image2;
        this.currentPeriod = currentPeriod;
    }

    public Drawable getImage1() {
        return image1;
    }

    public Drawable getImage2() {
        return image2;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public String getMessage1() {
        return message1;
    }

    public String getMessage2() {
        return message2;
    }

    public Period getCurrentPeriod() {
        return currentPeriod;
    }
}
