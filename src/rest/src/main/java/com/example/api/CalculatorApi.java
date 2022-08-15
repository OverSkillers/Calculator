package com.example.api;

import com.example.model.Operands;
import com.example.rabbitmq.RabbitMqSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
public class CalculatorApi {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorApi.class);

    private final static String SUM_OPERATION = "sum";
    private final static String DIV_OPERATION = "div";
    private final static String MULT_OPERATION = "mult";
    private final static String SUB_OPERATION = "sub";

    private final RabbitMqSender rabbitMqSender;

    @Autowired
    public CalculatorApi(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @GetMapping("sum")
    public String sum(@RequestParam int a,
                      @RequestParam int b) {
        logger.info("Received message: {} + {}", a, b);

        String receivedValue = (String) rabbitMqSender.send(new Operands(a, b, SUM_OPERATION));
        logger.info("Received calculated value: {}", receivedValue);

        return receivedValue;
    }

    @GetMapping("sub")
    public String sub(@RequestParam int a,
                      @RequestParam int b) {
        logger.info("Received message: {} + {}", a, b);

        String receivedValue = (String) rabbitMqSender.send(new Operands(a, b, SUB_OPERATION));
        logger.info("Received calculated value: {}", receivedValue);

        return receivedValue;
    }

    @GetMapping("mult")
    public String mult(@RequestParam int a,
                      @RequestParam int b) {
        logger.info("Received message: {} + {}", a, b);

        String receivedValue = (String) rabbitMqSender.send(new Operands(a, b, MULT_OPERATION));
        logger.info("Received calculated value: {}", receivedValue);

        return receivedValue;
    }

    @GetMapping("div")
    public String div(@RequestParam int a,
                      @RequestParam int b) {
        logger.info("Received message: {} + {}", a, b);

        String receivedValue = (String) rabbitMqSender.send(new Operands(a, b, DIV_OPERATION));
        logger.info("Received calculated value: {}", receivedValue);

        return receivedValue;
    }
}
