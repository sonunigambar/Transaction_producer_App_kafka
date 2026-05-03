package com.example.transactionproducerapp.controller;

import com.example.transactionproducerapp.entity.Tranasction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransactionController(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/transaction")
    public String createOrder(@RequestBody Tranasction tranasction) throws JsonProcessingException {
        //Read Cart details and payment info
        //write business logic for doing transaction
        //if transcation succesfull, I will convert this object into Json then I will
        //produce this json into kafka topic
        final String topicName= "transaction-producer-topic";
        String transactionLog = objectMapper.writeValueAsString(tranasction);
        kafkaTemplate.send(topicName, tranasction.getTransactionType(), transactionLog);
        return "Transaction Log successfully sent to topic: " + topicName;

    }

}
