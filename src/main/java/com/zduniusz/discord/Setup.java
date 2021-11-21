package com.zduniusz.discord;

import com.google.gson.Gson;
import com.zduniusz.Main;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.Objects;

public class Setup {

    private static Auth auth;

    public static void setup() throws LoginException {
        auth = getAuth();
        Main.jda = JDABuilder.createLight((auth.TESTING ? auth.TOKEN_TESTING : auth.TOKEN), Collections.emptyList())
                .setActivity(Activity.listening(" discopolo😞"))
                .build();
    }

    public static void registerCommandListeners(){
        Main.jda.getPresence().setActivity(Activity.listening(" jazzu😎"));
        Main.jda.addEventListener(new CommandHandler());

        auth = null; //Free my precious ram
        System.gc();
    }

    static Auth getAuth() {
        Reader reader = new InputStreamReader(Objects.requireNonNull(Setup.class
                .getResourceAsStream("/Auth.json")));
        return new Gson().fromJson(reader, Auth.class);
    }

    static class Auth {
        String TOKEN;
        String TOKEN_TESTING;
        Boolean TESTING;
    }
}
