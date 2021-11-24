package com.zduniusz;

import com.zduniusz.data.covid.DailyCovidStat;
import com.zduniusz.data.covid.Download;
import com.zduniusz.data.luckynumber.LuckyNumber;
import com.zduniusz.data.monitors.Monitor;
import com.zduniusz.discord.CommandManager;
import com.zduniusz.discord.Setup;
import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static JDA jda;

    public static DailyCovidStat dailyCovidStat;
    public static List<DailyCovidStat> weeklyCovidStat;
    public static List<LuckyNumber> luckyNumberList;
    public static List<Monitor> monitorsList;

    public static void main(String[] args) throws LoginException, IOException, NoSuchMethodException {
        new Thread(new com.zduniusz.threads.UpdateCovid()).start();
        new Thread(new com.zduniusz.threads.UpdateLists()).start();

        dailyCovidStat = Download.formatDataToday(Download.downloadDataToday());
        new Thread(() -> {
            try {
                weeklyCovidStat = Download.formatDataWeek(Download.downloadDataWeek());
            } catch (IOException ignored) {
            }
        }).start();

        Setup.setup();

        CommandManager.synchronizeCommands();

        Setup.registerCommandListeners();
    }
}
