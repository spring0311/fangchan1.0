package com.example.fangchan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

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
        Long uuid = Long.valueOf(UUID.randomUUID().toString());
        System.err.println(uuid);
    }

}
