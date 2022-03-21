package osmosis.wandering.trader.discord.bot.requests;

import osmosis.commons.message.ProcessingMessage;

public interface RequestProcessor {
    void process(ProcessingMessage request);
}
