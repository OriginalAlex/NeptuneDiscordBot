package com.originalalex.github.helper;


import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UserID {

    public boolean isValidUserID(MessageReceivedEvent e, String id) {
        return getMember(e, id) != null;
    }

    public static Member getMember(MessageReceivedEvent e, String id) {
        return e.getGuild().getMemberById(id);
    }

}
