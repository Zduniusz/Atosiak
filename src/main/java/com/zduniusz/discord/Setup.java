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
    public Setup() throws LoginException {
        Auth auth = getAuth();
        Main.jda = JDABuilder.createLight((auth.TESTING ? auth.TOKEN_TESTING : auth.TOKEN), Collections.emptyList())
                .addEventListeners(new CommandHandler())
                .setActivity(Activity.listening(" jazzu😎"))
                .build();
    }

    Auth getAuth() {
        Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
                .getResourceAsStream("/Auth.json")));
        return new Gson().fromJson(reader, Auth.class);
    }

    static class Auth {
        String TOKEN;
        String TOKEN_TESTING;
        Boolean TESTING;
    }
}
