package com.example.transactionproducerapp.config;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "com.example.transactionproducerapp:type=TransactionProducerAppApplication",
        description = "MBean for monitoring transactions")
public class TransactionMonitor {

    private int transactionCount = 0;

    @ManagedAttribute(description = "Current transaction count")
    public int getTransactionCount() {
        return transactionCount;
    }

    @ManagedOperation(description = "Increment transaction count")
    public void incrementTransactionCount() {
        transactionCount++;
    }

    @ManagedOperation(description = "Reset transaction count")
    public void resetTransactionCount() {
        transactionCount = 0;
    }
}