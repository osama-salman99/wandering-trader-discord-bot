package osmosis.wandering.trader.discord.bot.processors;

import lombok.AllArgsConstructor;
import osmosis.commons.message.ProcessingMessage;
import osmosis.wandering.trader.discord.bot.services.PostingService;

import java.util.function.Function;

@AllArgsConstructor
public class CommandsProcessor implements Function<ProcessingMessage, String> {
    private final PostingService postingService;

    @Override
    public String apply(ProcessingMessage processingMessage) {
        return postingService.postMessage(processingMessage.getProcessedMessage());
    }
}
