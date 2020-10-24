package com.example.fangchan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FangchanApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void money() {
        Double i = 300.07;
        Integer j = 14;
        Double k = i / j;
        System.err.println(k);
    }

    @Test
    void moneyOne() {
        Integer i = 5000;
        Integer j = 5000;
        Double d = j / (i * 1.0);
        System.err.println(d);
    }

}
