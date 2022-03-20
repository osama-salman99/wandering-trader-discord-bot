package osmosis.wandering.trader.discord.bot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import osmosis.appliers.message.MessageApplier;
import osmosis.filters.message.MessageFilter;
import osmosis.filters.message.bot.BotFilter;
import osmosis.pipes.messages.FilteredMessagePipeBuilder;
import osmosis.pipes.messages.terminating.NonTerminatingPipe;
import osmosis.pipes.messages.terminating.TerminablePipe;
import osmosis.pipes.messages.terminating.TerminatingPipe;
import osmosis.pipes.messages.terminating.chain.TerminablePipeChain;
import osmosis.pipes.messages.terminating.chain.TerminablePipeChainBuilder;

@Configuration
public class PipesConfiguration {
    @Bean
    public TerminablePipeChain terminablePipeChain(TerminablePipe botPipe,
                                                   TerminablePipe nicePipe) {
        return new TerminablePipeChainBuilder()
                .addPipe(botPipe)
                .addPipe(nicePipe)
                .build();
    }

    @Bean
    public TerminablePipe botPipe(BotFilter botFilter, MessageApplier emptyApplier) {
        return new TerminatingPipe(
                FilteredMessagePipeBuilder.builder()
                        .addFilter(botFilter)
                        .setApplier(emptyApplier)
                        .build()
        );
    }

    @Bean
    public TerminablePipe nicePipe(MessageFilter niceFilter, MessageApplier niceApplier) {
        return new NonTerminatingPipe(
                FilteredMessagePipeBuilder.builder()
                        .addFilter(niceFilter)
                        .setApplier(niceApplier)
                        .build()
        );
    }

    @Bean
    public TerminablePipe commandsPipe(MessageFilter commandsFilter, MessageApplier commandsApplier) {
        return new TerminatingPipe(
                FilteredMessagePipeBuilder.builder()
                        .addFilter(commandsFilter)
                        .setApplier(commandsApplier)
                        .build()
        );
    }
}
