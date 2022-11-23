package tech.meliora.mulika.spring.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.meliora.mulika.spring.service.ProductService;
import tech.meliora.mulika.spring.web.rest.dto.Answer;
import tech.meliora.mulika.spring.web.rest.dto.Request;

@RestController
@RequestMapping("/api")
public class SumController {

    private final Logger log = LoggerFactory.getLogger(SumController.class);

    private final String productEndpoint = "http://51.15.211.168/api/sum.php";

    @Autowired
    ProductService productService;

    @PostMapping("/sum")
    public ResponseEntity<Answer> sum(@RequestBody Request request) throws Exception {

        log.info("REST request to find product : {}", request);

        Answer answer = productService.product(request, productEndpoint, "hello");//new Answer( request.getA() * request.getB());

        log.info("REST answer is : {}", answer);

        return ResponseEntity.ok()
                .body(answer);
    }

}
