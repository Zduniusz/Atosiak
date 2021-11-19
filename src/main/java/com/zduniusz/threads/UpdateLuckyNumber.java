package com.zduniusz.threads;

import com.zduniusz.Main;
import com.zduniusz.data.luckynumber.GetData;

import java.io.IOException;

@SuppressWarnings("BusyWait")
public class UpdateLuckyNumber implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Main.luckyNumberList = GetData.formatData(GetData.downloadData());
                Thread.sleep(45 * 60 * 1000);
            } catch (InterruptedException | IOException ignored) {
            } //Potem coś z tym zrobię, żeby aplikacja była bardziej stabilna emocjonalnie
        }
    }
}