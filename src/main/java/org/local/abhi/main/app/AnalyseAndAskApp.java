package org.local.abhi.main.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(
        scanBasePackages = {
                "org.local.abhi.main.ingestion"
        }
)
public class AnalyseAndAskApp {
    public static void main(String[] args) {
        SpringApplication.run(AnalyseAndAskApp.class);
    }
}
