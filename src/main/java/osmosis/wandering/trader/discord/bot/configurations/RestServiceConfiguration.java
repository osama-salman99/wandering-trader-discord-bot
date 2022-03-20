package osmosis.wandering.trader.discord.bot.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import osmosis.wandering.trader.discord.bot.services.PostingService;
import osmosis.wandering.trader.discord.bot.services.RestService;

import java.time.Duration;

@Configuration
public class RestServiceConfiguration {
    @Bean
    public PostingService commandsPostingService(RestTemplate restTemplate, @Value("${minecraft.server.command.url}") String url) {
        return new RestService(restTemplate, url);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(100))
                .build();
    }
}
