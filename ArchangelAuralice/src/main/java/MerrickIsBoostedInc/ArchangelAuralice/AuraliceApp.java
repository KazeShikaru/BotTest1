package MerrickIsBoostedInc.ArchangelAuralice;

import java.awt.Color;
import java.util.List;
import java.io.*;
import java.lang.Object;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.RestAction;


/**
 * Hello world!
 *
 */
public class AuraliceApp extends ListenerAdapter {
	static JDA Auralice;
	
	EmbedBuilder error = new EmbedBuilder().setColor(Color.GREEN).setTitle("Salutations!")
			.setFooter(" proudly presented by MerrickIsBoostedInc",
					"https://s-media-cache-ak0.pinimg.com/originals/32/25/08/3225081a7fc7946655c1c6a2dae566cd.jpg")
			.setAuthor("Exemple Author", "http://steamcommunity.com/profiles/76561198047933690/",
					"http://steamcommunity-a.akamaihd.net/public/images/badges/02_years/steamyears502_54.png");

	public static void main(String[] args)
			throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {
		System.out.println("hi");
		Auralice = new JDABuilder(AccountType.BOT)
				.setToken("MzQ3NDA1MTk0NTQxNDAwMDY0.DHYD3A.x7Ao5rFpklw3-Zj04QzrQaOIHXo").buildBlocking();
		Auralice.addEventListener(new AuraliceApp());
	}

	public void onMessageReceived(MessageReceivedEvent e) {
		if (!e.isFromType(ChannelType.TEXT)) {
			return;
		}

		Message objMsg = e.getMessage();
		MessageChannel objChannel = e.getChannel();
		User objUser = e.getAuthor();
		String message = "";

		if (objUser.getName() != Auralice.getSelfUser().getName() && objMsg.getContent().length() > 2
				&& objMsg.getContent().substring(0, 2).equalsIgnoreCase("^^")) {

			// if (objUser.getName().equals("Kaze Shikaru#7424")) {
			
			// }

			// RestAction<MessageHistory> toPurge =
			// objChannel.getHistoryAround(objMsg.getId(), 3);
			// for(MessageHistory hi: toPurge) {

			// }
			// objChannel.deleteMessageById("");
			if (objMsg.getContent().substring(2, 7).toLowerCase().equals("purge")) {
				try {
					MessageHistory histoire = new MessageHistory(objChannel);
					List<Message> msn;
					objMsg.delete().queue();
					msn = histoire.retrievePast(5).complete();
					e.getTextChannel().deleteMessages(msn).queue();
					objChannel.sendMessage(error.setDescription("Last 5 Messages have been deleted.").setColor(Color.BLUE).build()).queue();
				}catch(Exception eat) {
					eat.printStackTrace();
				}
			}else if(objMsg.getContent().substring(2, 7).toLowerCase().equals("wooop")) {
				File image = new File("Ress//woopwoop.gif");
				//File image = new File("Ress//woopwoop.gif");
				objChannel.sendFile(image, null).queue();
			}else {
				objChannel.sendMessage(error.setDescription("Sup ma dude, watcha need from me?").build()).queue();
			}

		}
	}
}
