package com.dmkaw.kafka.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component

public class MainTransactionInvoker {
    @Autowired
    private KafkaSender kafkaSender;

    @Transactional
    public void execute() {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                kafkaSender.execute();
            }
        });
    }

}
