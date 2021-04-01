package com.alimmit.hellorabbit.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class WorkerApp implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WorkerApp.class);

    @Override
    public void run(final String... args) {
        logger.info("WorkerApp Starting {}", Arrays.toString(args));
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(10);
            }
            catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(final String... args) {
        SpringApplication.run(WorkerApp.class, args);
    }
}
