package com.originalalex.github.functionalities;


import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Function {

    public void handle(MessageReceivedEvent e, String[] parts);

}
