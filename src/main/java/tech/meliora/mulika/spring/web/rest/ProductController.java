package tech.meliora.mulika.spring.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.meliora.mulika.spring.web.rest.dto.Answer;
import tech.meliora.mulika.spring.web.rest.dto.Request;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/product")
    public ResponseEntity<Answer> product(@RequestBody Request request) throws Exception {

        log.info("REST request to find product : {}", request);

        Answer answer = new Answer( request.getA() * request.getB());

        log.info("REST answer is : {}", answer);

        return ResponseEntity.ok()
                .body(answer);
    }

}
