package com.originalalex.github.counting;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Counting {

    private static int currentCount = 0;
    private static String lastUser = "";

    public static void count(MessageReceivedEvent e) {
        String message = e.getMessage().getContentStripped();
        int number = 0;
        if (e.getAuthor().getId().equals(lastUser)) {
            e.getChannel().sendMessage("You can't count two numbers in a row!").queue();
            return;
        }
        try {
            number = Integer.parseInt(message);
        } catch (NumberFormatException ex) {
            return;
        }
        if (number == currentCount+1) {
            e.getMessage().addReaction("U+2705").queue();
            lastUser = e.getAuthor().getId();
            currentCount++;
        } else {
            e.getMessage().addReaction("U+274C").queue();
            e.getChannel().sendMessage(e.getAuthor().getAsMention() + " just broke the streak at " + currentCount+ "! Start again at 1").queue();
            currentCount=0;
        }
    }

}
