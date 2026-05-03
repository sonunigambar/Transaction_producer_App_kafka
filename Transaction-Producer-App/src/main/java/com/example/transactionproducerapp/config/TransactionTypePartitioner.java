package com.example.transactionproducerapp.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class TransactionTypePartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object keyObject, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        int partition = cluster.partitionCountForTopic(topic);
        String key = String.valueOf(keyObject);
        switch (key){
            case "UPI":
                return 1 % partition; // 1
            case "CREDIT_CARD":
                return 2 % partition; //2
            case "DEBIT_CARD":
                return 3 % partition; //0
            default:
                return 0;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
