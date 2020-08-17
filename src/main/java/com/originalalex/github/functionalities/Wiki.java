package com.originalalex.github.functionalities;

import com.originalalex.github.scrape.Scrape;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class Wiki implements Function {

    private String query;
    private Scrape scrape;

    public Wiki() {
        this.scrape = new Scrape();
    }

    @Override
    public void handle(MessageReceivedEvent e, String[] parts) {
        query="";
        for (int i = 1; i <parts.length; i++) {
            this.query+=parts[i] + " ";
        }
        query = query.strip();
        query = query.replaceAll(" ","_");
        char first = query.charAt(0);
        char capital = Character.toUpperCase(first);
        query = capital + query.substring(1);
        String firstParagraph = scrape.getFirstWikiParagraph(query);
        MessageEmbed embed;
        if (firstParagraph == null) {
            embed = new EmbedBuilder()
                    .setColor(Color.CYAN)
                    .setTitle("__No article found regarding the topic " + query + ".__")
                    .build();

        } else if (firstParagraph.contains("perhaps you meant one of the following")) { // a list
            embed = new EmbedBuilder()
                    .setColor(Color.CYAN)
                    .setTitle("**" + query + "**")
                    .setDescription("```css\n" +
                            firstParagraph + "\n" +
                            "```")
                    .build();
        }
        else {
            embed = new EmbedBuilder()
                    .setColor(Color.CYAN)
                    .setTitle("**" + query + "**") // bolden the title
                    .setDescription(firstParagraph)
                    .build();
        }
        e.getChannel().sendMessage(embed).queue();
    }

}
