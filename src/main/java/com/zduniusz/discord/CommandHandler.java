package com.zduniusz.discord;

import com.zduniusz.Main;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import com.zduniusz.discord.commandlist.*;

public class CommandHandler extends ListenerAdapter {
    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        switch (event.getCommandId()){
            case "909793669673005076":
                Infections.reply(event);
                break;
        }
    }

}
