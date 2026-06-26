package org.local.abhi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(
        scanBasePackages = {
                "org.local.abhi.controller"
        }
)
public class AnalyseAndAskApp {
    public static void main(String[] args) {
        SpringApplication.run(AnalyseAndAskApp.class);
    }
}
