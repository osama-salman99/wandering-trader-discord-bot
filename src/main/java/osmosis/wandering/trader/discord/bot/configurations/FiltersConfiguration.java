package osmosis.wandering.trader.discord.bot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import osmosis.filters.message.MessageFilter;
import osmosis.filters.message.bot.BotFilter;
import osmosis.filters.message.regex.ContainsFilter;

@Configuration
public class FiltersConfiguration {
    @Bean
    public MessageFilter niceFilter() {
        return new ContainsFilter("69");
    }

    @Bean
    public BotFilter botFilter() {
        return new BotFilter();
    }
}
