package com.originalalex.github.functionalities;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RandomUser implements Function {

    private JDA jda;

    public RandomUser(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void handle(MessageReceivedEvent e, String[] parts) {

    }

}
