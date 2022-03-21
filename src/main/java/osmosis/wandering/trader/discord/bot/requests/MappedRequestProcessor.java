package osmosis.wandering.trader.discord.bot.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import osmosis.appliers.message.MessageApplier;
import osmosis.commons.message.ProcessingMessage;

import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MappedRequestProcessor implements RequestProcessor {
    private final Map<String, MessageApplier> map;
    private final MessageApplier invalidRequestApplier;

    @Override
    public void process(ProcessingMessage request) {
        map.getOrDefault(request.getProcessedMessage(), invalidRequestApplier)
                .apply(request);
    }
}
