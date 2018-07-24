package com.example.android.quakereport;

public class Event {
    
    public final String title;

    public final long time;

    public final double magnitude;

    public Event(String eventTitle, long eventTime, double eventMagnitude){
        title = eventTitle;
        time = eventTime;
        magnitude = eventMagnitude;
    }
}
