package com.dmkaw.kafka.issue;

import org.hibernate.SessionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class KafkaTransactionsIssueApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTransactionsIssueApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(MainTransactionInvoker transactionInvoker) {
        return args -> {
            transactionInvoker.execute();
            transactionInvoker.execute();
        };
    }


    @Primary
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}