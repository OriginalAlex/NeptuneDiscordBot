package com.originalalex.github;

import com.originalalex.github.listener.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {

    public static void main(String[] args) {
        try {
            JDA jda = JDABuilder
                    .createDefault("MzUyNTczODI4OTc2NzM4MzA2.Wac19w.Sg0xKk5pX3A_B2-5kCl8e4htNL4")
                    .build();
            jda.addEventListener(new MessageListener(jda));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
