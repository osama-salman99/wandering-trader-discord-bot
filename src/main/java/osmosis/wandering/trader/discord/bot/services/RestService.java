package osmosis.wandering.trader.discord.bot.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return String.format("Response code %d: Error processing the command", responseEntity.getStatusCode().value());
    }
}
