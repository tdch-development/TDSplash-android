package ca.tdchristian.tdsplash.objects;



public class Period {

    private String name;
    private String start;
    private String end;

    public Period(String name, String start, String end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

}
