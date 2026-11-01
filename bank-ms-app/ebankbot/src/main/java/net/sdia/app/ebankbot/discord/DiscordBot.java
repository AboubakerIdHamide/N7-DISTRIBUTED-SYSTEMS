package net.sdia.app.ebankbot.discord;

import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.sdia.app.ebankbot.agents.EbankAIAgent;
import org.springframework.stereotype.Component;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Component
@DiscordController
public class DiscordBot {
    private final EbankAIAgent ebankAIAgent;

    public DiscordBot(EbankAIAgent ebankAIAgent) {
        this.ebankAIAgent = ebankAIAgent;
    }

    @DiscordMapping
    private void perform(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        String query = event.getMessage().getContentRaw();
        String response = ebankAIAgent.chat(query);
        event.getChannel().sendMessage(response).queue();
    }
}
