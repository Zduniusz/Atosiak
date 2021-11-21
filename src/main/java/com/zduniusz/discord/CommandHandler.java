package com.zduniusz.discord;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public class CommandHandler extends ListenerAdapter {
    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        Optional<CommandManager.Command> optionalCommand = Arrays.stream(CommandManager.commands).filter(x -> x.getName().equals(event.getName())).findFirst();

        if (optionalCommand.isEmpty())
            return;

        CommandManager.Command command = optionalCommand.get();

        if(command.getHandler() == null)
            return;

        try {
            command.getHandler().invoke(null, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //Nie wiem, java.lang.reflect raczej nie wymyśli żadnego dziwnego błędu, prawda?
        }
    }
}
