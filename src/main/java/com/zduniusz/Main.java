package com.zduniusz;

import com.zduniusz.data.covid.DailyCovidStat;
import com.zduniusz.data.covid.Download;
import com.zduniusz.discord.Setup;
import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {

    public static JDA jda;
    public static DailyCovidStat dailyCovidStat;

    public static void main(String[] args) throws LoginException, IOException {
        new Thread(new com.zduniusz.threads.UpdateCovid()).start();
        dailyCovidStat = Download.formatData(Download.downloadData());
        new Setup();

    }
}
