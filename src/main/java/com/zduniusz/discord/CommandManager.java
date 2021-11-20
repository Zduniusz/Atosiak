package com.zduniusz.discord;

import com.zduniusz.Main;
import com.zduniusz.discord.commandlist.Infections;
import com.zduniusz.discord.commandlist.LuckyNumber;
import com.zduniusz.discord.commandlist.Monitors;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandManager {

    public static Command[] commands;

    private static void loadCommands() throws NoSuchMethodException {
        commands = new Command[] {
                new Command("zakażenia", "Wyświetl liczbę zakażeń z dzisiaj", Infections.class),
                new Command("szczęśliwy", "Wyświetl jakie numerki mają dzisiaj szczęśliwy numerek", LuckyNumber.class),
                new Command("dyżurni","Wyświetl kto aktualnie jest dyżurnym", Monitors.class)
        };
    }

    public static void synchronizeCommands() throws NoSuchMethodException {

        loadCommands();

        List<net.dv8tion.jda.api.interactions.commands.Command> commandsFromDiscord = Main.jda.retrieveCommands().complete();

        for (net.dv8tion.jda.api.interactions.commands.Command commandFromDiscord : commandsFromDiscord) {
              Optional<Command> optionalCommandFromList = Arrays.stream(commands).filter(command -> command.getName().equals(commandFromDiscord.getName())).findFirst();

              if(optionalCommandFromList.isEmpty()) {
                  commandFromDiscord.delete().queue();
                  continue;
              }

              Command commandFromList = optionalCommandFromList.get();

              if(!commandFromList.getDescription().equals(commandFromDiscord.getDescription()))
                  commandFromDiscord.editCommand().setDescription(commandFromList.getDescription()).queue();
        }

        commandsFromDiscord = Main.jda.retrieveCommands().complete();

        for (Command commandFromList : commands) {
            Optional<net.dv8tion.jda.api.interactions.commands.Command> optionalCommandFromDiscord = commandsFromDiscord.stream().filter(command -> command.getName().equals(commandFromList.getName())).findFirst();

            if(optionalCommandFromDiscord.isEmpty()){
                Main.jda.upsertCommand(commandFromList.getName(), commandFromList.getDescription()).queue();
                continue;
            }

            net.dv8tion.jda.api.interactions.commands.Command commandFromDiscord = optionalCommandFromDiscord.get();

            if (!commandFromDiscord.getDescription().equals(commandFromList.getDescription()))
                commandFromDiscord.editCommand().setDescription(commandFromList.getDescription()).queue();

        }
    }

    protected static class Command{
        Command(String name, String description, Class<?> handler) throws NoSuchMethodException {
            mName = name;
            mDescription = description;
            if(handler != null)
                mHandler = handler.getDeclaredMethod("reply", SlashCommandEvent.class);
            else
                mHandler = null;
        }

        private final String mName;
        private final String mDescription;
        private final Method mHandler;

        public String getName() {
            return mName;
        }

        public String getDescription() {
            return mDescription;
        }

        public Method getHandler() {
            return mHandler;
        }
    }
}
