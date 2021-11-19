package com.zduniusz.data.covid;

public class DailyCovidStat {
    private int dailyInfected;
    private int dailyTested;
    private int dailyPositiveTests;
    private int dailyDeceased;
    private int dailyRecovered;
    private int dailyQuarantine;
    private String txtDate;

    public int getDailyInfected() {
        return dailyInfected;
    }

    public String getTxtDate() {
        return txtDate;
    }
    //Na razie nie potrzebuje nic oprócz getDaily Infected i getTxtDate
}
