package com.example.klnresearch;

public class SenderModel {
     String namesend;
     String speedsend;
    String timesend;
    String tempsend;

    public SenderModel(String namesend, String speedsend, String timesend, String tempsend) {
        this.namesend = namesend;
        this.speedsend = speedsend;
        this.timesend = timesend;
        this.tempsend = tempsend;
    }

    public SenderModel(String namesend, Integer speedsend, String timesend, String tempsend) {
    }

    public String getNamesend() {
        return namesend;
    }

    public void setNamesend(String namesend) {
        this.namesend = namesend;
    }

    public String getSpeedsend() {
        return speedsend;
    }

    public void setSpeedsend(String speedsend) {
        this.speedsend = speedsend;
    }

    public String getTimesend() {
        return timesend;
    }

    public void setTimesend(String timesend) {
        this.timesend = timesend;
    }

    public String getTempsend() {
        return tempsend;
    }

    public void setTempsend(String tempsend) {
        this.tempsend = tempsend;
    }


}
