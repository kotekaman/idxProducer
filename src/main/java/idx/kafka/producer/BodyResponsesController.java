package idx.kafka.producer;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Configuration
@Controller
class BodyResponsesController {

    private final BodyResponsesInterface repository;
    private final IdxKafkaProducer idxKafkaProducer;


    BodyResponsesController(BodyResponsesInterface repository, IdxKafkaProducer idxKafkaProducer) {
        this.repository = repository;
        this.idxKafkaProducer = idxKafkaProducer;
    }


    @PostMapping("/v1/idx/produces")
    BodyResponses newItem(@RequestBody BodyResponses data) throws IOException {
        idxKafkaProducer.send(data.getName(), data.getIdxGroup(), data.getTopic(), data.getIdxTotal(), data.getIdxNumber(),data.getPath(),data.getFilename(),data.getIdx_method());
        return data;
    }

}