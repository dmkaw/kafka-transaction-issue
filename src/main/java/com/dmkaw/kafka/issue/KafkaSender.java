package com.dmkaw.kafka.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.MANDATORY)
public class KafkaSender {

    @Autowired
    private KafkaTemplate<Object, Object> template;

    public void execute() {
        template.send("test", "test_msg");
        template.send("test", "test_msg1");
        template.send("test", "test_msg2");
        template.send("test", "test_msg3");
    }

}
