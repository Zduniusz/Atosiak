package com.zduniusz.threads;

import com.zduniusz.Main;
import com.zduniusz.data.luckynumber.GetData;
import com.zduniusz.data.monitors.GetMonitors;

import java.io.IOException;

@SuppressWarnings("BusyWait")
public class UpdateLists implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Main.luckyNumberList = GetData.formatData(GetData.downloadData());
                Main.monitorsList = GetMonitors.formatData(GetMonitors.downloadData());
                Thread.sleep(45 * 60 * 1000);
            } catch (InterruptedException | IOException ignored) {
            } //Potem coś z tym zrobię, żeby aplikacja była bardziej stabilna emocjonalnie
        }
    }
}