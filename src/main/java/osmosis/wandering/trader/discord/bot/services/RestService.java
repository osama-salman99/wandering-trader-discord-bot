package osmosis.wandering.trader.discord.bot.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@AllArgsConstructor
public class RestService implements PostingService {
    private final RestTemplate restTemplate;
    private final String url;


    @Override
    public String postMessage(String message) {
        if (Objects.isNull(message) || message.isBlank()) {
            return null;
        }
        HttpEntity<String> request = new HttpEntity<>(message);
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
            return responseEntity.getBody();
        } catch (Exception exception) {
            String exceptionMessage = exception.getMessage();
            logError(exceptionMessage);
            return exceptionMessage;
        }
    }

    private void logError(String message) {
        System.err.println(message);
    }
}
