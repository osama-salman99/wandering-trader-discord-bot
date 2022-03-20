package osmosis.wandering.trader.discord.bot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import osmosis.appliers.message.MessageApplier;
import osmosis.appliers.message.sending.SuppliedSendBackApplier;

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
}
