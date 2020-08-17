package com.originalalex.github.listener;

import com.originalalex.github.counting.Counting;
import com.originalalex.github.functionalities.*;
import com.originalalex.github.ranking.ModifyReputation;
import com.originalalex.github.ranking.SimpleDisplay;
import com.originalalex.github.ranking.WipeDatabase;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

	// Functions:
	private Help helpFunction; // Send the user a list of commands they can use.
	private Wiki wikiFunction; // Get the first paragraph from wikipedia on a
								// certain topic
	private Nyan nyan; // Paste a nyan cat
	private HorizontalLine hl; // Paste a horizontal line
	private BeautifulPic bp; // Scrape the top image from /r/EarthPorn for the
								// past 24 hours
	private Crypto crypto; // Grabbing information about the market cap, price,
							// and volume of a cryptocurrency
	private ModifyReputation modifyReputation; // The class to do with changing
												// reputation
	private SimpleDisplay simpleDisplay; // Showing reputation
	private WipeDatabase wipeDatabase; // Clear the reputation database
	private Talk talk; // A smart bot that can hold a conversation [implemented
						// using the Cleverbot API]
	private EightBall eightBall;

	public MessageListener(JDA jda) {
		this.helpFunction = new Help();
		this.hl = new HorizontalLine();
		this.wikiFunction = new Wiki();
		this.nyan = new Nyan();
		this.bp = new BeautifulPic();
		this.crypto = new Crypto();
		this.modifyReputation = new ModifyReputation();
		this.simpleDisplay = new SimpleDisplay(modifyReputation.getLevel());
		this.wipeDatabase = new WipeDatabase(modifyReputation.getWipeDatabaseUsers());
		this.talk = new Talk();
		this.eightBall = new EightBall();
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if (e.getChannel().getName().equals("counting")) {
			Counting.count(e);
		}
		String[] parts = e.getMessage().getContentStripped().split(" ");
		if (parts[0].equalsIgnoreCase("neptune.talk") || parts[0].equalsIgnoreCase("neptune.cleverbot")
				|| parts[0].equalsIgnoreCase("neptune.chat")) {
			talk.handle(e,parts);
		}
		if (parts[0].equals("neptune.wiki")) {
			wikiFunction.handle(e,parts);
		}
		if (parts[0].equals("neptune.8ball")) {
			eightBall.handle(e,parts);
		}
		if (parts.length == 3) {
			switch (parts[0].toLowerCase()) {
			case "neptune.rep":
				parts[2] = e.getMessage().getMentionedUsers().get(0).getId(); // Set
																				// this
																				// to
																				// the
																				// ID
																				// of
																				// the
																				// target
																				// user
				modifyReputation.handle(e,parts);
				break;
			case "neptune.setrep":
				modifyReputation.setRep(e);
			}
		} else if (parts.length == 2) {
			switch (parts[0].toLowerCase()) {
			case "neptune.rep":
				simpleDisplay.handle(e,parts);
				break;
			case "neptune.crypto":
				crypto.setTicker(parts[1]);
				crypto.handle(e,parts);
			}
		} else if (parts.length == 1) {
			switch (parts[0].toLowerCase()) {
			case "neptune.help":
				helpFunction.handle(e,parts);
				break;
			case "neptune.wipe":
			case "neptune.dbclear":
				wipeDatabase.handle(e,parts);
				break;
			case "neptune.cat":
			case "neptune.nyan":
				nyan.handle(e,parts);
				break;
			case "neptune.horizontalline":
			case "neptune.hl":
				hl.handle(e,parts);
				break;
			case "neptune.pic":
			case "neptune.beautifulpic":
				bp.handle(e,parts);
			}

		}
	}

}
