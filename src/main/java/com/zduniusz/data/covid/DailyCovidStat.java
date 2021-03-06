package com.zduniusz.data.covid;

@SuppressWarnings("unused") //Na razie nie potrzebuje nic oprócz getDaily Infected i getTxtDate
public class DailyCovidStat {
    private int dailyInfected;
    private int dailyTested;
    private int dailyPositiveTests;
    private int dailyDeceased;
    private int dailyRecovered;
    private int dailyQuarantine;
    private String lastUpdatedAtSource;

    public int getDailyInfected() {
        return dailyInfected;
    }

    public String getLastUpdatedAtSource() {
        return lastUpdatedAtSource;
    }
}
