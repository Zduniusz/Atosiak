package com.zduniusz.threads;

import com.zduniusz.Main;
import com.zduniusz.data.covid.Download;

import java.io.IOException;

@SuppressWarnings("BusyWait")
public class UpdateCovid implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Main.dailyCovidStat =  Download.formatData(Download.downloadData());
                Thread.sleep(2 * 60 * 1000);
            } catch (InterruptedException | IOException ignored) {} //Potem coś z tym zrobię, żeby aplikacja była bardziej stabilna emocjonalnie
        }
    }
}
