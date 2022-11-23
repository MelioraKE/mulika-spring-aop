package tech.meliora.mulika.spring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.meliora.mulika.spring.monitoring.util.HTTPClient;
import tech.meliora.mulika.spring.monitoring.util.HTTPResponse;
import tech.meliora.mulika.spring.web.rest.ProductController;
import tech.meliora.mulika.spring.web.rest.dto.Answer;
import tech.meliora.mulika.spring.web.rest.dto.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Answer product(Request request, String url, String test) {

        log.info(
            "sending request to {}, body {} ",
            url,
            request
        );

        try {
            String jsonRequest = objectMapper.writeValueAsString(request);

            HTTPResponse response = HTTPClient.send(url, jsonRequest, "POST",
                    "application/json", new HashMap<>(), 5000, 120000);


            log.info("response {} "+response);


            if(response.getResponseCode() >= 200 && response.getResponseCode() <=299){

                Answer ans = objectMapper.readValue(response.getBody(), Answer.class);
                return ans;
            } else {
                throw new Exception("http error "+response.getResponseCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
