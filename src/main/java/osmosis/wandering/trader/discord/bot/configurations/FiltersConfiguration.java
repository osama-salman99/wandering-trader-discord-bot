package osmosis.wandering.trader.discord.bot.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import osmosis.filters.message.MessageFilter;
import osmosis.filters.message.bot.BotFilter;
import osmosis.filters.message.chained.ChainedMessageFilterBuilder;
import osmosis.filters.message.regex.ContainsFilter;
import osmosis.filters.message.regex.PrefixFilter;
import osmosis.filters.message.role.RoleFilter;

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

    @Bean
    public MessageFilter commandsFilter(MessageFilter commandRoleFilter, MessageFilter commandPrefixFilter) {
        return new ChainedMessageFilterBuilder()
                .addFilter(commandRoleFilter)
                .addFilter(commandPrefixFilter)
                .build();
    }

    @Bean
    public MessageFilter commandRoleFilter(@Value("${command.role}") String commandRole) {
        return new RoleFilter(commandRole);
    }

    @Bean
    public MessageFilter commandPrefixFilter(@Value("${command.prefix}") String commandPrefix) {
        return new PrefixFilter(commandPrefix);
    }
}
