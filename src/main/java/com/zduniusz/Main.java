package com.zduniusz;

import com.zduniusz.data.covid.DailyCovidStat;

public class Main {
    public static DailyCovidStat dailyCovidStat = new DailyCovidStat();

    public static void main(String[] args) {
        new Thread(new com.zduniusz.threads.UpdateCovid()).start();
        

    }
}
