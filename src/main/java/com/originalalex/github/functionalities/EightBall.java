package com.originalalex.github.functionalities;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EightBall implements Function {

    public static List<String> response = new ArrayList<>();

    public EightBall() {
        response.add("It is certain.");
        response.add("It is decidedly so.");
        response.add("Without a doubt.");
        response.add("Yes - definitely.");
        response.add("You may rely on it.");
        response.add("As I see it, yes.");
        response.add("Most likely.");
        response.add("Outlook good.");
        response.add("Yes.");
        response.add("Signs point to yes.");
        response.add("Reply hazy, try again.");
        response.add("Ask again later.");
        response.add("Better not tell you now.");
        response.add("Cannot predict now.");
        response.add("Concentrate and ask again.");
        response.add("Don't count on it.");
        response.add("My reply is no.");
        response.add("My sources say no.");
        response.add("Outlook not so good.");
        response.add("Very doubtful.");

    }

    public void handle(MessageReceivedEvent e, String[] parts) {
        int randInt = ThreadLocalRandom.current().nextInt(0, response.size());
        e.getChannel().sendMessage(response.get(randInt)).queue();
    }

}
