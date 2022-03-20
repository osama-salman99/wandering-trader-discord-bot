package osmosis.wandering.trader.discord.bot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import osmosis.appliers.message.MessageApplier;
import osmosis.appliers.message.sending.SendBackApplier;
import osmosis.appliers.message.sending.SuppliedSendBackApplier;
import osmosis.wandering.trader.discord.bot.processors.CommandsProcessor;
import osmosis.wandering.trader.discord.bot.services.PostingService;

@Configuration
public class AppliersConfiguration {
    @Bean
    public MessageApplier niceApplier() {
        return new SuppliedSendBackApplier(() -> "nice");
    }

    @Bean
    public MessageApplier emptyApplier() {
        return processingMessage -> {

        };
    }

    @Bean
    public MessageApplier commandsApplier(CommandsProcessor commandsProcessor) {
        return new SendBackApplier(commandsProcessor);
    }

    @Bean
    public CommandsProcessor commandsProcessor(PostingService commandsPostingService) {
        return new CommandsProcessor(commandsPostingService);
    }
}
