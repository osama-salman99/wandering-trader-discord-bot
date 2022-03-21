package osmosis.wandering.trader.discord.bot.requests;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import osmosis.appliers.message.MessageApplier;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MappedRequestProcessorBuilder {
    public final Builder builder() {
        return new Builder();
    }

    public interface MapStep {
        MapStep putRequestEntry(String string, MessageApplier applier);

        BuildStep setInvalidRequestApplier(MessageApplier applier);
    }

    public interface BuildStep {
        MappedRequestProcessor build();
    }

    public static class Builder extends MappedRequestProcessorBuilder implements MapStep, BuildStep {
        private final Map<String, MessageApplier> map;
        private MessageApplier applier;

        public Builder() {
            map = new HashMap<>();
        }

        @Override
        public MapStep putRequestEntry(String string, MessageApplier applier) {
            this.map.put(string, applier);
            return this;
        }

        @Override
        public BuildStep setInvalidRequestApplier(MessageApplier applier) {
            this.applier = applier;
            return this;
        }

        @Override
        public MappedRequestProcessor build() {
            return new MappedRequestProcessor(map, applier);
        }
    }

}
