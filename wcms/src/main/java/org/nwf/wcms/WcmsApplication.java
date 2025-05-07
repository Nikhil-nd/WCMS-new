package org.nwf.wcms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class WcmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcmsApplication.class, args);
    }

}
