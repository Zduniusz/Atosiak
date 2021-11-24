package com.zduniusz;

import com.zduniusz.data.covid.DailyCovidStat;
import com.zduniusz.data.covid.Download;
import com.zduniusz.data.luckynumber.GetData;
import com.zduniusz.data.luckynumber.LuckyNumber;
import com.zduniusz.data.monitors.GetMonitors;
import com.zduniusz.data.monitors.Monitors;
import com.zduniusz.discord.CommandManager;
import com.zduniusz.discord.Setup;
import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static JDA jda;

    public static DailyCovidStat dailyCovidStat;
    public static List<LuckyNumber> luckyNumberList;
    public static List<Monitors> monitorsList;

    public static void main(String[] args) throws LoginException, IOException, NoSuchMethodException {
        new Thread(new com.zduniusz.threads.UpdateCovid()).start();
        new Thread(new com.zduniusz.threads.UpdateLuckyNumber()).start();
        new Thread(new com.zduniusz.threads.UpdateMonitors()).start();


        dailyCovidStat = Download.formatData(Download.downloadData());
        luckyNumberList = new GetData().getLuckyNumbersFromResources();
        monitorsList = new GetMonitors().getMonitorsFromResources();

        Setup.setup();

        CommandManager.synchronizeCommands();

        Setup.registerCommandListeners();
    }
}
