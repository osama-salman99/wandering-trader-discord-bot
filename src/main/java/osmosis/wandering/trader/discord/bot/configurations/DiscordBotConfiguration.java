package osmosis.wandering.trader.discord.bot.configurations;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import osmosis.commons.message.ProcessingMessage;
import osmosis.pipes.messages.terminating.chain.TerminablePipeChain;

@Configuration
public class DiscordBotConfiguration {
    @Bean
    public MessageCreateListener messageCreateListener(TerminablePipeChain terminablePipeChain) {
        return messageCreateEvent -> terminablePipeChain.process(new ProcessingMessage(messageCreateEvent.getMessage()));
    }

    @Bean
    public DiscordApi discordApi(@Value("${discord.api.token}") String token, MessageCreateListener messageCreateListener) {
        return new DiscordApiBuilder()
                .addMessageCreateListener(messageCreateListener)
                .setToken(token)
                .login()
                .join();
    }
}
